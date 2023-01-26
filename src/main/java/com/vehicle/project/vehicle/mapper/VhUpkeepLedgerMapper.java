package com.vehicle.project.vehicle.mapper;

import com.vehicle.project.vehicle.domain.VhUpkeepLedger;

import java.util.List;

/**
 * 保养台账Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhUpkeepLedgerMapper {
    /**
     * 查询保养台账
     *
     * @param id 保养台账ID
     * @return 保养台账
     */
    public VhUpkeepLedger selectVhUpkeepLedgerById(Long id);

    /**
     * 查询保养台账列表
     *
     * @param vhUpkeepLedger 保养台账
     * @return 保养台账集合
     */
    public List<VhUpkeepLedger> selectVhUpkeepLedgerList(VhUpkeepLedger vhUpkeepLedger);

    /**
     * 新增保养台账
     *
     * @param vhUpkeepLedger 保养台账
     * @return 结果
     */
    public int insertVhUpkeepLedger(VhUpkeepLedger vhUpkeepLedger);

    /**
     * 修改保养台账
     *
     * @param vhUpkeepLedger 保养台账
     * @return 结果
     */
    public int updateVhUpkeepLedger(VhUpkeepLedger vhUpkeepLedger);

    /**
     * 删除保养台账
     *
     * @param id 保养台账ID
     * @return 结果
     */
    public int deleteVhUpkeepLedgerById(Long id);

    /**
     * 批量删除保养台账
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhUpkeepLedgerByIds(Long[] ids);

}
