package com.vehicle.project.vehicle.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.project.vehicle.domain.*;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.mapper.VhMaintainCodeMapper;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.pojo.ItemBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhMaintainLedgerMapper;
import com.vehicle.project.vehicle.service.IVhMaintainLedgerService;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 维修台账Service业务层处理
 * * 此页作废
 * @author bobo
 * @date 2020-02-03
 */
@Service
public class VhMaintainLedgerServiceImpl implements IVhMaintainLedgerService {

    @Resource
    private VhMaintainLedgerMapper vhMaintainLedgerMapper;

    @Autowired
    private VhMaintainCodeMapper maintainCodeMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    /**
     * 查询维修台账
     * * 此页作废
     * @param id 维修台账ID
     * @return 维修台账
     */
    @Override
    public VhMaintainLedger selectVhMaintainLedgerById(Long id) {
        return vhMaintainLedgerMapper.selectVhMaintainLedgerById(id);
    }

    /**
     * 查询维修台账列表
     * 此页作废
     * @param vhMaintainLedger 维修台账
     * @return 维修台账
     */
    @Override
    public List<VhMaintainLedger> selectVhMaintainLedgerList(VhMaintainLedger vhMaintainLedger) {
        return vhMaintainLedgerMapper.selectVhMaintainLedgerList(vhMaintainLedger);
    }


    /**
     * 新增维修台账
     * * 此页作废！！
     * @param vhMaintainLedger 维修台账
     * @return 结果
     */
    @Override
    public int insertVhMaintainLedger(VhMaintainLedger vhMaintainLedger) {
        return vhMaintainLedgerMapper.insertVhMaintainLedger(vhMaintainLedger);
    }

    /**
     * 修改维修台账
     * * 此页作废
     * @param vhMaintainLedger 维修台账
     * @return 结果
     */
    @Override
    public int updateVhMaintainLedger(VhMaintainLedger vhMaintainLedger) {
        return vhMaintainLedgerMapper.updateVhMaintainLedger(vhMaintainLedger);
    }

    /**
     * 批量删除维修台账
     * * 此页作废！！
     * @param ids 需要删除的维修台账ID
     * @return 结果
     */
    @Override
    public int deleteVhMaintainLedgerByIds(Long[] ids) {
        return vhMaintainLedgerMapper.deleteVhMaintainLedgerByIds(ids);
    }

    /**
     * 删除维修台账信息
     * * 此页作废！！！
     * @param id 维修台账ID
     * @return 结果
     */
    @Override
    public int deleteVhMaintainLedgerById(Long id) {
        return vhMaintainLedgerMapper.deleteVhMaintainLedgerById(id);
    }


    /**
     * 功能描述:
     * 维修台账新增
     * * 此页作废！！！
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/13 10:32
     */
    @Override
    public int addVhMaintainLedger(CommonLedgerBo ledgerBo) {

        VhLedgerMain ledgerMain = new VhLedgerMain();
        VhBusinessInfo businessInfo;

        BeanUtils.copyProperties(ledgerBo, ledgerMain);
        ledgerMain.setLedgerType(BusinessType.MAINTAIN.ordinal());

        // 车辆信息
        if (ledgerBo.getVehicleInfo() != null) {
            businessInfo = new VhBusinessInfo();
            businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
            businessInfo.setBusinessCode(ledgerBo.getVehicleInfo().getVehicleCode());
            businessInfo.setBusinessType(BusinessType.VEHICLE.ordinal());
            businessInfoMapper.insertVhBusinessInfo(businessInfo);
        }
        //物料代码

        // 维修代码
        if (!CollectionUtils.isEmpty(ledgerBo.getMaintainCodeList())) {
            for (VhMaintainCode item : ledgerBo.getMaintainCodeList()) {
                businessInfo = new VhBusinessInfo();
                businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                businessInfo.setBusinessCode(item.getMaintainCode());
                businessInfo.setAmount(new BigDecimal(item.getCrashBook()));
                businessInfo.setBusinessType(BusinessType.MAINTAIN.ordinal());
                businessInfoMapper.insertVhBusinessInfo(businessInfo);
            }
        }
        //物料代码
        if (!CollectionUtils.isEmpty(ledgerBo.getPartsCodeList())) {
            for (VhPartsCode item : ledgerBo.getPartsCodeList()) {
                businessInfo = new VhBusinessInfo();
                businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                businessInfo.setBusinessCode(item.getMaterialCode());
                businessInfo.setAmount(item.getCount());
                businessInfo.setBusinessType(BusinessType.MAINTAIN.ordinal());
                businessInfoMapper.insertVhBusinessInfo(businessInfo);
            }
        }


        return 0;
    }
}
