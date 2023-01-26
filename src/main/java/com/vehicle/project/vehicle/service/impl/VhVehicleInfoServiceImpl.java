package com.vehicle.project.vehicle.service.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhVehicleInfoMapper;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.service.IVhVehicleInfoService;
import org.springframework.util.CollectionUtils;

/**
 * 车辆信息Service业务层处理
 *
 * @author onion
 * @date 2020-02-03
 */
@Service

public class VhVehicleInfoServiceImpl implements IVhVehicleInfoService {

    private static final Logger log = LoggerFactory.getLogger(VhUpkeepCodeServiceImpl.class);


    @Autowired
    private VhVehicleInfoMapper vhVehicleInfoMapper;


    /**
     * 查询车辆信息
     *
     * @param id 车辆信息ID
     * @return 车辆信息
     */
    @Override
    public VhVehicleInfo selectVhVehicleInfoById(Long id) {
        return vhVehicleInfoMapper.selectVhVehicleInfoById(id);
    }


    /**
     * 查询车辆信息列表
     *
     * @param vhVehicleInfo 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<VhVehicleInfo> selectVhVehicleInfoList(VhVehicleInfo vhVehicleInfo) {
        return vhVehicleInfoMapper.selectVhVehicleInfoList(vhVehicleInfo);
    }


    /**
     * 新增车辆信息
     *
     * @param vhVehicleInfo 车辆信息
     * @return 结果
     */
    @Override
    public int insertVhVehicleInfo(VhVehicleInfo vhVehicleInfo) {
        return vhVehicleInfoMapper.insertVhVehicleInfo(vhVehicleInfo);
    }

    /**
     * 修改车辆信息
     *
     * @param vhVehicleInfo 车辆信息
     * @return 结果
     */
    @Override
    public int updateVhVehicleInfo(VhVehicleInfo vhVehicleInfo) {
        return vhVehicleInfoMapper.updateVhVehicleInfo(vhVehicleInfo);
    }

    /**
     * 批量删除车辆信息
     *
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteVhVehicleInfoByIds(Long[] ids) {
        return vhVehicleInfoMapper.deleteVhVehicleInfoByIds(ids);
    }

    /**
     * 删除车辆信息信息
     *
     * @param id 车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteVhVehicleInfoById(Long id) {
        return vhVehicleInfoMapper.deleteVhVehicleInfoById(id);
    }

    @Override
    public ConcurrentHashMap<String, Object> importVhVehicleInfo(List<VhVehicleInfo> list, boolean isUpdateSupport, SysUser sysUser) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入车辆信息数据不能为空！");
        }
        log.info("{ } 单次导入数量 {}", sysUser.getDept().getDeptName(), String.valueOf(list.size()));


        AtomicInteger successNum = new AtomicInteger();
        AtomicInteger failureNum = new AtomicInteger();

        List<ConcurrentHashMap<String, Object>> messages = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String, Object> itemMap;
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        try {
            for (VhVehicleInfo obj : list) {
                if (!"".equals(obj.getVehicleCode().trim())) {
                    VhVehicleInfo param = new VhVehicleInfo();
                    param.setMaintainDept(sysUser.getDept().getDeptName());
                    param.setVehicleCode(obj.getVehicleCode());
                    List<VhVehicleInfo> vehicleList = vhVehicleInfoMapper.selectVhVehicleListNoScope(param);
                    if (CollectionUtils.isEmpty(vehicleList)) {
                        obj.setVehicleCode(obj.getVehicleCode().trim());
                        obj.setCreateBy(sysUser.getUserName());
                        obj.setUserId(sysUser.getUserId());
                        obj.setMaintainDept(sysUser.getDept().getDeptName());
                        this.insertVhVehicleInfo(obj);
                        successNum.getAndIncrement();
                    } else if (vehicleList.size() > 1) {
                        log.info("车辆重复: {}", obj.getVehicleCode());
                        itemMap = new ConcurrentHashMap<>();
                        itemMap.put("message", obj.getVehicleCode() + ": 车辆存在多条数据！");
                        messages.add(itemMap);
                        failureNum.getAndIncrement();
                    } else {
                        obj.setVehicleCode(obj.getVehicleCode().trim());
                        obj.setId(vehicleList.get(0).getId());
                        obj.setCreateBy(sysUser.getUserName());
                        obj.setUserId(sysUser.getUserId());
                        this.updateVhVehicleInfo(obj);
                        successNum.getAndIncrement();
                    }
                } else {
                    log.info("车辆编码为空: {}", obj.getVehicleCode());
                    itemMap = new ConcurrentHashMap<>();
                    itemMap.put("message", "存在车辆编码为空的数据！");
                    messages.add(itemMap);
                    failureNum.getAndIncrement();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("sum", successNum.get() + failureNum.get());
        map.put("success", successNum.get());
        map.put("fail", failureNum.get());
        map.put("messages", messages);

        return map;
    }

    @Override
    public List<VhVehicleInfo> selectVhVehicleInfoByIds(Long[] ids) {
        return vhVehicleInfoMapper.selectVhVehicleInfoByIds(ids);
    }

    @Override
    public VhVehicleInfo selectVhVehicleInfoByCode(String vehicleCode) {
        return vhVehicleInfoMapper.selectVhVehicleInfoByCode(vehicleCode);
    }

    @Override
    public void cleanAll(String deptName) {
        vhVehicleInfoMapper.cleanAll(deptName);
    }

    @Override
    public VhVehicleInfo selectVhVehicleInfo(VhVehicleInfo vhVehicleInfo) {
        return vhVehicleInfoMapper.selectVhVehicleInfo(vhVehicleInfo);
    }


    @Override
    public int insertBatch(List<VhVehicleInfo> list) {
        int size = list.size();
        int unitNum = 500;
        int startIndex = 0;
        int endIndex = 0;
        int success = 0;
        while (size > 0) {
            if (size > unitNum) {
                endIndex = startIndex + unitNum;
            } else {
                endIndex = startIndex + size;
            }
            List<VhVehicleInfo> insertData = list.subList(startIndex, endIndex);
            int rows = vhVehicleInfoMapper.insertBatch(insertData);
            size = size - unitNum;
            startIndex = endIndex;
            success += rows;
        }
        return success;
    }

}
