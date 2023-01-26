package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhLedgerMain;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 台账主表Mapper接口
 *
 * @author onion
 * @date 2020-02-12
 */
public interface VhLedgerMainMapper {
    /**
     * 查询台账主表
     *
     * @param id 台账主表ID
     * @return 台账主表
     */
    public VhLedgerMain selectVhLedgerMainById(Long id);

    /**
     * 查询台账主表列表
     *
     * @param vhLedgerMain 台账主表
     * @return 台账主表集合
     */
    public List<VhLedgerMain> selectVhLedgerMainList(VhLedgerMain vhLedgerMain);

    @DataScope(deptAlias = "d", userAlias = "u")
    List<VhLedgerMain> selectVhLedgerMainList(VhLedgerMain vhLedgerMain, VhVehicleInfo vehicleInfo);

    VhLedgerMain selectVhLedgerMain(VhLedgerMain vhLedgerMain);


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
     * 删除台账主表
     *
     * @param id 台账主表ID
     * @return 结果
     */
    public int deleteVhLedgerMainById(Long id);

    /**
     * 批量删除台账主表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhLedgerMainByIds(Long[] ids);

//    @DataScope(deptAlias = "d", userAlias = "u")
    VhLedgerMain selectVhLedgerMainByNo(@Param("businessNo") String businessNo);

    /**
     * 功能描述:
     * 根据台账类型生成台账业务单号
     *
     * @param:
     * @return:
     * @auther: onion
     * @date: 2020/2/14 17:30
     */
    String genTyreNo(@Param("ledgerType") Integer ledgerType, @Param("perfix") String perfix,
                     @Param("deptName") String deptName);

    String genLossNo(Integer ledgerType);

    int selectVhLedgerMainListNoDate(VhLedgerMain main, @Param("businessType") Integer businessType, @Param("codes") String[] codes);

    int selectVhLedgerCount(VhLedgerMain main);

    int updateStatus(@Param("businessNo") String businessNo, @Param("orderStatus") String orderStatus, @Param("statusRemark") String statusRemark);
}
