package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import com.vehicle.project.vehicle.domain.VhTyreCode;
import com.vehicle.project.vehicle.service.IVhTyreCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 轮胎代码Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/tyreCode")
public class VhTyreCodeController extends BaseController {
    @Autowired
    private IVhTyreCodeService vhTyreCodeService;

    /**
     * 查询轮胎代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhTyreCode vhTyreCode) {
        startPage();
        List<VhTyreCode> list = vhTyreCodeService.selectVhTyreCodeList(vhTyreCode);
        return getDataTable(list);
    }

    /**
     * 导出轮胎代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:export')")
    @Log(title = "轮胎代码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhTyreCode vhTyreCode) {
        List<VhTyreCode> list = vhTyreCodeService.selectVhTyreCodeList(vhTyreCode);
        ExcelUtil<VhTyreCode> util = new ExcelUtil<VhTyreCode>(VhTyreCode.class);
        return util.exportExcel(list, "轮胎代码数据");
    }

    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:export')")
    @Log(title = "轮胎代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids ) {
        List<VhTyreCode> list = vhTyreCodeService.selectVhTyreCodeByIds(ids);
        ExcelUtil<VhTyreCode> util = new ExcelUtil<VhTyreCode>(VhTyreCode.class);
        return util.exportExcel(list, "轮胎代码数据");
    }

    /**
     * 获取轮胎代码详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhTyreCodeService.selectVhTyreCodeById(id));
    }

    /**
     * 新增轮胎代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:add')")
    @Log(title = "轮胎代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhTyreCode vhTyreCode) {

        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhTyreCode.setMaintainDept(sysUser.getDept().getDeptName());
        vhTyreCode.setUserId(sysUser.getUserId());

        VhTyreCode tyreCode = vhTyreCodeService.selectVhTyreCode(vhTyreCode);
        if (tyreCode != null) {
            return AjaxResult.error("配件代码已存在！添加失败！");
        }

        return toAjax(vhTyreCodeService.insertVhTyreCode(vhTyreCode));
    }

    /**
     * 修改轮胎代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:edit')")
    @Log(title = "轮胎代码", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhTyreCode vhTyreCode) {
        return toAjax(vhTyreCodeService.updateVhTyreCode(vhTyreCode));
    }

    /**
     * 删除轮胎代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:remove')")
    @Log(title = "轮胎代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhTyreCodeService.deleteVhTyreCodeByIds(ids));
    }


    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhTyreCode> util = new ExcelUtil<VhTyreCode>(VhTyreCode.class);
        return util.importTemplateExcel("轮胎代码数据导入模板");
    }

    /**
     * 导入数据
     * @param file 导入数据文件
     * @param updateSupport  是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "轮胎代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:tyreCode:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<VhTyreCode> util = new ExcelUtil<>(VhTyreCode.class);
        List<VhTyreCode> list = util.importExcel(file.getInputStream());
        String message = vhTyreCodeService.importVhTyreCode(list, updateSupport, SecurityUtils.getUsername());
        logger.info(message);
        return AjaxResult.success(message);
    }
}
