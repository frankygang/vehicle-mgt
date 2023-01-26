package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhFaultCode;

import java.util.List;

/**
 * 故障代码Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhFaultCodeService {
    /**
     * 查询故障代码
     *
     * @param id 故障代码ID
     * @return 故障代码
     */
    VhFaultCode selectVhFaultCodeById(Long id);

    /**
     * 查询故障代码
     *
     * @param ids 故障代码IDS
     * @return 故障代码
     */
    List<VhFaultCode> selectVhFaultCodeByIds(Long[] ids);

    /**
     * 查询故障代码列表
     *
     * @param vhFaultCode 故障代码
     * @return 故障代码集合
     */
    List<VhFaultCode> selectVhFaultCodeList(VhFaultCode vhFaultCode);

    /**
     * 新增故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    int insertVhFaultCode(VhFaultCode vhFaultCode);

    /**
     * 修改故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    int updateVhFaultCode(VhFaultCode vhFaultCode);

    /**
     * 批量删除故障代码
     *
     * @param ids 需要删除的故障代码ID
     * @return 结果
     */
    int deleteVhFaultCodeByIds(Long[] ids);

    /**
     * 删除故障代码信息
     *
     * @param id 故障代码ID
     * @return 结果
     */
    int deleteVhFaultCodeById(Long id);

    /**
     * 导入 保养代码信息
     * @param list 故障代码数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param username 操作用户
     * @return 结果
     */
    String importVhFaultCode(List<VhFaultCode> list, boolean isUpdateSupport, String username);

    VhFaultCode selectVhMaintainCode(VhFaultCode vhFaultCode);
}
