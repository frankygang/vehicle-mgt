package com.vehicle.project.vehicle.service;

import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;

import java.util.List;

/**
 * 台账主表Service接口
 *
 * @author onion
 * @date 2020-02-12
 */
public interface IVhLedgerMainService {
    /**
     * 查询台账主表
     *
     * @param id 台账主表ID
     * @return 台账主表
     */
    public CommonLedgerBo selectVhLedgerMainById(Long id);

    /**
     * 新增台账主表
     *
     * @param vhLedgerMain 台账主表
     * @return 结果
     */
    public int insertVhLedgerMain(VhLedgerMain vhLedgerMain);

    /**
     * 修改台账主表
     *
     * @param vhLedgerMain 台账主表
     * @return 结果
     */
    public int updateVhLedgerMain(VhLedgerMain vhLedgerMain);

    /**
     * 批量删除台账主表
     *
     * @param ids 需要删除的台账主表ID
     * @return 结果
     */
    public int deleteVhLedgerMainByIds(Long[] ids);

    /**
     * 删除台账主表信息
     *
     * @param id 台账主表ID
     * @return 结果
     */
    public int deleteVhLedgerMainById(Long id);

    String genTyreNo(Integer ledgerType, String prefix, String deptName);


    List<VhLedgerMain> selectLedgerMainList(VhLedgerMain ledgerMain, Integer bisType, Integer mainTainType);

    AjaxResult addVhLedgerMain(CommonLedgerBo commonLedgerBo, Integer bisType, Integer mainTainType);

    AjaxResult addBusinessInfo(CommonLedgerBo ledgerBo, Integer bisType, Integer mainTainType);

    AjaxResult modifyVhLedgerMain(CommonLedgerBo commonLedgerBo, Integer bisType, Integer mainTainType);

    List<VhFaultCode> getFaultCode(List<String> code);

    CommonLedgerBo getBaseCodeInfoByBisNo(String businessNo, String delFlag);

    int invalidRecord(String[] businessNo);

    int validRecord(String[] businessNos);

    int genLossNo(String businessNo, Integer ledgerType);


    AjaxResult checkRepeat(String vehicleCode, String maintainCodes, Integer ledgerType, Integer businessType);

    boolean checkRepeatNo(String businessNo);

    int updateStatus(String businessNo, String orderStatus, String statusRemark);

    Boolean hasUnFinshedNo(String vehicleCode, String deptName);
}
