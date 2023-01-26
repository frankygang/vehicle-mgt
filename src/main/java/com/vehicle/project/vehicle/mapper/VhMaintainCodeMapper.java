package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.domain.VhMaintainCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 维修代码Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhMaintainCodeMapper {
    /**
     * 查询维修代码
     *
     * @param id 维修代码ID
     * @return 维修代码
     */
    VhMaintainCode selectVhMaintainCodeById(Long id);

    /**
     * 查询维修代码
     *
     * @param ids 维修代码ID数组
     * @return 维修代码集合
     */
    List<VhMaintainCode> selectVhMaintainCodeByIds(Long[] ids);

    VhMaintainCode selectVhMaintainCodeByCode(@Param("maintainCode") String maintainCode);

    /**
     * 查询维修代码列表
     *
     * @param vhMaintainCode 维修代码
     * @return 维修代码集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<VhMaintainCode> selectVhMaintainCodeList(VhMaintainCode vhMaintainCode);

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
     * 删除维修代码
     *
     * @param id 维修代码ID
     * @return 结果
     */
    public int deleteVhMaintainCodeById(Long id);

    /**
     * 批量删除维修代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhMaintainCodeByIds(Long[] ids);

    List<VhFaultCode> getFaultCode(List<String> code);

    VhMaintainCode selectVhMaintainCode(VhMaintainCode vhMaintainCode);

    VhMaintainCode selectVhMaintainItemByMaintainCodeAndMaintainDept(@Param("maintainCode") String maintainCode, @Param("maintainDept") String maintainDept);
}
