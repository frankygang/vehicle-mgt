package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.vehicle.domain.VhOperRecord;
import com.vehicle.project.vehicle.service.IVhOperRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作记录Controller
 *
 * @author bobo
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/vehicle/operRecord")
public class VhOperRecordController extends BaseController {
    @Autowired
    private IVhOperRecordService vhOperRecordService;

    /**
     * 查询操作记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(VhOperRecord vhOperRecord) {
        startPage();
        List<VhOperRecord> list = vhOperRecordService.selectVhOperRecordList(vhOperRecord);
        return getDataTable(list);
    }

    /**
     * 导出操作记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:export')")
    @Log(title = "操作记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhOperRecord vhOperRecord) {
        List<VhOperRecord> list = vhOperRecordService.selectVhOperRecordList(vhOperRecord);
        ExcelUtil<VhOperRecord> util = new ExcelUtil<VhOperRecord>(VhOperRecord.class);
        return util.exportExcel(list, "VhOperRecord");
    }

    /**
     * 获取操作记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhOperRecordService.selectVhOperRecordById(id));
    }

    /**
     * 新增操作记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:add')")
    @Log(title = "操作记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhOperRecord vhOperRecord) {
        return toAjax(vhOperRecordService.insertVhOperRecord(vhOperRecord));
    }

    /**
     * 修改操作记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:edit')")
    @Log(title = "操作记录", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhOperRecord vhOperRecord) {
        return toAjax(vhOperRecordService.updateVhOperRecord(vhOperRecord));
    }

    /**
     * 删除操作记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:operRecord:remove')")
    @Log(title = "操作记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhOperRecordService.deleteVhOperRecordByIds(ids));
    }
}
