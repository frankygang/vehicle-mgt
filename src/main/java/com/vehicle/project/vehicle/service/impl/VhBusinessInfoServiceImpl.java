package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.utils.DateUtils;
import com.vehicle.project.vehicle.domain.VhBusinessInfo;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.service.IVhBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务信息Service业务层处理
 *
 * @author bobo
 * @date 2020-02-12
 */
@Service
public class VhBusinessInfoServiceImpl implements IVhBusinessInfoService {

    @Autowired
    private VhBusinessInfoMapper vhBusinessInfoMapper;

    /**
     * 查询业务信息
     *
     * @param id 业务信息ID
     * @return 业务信息
     */
    @Override
    public VhBusinessInfo selectVhBusinessInfoById(Long id) {
        return vhBusinessInfoMapper.selectVhBusinessInfoById(id);
    }

    /**
     * 查询业务信息列表
     *
     * @param vhBusinessInfo 业务信息
     * @return 业务信息
     */
    @Override
    public List<VhBusinessInfo> selectVhBusinessInfoList(VhBusinessInfo vhBusinessInfo) {
        return vhBusinessInfoMapper.selectVhBusinessInfoList(vhBusinessInfo);
    }

    /**
     * 新增业务信息
     *
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    @Override
    public int insertVhBusinessInfo(VhBusinessInfo vhBusinessInfo) {
        vhBusinessInfo.setCreateTime(DateUtils.getNowDate());
        return vhBusinessInfoMapper.insertVhBusinessInfo(vhBusinessInfo);
    }

    /**
     * 修改业务信息
     *
     * @param vhBusinessInfo 业务信息
     * @return 结果
     */
    @Override
    public int updateVhBusinessInfo(VhBusinessInfo vhBusinessInfo) {
        vhBusinessInfo.setUpdateTime(DateUtils.getNowDate());
        return vhBusinessInfoMapper.updateVhBusinessInfo(vhBusinessInfo);
    }

    /**
     * 批量删除业务信息
     *
     * @param ids 需要删除的业务信息ID
     * @return 结果
     */
    @Override
    public int deleteVhBusinessInfoByIds(Long[] ids) {
        return vhBusinessInfoMapper.deleteVhBusinessInfoByIds(ids);
    }

    /**
     * 删除业务信息信息
     *
     * @param id 业务信息ID
     * @return 结果
     */
    @Override
    public int deleteVhBusinessInfoById(Long id) {
        return vhBusinessInfoMapper.deleteVhBusinessInfoById(id);
    }
}
