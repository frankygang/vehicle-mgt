package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.domain.VhMaintainCode;

import java.util.List;

/**
 * 维修代码Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhMaintainCodeService {
    /**
     * 查询维修代码
     *
     * @param id 维修代码ID
     * @return 维修代码
     */
    public VhMaintainCode selectVhMaintainCodeById(Long id);

    /**
     * 查询维修代码
     *
     * @param ids 维修代码ID数组
     * @return 维修代码集会
     */
    List<VhMaintainCode> selectVhMaintainCodeByIds(Long[] ids);

    /**
     * 查询维修代码列表
     *
     * @param vhMaintainCode 维修代码
     * @return 维修代码集合
     */
    public List<VhMaintainCode> selectVhMaintainCodeList(VhMaintainCode vhMaintainCode);

    VhMaintainCode selectVhMaintainCode(VhMaintainCode vhMaintainCode);

    /**
     * 新增维修代码
     *
     * @param vhMaintainCode 维修代码
     * @return 结果
     */
    public int insertVhMaintainCode(VhMaintainCode vhMaintainCode);

    /**
     * 修改维修代码
     *
     * @param vhMaintainCode 维修代码
     * @return 结果
     */
    public int updateVhMaintainCode(VhMaintainCode vhMaintainCode);

    /**
     * 批量删除维修代码
     *
     * @param ids 需要删除的维修代码ID
     * @return 结果
     */
    public int deleteVhMaintainCodeByIds(Long[] ids);

    /**
     * 删除维修代码信息
     *
     * @param id 维修代码ID
     * @return 结果
     */
    public int deleteVhMaintainCodeById(Long id);


    String importVhMaintainCode(List<VhMaintainCode> list, boolean isUpdateSupport, String username);

    /** 根据故障码获取故障类型码*/
    List<VhFaultCode> getFaultCode(List<String> code);
}
