package com.vehicle.project.vehicle.service.impl;

import java.util.List;

import com.vehicle.common.utils.DateUtils;
import com.vehicle.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhApplyInfoMapper;
import com.vehicle.project.vehicle.domain.VhApplyInfo;
import com.vehicle.project.vehicle.service.IVhApplyInfoService;

/**
 * 配件申购Service业务层处理
 *
 * @author .
 * @date 2020-09-19
 */
@Service
public class VhApplyInfoServiceImpl implements IVhApplyInfoService {
    @Autowired
    private VhApplyInfoMapper vhApplyInfoMapper;

    /**
     * 查询配件申购
     *
     * @param id 配件申购ID
     * @return 配件申购
     */
    @Override
    public VhApplyInfo selectVhApplyInfoById(Long id) {
        return vhApplyInfoMapper.selectVhApplyInfoById(id);
    }

    /**
     * 查询配件申购列表
     *
     * @param vhApplyInfo 配件申购
     * @return 配件申购
     */
    @Override
    public List<VhApplyInfo> selectVhApplyInfoList(VhApplyInfo vhApplyInfo) {
        return vhApplyInfoMapper.selectVhApplyInfoList(vhApplyInfo);
    }

    /**
     * 新增配件申购
     *
     * @param vhApplyInfo 配件申购
     * @return 结果
     */
    @Override
    public int insertVhApplyInfo(VhApplyInfo vhApplyInfo) {
        vhApplyInfo.setCreateTime(DateUtils.getNowDate());
        return vhApplyInfoMapper.insertVhApplyInfo(vhApplyInfo);
    }

    /**
     * 修改配件申购
     *
     * @param vhApplyInfo 配件申购
     * @return 结果
     */
    @Override
    public int updateVhApplyInfo(VhApplyInfo vhApplyInfo) {
        vhApplyInfo.setUpdateTime(DateUtils.getNowDate());
        return vhApplyInfoMapper.updateVhApplyInfo(vhApplyInfo);
    }

    /**
     * 批量删除配件申购
     *
     * @param ids 需要删除的配件申购ID
     * @return 结果
     */
    @Override
    public int deleteVhApplyInfoByIds(Long[] ids) {
        return vhApplyInfoMapper.deleteVhApplyInfoByIds(ids);
    }

    /**
     * 删除配件申购信息
     *
     * @param id 配件申购ID
     * @return 结果
     */
    @Override
    public int deleteVhApplyInfoById(Long id) {
        return vhApplyInfoMapper.deleteVhApplyInfoById(id);
    }

    @Override
    public VhApplyInfo genPurchaseNo() {
        return vhApplyInfoMapper.genPurchaseNo();
    }
}
