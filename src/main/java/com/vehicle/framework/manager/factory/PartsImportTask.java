package com.vehicle.framework.manager.factory;

import com.vehicle.common.utils.spring.SpringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.service.IVhPartsCodeService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class PartsImportTask extends RecursiveTask<LinkedHashMap> {

    //单个任务处理数据
    private static final int THRESHOLD_NUM = 500;
    //下标
    private int start, end;

    private SysUser user;

    //需要处理的数据
    private List list;

    public PartsImportTask(int start, int end, List list, SysUser user) {
        this.start = start;
        this.end = end;
        this.list = list;
        this.user = user;
    }

    @Override
    protected LinkedHashMap compute() {


        if ((end - start) < THRESHOLD_NUM) {
            List taskList = new ArrayList<>(list.subList(start, end));
            LinkedHashMap<String, Object> resMap = SpringUtils.getBean(IVhPartsCodeService.class).importVhPartsCode(taskList, true, user);
            return resMap;
        } else {
            int middle = (start + end) / 2;
            PartsImportTask left = new PartsImportTask(start, middle, this.list, user);
            PartsImportTask right = new PartsImportTask(middle, end, this.list, user);

            left.fork();
            right.fork();

            LinkedHashMap<String, Object> lMap = left.join();
            LinkedHashMap<String, Object> rMap = right.join();

            synchronized (this) {
                Integer lSum = (Integer) lMap.get("sum");
                Integer lSuccess = (Integer) lMap.get("success");
                Integer lFail = (Integer) lMap.get("fail");

                Integer rSum = (Integer) rMap.get("sum");
                Integer rSuccess = (Integer) rMap.get("success");
                Integer rFail = (Integer) rMap.get("fail");

                rMap.put("sum", lSum + rSum);
                rMap.put("success", lSuccess + rSuccess);
                rMap.put("fail", lFail + rFail);
            }

            //何并结果
            return rMap;
        }
    }

}
