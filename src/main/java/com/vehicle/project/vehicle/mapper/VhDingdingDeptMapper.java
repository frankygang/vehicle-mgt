package com.vehicle.project.vehicle.mapper;

import com.vehicle.project.vehicle.domain.VhDingdingDept;
import java.util.List;

/**
 * 钉钉部门Mapper接口
 * 
 * @author onion
 * @date 2020-04-17
 */
public interface VhDingdingDeptMapper 
{
    /**
     * 查询钉钉部门
     * 
     * @param id 钉钉部门ID
     * @return 钉钉部门
     */
    public VhDingdingDept selectVhDingdingDeptById(Long id);

    /**
     * 查询钉钉部门列表
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 钉钉部门集合
     */
    public List<VhDingdingDept> selectVhDingdingDeptList(VhDingdingDept vhDingdingDept);

    /**
     * 新增钉钉部门
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 结果
     */
    public int insertVhDingdingDept(VhDingdingDept vhDingdingDept);

    /**
     * 修改钉钉部门
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 结果
     */
    public int updateVhDingdingDept(VhDingdingDept vhDingdingDept);

    /**
     * 删除钉钉部门
     * 
     * @param id 钉钉部门ID
     * @return 结果
     */
    public int deleteVhDingdingDeptById(Long id);

    /**
     * 批量删除钉钉部门
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhDingdingDeptByIds(Long[] ids);
}
