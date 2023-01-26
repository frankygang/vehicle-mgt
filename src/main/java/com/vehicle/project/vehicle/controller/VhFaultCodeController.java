package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.domain.VhMaintainCode;
import com.vehicle.project.vehicle.service.IVhFaultCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 故障代码Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/faultCode")
public class VhFaultCodeController extends BaseController {
    @Autowired
    private IVhFaultCodeService vhFaultCodeService;

    /**
     * 查询故障代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhFaultCode vhFaultCode) {
        startPage();
        List<VhFaultCode> list = vhFaultCodeService.selectVhFaultCodeList(vhFaultCode);
        return getDataTable(list);
    }

    /**
     * 导出故障代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:export')")
    @Log(title = "故障代码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhFaultCode vhFaultCode) {
        List<VhFaultCode> list = vhFaultCodeService.selectVhFaultCodeList(vhFaultCode);
        ExcelUtil<VhFaultCode> util = new ExcelUtil<VhFaultCode>(VhFaultCode.class);
        return util.exportExcel(list, "故障代码");
    }

    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:export')")
    @Log(title = "故障代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids) {
        List<VhFaultCode> list = vhFaultCodeService.selectVhFaultCodeByIds(ids);
        ExcelUtil<VhFaultCode> util = new ExcelUtil<VhFaultCode>(VhFaultCode.class);
        return util.exportExcel(list, "故障代码数据");
    }

    /**
     * 获取故障代码详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhFaultCodeService.selectVhFaultCodeById(id));
    }

    /**
     * 新增故障代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:add')")
    @Log(title = "故障代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhFaultCode vhFaultCode) {

        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhFaultCode.setMaintainDept(sysUser.getDept().getDeptName());
        vhFaultCode.setUserId(sysUser.getUserId());

        VhFaultCode faultCode = vhFaultCodeService.selectVhMaintainCode(vhFaultCode);
        if (faultCode != null) {
            return AjaxResult.error("故障代码已存在！添加失败！");
        }

        return toAjax(vhFaultCodeService.insertVhFaultCode(vhFaultCode));
    }

    /**
     * 修改故障代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:edit')")
    @Log(title = "故障代码", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhFaultCode vhFaultCode) {
        return toAjax(vhFaultCodeService.updateVhFaultCode(vhFaultCode));
    }

    /**
     * 删除故障代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:remove')")
    @Log(title = "故障代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhFaultCodeService.deleteVhFaultCodeByIds(ids));
    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhFaultCode> util = new ExcelUtil<VhFaultCode>(VhFaultCode.class);
        return util.importTemplateExcel("故障代码数据导入模板");
    }

    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "故障代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:faultCode:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<VhFaultCode> util = new ExcelUtil<>(VhFaultCode.class);
        List<VhFaultCode> list = util.importExcel(file.getInputStream());
        String message = vhFaultCodeService.importVhFaultCode(list, updateSupport, SecurityUtils.getUsername());
        logger.info(message);
        return AjaxResult.success(message);
    }
}
