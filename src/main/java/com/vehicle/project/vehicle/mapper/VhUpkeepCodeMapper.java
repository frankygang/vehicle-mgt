package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhUpkeepCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 保养代码Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhUpkeepCodeMapper {
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
     * @param ids 保养代码ID数组
     * @return 保养代码集合
     */
    List<VhUpkeepCode> selectVhUpkeepCodeByIds(Long[] ids);

    /**
     * 查询保养代码
     * @param upkeepCode 保养代码
     * @return com.vehicle.project.vehicle.domain.VhUpkeepCode
     */
    VhUpkeepCode selectVhUpkeepCodeByCode(String upkeepCode);

    /**
     * 查询保养代码列表
     *
     * @param vhUpkeepCode 保养代码
     * @return 保养代码集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
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
     * 删除保养代码
     *
     * @param id 保养代码ID
     * @return 结果
     */
    public int deleteVhUpkeepCodeById(Long id);

    /**
     * 批量删除保养代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhUpkeepCodeByIds(Long[] ids);

    VhUpkeepCode selectVhUpkeepCodeByCodeAndDept(@Param("upkeepCode") String upkeepCode, @Param("maintainDept") String deptName);
}
