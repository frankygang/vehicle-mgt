package com.vehicle.project.vehicle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhDingdingDeptMapper;
import com.vehicle.project.vehicle.domain.VhDingdingDept;
import com.vehicle.project.vehicle.service.IVhDingdingDeptService;

/**
 * 钉钉部门Service业务层处理
 * 
 * @author onion
 * @date 2020-04-17
 */
@Service
public class VhDingdingDeptServiceImpl implements IVhDingdingDeptService 
{
    @Autowired
    private VhDingdingDeptMapper vhDingdingDeptMapper;

    /**
     * 查询钉钉部门
     * 
     * @param id 钉钉部门ID
     * @return 钉钉部门
     */
    @Override
    public VhDingdingDept selectVhDingdingDeptById(Long id)
    {
        return vhDingdingDeptMapper.selectVhDingdingDeptById(id);
    }

    /**
     * 查询钉钉部门列表
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 钉钉部门
     */
    @Override
    public List<VhDingdingDept> selectVhDingdingDeptList(VhDingdingDept vhDingdingDept)
    {
        return vhDingdingDeptMapper.selectVhDingdingDeptList(vhDingdingDept);
    }

    /**
     * 新增钉钉部门
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 结果
     */
    @Override
    public int insertVhDingdingDept(VhDingdingDept vhDingdingDept)
    {
        return vhDingdingDeptMapper.insertVhDingdingDept(vhDingdingDept);
    }

    /**
     * 修改钉钉部门
     * 
     * @param vhDingdingDept 钉钉部门
     * @return 结果
     */
    @Override
    public int updateVhDingdingDept(VhDingdingDept vhDingdingDept)
    {
        return vhDingdingDeptMapper.updateVhDingdingDept(vhDingdingDept);
    }

    /**
     * 批量删除钉钉部门
     * 
     * @param ids 需要删除的钉钉部门ID
     * @return 结果
     */
    @Override
    public int deleteVhDingdingDeptByIds(Long[] ids)
    {
        return vhDingdingDeptMapper.deleteVhDingdingDeptByIds(ids);
    }

    /**
     * 删除钉钉部门信息
     * 
     * @param id 钉钉部门ID
     * @return 结果
     */
    @Override
    public int deleteVhDingdingDeptById(Long id)
    {
        return vhDingdingDeptMapper.deleteVhDingdingDeptById(id);
    }
}
