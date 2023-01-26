package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 维修台账对象 vh_maintain_ledger
 *
 * @author bobo
 * @date 2020-02-03
 */
@Getter
@Setter
public class VhMaintainLedger extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 维修单号 */
    @Excel(name = "维修单号")
    private String maintainNo;

    /** 维修日期 */
    @Excel(name = "维修日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintainDate;

    /** 维修项目 */
    @Excel(name = "维修项目")
    private String maintainItem;

    /** 维修部门 */
    @Excel(name = "维修部门")
    private String maintainDept;

    /** 维修班组 */
    @Excel(name = "维修班组")
    private String maintainShift;

    /** 维修人 */
    @Excel(name = "维修人")
    private String maintainMan;

    /** 车辆编码 */
    @Excel(name = "车辆编码")
    private String vehicleCode;

    /** 车辆类别 */
    @Excel(name = "车辆类别")
    private String vehicleType;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String vehicleModelNum;

    /** 使用部门 */
    @Excel(name = "使用部门")
    private String userDept;

    /** 出厂日期 */
    @Excel(name = "出厂日期")
    private String leaveFactoryDate;

    /** 车辆负责人 */
    @Excel(name = "车辆负责人")
    private String vehicleOwner;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 故障代码 */
    @Excel(name = "故障代码")
    private String faultCode;

    /** 损坏原因 */
    @Excel(name = "损坏原因")
    private String damageReason;

    /** 报修人 */
    @Excel(name = "报修人")
    private String repairsMan;

    /** 配件信息 */
    @Excel(name = "配件信息")
    private String partsInfo;

    /** 配件金额 */
    @Excel(name = "配件金额")
    private String partsAmount;

    /** 工时费 */
    @Excel(name = "工时费")
    private String crashBook;

    /** 总金额 */
    @Excel(name = "总金额")
    private String totalAmount;

    /** 完成状态 */
    @Excel(name = "完成状态")
    private String completeStatus;

    /** 最后修改日期 */
    @Excel(name = "最后修改日期")
    private String finallyUpdateDate;

    /** 预计完成时间 */
    @Excel(name = "预计完成时间")
    private String predictCompleteDate;

    /** 未完成原因 */
    @Excel(name = "未完成原因")
    private String incompleteReason;

    /** 异常状态 */
    @Excel(name = "异常状态")
    private String exceptionStatus;

    /** 异常信息 */
    @Excel(name = "异常信息")
    private String exceptionInfo;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String handleResult;

    /** 作废标志 */
    private String delFlag;

    /** 定损单号 */
    @Excel(name = "定损单号")
    private String lossNo;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("maintainNo", getMaintainNo())
                .append("maintainDate", getMaintainDate())
                .append("maintainItem", getMaintainItem())
                .append("maintainDept", getMaintainDept())
                .append("maintainShift", getMaintainShift())
                .append("maintainMan", getMaintainMan())
                .append("vehicleCode", getVehicleCode())
                .append("vehicleType", getVehicleType())
                .append("vehicleModelNum", getVehicleModelNum())
                .append("userDept", getUserDept())
                .append("leaveFactoryDate", getLeaveFactoryDate())
                .append("vehicleOwner", getVehicleOwner())
                .append("phone", getPhone())
                .append("faultCode", getFaultCode())
                .append("damageReason", getDamageReason())
                .append("repairsMan", getRepairsMan())
                .append("partsInfo", getPartsInfo())
                .append("partsAmount", getPartsAmount())
                .append("crashBook", getCrashBook())
                .append("totalAmount", getTotalAmount())
                .append("remark", getRemark())
                .append("completeStatus", getCompleteStatus())
                .append("finallyUpdateDate", getFinallyUpdateDate())
                .append("predictCompleteDate", getPredictCompleteDate())
                .append("incompleteReason", getIncompleteReason())
                .append("exceptionStatus", getExceptionStatus())
                .append("exceptionInfo", getExceptionInfo())
                .append("handleResult", getHandleResult())
                .append("delFlag", getDelFlag())
                .append("lossNo", getLossNo())
                .toString();
    }
}
