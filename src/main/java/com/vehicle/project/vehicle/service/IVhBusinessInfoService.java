package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhBusinessInfo;
import java.util.List;

/**
 * 业务信息Service接口
 * 
 * @author bobo
 * @date 2020-02-12
 */
public interface IVhBusinessInfoService 
{
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

    /**
     * 新增业务信息
     * 
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    public int insertVhBusinessInfo(VhBusinessInfo vhBusinessInfo);

    /**
     * 修改业务信息
     * 
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    public int updateVhBusinessInfo(VhBusinessInfo vhBusinessInfo);

    /**
     * 批量删除业务信息
     * 
     * @param ids 需要删除的业务信息ID
     * @return 结果
     */
    public int deleteVhBusinessInfoByIds(Long[] ids);

    /**
     * 删除业务信息信息
     * 
     * @param id 业务信息ID
     * @return 结果
     */
    public int deleteVhBusinessInfoById(Long id);
}
