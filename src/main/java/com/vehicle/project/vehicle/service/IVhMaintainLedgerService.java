package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.domain.VhMaintainLedger;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;

import java.util.List;

/**
 * 维修台账Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhMaintainLedgerService {
    /**
     * 查询维修台账
     *
     * @param id 维修台账ID
     * @return 维修台账
     */
    public VhMaintainLedger selectVhMaintainLedgerById(Long id);

    /**
     * 查询维修台账列表
     *
     * @param vhMaintainLedger 维修台账
     * @return 维修台账集合
     */
    public List<VhMaintainLedger> selectVhMaintainLedgerList(VhMaintainLedger vhMaintainLedger);


    /**
     * 新增维修台账
     *
     * @param vhMaintainLedger 维修台账
     * @return 结果
     */
    public int insertVhMaintainLedger(VhMaintainLedger vhMaintainLedger);

    int addVhMaintainLedger(CommonLedgerBo ledgerBo);

    /**
     * 修改维修台账
     *
     * @param vhMaintainLedger 维修台账
     * @return 结果
     */
    public int updateVhMaintainLedger(VhMaintainLedger vhMaintainLedger);

    /**
     * 批量删除维修台账
     *
     * @param ids 需要删除的维修台账ID
     * @return 结果
     */
    public int deleteVhMaintainLedgerByIds(Long[] ids);

    /**
     * 删除维修台账信息
     *
     * @param id 维修台账ID
     * @return 结果
     */
    public int deleteVhMaintainLedgerById(Long id);

}
