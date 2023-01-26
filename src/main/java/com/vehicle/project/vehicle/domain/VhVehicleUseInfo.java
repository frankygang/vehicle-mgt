package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 车辆使用信息对象 vh_vehicle_use_info
 *
 * @author bobo
 * @date 2020-02-12
 */
public class VhVehicleUseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车辆编码 */
    @Excel(name = "车辆编码")
    private String vehicleCode;

    /** 使用部门 */
    @Excel(name = "使用部门")
    private String useDept;

    /** 责任人 */
    @Excel(name = "责任人")
    private String vehicleOwner;

    /** $column.columnComment */
    @Excel(name = "责任人")
    private String phone;

    /** 维修部门 */
    @Excel(name = "维修部门")
    private String maintainDept;

    /** 维修班组 */
    @Excel(name = "维修班组")
    private String maintainShift;

    /** 删除标识 默认 0 未删除 */
    private boolean delFlag;

    /** 故障代码 */
    @Excel(name = "故障代码")
    private String faultCode;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setUseDept(String useDept) {
        this.useDept = useDept;
    }

    public String getUseDept() {
        return useDept;
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

    public void setdelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public boolean getdelFlag() {
        return delFlag;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    public String getFaultCode() {
        return faultCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("vehicleCode", getVehicleCode())
                .append("useDept", getUseDept())
                .append("vehicleOwner", getVehicleOwner())
                .append("phone", getPhone())
                .append("maintainDept", getMaintainDept())
                .append("maintainShift", getMaintainShift())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("delFlag", getdelFlag())
                .append("faultCode", getFaultCode())
                .toString();
    }
}
