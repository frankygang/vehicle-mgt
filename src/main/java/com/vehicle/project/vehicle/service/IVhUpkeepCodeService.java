package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhUpkeepCode;

import java.util.List;

/**
 * 保养代码Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhUpkeepCodeService {
    /**
     * 查询保养代码
     *
     * @param id 保养代码ID
     * @return 保养代码
     */
    public VhUpkeepCode selectVhUpkeepCodeById(Long id);

    /**
     * 查询保养代码
     *
     * @param upkeepCode 保养代码
     * @return 保养代码
     */
    VhUpkeepCode selectVhUpkeepCodeByCode(String upkeepCode);

    /**
     * 查询保养代码
     *
     * @param ids 保养代码数组
     * @return 保养代码集会
     */
    List<VhUpkeepCode> selectVhUpkeepCodeByIds(Long[] ids);

    /**
     * 查询保养代码列表
     *
     * @param vhUpkeepCode 保养代码
     * @return 保养代码集合
     */
    public List<VhUpkeepCode> selectVhUpkeepCodeList(VhUpkeepCode vhUpkeepCode);

    VhUpkeepCode selectVhUpkeepCode(VhUpkeepCode vhUpkeepCode);

    /**
     * 新增保养代码
     *
     * @param vhUpkeepCode 保养代码
     * @return 结果
     */
    public int insertVhUpkeepCode(VhUpkeepCode vhUpkeepCode);

    /**
     * 修改保养代码
     *
     * @param vhUpkeepCode 保养代码
     * @return 结果
     */
    public int updateVhUpkeepCode(VhUpkeepCode vhUpkeepCode);

    /**
     * 批量删除保养代码
     *
     * @param ids 需要删除的保养代码ID
     * @return 结果
     */
    public int deleteVhUpkeepCodeByIds(Long[] ids);

    /**
     * 删除保养代码信息
     *
     * @param id 保养代码ID
     * @return 结果
     */
    public int deleteVhUpkeepCodeById(Long id);

    /**
     * 导入 保养代码信息
     * @param vhUpkeepCodeList 保养代码数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param username 操作用户
     * @return 结果
     */
    String importVhUpKeepCode(List<VhUpkeepCode> vhUpkeepCodeList, boolean isUpdateSupport, String username);

}
