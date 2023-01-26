package com.vehicle.framework.manager.factory;

import com.vehicle.common.utils.spring.SpringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.service.IVhVehicleInfoService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class ForkTask extends RecursiveTask<ConcurrentHashMap> {

    //单个任务处理数据
    private static final int THRESHOLD_NUM = 500;
    //下标
    private int start, end;

    private SysUser user;

    //需要处理的数据
    private List<VhVehicleInfo> list;

    public ForkTask(int start, int end, List<VhVehicleInfo> list, SysUser user) {
        this.start = start;
        this.end = end;
        this.list = list;
        this.user = user;
    }


    @Override
    protected ConcurrentHashMap compute() {


        if ((end - start) < THRESHOLD_NUM) {
            List<VhVehicleInfo> taskList = new ArrayList<>(list.subList(start, end));
            ConcurrentHashMap<String, Object> resMap = SpringUtils.getBean(IVhVehicleInfoService.class).importVhVehicleInfo(taskList, true, user);
            return resMap;
        } else {
            int middle = (start + end) / 2;
            ForkTask left = new ForkTask(start, middle, this.list, user);
            ForkTask right = new ForkTask(middle, end, this.list, user);

            left.fork();
            right.fork();

            ConcurrentHashMap<String, Object> lMap = left.join();
            ConcurrentHashMap<String, Object> rMap = right.join();

            synchronized (this) {
                Integer lSum = (Integer) lMap.get("sum");
                Integer lSuccess = (Integer) lMap.get("success");
                Integer lFail = (Integer) lMap.get("fail");
                List<ConcurrentHashMap<String, Object>> lMsg = (List<ConcurrentHashMap<String, Object>>) lMap.get("messages");

                Integer rSum = (Integer) rMap.get("sum");
                Integer rSuccess = (Integer) rMap.get("success");
                Integer rFail = (Integer) rMap.get("fail");
                List<ConcurrentHashMap<String, Object>> rMsg = (List<ConcurrentHashMap<String, Object>>) rMap.get("messages");

                rMsg.addAll(lMsg);

                rMap.put("sum", lSum + rSum);
                rMap.put("success", lSuccess + rSuccess);
                rMap.put("fail", lFail + rFail);
                rMap.put("messages", rMsg);
            }

            //何并结果
            return rMap;
        }
    }
}
