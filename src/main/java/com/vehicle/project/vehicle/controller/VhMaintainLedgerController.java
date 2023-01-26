package com.vehicle.project.vehicle.controller;

import com.vehicle.common.constant.Constants;
import com.vehicle.common.constant.UserConstants;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.interceptor.annotation.RepeatSubmit;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.*;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维修台账Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@RestController
@RequestMapping("/vehicle/maintainLedger")
public class VhMaintainLedgerController extends BaseController {
    @Autowired
    private IVhMaintainLedgerService vhMaintainLedgerService;

    @Autowired
    private IVhLedgerMainService ledgerMainService;

    @Autowired
    private IVhVehicleInfoService vehicleInfoService;

    @Autowired
    private IVhPartsCodeService vhPartsCodeService;

    @Autowired
    private IVhVehicleInfoService vhVehicleInfoService;

    @Autowired
    private IVhUpkeepCodeService vhUpkeepCodeService;

    @Autowired
    private IVhTyreCodeService vhTyreCodeService;

    @Autowired
    private IVhMaintainCodeService vhMaintainCodeService;

    @Autowired
    private IVhFaultCodeService vhFaultCodeService;

    /**
     * 查询维修台账列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody VhLedgerMain vhLedgerMain) {
        startPage(vhLedgerMain.getPageNum(), vhLedgerMain.getPageSize());
        vhLedgerMain.setTimeTypeStr(UserConstants.MAINTAIN_DATE);
        List<VhLedgerMain> list = ledgerMainService.selectLedgerMainList(vhLedgerMain,
                BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
        return getDataTable(list);
    }

    /**
     * 导出维修台账列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:export')")
    @Log(title = "维修台账", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhLedgerMain vhLedgerMain) {
//        List<VhLedgerMain> list = ledgerMainService.selectLedgerMainList(vhLedgerMain, BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
//        ExcelUtil<VhLedgerMain> util = new ExcelUtil<>(VhLedgerMain.class);
//        List<String> excludeColumn = new ArrayList<>();
//        excludeColumn.add("maintainDept");
//        excludeColumn.add("maintainShift");
//        excludeColumn.add("belongDept");
//        excludeColumn.add("nextUpkeepDate");
//        return util.exportExcel(list, "VhMaintainLedger", excludeColumn);
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("deptId", sysUser.getDept().getDeptId());
        map.put("roleId", sysUser.getRoles().get(0).getRoleId());
        map.put("scope", sysUser.getRoles().get(0).getDataScope());
        return AjaxResult.success(map);

    }

    /**
     * 获取维修台账详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        CommonLedgerBo commonLedgerBo = ledgerMainService.selectVhLedgerMainById(id);
        return commonLedgerBo == null ? AjaxResult.error("数据获取异常！") : AjaxResult.success("获取成功！", commonLedgerBo);
    }

    /**
     * 新增维修台账
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:add')")
    @Log(title = "维修台账", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @RepeatSubmit
    public AjaxResult add(@RequestBody CommonLedgerBo commonLedgerBo) {
        try {
            //判断该车辆或车辆使用部门有无相关业务权限
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            VhVehicleInfo param = new VhVehicleInfo();
            param.setVehicleCode(commonLedgerBo.getVehicleInfo().getVehicleCode());
            param.setMaintainDept(sysUser.getDept().getDeptName());
            VhVehicleInfo vhVehicleInfo = vehicleInfoService.selectVhVehicleInfo(param);

//            Boolean o = ledgerMainService.hasUnFinshedNo(commonLedgerBo.getVehicleInfo().getVehicleCode(), sysUser.getDept().getDeptName());
//            if (o) {
//                if (Constants.DEPT_1.equals(sysUser.getDept().getDeptName())) {
//                    return AjaxResult.error("该车辆有单据未完成！");
//                }
//            }

            if ("".equals(vhVehicleInfo.getLedgerType())) {
                if (Constants.DEPT_1.equals(sysUser.getDept().getDeptName())) {
                    return AjaxResult.error("该车辆没有此项业务权限！");
                }
                return ledgerMainService.addVhLedgerMain(commonLedgerBo, BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
            } else {
                boolean res = StringUtils.inStringIgnoreCase(String.valueOf(BusinessType.MAINTAIN.ordinal()), vhVehicleInfo.getLedgerType().split("-"));
                if (res) {
                    return ledgerMainService.addVhLedgerMain(commonLedgerBo, BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
                }
                return AjaxResult.error("该车辆没有此项业务权限！");
            }
        } catch (Exception e) {
            log.error("新增轮胎台账｛ " + commonLedgerBo.getBusinessNo() + " ｝失败!", e.getMessage());
            return AjaxResult.error("添加异常");
        }
    }

    /**
     * 修改维修台账
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:edit')")
    @Log(title = "维修台账", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody CommonLedgerBo commonLedgerBo) {
        try {
            return ledgerMainService.modifyVhLedgerMain(commonLedgerBo, BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
        } catch (Exception e) {
            log.error("修改轮胎台账｛ " + commonLedgerBo.getBusinessNo() + " ｝失败", e.getMessage());
            return AjaxResult.error("修改失败！");
        }
    }

    /**
     * 删除维修台账
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:remove')")
    @Log(title = "维修台账", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(ledgerMainService.deleteVhLedgerMainByIds(ids));
    }

    /**
     * 功能描述:
     * 生成维修单号
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/10 15:16
     */
    @PostMapping("/genMaintainNo")
    public AjaxResult genMaintainNo() {
        String deptName = SecurityUtils.getLoginUser().getUser().getDept().getDeptName();
        String prefix = "";
        if (Constants.DEPT_1.equals(deptName)) {
            prefix = Constants.MAINTAIN_PREFIX;
        }
        if (Constants.DEPT_2.equals(deptName)) {
            prefix = Constants.MAINTAIN_2;
        }
        if (Constants.DEPT_3.equals(deptName)) {
            prefix = Constants.MAINTAIN_3;
        }
        if (Constants.DEPT_4.equals(deptName)) {
            prefix = Constants.MAINTAIN_4;
        }
        if (Constants.DEPT_5.equals(deptName)) {
            prefix = Constants.MAINTAIN_5;
        }
        if (Constants.DEPT_6.equals(deptName)) {
            prefix = Constants.MAINTAIN_6;
        }
        return AjaxResult.success("单号获取成功", ledgerMainService.genTyreNo(BusinessType.MAINTAIN.ordinal(), prefix, deptName));
    }

    /**
     * 功能描述:
     * 根据故障码获取故障类型码
     */
    @PostMapping("/getFaultCode")
    public AjaxResult getFaultCode(@RequestParam(value = "codes[]") List<String> code) {
        List<VhFaultCode> codeList = ledgerMainService.getFaultCode(code);
        return AjaxResult.success("获取信息成功", codeList);
    }

    /**
     * 功能描述:
     * 获取当前单号下的基础代码信息
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/18 14:47
     */
    @GetMapping("/codeInfos")
    public AjaxResult getBaseCodeInfos(@RequestParam("businessNo") String businessNo) {
        return AjaxResult.success(ledgerMainService.getBaseCodeInfoByBisNo(businessNo, UserConstants.UN_DELECT));
    }

    /**
     * 功能描述:
     * 单据作废
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2019/2/20 15:51
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:invalid')")
    @Log(title = "维修台账作废", businessType = BusinessType.DELETE)
    @PostMapping("/invalid")
    public AjaxResult invalidRecord(@RequestParam("businessNoS[]") String[] businessNos) {
        return toAjax(ledgerMainService.invalidRecord(businessNos));
    }

    /**
     * 功能描述:
     * 生成定损单号
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2019/2/28 15:25
     */
    @Log(title = "生成定损单", businessType = BusinessType.OTHER)
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:lossNo')")
    @PostMapping("/genLossNo")
    public AjaxResult genLossNo(@RequestParam("businessNo") String businessNo) {
        String deptName = SecurityUtils.getLoginUser().getUser().getDept().getDeptName();
        if (!Constants.DEPT_1.equals(deptName)) {
            return AjaxResult.success();
        }
        return toAjax(ledgerMainService.genLossNo(businessNo, BusinessType.MAINTAIN.ordinal()));
    }

    /**
     * 校验重复报修
     *
     * @param vehicleCode
     * @param ledgerType
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @PostMapping("/checkRepeat")
    public AjaxResult checkRepeat(@RequestParam("vehicleCode") String vehicleCode,
                                  @RequestParam("maintainCodes") String maintainCodes,
                                  @RequestParam("ledgerType") Integer ledgerType) {
        return ledgerMainService.checkRepeat(vehicleCode, maintainCodes, ledgerType, BusinessType.MAINTAIN_CODE.ordinal());
    }

    /**
     * 获取当前用户部门信息
     *
     * @return
     */
    @GetMapping("/dept")
    public AjaxResult currentDept() {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        if (sysUser != null) {
            return AjaxResult.success(sysUser.getDept());
        }
        return AjaxResult.error();
    }

    @GetMapping("/partsCodeList")
    public AjaxResult list(VhPartsCode vhPartsCode) {
        List<VhPartsCode> list = vhPartsCodeService.selectVhPartsCodeList(vhPartsCode);
        return AjaxResult.success(list);
    }

    @GetMapping("/vehicleInfoList")
    public AjaxResult list(VhVehicleInfo vhVehicleInfo) {
        List<VhVehicleInfo> list = vhVehicleInfoService.selectVhVehicleInfoList(vhVehicleInfo);
        return AjaxResult.success(list);
    }


    @GetMapping("/faultCodeList")
    public AjaxResult list(VhFaultCode vhFaultCode) {
        List<VhFaultCode> list = vhFaultCodeService.selectVhFaultCodeList(vhFaultCode);
        return AjaxResult.success(list);
    }

    @GetMapping("/maintainCodeList")
    public AjaxResult list(VhMaintainCode vhMaintainCode) {
        List<VhMaintainCode> list = vhMaintainCodeService.selectVhMaintainCodeList(vhMaintainCode);
        return AjaxResult.success(list);
    }

    @Log(title = "维修单", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('vehicle:maintainLedger:updateStatus')")
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestParam("businessNo") String businessNo,
                                   @RequestParam("orderStatus") String orderStatus,
                                   @RequestParam(name = "statusRemark", defaultValue = "") String statusRemark) {

        return toAjax(ledgerMainService.updateStatus(businessNo, orderStatus, statusRemark));
    }

}
