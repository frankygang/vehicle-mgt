package com.vehicle.project.vehicle.service.impl;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.project.common.WebSocketServer;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhBusinessInfo;
import com.vehicle.project.vehicle.domain.VhCommonPartsImport;
import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.mapper.VhCommonPartsImportMapper;
import com.vehicle.project.vehicle.mapper.VhLedgerMainMapper;
import com.vehicle.project.vehicle.service.IVhCommonPartsImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 通用配件信息导入Service业务层处理
 *
 * @author onion
 * @date 2020-02-24
 */
@Slf4j
@Service
public class VhCommonPartsImportServiceImpl implements IVhCommonPartsImportService {
    @Autowired
    private VhCommonPartsImportMapper vhCommonPartsImportMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    @Autowired
    private VhLedgerMainMapper ledgerMainMapper;

    /**
     * 查询通用配件信息导入
     *
     * @param id 通用配件信息导入ID
     * @return 通用配件信息导入
     */
    @Override
    public VhCommonPartsImport selectVhCommonPartsImportById(Long id) {
        return vhCommonPartsImportMapper.selectVhCommonPartsImportById(id);
    }

    /**
     * 查询通用配件信息导入列表
     *
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 通用配件信息导入
     */
    @Override
    public List<VhCommonPartsImport> selectVhCommonPartsImportList(VhCommonPartsImport vhCommonPartsImport) {
        return vhCommonPartsImportMapper.selectVhCommonPartsImportList(vhCommonPartsImport);
    }

    /**
     * 新增通用配件信息导入
     *
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 结果
     */
    @Override
    public int insertVhCommonPartsImport(VhCommonPartsImport vhCommonPartsImport) {
        return vhCommonPartsImportMapper.insertVhCommonPartsImport(vhCommonPartsImport);
    }

    /**
     * 修改通用配件信息导入
     *
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 结果
     */
    @Override
    public int updateVhCommonPartsImport(VhCommonPartsImport vhCommonPartsImport) {
        return vhCommonPartsImportMapper.updateVhCommonPartsImport(vhCommonPartsImport);
    }

    /**
     * 批量删除通用配件信息导入
     *
     * @param ids 需要删除的通用配件信息导入ID
     * @return 结果
     */
    @Override
    public int deleteVhCommonPartsImportByIds(Long[] ids) {
        return vhCommonPartsImportMapper.deleteVhCommonPartsImportByIds(ids);
    }

    /**
     * 删除通用配件信息导入信息
     *
     * @param id 通用配件信息导入ID
     * @return 结果
     */
    @Override
    public int deleteVhCommonPartsImportById(Long id) {
        return vhCommonPartsImportMapper.deleteVhCommonPartsImportById(id);
    }

    /**
     * 导入
     *
     * @param list
     * @param isUpdateSupport
     * @return java.util.LinkedHashMap<java.lang.String, java.lang.Object>
     */
    @Transactional
    @Override
    public LinkedHashMap<String, Object> commonPartsImport(List<VhCommonPartsImport> list, boolean isUpdateSupport, SysUser sysUser) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入配件代码数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        //配件费总计
        BigDecimal totalPartsAmount = new BigDecimal(0);
        String msg = "";
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> itemMap;
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        try {
            int i = 0;
            for (VhCommonPartsImport obj : list) {
                i++;
                if (StringUtils.isEmpty(obj.getBusinessNo()) || StringUtils.isEmpty(obj.getMaterialCode())
                        || StringUtils.isEmpty(obj.getMaterialName()) || StringUtils.isEmpty(obj.getAmount())
                        || StringUtils.isEmpty(obj.getCost())) {
                    msg = "第" + i + "条数据不完整，请检查！";
                    itemMap = new HashMap<>();
                    itemMap.put("message", msg);
                    messages.add(itemMap);
                    failureNum++;
                    continue;
                }
                if (!StringUtils.isNumber(obj.getAmount()) || !StringUtils.isNumber(obj.getCost())) {
                    msg = "第" + i + "条数据 " + obj.getBusinessNo() + " 的数量或单价有问题，请检查！";
                    itemMap = new HashMap<>();
                    itemMap.put("message", msg);
                    messages.add(itemMap);
                    failureNum++;
                    continue;
                }

                VhLedgerMain main = ledgerMainMapper.selectVhLedgerMainByNo(obj.getBusinessNo());
                if (main == null) {
                    msg = "第" + i + "条数据 " + obj.getBusinessNo() + " 不存在！";
                    itemMap = new HashMap<>();
                    itemMap.put("message", msg);
                    messages.add(itemMap);
                    failureNum++;
                    continue;
                }
                if (main != null && !main.getMaintainDept().equals(sysUser.getDept().getDeptName())) {
                    msg = "第" + i + "条数据 " + obj.getBusinessNo() + " 非本部门数据，无操作权限！";
                    itemMap = new HashMap<>();
                    itemMap.put("message", msg);
                    messages.add(itemMap);
                    failureNum++;
                    continue;
                }

                // 同编码同单价 数量加  不同单价新加一条
                VhBusinessInfo bis = new VhBusinessInfo();
                bis.setBusinessNo(obj.getBusinessNo().trim());
                bis.setBusinessCode(obj.getMaterialCode().trim());
                bis.setCost(new BigDecimal(obj.getCost().trim()));
                List<VhBusinessInfo> infos = businessInfoMapper.selectVhBusinessInfoList(bis);

                if (!CollectionUtils.isEmpty(infos)) {
                    VhBusinessInfo info = infos.get(0);
                    info.setAmount(info.getAmount().add(new BigDecimal(obj.getAmount().trim())));

//                    BigDecimal totalCost = new BigDecimal(Integer.valueOf(obj.getAmount()))
//                            .multiply(new BigDecimal(obj.getCost()));
                    //数量可以是小数
                    BigDecimal totalCost = new BigDecimal(obj.getAmount())
                            .multiply(new BigDecimal(obj.getCost()));

                    info.setTotalCost(info.getTotalCost().add(totalCost));
                    businessInfoMapper.updateVhBusinessInfo(info);
                } else {
                    VhBusinessInfo businessInfo = mapBusinessInfo(obj, main.getLedgerType());
                    businessInfo.setAmount(new BigDecimal(obj.getAmount().trim()));
                    businessInfo.setCost(new BigDecimal(obj.getCost().trim()));
                    BigDecimal totalCost = new BigDecimal(obj.getAmount())
                            .multiply(new BigDecimal(obj.getCost()));
                    businessInfo.setTotalCost(totalCost);
                    businessInfoMapper.insertVhBusinessInfo(businessInfo);
                }

                obj.setCreateBy(sysUser.getUserName());
                obj.setMaintainDept(sysUser.getDept().getDeptName());
                obj.setUserId(sysUser.getUserId());
                vhCommonPartsImportMapper.insertVhCommonPartsImport(obj);

                //重新计算单号总金额
                List<VhBusinessInfo> infoList = businessInfoMapper.selectVhBusinessInfoList(new VhBusinessInfo(obj.getBusinessNo(), BusinessType.PARTS_CODE.ordinal()));
                //配件费总计
                for (VhBusinessInfo b : infoList) {
                    totalPartsAmount = totalPartsAmount.add(b.getTotalCost());
                }

                main.setTotalPartsAmount(totalPartsAmount);
                main.setTotalAmount(main.getTotalCrashBook().add(totalPartsAmount));
                ledgerMainMapper.updateVhLedgerMain(main);
                successNum++;
                totalPartsAmount = new BigDecimal(0);

            }
            if (failureNum == 0) {
                msg = "数据无误，全部导入成功！";
                itemMap = new HashMap<>();
                itemMap.put("message", msg);
                messages.add(itemMap);
            }
            map.put("sum", successNum + failureNum);
            map.put("success", successNum);
            map.put("fail", failureNum);
            map.put("messages", messages);
            if (failureNum > 0) {
                String fileName = "导入错误信息分析" + ".txt";
                map.put("fileName", fileName);
                sentTxtFile(JSON.toJSONString(map, true), fileName);
            }
            WebSocketServer.sendInfo(JSON.toJSONString(map, true), "commonPartsImport", sysUser.getUserName());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导入失败！", e);
        }

    }

    private void sentTxtFile(String context, String fileName) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(ExcelUtil.getAbsoluteFile(fileName));
            fw.write(context);
            fw.flush();
        } catch (Exception e) {
            log.error("导出错误信息分析异常{}", e.getMessage());
            throw new CustomException("导出错误信息分析失败，请联系网站管理员！");
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private VhBusinessInfo mapBusinessInfo(VhCommonPartsImport obj, Integer ledgerType) {
        VhBusinessInfo businessInfo = new VhBusinessInfo();
        businessInfo.setBusinessNo(obj.getBusinessNo());
        businessInfo.setBusinessCode(obj.getMaterialCode());
        businessInfo.setBusinessName(obj.getMaterialName());
        businessInfo.setLedgerType(ledgerType);
        businessInfo.setBusinessType(BusinessType.PARTS_CODE.ordinal());
        businessInfo.setUnit(obj.getMaterialUnit());
        return businessInfo;
    }

    @Override
    public void cleanAll(String deptName) {
        vhCommonPartsImportMapper.cleanAll(deptName);
    }
}
