package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhTyreCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮胎代码Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhTyreCodeMapper {
    /**
     * 查询轮胎代码
     *
     * @param id 轮胎代码ID
     * @return 轮胎代码
     */
    public VhTyreCode selectVhTyreCodeById(Long id);

    VhTyreCode selectVhTyreCodeByCode(@Param("tyreCode") String tyreCode);

    /**
     * 查询轮胎代码列表
     *
     * @param vhTyreCode 轮胎代码
     * @return 轮胎代码集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
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
     * 删除轮胎代码
     *
     * @param id 轮胎代码ID
     * @return 结果
     */
    public int deleteVhTyreCodeById(Long id);

    /**
     * 批量删除轮胎代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhTyreCodeByIds(Long[] ids);

    List<VhTyreCode> selectVhTyreCodeByIds(Long[] ids);

    VhTyreCode selectVhTyreCode(VhTyreCode vhTyreCode);

    VhTyreCode selectVhTyreCodeByCodeAndDept(@Param("tyreCode") String tyreCode, @Param("maintainDept") String maintainDept);
}
