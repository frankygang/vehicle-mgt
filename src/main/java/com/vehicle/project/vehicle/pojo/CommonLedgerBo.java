package com.vehicle.project.vehicle.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vehicle.project.vehicle.domain.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * 功能描述:
 *      台账生成业务对象
 * @param:
 * @return:
 * @auther: onion
 * @date: 2019/2/13 9:12
 */

@Getter
@Setter
public class CommonLedgerBo {

    private Long id;

    /** 业务单号 */
    @NotEmpty
    private String businessNo;

    /** 维修日期 */
    private Date maintainDate;

    /** 维修部门 */
    private String maintainDept;

    /** 维修班组 */
    private String maintainShift;

    /** 镍铁部门 */
    private String niCompany;

    /** 维修人 */
    private String maintainer;

    /** 损坏原因 */
    private String damageReason;

    /** 预计完成日期 */
    private Date predictCompleteDate;

    /** 下次保养时间 */
    private Date nextUpkeepDate;

    /** 未完成原因 */
    private String unfinishedReason;

    /** 完成状态 */
    private String completeStatus;

    /**  总金额 */
    private BigDecimal totalAmount;

    /** 备注 */
    private String remark;

    /**
     * 报修人
     */
    private String custom;

    /**
     * 工号
     */
    private String staffNum;


    /**
     * 提车验收人
     */
    private String consignee;

    /**
     * 维修开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date processStartTime;

    /**
     * 维修结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date processEndTime;

    private String mileage;


    /** 业务项 */

    VhVehicleInfo vehicleInfo;

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
