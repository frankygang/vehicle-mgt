package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.constant.Constants;
import com.vehicle.common.constant.DicConstants;
import com.vehicle.common.constant.HttpStatus;
import com.vehicle.common.constant.UserConstants;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.system.domain.SysDictData;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.system.service.ISysDictDataService;
import com.vehicle.project.vehicle.domain.*;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.mapper.VhLedgerMainMapper;
import com.vehicle.project.vehicle.mapper.VhMaintainCodeMapper;
import com.vehicle.project.vehicle.mapper.VhVehicleInfoMapper;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.service.IVhLedgerMainService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.vehicle.framework.aspectj.lang.enums.BusinessType.*;

/**
 * 台账主表Service业务层处理
 *
 * @author onion
 * @date 2020-02-12
 */
@Slf4j
@Service
public class VhLedgerMainServiceImpl implements IVhLedgerMainService {
    @Autowired
    private VhLedgerMainMapper vhLedgerMainMapper;

    @Autowired
    private VhLedgerMainMapper ledgerMainMapper;

    @Autowired
    private VhVehicleInfoMapper vehicleInfoMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    @Autowired
    private VhMaintainCodeMapper vhMaintainCodeMapper;

    @Autowired
    private ISysDictDataService dictDataService;


    @Override
    public CommonLedgerBo selectVhLedgerMainById(Long id) {
        CommonLedgerBo ledgerBo = new CommonLedgerBo();

        //TODO selectVhLedgerMainById(id) 查询慢
        VhLedgerMain ledgerMain = ledgerMainMapper.selectVhLedgerMainById(id);
        BeanUtils.copyProperties(ledgerMain, ledgerBo);
        VhBusinessInfo vehicleBis = businessInfoMapper.selectVhBusinessInfo(new VhBusinessInfo(ledgerMain.getBusinessNo(), VEHICLE.ordinal()));
        if (vehicleBis == null || vehicleBis.getBusinessCode().isEmpty()) {
            return null;
        }
        VhVehicleInfo param = new VhVehicleInfo();
        param.setVehicleCode(ledgerMain.getVehicleCode());
        param.setMaintainDept(ledgerMain.getMaintainDept());
        VhVehicleInfo vehicleInfo = vehicleInfoMapper.selectVhVehicleInfo(param);

        //为了和以前业务兼容
        List<VhBusinessInfo> bisVehicle = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                VEHICLE.ordinal(), ledgerMain.getDelFlag()));
        if (vehicleInfo == null) {
            vehicleInfo = new VhVehicleInfo();
        }
        if (!CollectionUtils.isEmpty(bisVehicle)) {
            BeanUtils.copyProperties(bisVehicle.get(0), vehicleInfo);
            vehicleInfo.setVehicleCode(bisVehicle.get(0).getBusinessCode());
            vehicleInfo.setVehicleType(bisVehicle.get(0).getBusinessName());
        }


        //轮胎代码
        List<VhBusinessInfo> tyreBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                TYRE_CODE.ordinal()));
        if (!CollectionUtils.isEmpty(tyreBisList)) {
            Set<VhTyreCode> tyreCodeList = tyreBisList.stream().map(item -> {
                VhTyreCode tyreCode = new VhTyreCode();
                BeanUtils.copyProperties(item, tyreCode);
                tyreCode.setCrashBook(item.getCost().toString());
                tyreCode.setTyreCode(item.getBusinessCode());
                tyreCode.setTyreItem(item.getBusinessName());
                tyreCode.setCount(item.getAmount());
                return tyreCode;
            }).collect(Collectors.toSet());
            ledgerBo.setTyreCodeList(tyreCodeList);
        }

        //维修代码
        List<VhBusinessInfo> mainTainBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                MAINTAIN_CODE.ordinal()));
        Set<VhMaintainCode> mtCodeList;
        if (!CollectionUtils.isEmpty(mainTainBisList)) {
            mtCodeList = mainTainBisList.stream().map(item -> {
                VhMaintainCode mtCode = new VhMaintainCode();
                BeanUtils.copyProperties(item, mtCode);
                mtCode.setCrashBook(item.getCost().toString());
                mtCode.setMaintainCode(item.getBusinessCode());
                mtCode.setMaintainItem(item.getBusinessName());
                mtCode.setCount(item.getAmount());
                return mtCode;
            }).collect(Collectors.toSet());
            ledgerBo.setMaintainCodeList(mtCodeList);
            ledgerBo.setMaintainCodeList(mtCodeList);
        }

        //保养代码
        List<VhBusinessInfo> upKeepBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                UPKEEP_CODE.ordinal()));
        Set<VhUpkeepCode> upkeepCodeList;
        if (!CollectionUtils.isEmpty(upKeepBisList)) {
            upkeepCodeList = upKeepBisList.stream().map(item -> {
                VhUpkeepCode upkeepCode = new VhUpkeepCode();
                BeanUtils.copyProperties(item, upkeepCode);
                upkeepCode.setUpkeepType(item.getUpkeepType());
                upkeepCode.setCrashBook(item.getCost().toString());
                upkeepCode.setUpkeepCode(item.getBusinessCode());
                upkeepCode.setUpkeepContent(item.getBusinessName());
                upkeepCode.setCount(item.getAmount());
//                upkeepCode.setMaterialUnit(item.getUnit());
                return upkeepCode;
            }).collect(Collectors.toSet());
            ledgerBo.setUpkeepCodeList(upkeepCodeList);
            ledgerBo.setUpkeepCodeList(upkeepCodeList);
        }


        //配件代码
        List<VhBusinessInfo> partsBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                PARTS_CODE.ordinal()));
        Set<VhPartsCode> partsCodeList = partsBisList.stream().map(item -> {
            VhPartsCode partsCode = new VhPartsCode();
            BeanUtils.copyProperties(item, partsCode);
            partsCode.setMaterialCode(item.getBusinessCode());
            partsCode.setMaterialName(item.getBusinessName());
            partsCode.setAmount(item.getCost().toString());
            partsCode.setCount(item.getAmount());
            partsCode.setMaterialUnit(item.getUnit());
            return partsCode;
        }).collect(Collectors.toSet());

        //故障代码
        List<VhBusinessInfo> faultBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(ledgerMain.getBusinessNo(),
                FAULT_CODE.ordinal()));
        Set<VhFaultCode> faultCodeList = faultBisList.stream().map(item -> {
            VhFaultCode faultCode = new VhFaultCode();
            faultCode.setFaultCode(item.getBusinessCode());
            faultCode.setCodeExplain(item.getBusinessName());
            return faultCode;
        }).collect(Collectors.toSet());

        ledgerBo.setVehicleInfo(vehicleInfo);

        ledgerBo.setPartsCodeList(partsCodeList);
        ledgerBo.setFaultCodeList(faultCodeList);
        return ledgerBo;
    }


    /**
     * 新增台账主表
     *
     * @param vhLedgerMain 台账主表
     * @return 结果
     */
    @Override
    public int insertVhLedgerMain(VhLedgerMain vhLedgerMain) {
        vhLedgerMain.setCreateTime(DateUtils.getNowDate());
        return vhLedgerMainMapper.insertVhLedgerMain(vhLedgerMain);
    }

    /**
     * 修改台账主表
     *
     * @param vhLedgerMain 台账主表
     * @return 结果
     */
    @Override
    public int updateVhLedgerMain(VhLedgerMain vhLedgerMain) {
        vhLedgerMain.setUpdateTime(DateUtils.getNowDate());
        return vhLedgerMainMapper.updateVhLedgerMain(vhLedgerMain);
    }

    /**
     * 批量删除台账主表
     *
     * @param ids 需要删除的台账主表ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteVhLedgerMainByIds(Long[] ids) {
        try {
            for (Long id : ids) {
                VhLedgerMain vhLedgerMain = vhLedgerMainMapper.selectVhLedgerMainById(id);
                if (vhLedgerMain != null) {
                    businessInfoMapper.deleteVhBusinessInfo(vhLedgerMain.getBusinessNo(), null);
                }
            }
            return vhLedgerMainMapper.deleteVhLedgerMainByIds(ids);
        } catch (Exception e) {
            throw new RuntimeException("数据删除异常！");
        }
    }

    /**
     * 删除台账主表信息
     *
     * @param id 台账主表ID
     * @return 结果
     */
    @Override
    public int deleteVhLedgerMainById(Long id) {
        return vhLedgerMainMapper.deleteVhLedgerMainById(id);
    }

    @Override
    public String genTyreNo(Integer ledgerType, String prefix, String deptName) {
        return vhLedgerMainMapper.genTyreNo(ledgerType, prefix, deptName);
    }
    /**
     * 选择台账主表列表
     *
     *
     * @return 结果
     */
    @Override
    public List<VhLedgerMain> selectLedgerMainList(VhLedgerMain ledgerMain, Integer bisType, Integer mainTainType) {
        ledgerMain.setLedgerType(bisType);//参数组合，将第二个参数作为补充设置到第一个参数中
        List<VhLedgerMain> ledgerMains = ledgerMainMapper.selectVhLedgerMainList(ledgerMain, ledgerMain.getVehicleInfo());
        if (CollectionUtils.isEmpty(ledgerMains)) {
            return null;
        }//非空判断
        for (VhLedgerMain main : ledgerMains) {
            VhVehicleInfo param = new VhVehicleInfo();
            param.setVehicleCode(main.getVehicleCode());
            param.setMaintainDept(main.getMaintainDept());

            //通过主表中的车辆编码和部门获得车辆信息
            VhVehicleInfo vehicleInfo = vehicleInfoMapper.selectVhVehicleInfo(param);
            //为了和以前业务兼容
            List<VhBusinessInfo> bisVehicle = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(main.getBusinessNo(),
                    VEHICLE.ordinal(), ledgerMain.getDelFlag()));
            if (vehicleInfo == null) {
                vehicleInfo = new VhVehicleInfo();
            }
            if (!CollectionUtils.isEmpty(bisVehicle)) {
                BeanUtils.copyProperties(bisVehicle.get(0), vehicleInfo);
                vehicleInfo.setVehicleCode(bisVehicle.get(0).getBusinessCode());
                vehicleInfo.setVehicleType(bisVehicle.get(0).getBusinessName());
            }

            main.setVehicleInfo(vehicleInfo);

            //维修信息
            List<VhBusinessInfo> codeList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(main.getBusinessNo(),
                    mainTainType, ledgerMain.getDelFlag()));
            String codeStrs = codeList.stream()
                    .map(VhBusinessInfo::getBusinessCode)
                    .collect(Collectors.joining("|"));
            main.setMaintainItem(codeStrs);

            //配件信息
            List<VhBusinessInfo> partCodeList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(main.getBusinessNo(),
                    PARTS_CODE.ordinal(), ledgerMain.getDelFlag()));
            String partsCodeStrs = partCodeList.stream()
                    .map(VhBusinessInfo::getBusinessCode)
                    .collect(Collectors.joining("|"));
            main.setPartsInfo(partsCodeStrs);

            //故障码
            List<VhBusinessInfo> faultCodeList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(main.getBusinessNo(),
                    FAULT_CODE.ordinal(), ledgerMain.getDelFlag()));
            String faultCodeStrs = faultCodeList.stream()
                    .map(VhBusinessInfo::getBusinessCode)
                    .collect(Collectors.joining("|"));
            main.setFaultCode(faultCodeStrs);
        }
        return ledgerMains;
    }

    @Transactional
    @Override
    public AjaxResult addVhLedgerMain(CommonLedgerBo ledgerBo, Integer bisType, Integer mainTainType) {
        try {
            VhLedgerMain ledgerMain = ledgerMainMapper.selectVhLedgerMainByNo(ledgerBo.getBusinessNo());
            if (ledgerMain != null) {
                return AjaxResult.error("该单号已存在！请刷新单号");
            }
            ledgerMain = new VhLedgerMain();

            AjaxResult res = this.addBusinessInfo(ledgerBo, bisType, mainTainType);
            if (!res.get(AjaxResult.CODE_TAG).equals(HttpStatus.SUCCESS)) {
                log.error("子表数据写入失败！单号 {}", ledgerBo.getBusinessNo());
                throw new RuntimeException("子表数据写入失败！");
            }
            Map<String, BigDecimal> result = (HashMap) res.get(AjaxResult.DATA_TAG);

            BeanUtils.copyProperties(ledgerBo, ledgerMain);
            ledgerMain.setLedgerType(bisType);
            ledgerMain.setVehicleCode(ledgerBo.getVehicleInfo().getVehicleCode());
            ledgerMain.setTotalCrashBook(result.get("totalCrashBook"));
            ledgerMain.setTotalPartsAmount(result.get("totalPartsAmount"));
            ledgerMain.setTotalAmount(result.get("totalCrashBook").add(result.get("totalPartsAmount")));
            ledgerMain.setNiCompany(ledgerBo.getNiCompany());

            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            ledgerMain.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            ledgerMain.setMaintainDept(sysUser.getDept().getDeptName());
            ledgerMain.setUserDept(ledgerBo.getVehicleInfo().getUserDept());

            List<SysDictData> status = dictDataService.selectDictDataByType("order_status");
            ledgerMain.setOrderStatus(status.get(0).getDictValue());

            ledgerMainMapper.insertVhLedgerMain(ledgerMain);

            return AjaxResult.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getCause().getMessage());
            e.printStackTrace();
            throw new RuntimeException("轮胎台账: { " + ledgerBo.getBusinessNo() + " } 数据操作失败！", e.getCause());
        }
    }

    @Transactional
    @Override
    public AjaxResult addBusinessInfo(CommonLedgerBo ledgerBo, Integer bisType, Integer mainTainType) {
        try {
            VhBusinessInfo businessInfo;
            //工时费总计
            BigDecimal totalCrashBook = new BigDecimal(0);
            //配件费总计
            BigDecimal totalPartsAmount = new BigDecimal(0);

            // 车辆信息
            if (ledgerBo.getVehicleInfo() == null) {
                return AjaxResult.error("车辆信息不能为空！");
            }
            businessInfo = new VhBusinessInfo();
            businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
            businessInfo.setBusinessCode(ledgerBo.getVehicleInfo().getVehicleCode());
            businessInfo.setBusinessName(ledgerBo.getVehicleInfo().getVehicleType());
            businessInfo.setLedgerType(bisType);
            businessInfo.setBusinessType(VEHICLE.ordinal());
            businessInfo.setVehicleModelNum(ledgerBo.getVehicleInfo().getVehicleModelNum());
            businessInfo.setUserDept(ledgerBo.getVehicleInfo().getUserDept());
            businessInfo.setVehicleOwner(ledgerBo.getVehicleInfo().getVehicleOwner());
            businessInfo.setPhone(ledgerBo.getVehicleInfo().getPhone());
            businessInfo.setBelongDept(ledgerBo.getVehicleInfo().getBelongDept());
            businessInfo.setBelongCompany(ledgerBo.getVehicleInfo().getBelongCompany());
            businessInfo.setVehicleBrands(ledgerBo.getVehicleInfo().getVehicleBrands());
            businessInfo.setVehicleModelNum2(ledgerBo.getVehicleInfo().getVehicleModelNum2());
            businessInfo.setVehicleAge(ledgerBo.getVehicleInfo().getVehicleAge());
            businessInfo.setEntryFactoryDate(ledgerBo.getVehicleInfo().getEntryFactoryDate());
            businessInfo.setLeaveFactoryDate(ledgerBo.getVehicleInfo().getLeaveFactoryDate());


            int row = businessInfoMapper.insertVhBusinessInfo(businessInfo);


            if (bisType.equals(TYRE.ordinal())) {
                if (CollectionUtils.isEmpty(ledgerBo.getTyreCodeList())) {
                    throw new RuntimeException("轮胎代码不能为空！");
                }
                for (VhTyreCode item : ledgerBo.getTyreCodeList()) {
                    businessInfo = new VhBusinessInfo();
                    businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                    businessInfo.setBusinessCode(item.getTyreCode());
                    businessInfo.setBusinessName(item.getTyreItem());
                    businessInfo.setAmount(item.getCount());
                    businessInfo.setMaintainHour(item.getMaintainHour());
                    businessInfo.setCost(new BigDecimal(item.getCrashBook()));
                    businessInfo.setTotalCost(new BigDecimal(item.getCrashBook())
                            .multiply(item.getCount()));
                    businessInfo.setLedgerType(bisType);
                    businessInfo.setBusinessType(mainTainType);
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                    totalCrashBook = totalCrashBook.add(businessInfo.getTotalCost());
                }
            }
            if (bisType.equals(UPKEEP.ordinal())) {
                if (CollectionUtils.isEmpty(ledgerBo.getUpkeepCodeList())) {
                    throw new RuntimeException("维修代码不能为空！");
                }
                for (VhUpkeepCode item : ledgerBo.getUpkeepCodeList()) {
                    businessInfo = new VhBusinessInfo();
                    businessInfo.setUpkeepType(item.getUpkeepType());
                    businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                    businessInfo.setBusinessCode(item.getUpkeepCode());
                    businessInfo.setBusinessName(item.getUpkeepContent());
                    businessInfo.setAmount(item.getCount());
                    businessInfo.setMaintainHour(item.getMaintainHour());

                    businessInfo.setCost(new BigDecimal(item.getCrashBook()));
                    businessInfo.setTotalCost(new BigDecimal(item.getCrashBook())
                            .multiply(item.getCount()));
                    businessInfo.setLedgerType(bisType);
                    businessInfo.setBusinessType(mainTainType);
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                    totalCrashBook = totalCrashBook.add(businessInfo.getTotalCost());
                }
            }
            if (bisType.equals(MAINTAIN.ordinal())) {
                if (CollectionUtils.isEmpty(ledgerBo.getMaintainCodeList())) {
                    throw new RuntimeException("保养代码不能为空！");
                }
                for (VhMaintainCode item : ledgerBo.getMaintainCodeList()) {
                    businessInfo = new VhBusinessInfo();
                    businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                    businessInfo.setBusinessCode(item.getMaintainCode());
                    businessInfo.setBusinessName(item.getMaintainItem());
                    businessInfo.setAmount(item.getCount());
                    businessInfo.setMaintainHour(item.getMaintainHour());
                    businessInfo.setCost(new BigDecimal(item.getCrashBook()));
                    businessInfo.setTotalCost(new BigDecimal(item.getCrashBook())
                            .multiply(item.getCount()));
                    businessInfo.setLedgerType(bisType);
                    businessInfo.setBusinessType(mainTainType);
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                    totalCrashBook = totalCrashBook.add(businessInfo.getTotalCost());
                }
            }

            if (!CollectionUtils.isEmpty(ledgerBo.getPartsCodeList())) {
                for (VhPartsCode item : ledgerBo.getPartsCodeList()) {
                    businessInfo = new VhBusinessInfo();
                    businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                    businessInfo.setBusinessCode(item.getMaterialCode());
                    businessInfo.setBusinessName(item.getMaterialName());
                    businessInfo.setAmount(item.getCount());
                    businessInfo.setCost(new BigDecimal(item.getAmount()));
                    businessInfo.setTotalCost(new BigDecimal(item.getAmount())
                            .multiply(item.getCount()));
                    businessInfo.setLedgerType(bisType);
                    businessInfo.setBusinessType(PARTS_CODE.ordinal());
                    businessInfo.setUnit(item.getMaterialUnit());
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                    totalPartsAmount = totalPartsAmount.add(businessInfo.getTotalCost());
                }
            }
            //故障代码
            if (!CollectionUtils.isEmpty(ledgerBo.getFaultCodeList())) {
                for (VhFaultCode faultCode : ledgerBo.getFaultCodeList()) {
                    businessInfo = new VhBusinessInfo();
                    businessInfo.setBusinessNo(ledgerBo.getBusinessNo());
                    businessInfo.setBusinessCode(faultCode.getFaultCode());
                    businessInfo.setBusinessName(faultCode.getCodeExplain());
                    businessInfo.setLedgerType(bisType);
                    businessInfo.setBusinessType(FAULT_CODE.ordinal());
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                }
            }
            Map<String, BigDecimal> map = new HashMap<>();
            map.put("totalCrashBook", totalCrashBook);
            map.put("totalPartsAmount", totalPartsAmount);
            if (row > 0) {
                return AjaxResult.success("添加成功", map);
            }
            return AjaxResult.error("", map);
        } catch (Exception e) {
            log.error("轮胎台账添加失败{}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("轮胎台账: { " + ledgerBo.getBusinessNo() + " } 添加失败！", e.getCause());
        }
    }

    @Transactional
    @Override
    public AjaxResult modifyVhLedgerMain(CommonLedgerBo ledgerBo, Integer bisType, Integer mainTainType) {
        try {
            VhLedgerMain ledgerMain = ledgerMainMapper.selectVhLedgerMainByNo(ledgerBo.getBusinessNo());
            if (ledgerMain == null) {
                return AjaxResult.error("数据查询失败");
            }
            businessInfoMapper.deleteVhBusinessInfo(ledgerBo.getBusinessNo(), null);

            AjaxResult res = this.addBusinessInfo(ledgerBo, bisType, mainTainType);
            if (!res.get(AjaxResult.CODE_TAG).equals(HttpStatus.SUCCESS)) {
                log.error("子表数据写入失败！单号 {}", ledgerBo.getBusinessNo());
                throw new RuntimeException("子表数据写入失败！");
            }
            Map<String, BigDecimal> result = (HashMap) res.get(AjaxResult.DATA_TAG);

            BeanUtils.copyProperties(ledgerBo, ledgerMain);
            ledgerMain.setUserDept(ledgerBo.getVehicleInfo().getUserDept());
            ledgerMain.setVehicleCode(ledgerBo.getVehicleInfo().getVehicleCode());
            ledgerMain.setTotalCrashBook(result.get("totalCrashBook"));
            ledgerMain.setTotalPartsAmount(result.get("totalPartsAmount"));
            ledgerMain.setTotalAmount(result.get("totalCrashBook").add(result.get("totalPartsAmount")));
            if (DicConstants.COMPLETE_STATUS.COMPLETE.equals(ledgerBo.getCompleteStatus())) {
                List<SysDictData> status = dictDataService.selectDictDataByType("order_status");
                ledgerMain.setOrderStatus(status.get(status.size() - 1).getDictValue());
            }
            ledgerMain.setNiCompany(ledgerBo.getNiCompany());
            ledgerMainMapper.updateVhLedgerMain(ledgerMain);

            return AjaxResult.success("修改成功");
        } catch (Exception e) {
            log.error("轮胎台账修改异常{}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("轮胎台账 { " + ledgerBo.getBusinessNo() + " } 修改异常！", e.getCause());
        }
    }

    @Override
    public List<VhFaultCode> getFaultCode(List<String> code) {
        if (!CollectionUtils.isEmpty(code)) {
            List<String> list = code.stream().map(item -> item.split("-")[0]).collect(Collectors.toList());
            return vhMaintainCodeMapper.getFaultCode(list);
        }
        return null;
    }

    @Override
    public CommonLedgerBo getBaseCodeInfoByBisNo(String businessNo, String delFlag) {
        CommonLedgerBo ledgerBo = new CommonLedgerBo();
        //轮胎代码
        List<VhBusinessInfo> tyreBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(businessNo,
                TYRE_CODE.ordinal(), delFlag));
        Set<VhTyreCode> tyreCodeList;
        if (!CollectionUtils.isEmpty(tyreBisList)) {
            tyreCodeList = tyreBisList.stream().map(item -> {
                VhTyreCode tyreCode = new VhTyreCode();
                BeanUtils.copyProperties(item, tyreCode);
                tyreCode.setCrashBook(item.getTotalCost().toString());
                tyreCode.setTyreCode(item.getBusinessCode());
                tyreCode.setTyreItem(item.getBusinessName());
                tyreCode.setCount(item.getAmount());
                return tyreCode;
            }).collect(Collectors.toSet());
            ledgerBo.setTyreCodeList(tyreCodeList);
        }

        //维修代码
        List<VhBusinessInfo> mainTainBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(businessNo,
                MAINTAIN_CODE.ordinal(), delFlag));
        Set<VhMaintainCode> mtCodeList;
        if (!CollectionUtils.isEmpty(mainTainBisList)) {
            mtCodeList = mainTainBisList.stream().map(item -> {
                VhMaintainCode mtCode = new VhMaintainCode();
                BeanUtils.copyProperties(item, mtCode);
                mtCode.setCrashBook(item.getTotalCost().toString());
                mtCode.setMaintainCode(item.getBusinessCode());
                mtCode.setMaintainItem(item.getBusinessName());
                mtCode.setCount(item.getAmount());
                return mtCode;
            }).collect(Collectors.toSet());
            ledgerBo.setMaintainCodeList(mtCodeList);
        }

        //保养代码
        List<VhBusinessInfo> upKeepBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(businessNo,
                UPKEEP_CODE.ordinal(), delFlag));
        Set<VhUpkeepCode> upkeepCodeList;
        if (!CollectionUtils.isEmpty(upKeepBisList)) {
            upkeepCodeList = upKeepBisList.stream().map(item -> {
                VhUpkeepCode upkeepCode = new VhUpkeepCode();
                BeanUtils.copyProperties(item, upkeepCode);
                upkeepCode.setCrashBook(item.getTotalCost().toString());
                upkeepCode.setUpkeepCode(item.getBusinessCode());
                upkeepCode.setUpkeepContent(item.getBusinessName());
                upkeepCode.setCount(item.getAmount());
                return upkeepCode;
            }).collect(Collectors.toSet());
            ledgerBo.setUpkeepCodeList(upkeepCodeList);
        }

        //配件代码
        List<VhBusinessInfo> partsBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(businessNo,
                PARTS_CODE.ordinal(), delFlag));
        Set<VhPartsCode> partsCodeList = partsBisList.stream().map(item -> {
            VhPartsCode partsCode = new VhPartsCode();
            BeanUtils.copyProperties(item, partsCode);
            partsCode.setMaterialCode(item.getBusinessCode());
            partsCode.setMaterialName(item.getBusinessName());
            partsCode.setAmount(item.getTotalCost().toString());
            partsCode.setCount(item.getAmount());
            partsCode.setMaterialUnit(item.getUnit());
            return partsCode;
        }).collect(Collectors.toSet());

        //故障代码
        List<VhBusinessInfo> faultBisList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(businessNo,
                FAULT_CODE.ordinal(), delFlag));
        Set<VhFaultCode> faultCodeList = faultBisList.stream().map(item -> {
            VhFaultCode faultCode = new VhFaultCode();
            faultCode.setFaultCode(item.getBusinessCode());
            faultCode.setCodeExplain(item.getBusinessName());
            return faultCode;
        }).collect(Collectors.toSet());

        ledgerBo.setPartsCodeList(partsCodeList);
        ledgerBo.setFaultCodeList(faultCodeList);
        return ledgerBo;
    }

    @Override
    @Transactional
    public int invalidRecord(String[] businessNos) {
        if (StringUtils.isEmpty(businessNos)) {
            return 0;
        }
        try {
            for (String businessNo : businessNos) {
                VhBusinessInfo businessInfo = new VhBusinessInfo();
                businessInfo.setBusinessNo(businessNo);
                businessInfo.setDelFlag(UserConstants.DELECT);
                businessInfoMapper.updateVhBusinessInfo(businessInfo);

                VhLedgerMain main = new VhLedgerMain();
                main.setBusinessNo(businessNo);
                main.setDelFlag(UserConstants.DELECT);
                main.setDelDate(new Date());
                ledgerMainMapper.updateVhLedgerMain(main);
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("数据作废异常！");
        }
    }

    @Override
    public int validRecord(String[] businessNos) {
        if (StringUtils.isEmpty(businessNos)) {
            return 0;
        }
        try {
            for (String businessNo : businessNos) {
                VhBusinessInfo businessInfo = new VhBusinessInfo();
                businessInfo.setBusinessNo(businessNo);
                businessInfo.setDelFlag(UserConstants.UN_DELECT);
                businessInfoMapper.updateVhBusinessInfo(businessInfo);

                VhLedgerMain main = new VhLedgerMain();
                main.setBusinessNo(businessNo);
                main.setDelFlag(UserConstants.UN_DELECT);
                ledgerMainMapper.updateVhLedgerMain(main);
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("数据恢复异常！");
        }
    }

    @Override
    public int genLossNo(String businessNo, Integer ledgerType) {
        String lossNo = ledgerMainMapper.genLossNo(ledgerType);
        VhLedgerMain main = ledgerMainMapper.selectVhLedgerMainByNo(businessNo);
        if (StringUtils.isEmpty(main.getLossNo())) {
            main.setLossNo(lossNo);
            return ledgerMainMapper.updateVhLedgerMain(main);
        }
        return 1;
    }

    @Override
    public AjaxResult checkRepeat(String vehicleCode, String maintainCodes, Integer ledgerType, Integer businessType) {
        VhLedgerMain main = new VhLedgerMain();
        main.setVehicleCode(vehicleCode);
        main.setLedgerType(ledgerType);
        main.setMaintainDate(DateUtils.localDate2Date(LocalDate.now()));
        main.setMaintainDept(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());

        String[] codes = maintainCodes.split(",");
        int num = ledgerMainMapper.selectVhLedgerMainListNoDate(main, businessType, codes);
        if (num > 0) {
            return AjaxResult.success(vehicleCode + " Perbaikan Kembali", true);
        }
        return AjaxResult.success(false);
    }

    @Override
    public boolean checkRepeatNo(String businessNo) {
        VhLedgerMain ledgerMain = ledgerMainMapper.selectVhLedgerMainByNo(businessNo);
        if (ledgerMain != null) {
            return true;
        }
        return false;
    }

    @Override
    public int updateStatus(String businessNo, String orderStatus, String statusRemark) {
        return ledgerMainMapper.updateStatus(businessNo, orderStatus, statusRemark);
    }

    @Override
    public Boolean hasUnFinshedNo(String vehicleCode, String deptName) {

        VhLedgerMain main = new VhLedgerMain();
        main.setVehicleCode(vehicleCode);
        main.setMaintainDept(deptName);
        main.setCompleteStatus(DicConstants.COMPLETE_STATUS.UN_COMPLETE);

        int i = ledgerMainMapper.selectVhLedgerCount(main);
        return i > 0 ? true : false;
    }


}
