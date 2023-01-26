package com.vehicle.project.vehicle.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.service.IVhAutopartsStoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.project.vehicle.domain.VhApplyInfo;
import com.vehicle.project.vehicle.service.IVhApplyInfoService;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.web.page.TableDataInfo;

/**
 * 配件申购Controller
 *
 * @author .
 * @date 2020-09-19
 */
@RestController
@RequestMapping("/vehicle/VhApplyInfo")
public class VhApplyInfoController extends BaseController {
    @Autowired
    private IVhApplyInfoService vhApplyInfoService;

    @Autowired
    private IVhAutopartsStoreService vhAutopartsStoreService;

    /**
     * 查询配件申购列表
     */
    @GetMapping("/list")
    public TableDataInfo list(VhApplyInfo vhApplyInfo) {
        startPage();
        List<VhApplyInfo> list = vhApplyInfoService.selectVhApplyInfoList(vhApplyInfo);
        return getDataTable(list);
    }

    /**
     * 物料查询接口
     *
     * @param
     * @return
     */
    @GetMapping("/materialList")
    public AjaxResult materialList() {
        List<VhAutopartsStore> list = vhAutopartsStoreService.selectVhAutopartsStoreList(new VhAutopartsStore());
        return AjaxResult.success(list);
    }

    /**
     * 导出配件申购列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhApplyInfo:export')")
    @Log(title = "配件申购", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhApplyInfo vhApplyInfo) {
        List<VhApplyInfo> list = vhApplyInfoService.selectVhApplyInfoList(vhApplyInfo);
        ExcelUtil<VhApplyInfo> util = new ExcelUtil<VhApplyInfo>(VhApplyInfo.class);
        return util.exportExcel(list, "VhApplyInfo");
    }

    /**
     * 获取配件申购详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhApplyInfoService.selectVhApplyInfoById(id));
    }

    /**
     * 新增配件申购
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhApplyInfo:add')")
    @Log(title = "配件申购", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhApplyInfo vhApplyInfo) {
        List<VhApplyInfo> list = vhApplyInfoService.selectVhApplyInfoList(new VhApplyInfo(vhApplyInfo.getPurchaseNo(), vhApplyInfo.getMaterialSeq()));
        if (!CollectionUtils.isEmpty(list)) {
            return AjaxResult.error("单号重复，添加失败！");
        }
        return toAjax(vhApplyInfoService.insertVhApplyInfo(vhApplyInfo));
    }

    /**
     * 修改配件申购
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhApplyInfo:edit')")
    @Log(title = "配件申购", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhApplyInfo vhApplyInfo) {
        return toAjax(vhApplyInfoService.updateVhApplyInfo(vhApplyInfo));
    }

    /**
     * 删除配件申购
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhApplyInfo:remove')")
    @Log(title = "配件申购", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhApplyInfoService.deleteVhApplyInfoByIds(ids));
    }

    /**
     * 采购单号及单内序号生成
     * <p>
     * 返回最大单号及单内最大序号+1
     */
    @GetMapping("/purchaseNo")
    public AjaxResult genPurchaseNo() {
        VhApplyInfo vhApplyInfo = vhApplyInfoService.genPurchaseNo();
        return AjaxResult.success(vhApplyInfo);
    }
}
