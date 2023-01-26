package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆信息Mapper接口
 *
 * @author onion
 * @date 2020-02-03
 */
public interface VhVehicleInfoMapper {
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
    @DataScope(deptAlias = "d", userAlias = "u")
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
     * 删除车辆信息
     *
     * @param id 车辆信息ID
     * @return 结果
     */
    public int deleteVhVehicleInfoById(Long id);

    /**
     * 批量删除车辆信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhVehicleInfoByIds(Long[] ids);


    VhVehicleInfo selectVhVehicleInfoByCode(@Param("vehicleCode") String vehicleCode);

    List<VhVehicleInfo> selectVhVehicleListNoScope(VhVehicleInfo vehicleInfo);

    VhVehicleInfo selectVhVehicleInfo(VhVehicleInfo vehicleInfo);

    List<VhVehicleInfo> selectVhVehicleInfoByIds(Long[] ids);

    void cleanAll(@Param("deptName") String deptName);

    int insertBatch(List<VhVehicleInfo> insertData);
}
