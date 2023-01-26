package com.vehicle.project.vehicle.pojo;

import com.vehicle.project.vehicle.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 *
 * 功能描述:
 *      台账生成业务返回对象
 * @param:
 * @return:
 * @auther: onion
 * @date: 2019/2/13 9:12
 */

@Getter
@Setter
public class LedgerMainVo {

    private Long id;

    /** 业务单号 */
    private String businessNo;

    /** 维修日期 */
    private Date maintainDate;

    /** 维修部门 */
    private String maintainDept;

    /** 维修班组 */
    private String maintainShift;

    /** 维修人 */
    private String maintainer;

    /** 损坏原因 */
    private String damageReason;

    /** 预计完成日期 */
    private Date predictCompleteDate;

    /** 未完成原因 */
    private String unfinishedReason;

    /** 完成状态 */
    private boolean completeStatus;

    /** 备注 */
    private String remark;

    /** 业务项 */

    private VhVehicleInfo vehicleInfo;

    Set<VhVehicleInfo> vehicleInfoList;
    /** 配件 */
    Set<VhPartsCode> partsCodeList;
    /** 故障 */
    Set<VhFaultCode> faultCodeList;
    /** 维修 */
    Set<VhMaintainCode> maintainCodeList;
    /** 轮胎 */
    Set<VhTyreCode> tyreCodeList;
    /** 保养 */
    Set<VhUpkeepCode> upkeepCodeList;

}
