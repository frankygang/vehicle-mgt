package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhTyreCode;

import java.util.List;

/**
 * 轮胎代码Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhTyreCodeService {
    /**
     * 查询轮胎代码
     *
     * @param id 轮胎代码ID
     * @return 轮胎代码
     */
    public VhTyreCode selectVhTyreCodeById(Long id);

    /**
     * 查询轮胎代码列表
     *
     * @param vhTyreCode 轮胎代码
     * @return 轮胎代码集合
     */
    public List<VhTyreCode> selectVhTyreCodeList(VhTyreCode vhTyreCode);

    /**
     * 新增轮胎代码
     *
     * @param vhTyreCode 轮胎代码
     * @return 结果
     */
    public int insertVhTyreCode(VhTyreCode vhTyreCode);

    /**
     * 修改轮胎代码
     *
     * @param vhTyreCode 轮胎代码
     * @return 结果
     */
    public int updateVhTyreCode(VhTyreCode vhTyreCode);

    /**
     * 批量删除轮胎代码
     *
     * @param ids 需要删除的轮胎代码ID
     * @return 结果
     */
    public int deleteVhTyreCodeByIds(Long[] ids);

    /**
     * 删除轮胎代码信息
     *
     * @param id 轮胎代码ID
     * @return 结果
     */
    public int deleteVhTyreCodeById(Long id);

    String importVhTyreCode(List<VhTyreCode> list, boolean isUpdateSupport, String username);


    List<VhTyreCode> selectVhTyreCodeByIds(Long[] ids);

    VhTyreCode selectVhTyreCode(VhTyreCode vhTyreCode);
}
