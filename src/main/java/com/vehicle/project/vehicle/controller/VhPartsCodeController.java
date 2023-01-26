package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.interceptor.annotation.RepeatSubmit;
import com.vehicle.framework.manager.AsyncManager;
import com.vehicle.framework.manager.factory.AsyncFactory;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhMaintainCode;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import com.vehicle.project.vehicle.service.IVhPartsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 配件代码Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/partsCode")
public class VhPartsCodeController extends BaseController {
    @Autowired
    private IVhPartsCodeService vhPartsCodeService;

    /**
     * 查询配件代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhPartsCode vhPartsCode) {
        startPage();
        List<VhPartsCode> list = vhPartsCodeService.selectVhPartsCodeList(vhPartsCode);
        return getDataTable(list);
    }

    /**
     * 导出配件代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:export')")
    @Log(title = "配件代码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhPartsCode vhPartsCode) {
        List<VhPartsCode> list = vhPartsCodeService.selectVhPartsCodeList(vhPartsCode);
        ExcelUtil<VhPartsCode> util = new ExcelUtil<VhPartsCode>(VhPartsCode.class);
        return util.exportExcel(list, "配件代码数据");
    }

    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:export')")
    @Log(title = "配件代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids) {
        List<VhPartsCode> list = vhPartsCodeService.selectVhPartsCodeByIds(ids);
        ExcelUtil<VhPartsCode> util = new ExcelUtil<VhPartsCode>(VhPartsCode.class);
        return util.exportExcel(list, "配件代码数据");
    }

    /**
     * 获取配件代码详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhPartsCodeService.selectVhPartsCodeById(id));
    }

    /**
     * 新增配件代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:add')")
    @Log(title = "配件代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhPartsCode vhPartsCode) {

        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhPartsCode.setMaintainDept(sysUser.getDept().getDeptName());
        vhPartsCode.setUserId(sysUser.getUserId());

        VhPartsCode partsCode = vhPartsCodeService.selectVhPartsCode(vhPartsCode);
        if (partsCode != null) {
            return AjaxResult.error("配件代码已存在！添加失败！");
        }

        return toAjax(vhPartsCodeService.insertVhPartsCode(vhPartsCode));
    }

    /**
     * 修改配件代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:edit')")
    @Log(title = "配件代码", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhPartsCode vhPartsCode) {
        return toAjax(vhPartsCodeService.updateVhPartsCode(vhPartsCode));
    }

    /**
     * 删除配件代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:remove')")
    @Log(title = "配件代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhPartsCodeService.deleteVhPartsCodeByIds(ids));
    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhPartsCode> util = new ExcelUtil<VhPartsCode>(VhPartsCode.class);
        return util.importTemplateExcel("配件代码数据导入模板");
    }


    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "配件代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:partsCode:import')")
    @PostMapping("/importData")
    @RepeatSubmit
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        ExcelUtil<VhPartsCode> util = new ExcelUtil<>(VhPartsCode.class);
        List<VhPartsCode> list = util.importExcel(file.getInputStream());
        AsyncManager.me().execute(AsyncFactory.partsImport(list, sysUser));
        return AjaxResult.success();
    }
}
