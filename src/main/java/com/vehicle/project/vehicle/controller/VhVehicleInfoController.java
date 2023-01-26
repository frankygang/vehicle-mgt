package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.manager.AsyncManager;
import com.vehicle.framework.manager.factory.AsyncFactory;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.service.IVhVehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 车辆信息Controller
 *
 * @author onion
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/vehicleInfo")
public class VhVehicleInfoController extends BaseController {
    @Autowired
    private IVhVehicleInfoService vhVehicleInfoService;

    /**
     * 查询车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhVehicleInfo vhVehicleInfo) {
        startPage();
        List<VhVehicleInfo> list = vhVehicleInfoService.selectVhVehicleInfoList(vhVehicleInfo);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:export')")
    @Log(title = "车辆信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhVehicleInfo vhVehicleInfo) {
        vhVehicleInfo.setId(Long.valueOf(1));
        List<VhVehicleInfo> list = vhVehicleInfoService.selectVhVehicleInfoList(vhVehicleInfo);
        ExcelUtil<VhVehicleInfo> util = new ExcelUtil<VhVehicleInfo>(VhVehicleInfo.class);
        return util.exportExcel(list, "车辆信息");
    }

    @Log(title = "故障代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids) {
        List<VhVehicleInfo> list = vhVehicleInfoService.selectVhVehicleInfoByIds(ids);
        ExcelUtil<VhVehicleInfo> util = new ExcelUtil<>(VhVehicleInfo.class);
        return util.exportExcel(list, "车辆信息");
    }

    /**
     * 获取车辆信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhVehicleInfoService.selectVhVehicleInfoById(id));
    }

    /**
     * 新增车辆信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:add')")
    @Log(title = "车辆信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhVehicleInfo vhVehicleInfo) {
        vhVehicleInfo.setLedgerType(StringUtils.strArrToString(vhVehicleInfo.getPermissions()));
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhVehicleInfo.setUserId(sysUser.getUserId());
        vhVehicleInfo.setMaintainDept(sysUser.getDept().getDeptName());

        VhVehicleInfo param = new VhVehicleInfo();
        param.setVehicleCode(vhVehicleInfo.getVehicleCode());
        param.setMaintainDept(sysUser.getDept().getDeptName());
        VhVehicleInfo vehicleInfo = vhVehicleInfoService.selectVhVehicleInfo(param);

        if (vehicleInfo != null) {
            return AjaxResult.error("车辆已存在！添加失败！");
        }

        return toAjax(vhVehicleInfoService.insertVhVehicleInfo(vhVehicleInfo));
    }

    /**
     * 修改车辆信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:edit')")
    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhVehicleInfo vhVehicleInfo) {

        vhVehicleInfo.setLedgerType(StringUtils.strArrToString(vhVehicleInfo.getPermissions()));
        return toAjax(vhVehicleInfoService.updateVhVehicleInfo(vhVehicleInfo));
    }

    /**
     * 删除车辆信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhVehicleInfoService.deleteVhVehicleInfoByIds(ids));
    }

    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "车辆信息", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        ExcelUtil<VhVehicleInfo> util = new ExcelUtil<>(VhVehicleInfo.class);
        try {
            List<VhVehicleInfo> list = util.importExcel(file.getInputStream());
            AsyncManager.me().execute(AsyncFactory.vehicleImport(list, sysUser));
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("模板异常，请用系统模板！");
        }

    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhVehicleInfo> util = new ExcelUtil<>(VhVehicleInfo.class);
        return util.importTemplateExcel("车辆信息数据导入模板");
    }

    /**
     * 清空表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicleInfo:clean')")
    @Log(title = "车辆信息清空", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhVehicleInfoService.cleanAll(sysUser.getDept().getDeptName());

        return AjaxResult.success();
    }

}
