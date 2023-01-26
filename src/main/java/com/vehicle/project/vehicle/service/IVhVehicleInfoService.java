package com.vehicle.project.vehicle.service;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.pojo.VehicleInfoBo;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 车辆信息Service接口
 *
 * @author onion
 * @date 2020-02-03
 */
public interface IVhVehicleInfoService {
    /**
     * 查询车辆信息
     *
     * @param id 车辆信息ID
     * @return 车辆信息
     */
    public VhVehicleInfo selectVhVehicleInfoById(Long id);


    /**
     * 查询车辆信息列表
     *
     * @param vhVehicleInfo 车辆信息
     * @return 车辆信息集合
     */
    public List<VhVehicleInfo> selectVhVehicleInfoList(VhVehicleInfo vhVehicleInfo);


    /**
     * 新增车辆信息
     *
     * @param vhVehicleInfo 车辆信息
     * @return 结果
     */
    public int insertVhVehicleInfo(VhVehicleInfo vhVehicleInfo);


    /**
     * 修改车辆信息
     *
     * @param vhVehicleInfo 车辆信息
     * @return 结果
     */
    public int updateVhVehicleInfo(VhVehicleInfo vhVehicleInfo);


    /**
     * 批量删除车辆信息
     *
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    public int deleteVhVehicleInfoByIds(Long[] ids);


    /**
     * 删除车辆信息信息
     *
     * @param id 车辆信息ID
     * @return 结果
     */
    public int deleteVhVehicleInfoById(Long id);

    /**
     * 功能描述:
     * 导入
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/6 15:45
     */
    ConcurrentHashMap<String, Object> importVhVehicleInfo(List<VhVehicleInfo> list, boolean isUpdateSupport, SysUser sysUser);

    List<VhVehicleInfo> selectVhVehicleInfoByIds(Long[] ids);

    VhVehicleInfo selectVhVehicleInfoByCode(String vehicleCode);

    void cleanAll(String deptName);

    VhVehicleInfo selectVhVehicleInfo(VhVehicleInfo vhVehicleInfo);

    /**
     * 500条批量写入
     *
     * @param list
     * @return
     */
    int insertBatch(List<VhVehicleInfo> list);
}
