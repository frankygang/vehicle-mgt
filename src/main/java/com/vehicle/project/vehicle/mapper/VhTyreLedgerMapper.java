package com.vehicle.project.vehicle.mapper;

import com.vehicle.project.vehicle.domain.VhTyreLedger;

import java.util.List;

/**
 * 轮胎台账Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhTyreLedgerMapper {
    /**
     * 查询轮胎台账
     *
     * @param id 轮胎台账ID
     * @return 轮胎台账
     */
    public VhTyreLedger selectVhTyreLedgerById(Long id);

    /**
     * 查询轮胎台账列表
     *
     * @param vhTyreLedger 轮胎台账
     * @return 轮胎台账集合
     */
    public List<VhTyreLedger> selectVhTyreLedgerList(VhTyreLedger vhTyreLedger);

    /**
     * 新增轮胎台账
     *
     * @param vhTyreLedger 轮胎台账
     * @return 结果
     */
    public int insertVhTyreLedger(VhTyreLedger vhTyreLedger);

    /**
     * 修改轮胎台账
     *
     * @param vhTyreLedger 轮胎台账
     * @return 结果
     */
    public int updateVhTyreLedger(VhTyreLedger vhTyreLedger);

    /**
     * 删除轮胎台账
     *
     * @param id 轮胎台账ID
     * @return 结果
     */
    public int deleteVhTyreLedgerById(Long id);

    /**
     * 批量删除轮胎台账
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhTyreLedgerByIds(Long[] ids);

}
