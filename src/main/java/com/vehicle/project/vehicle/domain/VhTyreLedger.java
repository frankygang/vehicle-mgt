package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 轮胎台账对象 vh_tyre_ledger
 *
 * @author bobo
 * @date 2020-02-03
 */
public class VhTyreLedger extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 轮胎单号 */
    @Excel(name = "轮胎单号")
    private String tyreNo;

    /** 轮胎日期 */
    @Excel(name = "轮胎日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tyreDate;

    /** 轮胎项目 */
    @Excel(name = "轮胎项目")
    private String tyreItem;

    /** 轮胎部门 */
    @Excel(name = "轮胎部门")
    private String tyreDept;

    /** 轮胎班组 */
    @Excel(name = "轮胎班组")
    private String tyreShift;

    /** 轮胎人 */
    @Excel(name = "轮胎人")
    private String tyreMan;

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
    @Excel(name = "出厂日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveFactoryDate;

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

    /** 为了和维修台账相同添加，暂时没用 */
    @Excel(name = "为了和维修台账相同添加，暂时没用")
    private String lossNo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTyreNo(String tyreNo) {
        this.tyreNo = tyreNo;
    }

    public String getTyreNo() {
        return tyreNo;
    }

    public void setTyreDate(Date tyreDate) {
        this.tyreDate = tyreDate;
    }

    public Date getTyreDate() {
        return tyreDate;
    }

    public void setTyreItem(String tyreItem) {
        this.tyreItem = tyreItem;
    }

    public String getTyreItem() {
        return tyreItem;
    }

    public void setTyreDept(String tyreDept) {
        this.tyreDept = tyreDept;
    }

    public String getTyreDept() {
        return tyreDept;
    }

    public void setTyreShift(String tyreShift) {
        this.tyreShift = tyreShift;
    }

    public String getTyreShift() {
        return tyreShift;
    }

    public void setTyreMan(String tyreMan) {
        this.tyreMan = tyreMan;
    }

    public String getTyreMan() {
        return tyreMan;
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

    public void setLeaveFactoryDate(Date leaveFactoryDate) {
        this.leaveFactoryDate = leaveFactoryDate;
    }

    public Date getLeaveFactoryDate() {
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

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    public String getFaultCode() {
        return faultCode;
    }

    public void setDamageReason(String damageReason) {
        this.damageReason = damageReason;
    }

    public String getDamageReason() {
        return damageReason;
    }

    public void setRepairsMan(String repairsMan) {
        this.repairsMan = repairsMan;
    }

    public String getRepairsMan() {
        return repairsMan;
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

    public void setFinallyUpdateDate(String finallyUpdateDate) {
        this.finallyUpdateDate = finallyUpdateDate;
    }

    public String getFinallyUpdateDate() {
        return finallyUpdateDate;
    }

    public void setPredictCompleteDate(String predictCompleteDate) {
        this.predictCompleteDate = predictCompleteDate;
    }

    public String getPredictCompleteDate() {
        return predictCompleteDate;
    }

    public void setIncompleteReason(String incompleteReason) {
        this.incompleteReason = incompleteReason;
    }

    public String getIncompleteReason() {
        return incompleteReason;
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

    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }

    public String getLossNo() {
        return lossNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tyreNo", getTyreNo())
                .append("tyreDate", getTyreDate())
                .append("tyreItem", getTyreItem())
                .append("tyreDept", getTyreDept())
                .append("tyreShift", getTyreShift())
                .append("tyreMan", getTyreMan())
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
