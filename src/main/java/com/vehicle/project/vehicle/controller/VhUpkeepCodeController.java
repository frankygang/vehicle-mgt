package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhUpkeepCode;
import com.vehicle.project.vehicle.service.IVhUpkeepCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 保养代码Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/upkeepCode")
public class VhUpkeepCodeController extends BaseController {
    @Autowired
    private IVhUpkeepCodeService vhUpkeepCodeService;

    /**
     * 查询保养代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhUpkeepCode vhUpkeepCode) {
        startPage();
        List<VhUpkeepCode> list = vhUpkeepCodeService.selectVhUpkeepCodeList(vhUpkeepCode);
        return getDataTable(list);
    }

    /**
     * 导出保养代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:export')")
    @Log(title = "保养代码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhUpkeepCode vhUpkeepCode) {
        List<VhUpkeepCode> list = vhUpkeepCodeService.selectVhUpkeepCodeList(vhUpkeepCode);
        ExcelUtil<VhUpkeepCode> util = new ExcelUtil<VhUpkeepCode>(VhUpkeepCode.class);
        return util.exportExcel(list, "保养代码数据");
    }

    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:export')")
    @Log(title = "保养代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids) {
        List<VhUpkeepCode> list = vhUpkeepCodeService.selectVhUpkeepCodeByIds(ids);
        ExcelUtil<VhUpkeepCode> util = new ExcelUtil<VhUpkeepCode>(VhUpkeepCode.class);
        return util.exportExcel(list, "保养代码数据");
    }


    /**
     * 获取保养代码详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhUpkeepCodeService.selectVhUpkeepCodeById(id));
    }

    /**
     * 新增保养代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:add')")
    @Log(title = "保养代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhUpkeepCode vhUpkeepCode) {

        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhUpkeepCode.setMaintainDept(sysUser.getDept().getDeptName());
        vhUpkeepCode.setUserId(sysUser.getUserId());

        VhUpkeepCode upkeepCode = vhUpkeepCodeService.selectVhUpkeepCode(new VhUpkeepCode(vhUpkeepCode.getUpkeepCode(), sysUser.getDept().getDeptName()));
        if (upkeepCode != null) {
            return AjaxResult.error("保养代码已存在！添加失败！");
        }

        return toAjax(vhUpkeepCodeService.insertVhUpkeepCode(vhUpkeepCode));
    }

    /**
     * 修改保养代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:edit')")
    @Log(title = "保养代码", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhUpkeepCode vhUpkeepCode) {
        return toAjax(vhUpkeepCodeService.updateVhUpkeepCode(vhUpkeepCode));
    }

    /**
     * 删除保养代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:remove')")
    @Log(title = "保养代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhUpkeepCodeService.deleteVhUpkeepCodeByIds(ids));
    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhUpkeepCode> util = new ExcelUtil<VhUpkeepCode>(VhUpkeepCode.class);
        return util.importTemplateExcel("保养代码数据导入模板");
    }

    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "保养代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:upkeepCode:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<VhUpkeepCode> util = new ExcelUtil<VhUpkeepCode>(VhUpkeepCode.class);
        List<VhUpkeepCode> vhUpkeepCodeList = util.importExcel(file.getInputStream());
        String message = vhUpkeepCodeService.importVhUpKeepCode(vhUpkeepCodeList, updateSupport, SecurityUtils.getUsername());
        logger.info(message);
        return AjaxResult.success(message);
    }
}
