package com.vehicle.project.vehicle.mapper;

import com.vehicle.project.vehicle.domain.VhBusinessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务信息Mapper接口
 *
 * @author bobo
 * @date 2020-02-12
 */
public interface VhBusinessInfoMapper {
    /**
     * 查询业务信息
     *
     * @param id 业务信息ID
     * @return 业务信息
     */
    public VhBusinessInfo selectVhBusinessInfoById(Long id);

    /**
     * 查询业务信息列表
     *
     * @param vhBusinessInfo 业务信息
     * @return 业务信息集合
     */
    public List<VhBusinessInfo> selectVhBusinessInfoList(VhBusinessInfo vhBusinessInfo);

    VhBusinessInfo selectVhBusinessInfo(VhBusinessInfo vhBusinessInfo);

    /**
     * 新增业务信息
     *
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    public int   insertVhBusinessInfo(VhBusinessInfo vhBusinessInfo);

    /**
     * 修改业务信息
     *
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    public int updateVhBusinessInfo(VhBusinessInfo vhBusinessInfo);

    /**
     * 删除业务信息
     *
     * @param id 业务信息ID
     * @return 结果
     */
    public int deleteVhBusinessInfoById(Long id);

    /**
     * 批量删除业务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhBusinessInfoByIds(Long[] ids);

    /**
     * 功能描述:
     * 删除业务单号下的车辆信息
     *
     * @param: businessType
     *              BusinessType.VEHICLE.ordinal()
     * @return:
     * @auther: onion
     * @date: 2020/2/14 16:02
     */
    int deleteVhBusinessInfo(@Param("businessNo") String businessNo, @Param("businessType") Integer businessType);

}
