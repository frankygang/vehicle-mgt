package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 保养台账对象 vh_upkeep_ledger
 *
 * @author bobo
 * @date 2020-02-03
 */
public class VhUpkeepLedger extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 保养单号 */
    @Excel(name = "保养单号")
    private String upkeepNo;

    /** 保养日期 */
    @Excel(name = "保养日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date upkeepDate;

    /** 保养类型 */
    @Excel(name = "保养类型")
    private String upkeepType;

    /** 保养内容 */
    @Excel(name = "保养内容")
    private String upkeepContent;

    /** 保养部门 */
    @Excel(name = "保养部门")
    private String upkeepDept;

    /** 保养班组 */
    @Excel(name = "保养班组")
    private String upkeepShift;

    /** 保养人 */
    @Excel(name = "保养人")
    private String upkeepMan;

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

    /** 最后修改时间 */
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finallyUpdateDate;

    /** 下次保养时间 */
    @Excel(name = "下次保养时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextUpkeepDate;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUpkeepNo(String upkeepNo) {
        this.upkeepNo = upkeepNo;
    }

    public String getUpkeepNo() {
        return upkeepNo;
    }

    public void setUpkeepDate(Date upkeepDate) {
        this.upkeepDate = upkeepDate;
    }

    public Date getUpkeepDate() {
        return upkeepDate;
    }

    public void setUpkeepType(String upkeepType) {
        this.upkeepType = upkeepType;
    }

    public String getUpkeepType() {
        return upkeepType;
    }

    public void setUpkeepContent(String upkeepContent) {
        this.upkeepContent = upkeepContent;
    }

    public String getUpkeepContent() {
        return upkeepContent;
    }

    public void setUpkeepDept(String upkeepDept) {
        this.upkeepDept = upkeepDept;
    }

    public String getUpkeepDept() {
        return upkeepDept;
    }

    public void setUpkeepShift(String upkeepShift) {
        this.upkeepShift = upkeepShift;
    }

    public String getUpkeepShift() {
        return upkeepShift;
    }

    public void setUpkeepMan(String upkeepMan) {
        this.upkeepMan = upkeepMan;
    }

    public String getUpkeepMan() {
        return upkeepMan;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleModelNum(String vehicleModelNum) {
        this.vehicleModelNum = vehicleModelNum;
    }

    public String getVehicleModelNum() {
        return vehicleModelNum;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setLeaveFactoryDate(String leaveFactoryDate) {
        this.leaveFactoryDate = leaveFactoryDate;
    }

    public String getLeaveFactoryDate() {
        return leaveFactoryDate;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPartsInfo(String partsInfo) {
        this.partsInfo = partsInfo;
    }

    public String getPartsInfo() {
        return partsInfo;
    }

    public void setPartsAmount(String partsAmount) {
        this.partsAmount = partsAmount;
    }

    public String getPartsAmount() {
        return partsAmount;
    }

    public void setCrashBook(String crashBook) {
        this.crashBook = crashBook;
    }

    public String getCrashBook() {
        return crashBook;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setFinallyUpdateDate(Date finallyUpdateDate) {
        this.finallyUpdateDate = finallyUpdateDate;
    }

    public Date getFinallyUpdateDate() {
        return finallyUpdateDate;
    }

    public void setNextUpkeepDate(Date nextUpkeepDate) {
        this.nextUpkeepDate = nextUpkeepDate;
    }

    public Date getNextUpkeepDate() {
        return nextUpkeepDate;
    }

    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public String getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("upkeepNo", getUpkeepNo())
                .append("upkeepDate", getUpkeepDate())
                .append("upkeepType", getUpkeepType())
                .append("upkeepContent", getUpkeepContent())
                .append("upkeepDept", getUpkeepDept())
                .append("upkeepShift", getUpkeepShift())
                .append("upkeepMan", getUpkeepMan())
                .append("vehicleCode", getVehicleCode())
                .append("vehicleType", getVehicleType())
                .append("vehicleModelNum", getVehicleModelNum())
                .append("userDept", getUserDept())
                .append("leaveFactoryDate", getLeaveFactoryDate())
                .append("vehicleOwner", getVehicleOwner())
                .append("phone", getPhone())
                .append("partsInfo", getPartsInfo())
                .append("partsAmount", getPartsAmount())
                .append("crashBook", getCrashBook())
                .append("totalAmount", getTotalAmount())
                .append("remark", getRemark())
                .append("completeStatus", getCompleteStatus())
                .append("finallyUpdateDate", getFinallyUpdateDate())
                .append("nextUpkeepDate", getNextUpkeepDate())
                .append("exceptionStatus", getExceptionStatus())
                .append("exceptionInfo", getExceptionInfo())
                .append("handleResult", getHandleResult())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
