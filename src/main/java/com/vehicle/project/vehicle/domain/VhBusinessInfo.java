package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务信息对象 vh_business_info
 *
 * @date 2020-02-13
 */
@Getter
@Setter
public class VhBusinessInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 业务单号
     */
    @Excel(name = "业务单号")
    private String businessNo;

    /**
     * 业务代码
     */
    @Excel(name = "业务代码")
    private String businessCode;

    /**
     * 业务项名称/车辆类别
     */
    @Excel(name = "业务项名称")
    private String businessName;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private BigDecimal amount;

    /**
     * 费用
     */
    @Excel(name = "费用")
    private BigDecimal cost;

    /**
     * 费用合计
     */
    @Excel(name = "费用合计")
    private BigDecimal totalCost;

    /**
     * $column.columnComment
     */
    private String delFlag = "0";

    /**
     * $column.columnComment
     */
    private Integer ledgerType;


    /** */
    private Integer businessType;

    /** 保养类型 */
    private String upkeepType;
    /** 所属公司 */
    private String belongCompany;
    /** 所属部门 */
    private String belongDept;
    /** 车辆品牌 */
    private String vehicleBrands;

    /** 车辆型号1 */
    private String vehicleModelNum;
    /** 车辆型号2 */
    private String vehicleModelNum2;
    /** 车龄 */
    private String vehicleAge;
    /** 工时 */
    private String maintainHour;

    /** 生厂日期 */
    private Date leaveFactoryDate;
    /** 入场日期 */
    private Date entryFactoryDate;

    /**  */
    private String userDept;

    /**  */
    private String vehicleOwner;

    /**  */
    private String phone;





    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    public VhBusinessInfo() {
    }

    public VhBusinessInfo(String businessNo, Integer businessType) {
        this.businessNo = businessNo;
        this.businessType = businessType;
    }

    public VhBusinessInfo(String businessNo, Integer businessType, String delFlag) {
        this.businessNo = businessNo;
        this.businessType = businessType;
        this.delFlag = delFlag;
    }

    public VhBusinessInfo(String businessNo, String businessCode, BigDecimal amount) {
        this.businessNo = businessNo;
        this.businessCode = businessCode;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("businessNo", getBusinessNo())
                .append("businessCode", getBusinessCode())
                .append("amount", getAmount())
                .append("maintainHour", getMaintainHour())
                .append("cost", getCost())
                .append("totalCost", getTotalCost())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("delFlag", getDelFlag())
                .append("businessName", getBusinessName())
                .append("unit", getUnit())
                .toString();
    }
}
