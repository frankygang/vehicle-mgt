package com.vehicle.project.monitor.controller;

import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.monitor.domain.SysJob;
import com.vehicle.project.monitor.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务调度Controller
 *
 * @author bobo
 * @date 2019-12-27
 */
@RestController
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {
    @Autowired
    private ISysJobService sysJobService;

    /**
     * 查询定时任务调度列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob) {
        startPage();
        List<SysJob> list = sysJobService.selectSysJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @Log(title = "定时任务调度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysJob sysJob) {
        List<SysJob> list = sysJobService.selectSysJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "job");
    }

    /**
     * 获取定时任务调度详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
        return AjaxResult.success(sysJobService.selectSysJobById(jobId));
    }

    /**
     * 新增定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:add')")
    @Log(title = "定时任务调度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob sysJob) {
        return toAjax(sysJobService.insertSysJob(sysJob));
    }

    /**
     * 修改定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
    @Log(title = "定时任务调度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob sysJob) {
        return toAjax(sysJobService.updateSysJob(sysJob));
    }

    /**
     * 删除定时任务调度
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @Log(title = "定时任务调度", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) {
        return toAjax(sysJobService.deleteSysJobByIds(jobIds));
    }
}
