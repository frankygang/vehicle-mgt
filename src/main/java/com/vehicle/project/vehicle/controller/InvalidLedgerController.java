package com.vehicle.project.vehicle.controller;

import com.vehicle.common.constant.UserConstants;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.service.IVhLedgerMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 作废台账 Controller
 */
@RestController
@RequestMapping("/vehicle/invalid")
public class InvalidLedgerController extends BaseController {
    @Autowired
    private IVhLedgerMainService ledgerMainService;

    /**
     * 查询作废台账列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:invalid:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody VhLedgerMain vhLedgerMain) {
        startPage(vhLedgerMain.getPageNum(), vhLedgerMain.getPageSize());
        vhLedgerMain.setDelFlag(UserConstants.DELECT);
        vhLedgerMain.setTimeTypeStr(UserConstants.DEL_DATE);
        List<VhLedgerMain> list = ledgerMainService.selectLedgerMainList(vhLedgerMain, vhLedgerMain.getLedgerType(),
                UserConstants.LEDGER_TYPE_MAP.get(vhLedgerMain.getLedgerType()));
        return getDataTable(list);
    }

    /**
     * 导出作废台账列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:invalid:export')")
    @Log(title = "作废台账导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhLedgerMain vhLedgerMain) {
        vhLedgerMain.setDelFlag(UserConstants.DELECT);
        vhLedgerMain.setTimeTypeStr(UserConstants.DEL_DATE);
        List<VhLedgerMain> list = ledgerMainService.selectLedgerMainList(vhLedgerMain, vhLedgerMain.getLedgerType(),
                UserConstants.LEDGER_TYPE_MAP.get(vhLedgerMain.getLedgerType()));
        ExcelUtil<VhLedgerMain> util = new ExcelUtil<>(VhLedgerMain.class);

        List<String> excludeColumn = new ArrayList<>();
        excludeColumn.add("maintainDept");
        excludeColumn.add("maintainShift");
        excludeColumn.add("belongDept");
        return util.exportExcel(list, "作废台账", excludeColumn);
    }

    /**
     * 功能描述:
     * 取消作废 恢复台账
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/21 8:09
     */
    @Log(title = "作废台账恢复", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('vehicle:invalid:valid')")
    @PostMapping("/valid")
    public AjaxResult validLedger(@RequestParam("businessNoS[]") String[] businessNos) {
        return toAjax(ledgerMainService.validRecord(businessNos));
    }

    @GetMapping("/codeInfos")
    public AjaxResult getBaseCodeInfos(@RequestParam("businessNo") String businessNo) {
        return AjaxResult.success(ledgerMainService.getBaseCodeInfoByBisNo(businessNo, UserConstants.DELECT));
    }

    /**
     * 删除作废台账
     */
    @PreAuthorize("@ss.hasPermi('vehicle:invalid:remove')")
    @Log(title = "作废台账", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(ledgerMainService.deleteVhLedgerMainByIds(ids));
    }

}
