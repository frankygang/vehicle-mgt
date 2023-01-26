package com.vehicle.project.vehicle.pojo;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 车辆信息
 *
 * @author onion
 * @date 2020-02-03
 */
public class VehicleInfoBo extends BaseEntity {

    /** ID */
    private Long id;

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

    /** 维修部门 */
    @Excel(name = "维修部门")
    private String maintainDept;

    /** 维修班组 */
    @Excel(name = "维修班组")
    private String maintainShift;

    /** 归属部门 */
    @Excel(name = "归属部门")
    private String belongDept;

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

    public void setMaintainDept(String maintainDept) {
        this.maintainDept = maintainDept;
    }

    public String getMaintainDept() {
        return maintainDept;
    }

    public void setMaintainShift(String maintainShift) {
        this.maintainShift = maintainShift;
    }

    public String getMaintainShift() {
        return maintainShift;
    }

    public void setBelongDept(String belongDept) {
        this.belongDept = belongDept;
    }

    public String getBelongDept() {
        return belongDept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("vehicleCode", getVehicleCode())
                .append("vehicleType", getVehicleType())
                .append("vehicleModelNum", getVehicleModelNum())
                .append("userDept", getUserDept())
                .append("leaveFactoryDate", getLeaveFactoryDate())
                .append("vehicleOwner", getVehicleOwner())
                .append("phone", getPhone())
                .append("maintainDept", getMaintainDept())
                .append("maintainShift", getMaintainShift())
                .append("belongDept", getBelongDept())
                .append("remark", getRemark())
                .toString();
    }
}
