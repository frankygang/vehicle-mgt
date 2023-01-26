package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.manager.AsyncManager;
import com.vehicle.framework.manager.factory.AsyncFactory;
import com.vehicle.framework.security.LoginUser;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysDept;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhCommonPartsImport;
import com.vehicle.project.vehicle.service.IVhCommonPartsImportService;
import com.vehicle.project.vehicle.service.IVhLedgerMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用配件信息导入Controller
 *
 * @author onion
 * @date 2020-02-24
 */
@RestController
@RequestMapping("/vehicle/commonPartsImport")
public class VhCommonPartsImportController extends BaseController {

    @Autowired
    private IVhCommonPartsImportService vhCommonPartsImportService;

    @Autowired
    private IVhLedgerMainService ledgerMainService;

    /**
     * 查询通用配件信息导入列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:commonPartsImport:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhCommonPartsImport vhCommonPartsImport) {
        startPage();
        List<VhCommonPartsImport> list = vhCommonPartsImportService.selectVhCommonPartsImportList(vhCommonPartsImport);
        return getDataTable(list);
    }

    /**
     * 导出通用配件信息导入列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:commonPartsImport:export')")
    @Log(title = "通用配件信息导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhCommonPartsImport vhCommonPartsImport) {
        List<VhCommonPartsImport> list = vhCommonPartsImportService.selectVhCommonPartsImportList(vhCommonPartsImport);
        ExcelUtil<VhCommonPartsImport> util = new ExcelUtil<VhCommonPartsImport>(VhCommonPartsImport.class);
        return util.exportExcel(list, "VhCommonPartsImport");
    }


    /**
     * 删除通用配件信息导入
     */
    @PreAuthorize("@ss.hasPermi('vehicle:commonPartsImport:remove')")
    @Log(title = "通用配件信息删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhCommonPartsImportService.deleteVhCommonPartsImportByIds(ids));
    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhCommonPartsImport> util = new ExcelUtil<>(VhCommonPartsImport.class);
        return util.importTemplateExcel("通用物料代码数据导入模板");
    }

    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "通用配件代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:commonPartsImport:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<VhCommonPartsImport> util = new ExcelUtil<>(VhCommonPartsImport.class);
        try {
            List<VhCommonPartsImport> list = util.importExcel(file.getInputStream());
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            AsyncManager.me().execute(AsyncFactory.excelImport(list, updateSupport, sysUser));
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("模板异常，请用系统模板！");
        }
    }

    /**
     * 清空表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:commonPartsImport:clean')")
    @Log(title = "通用配件代码清空", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhCommonPartsImportService.cleanAll(sysUser.getDept().getDeptName());
        return AjaxResult.success();
    }

}
