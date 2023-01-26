package com.vehicle.framework.manager.factory;

import com.vehicle.common.utils.spring.SpringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.service.IVhAutopartsStoreService;
import com.vehicle.project.vehicle.service.IVhVehicleInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class StoreImportTask extends RecursiveTask<ConcurrentHashMap> {

    //单个任务处理数据
    private static final int THRESHOLD_NUM = 500;
    //下标
    private int start, end;

    private SysUser user;

    //需要处理的数据
    private List<VhAutopartsStore> list;

    public StoreImportTask(int start, int end, List<VhAutopartsStore> list, SysUser user) {
        this.start = start;
        this.end = end;
        this.list = list;
        this.user = user;
    }


    @Override
    protected ConcurrentHashMap compute() {

        if ((end - start) < THRESHOLD_NUM) {
            List<VhAutopartsStore> taskList = new ArrayList<>(list.subList(start, end));
            SpringUtils.getBean(IVhAutopartsStoreService.class).importList(taskList);
            return null;
        } else {
            int middle = (start + end) / 2;
            StoreImportTask left = new StoreImportTask(start, middle, this.list, null);
            StoreImportTask right = new StoreImportTask(middle, end, this.list, null);

            left.fork();
            right.fork();

            //何并结果
            return null;
        }
    }
}
