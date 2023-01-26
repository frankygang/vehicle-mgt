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
import com.vehicle.project.vehicle.domain.VhUpkeepCode;
import com.vehicle.project.vehicle.service.IVhMaintainCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 维修代码Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@RestController
@RequestMapping("/vehicle/maintainCode")
public class VhMaintainCodeController extends BaseController {
    @Autowired
    private IVhMaintainCodeService vhMaintainCodeService;

    /**
     * 查询维修代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhMaintainCode vhMaintainCode) {
        startPage();
        List<VhMaintainCode> list = vhMaintainCodeService.selectVhMaintainCodeList(vhMaintainCode);
        return getDataTable(list);
    }

    /**
     * 导出维修代码列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:export')")
    @Log(title = "维修代码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhMaintainCode vhMaintainCode) {
        List<VhMaintainCode> list = vhMaintainCodeService.selectVhMaintainCodeList(vhMaintainCode);
        ExcelUtil<VhMaintainCode> util = new ExcelUtil<VhMaintainCode>(VhMaintainCode.class);
        return util.exportExcel(list, "维修代码数据");
    }

    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:export')")
    @Log(title = "保养代码", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBySelect")
    public AjaxResult exportBySelect(@RequestParam(value = "ids[]") Long[] ids) {
        List<VhMaintainCode> list = vhMaintainCodeService.selectVhMaintainCodeByIds(ids);
        ExcelUtil<VhMaintainCode> util = new ExcelUtil<VhMaintainCode>(VhMaintainCode.class);
        return util.exportExcel(list, "维修代码数据");
    }

    /**
     * 获取维修代码详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhMaintainCodeService.selectVhMaintainCodeById(id));
    }

    /**
     * 新增维修代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:add')")
    @Log(title = "维修代码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhMaintainCode vhMaintainCode) {

        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        vhMaintainCode.setMaintainDept(sysUser.getDept().getDeptName());
        vhMaintainCode.setUserId(sysUser.getUserId());

        VhMaintainCode maintainCode = vhMaintainCodeService.selectVhMaintainCode(new VhMaintainCode(vhMaintainCode.getMaintainCode(), sysUser.getDept().getDeptName()));
        if (maintainCode != null) {
            return AjaxResult.error("维修代码已存在！添加失败！");
        }

        return toAjax(vhMaintainCodeService.insertVhMaintainCode(vhMaintainCode));
    }

    /**
     * 修改维修代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:edit')")
    @Log(title = "维修代码", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhMaintainCode vhMaintainCode) {
        return toAjax(vhMaintainCodeService.updateVhMaintainCode(vhMaintainCode));
    }

    /**
     * 删除维修代码
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:remove')")
    @Log(title = "维修代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhMaintainCodeService.deleteVhMaintainCodeByIds(ids));
    }

    /**
     * 下载导入保养代码Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhMaintainCode> util = new ExcelUtil<VhMaintainCode>(VhMaintainCode.class);
        return util.importTemplateExcel("维修代码数据导入模板");
    }

    /**
     * 导入数据
     *
     * @param file          导入数据文件
     * @param updateSupport 是否更新数据
     * @return com.vehicle.framework.web.domain.AjaxResult
     */
    @Log(title = "维修代码", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:maintainCode:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<VhMaintainCode> util = new ExcelUtil<>(VhMaintainCode.class);
        List<VhMaintainCode> list = util.importExcel(file.getInputStream());
        String message = vhMaintainCodeService.importVhMaintainCode(list, updateSupport, SecurityUtils.getUsername());
        logger.info(message);
        return AjaxResult.success(message);
    }


    /**
     * 功能描述:
     * 根据故障码获取故障类型码
     *
     * @param:
     * @return:
     * @date: 2020/2/12 14:03
     */
    @Log(title = "维修代码", businessType = BusinessType.IMPORT)
    @PostMapping("/getFaultCode")
    public AjaxResult getFaultCode(List<String> code) {
        List<VhFaultCode> codeList = vhMaintainCodeService.getFaultCode(code);
        return CollectionUtils.isEmpty(codeList) ? AjaxResult.error() : AjaxResult.success("获取信息成功", code);
    }

}
