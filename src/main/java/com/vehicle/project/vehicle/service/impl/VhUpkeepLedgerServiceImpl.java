package com.vehicle.project.vehicle.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.domain.*;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.mapper.VhLedgerMainMapper;
import com.vehicle.project.vehicle.mapper.VhVehicleInfoMapper;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.service.IVhTyreLedgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhUpkeepLedgerMapper;
import com.vehicle.project.vehicle.service.IVhUpkeepLedgerService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养台账Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhUpkeepLedgerServiceImpl implements IVhUpkeepLedgerService {
    @Autowired
    private VhUpkeepLedgerMapper vhUpkeepLedgerMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    @Autowired
    private VhLedgerMainMapper ledgerMainMapper;

    @Autowired
    private VhVehicleInfoMapper vehicleInfoMapper;

    @Autowired
    private IVhTyreLedgerService tyreLedgerService;

    /**
     * 查询保养台账
     *
     * @param id 保养台账ID
     * @return 保养台账
     */
    @Override
    public CommonLedgerBo selectVhUpkeepLedgerById(Long id) {
//        return vhUpkeepLedgerMapper.selectVhUpkeepLedgerById(id);
        CommonLedgerBo ledgerBo = new CommonLedgerBo();

        //TODO selectVhLedgerMainById(id) 查询慢
        VhLedgerMain ledgerMain = ledgerMainMapper.selectVhLedgerMainById(id);
        BeanUtils.copyProperties(ledgerMain, ledgerBo);
        VhBusinessInfo vehicleBis = businessInfoMapper.selectVhBusinessInfo(new VhBusinessInfo(ledgerMain.getBusinessNo(), BusinessType.VEHICLE.ordinal()));
        if (vehicleBis == null || vehicleBis.getBusinessCode().isEmpty()) {
            return null;
        }
        VhVehicleInfo vehicleInfo = vehicleInfoMapper.selectVhVehicleInfoByCode(vehicleBis.getBusinessCode());

        //轮胎代码
        List<VhBusinessInfo> tyreBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                BusinessType.UPKEEP_CODE.ordinal()));
        Set<VhTyreCode> tyreCodeList = tyreBisList.stream().map(item -> {
            VhTyreCode tyreCode = new VhTyreCode();
            BeanUtils.copyProperties(item, tyreCode);
            tyreCode.setCrashBook(item.getCost().toString());
            tyreCode.setTyreCode(item.getBusinessCode());
            tyreCode.setTyreItem(item.getBusinessName());
            tyreCode.setCount(item.getAmount());
            return tyreCode;
        }).collect(Collectors.toSet());

        //配件代码
        List<VhBusinessInfo> partsBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                BusinessType.PARTS_CODE.ordinal()));
        Set<VhPartsCode> partsCodeList = partsBisList.stream().map(item -> {
            VhPartsCode partsCode = new VhPartsCode();
            BeanUtils.copyProperties(item, partsCode);
            partsCode.setMaterialCode(item.getBusinessCode());
            partsCode.setMaterialName(item.getBusinessName());
            partsCode.setAmount(item.getCost().toString());
            partsCode.setCount(item.getAmount());
            return partsCode;
        }).collect(Collectors.toSet());

        //故障代码
        List<VhBusinessInfo> faultBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                BusinessType.FAULT_CODE.ordinal()));
        Set<VhFaultCode> faultCodeList = faultBisList.stream().map(item -> {
            VhFaultCode faultCode = new VhFaultCode();
            faultCode.setFaultCode(item.getBusinessCode());
            faultCode.setCodeExplain(item.getBusinessName());
            return faultCode;
        }).collect(Collectors.toSet());

        ledgerBo.setVehicleInfo(vehicleInfo);
        ledgerBo.setTyreCodeList(tyreCodeList);
        ledgerBo.setPartsCodeList(partsCodeList);
        ledgerBo.setFaultCodeList(faultCodeList);
        return ledgerBo;
    }

    /**
     * 查询保养台账列表
     *
     * @param vhUpkeepLedger 保养台账
     * @return 保养台账
     */
    @Override
    public List<VhUpkeepLedger> selectVhUpkeepLedgerList(VhUpkeepLedger vhUpkeepLedger) {
        return vhUpkeepLedgerMapper.selectVhUpkeepLedgerList(vhUpkeepLedger);
    }



    /**
     * 新增保养台账
     *
     * @param vhUpkeepLedger 保养台账
     * @return 结果
     */
    @Override
    public int insertVhUpkeepLedger(VhUpkeepLedger vhUpkeepLedger) {
        return vhUpkeepLedgerMapper.insertVhUpkeepLedger(vhUpkeepLedger);
    }


    /**
     * 修改保养台账
     *
     * @param vhUpkeepLedger 保养台账
     * @return 结果
     */
    @Override
    public int updateVhUpkeepLedger(VhUpkeepLedger vhUpkeepLedger) {
        return vhUpkeepLedgerMapper.updateVhUpkeepLedger(vhUpkeepLedger);
    }


    /**
     * 批量删除保养台账
     *
     * @param ids 需要删除的保养台账ID
     * @return 结果
     */
    @Override
    public int deleteVhUpkeepLedgerByIds(Long[] ids) {
        return vhUpkeepLedgerMapper.deleteVhUpkeepLedgerByIds(ids);
    }

    /**
     * 删除保养台账信息
     *
     * @param id 保养台账ID
     * @return 结果
     */
    @Override
    public int deleteVhUpkeepLedgerById(Long id) {
        return vhUpkeepLedgerMapper.deleteVhUpkeepLedgerById(id);
    }


}
