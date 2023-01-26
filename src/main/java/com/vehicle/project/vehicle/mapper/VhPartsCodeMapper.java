package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 配件代码Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhPartsCodeMapper {
    /**
     * 查询配件代码
     *
     * @param id 配件代码ID
     * @return 配件代码
     */
    VhPartsCode selectVhPartsCodeById(Long id);

    VhPartsCode selectVhPartsCodeByCode(@Param("materialCode") String materialCode);

    /**
     * 查询配件代码列表
     *
     * @param vhPartsCode 配件代码
     * @return 配件代码集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<VhPartsCode> selectVhPartsCodeList(VhPartsCode vhPartsCode);

    /**
     * 新增配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    public int insertVhPartsCode(VhPartsCode vhPartsCode);

    /**
     * 修改配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    public int updateVhPartsCode(VhPartsCode vhPartsCode);

    /**
     * 删除配件代码
     *
     * @param id 配件代码ID
     * @return 结果
     */
    public int deleteVhPartsCodeById(Long id);

    /**
     * 批量删除配件代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteVhPartsCodeByIds(Long[] ids);

    /**
     * 查询配件代码
     * @param ids
     * @return java.util.List<com.vehicle.project.vehicle.domain.VhPartsCode>
     */
    List<VhPartsCode> selectVhPartsCodeByIds(Long[] ids);

    VhPartsCode selectVhPartsCode(VhPartsCode vhPartsCode);

    int batchInsert(List<VhPartsCode> vhPartsCode);

    int batchUpdate(List<VhPartsCode> vhPartsCode);

    VhPartsCode selectVhPartsCodeByPartsCodeAndDept(@Param("materialCode") String materialCode, @Param("maintainDept") String maintainDept);
}
