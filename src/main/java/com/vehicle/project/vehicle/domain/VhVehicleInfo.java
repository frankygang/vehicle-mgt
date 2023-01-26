package com.vehicle.project.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * 车辆信息对象 vh_vehicle_info
 *
 * @author onion
 * @date 2020-02-03
 */
@Getter
@Setter
public class VhVehicleInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 车辆编码
     */
    @Excel(name = "车辆编码")
    private String vehicleCode;
    /**
     * 车辆品牌
     */
    @Excel(name = "车辆品牌")
    private String vehicleBrands;

    /**
     * 车辆类型
     */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /**
     * 车辆型号
     */
    @Excel(name = "车辆型号1")
    private String vehicleModelNum;
    /**
     * 车辆型号
     */
    @Excel(name = "车辆型号2")
    private String vehicleModelNum2;
    /**
     * 所属公司
     */
    @Excel(name = "所属公司")
    private String belongCompany;/**
     * 所属部门
     */
    @Excel(name = "所属部门")
    private String belongDept;

    private String delFlag = "0";

    /**
     * 使用部门
     */
    @Excel(name = "使用部门")
    private String userDept;
    /**
     * 维修班组
     */
    @Excel(name = "维修班组")
    private String maintainShift;

    /**
     * 生产日期
     */
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveFactoryDate;
    /**
     * 入厂日期
     */
    @Excel(name = "入厂日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryFactoryDate;
    /**
     * 车龄
     */
    @Excel(name = "车龄")
    private String vehicleAge;

    /**
     * 车辆负责人
     */
    @Excel(name = "车辆负责人")
    private String vehicleOwner;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String phone;

    /**
     * 维修部门
     */
//    @Excel(name = "维修部门")
    private String maintainDept;




    /**
     * 业务类型  维修（10）轮胎（11）保养（12）
     */
    @Excel(name = "业务权限", prompt = "维修(10)轮胎(11)保养(12)；空表示3种都可以；填写格式：10-11")
    @JsonIgnore
    private String ledgerType;

    private String[] permissions;

    private Long userId;


    public void setLedgerType(String ledgerType) {
        if (StringUtils.isNotEmpty(permissions)) {
            this.ledgerType = StringUtils.strArrToString(permissions);
        } else {
            this.ledgerType = ledgerType;
        }
    }

    public String[] getPermissions() {
        if (StringUtils.isNotEmpty(ledgerType)) {
            return this.ledgerType.split("-");
        } else {
            return null;
        }

    }

    public VhVehicleInfo(String vehicleCode, String delFlag) {
        this.vehicleCode = vehicleCode;
        this.delFlag = delFlag;
    }

    public VhVehicleInfo() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("vehicleCode", getVehicleCode())
                .append("vehicleBrands",getVehicleBrands())
                .append("vehicleType", getVehicleType())
                .append("vehicleModelNum", getVehicleModelNum())
                .append("vehicleModelNum2", getVehicleModelNum2())
                .append("belongCompany", getBelongCompany())
                .append("belongDept", getBelongDept())
                .append("userDept", getUserDept())
                .append("maintainDept", getMaintainDept())
                .append("leaveFactoryDate", getLeaveFactoryDate())
                .append("entryFactoryDate", getEntryFactoryDate())
                .append("vehicleAge", getVehicleAge())
                .append("vehicleOwner", getVehicleOwner())
                .append("phone", getPhone())
                .append("maintainShift", getMaintainShift())
                .append("remark", getRemark())
                .toString();
    }
}
