package com.vehicle.project.vehicle.service;

import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.domain.VhTyreLedger;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.pojo.LedgerMainVo;

import java.util.List;

/**
 * 轮胎台账Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhTyreLedgerService {
    /**
     * 查询轮胎台账
     *
     * @param id 轮胎台账ID
     * @return 轮胎台账
     */
    public VhTyreLedger selectVhTyreLedgerById(Long id);

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
     * 批量删除轮胎台账
     *
     * @param ids 需要删除的轮胎台账ID
     * @return 结果
     */
    public int deleteVhTyreLedgerByIds(Long[] ids);

    /**
     * 删除轮胎台账信息
     *
     * @param id 轮胎台账ID
     * @return 结果
     */
    public int deleteVhTyreLedgerById(Long id);


}
