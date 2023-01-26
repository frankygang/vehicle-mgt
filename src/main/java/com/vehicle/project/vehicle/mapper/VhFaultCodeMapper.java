package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 故障代码Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhFaultCodeMapper {
    /**
     * 查询故障代码
     *
     * @param id 故障代码ID
     * @return 故障代码
     */
    public VhFaultCode selectVhFaultCodeById(Long id);

    /**
     * 查询故障代码
     *
     * @param ids 故障代码ID数组
     * @return 故障代码集合
     */
    List<VhFaultCode> selectVhFaultCodeByIds(Long[] ids);

    VhFaultCode selectVhFaultCodeByCode(@Param("faultCode") String faultCode);

    /**
     * 查询故障代码列表
     *
     * @param vhFaultCode 故障代码
     * @return 故障代码集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<VhFaultCode> selectVhFaultCodeList(VhFaultCode vhFaultCode);

    /**
     * 新增故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    public int insertVhFaultCode(VhFaultCode vhFaultCode);

    /**
     * 修改故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    public int updateVhFaultCode(VhFaultCode vhFaultCode);

    /**
     * 删除故障代码
     *
     * @param id 故障代码ID
     * @return 结果
     */
    public int deleteVhFaultCodeById(Long id);

    /**
     * 批量删除故障代码
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhFaultCodeByIds(Long[] ids);

    VhFaultCode selectVhMaintainCode(VhFaultCode vhFaultCode);

    VhFaultCode selectVhFaultCodeByFaultCodeAndDept(@Param("faultCode") String faultCode, @Param("maintainDept") String maintainDept);

}
