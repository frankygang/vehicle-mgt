package com.vehicle.project.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 台账主表 vh_ledger_main
 *
 * @date 2020-02-13
 */
@Getter
@Setter
public class VhLedgerMain extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer pageNum;
    private Integer pageSize;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 业务单号
     */
    @Excel(name = "业务单号")
    private String businessNo;

    /**
     * 作废日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "作废日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date delDate;

    @Excel(name = "维修日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintainDate;

    @Excel(name = "车辆编码")
    private String vehicleCode;

    private VhVehicleInfo vehicleInfo;

    @Excel(name = "维修项目")
    private String maintainItem;

    @Excel(name = "配件信息")
    private String partsInfo;

    @Excel(name = "故障代码")
    private String faultCode;

    /**
     * 工时费合计
     */
    @Excel(name = "工时费合计")
    private BigDecimal totalCrashBook;

    /**
     * 配件费合计
     */
    @Excel(name = "配件费合计")
    private BigDecimal totalPartsAmount;

    /**
     * 总金额
     */
    @Excel(name = "总金额")
    private BigDecimal totalAmount;

    /**
     * 完成状态
     */
    @Excel(name = "完成状态", readConverterExp = "0=未完成,1=完成")
    private String completeStatus;

    /**
     * 取车日期
     */
    @Excel(name = "取车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date predictCompleteDate;

    /**
     * 未完成原因
     */
    @Excel(name = "未完成原因")
    private String unfinishedReason;

    /**
     * 异常状态
     */
    @Excel(name = "异常状态")
    private String exceptionStatus;

    /**
     * 处理结果
     */
    @Excel(name = "处理结果")
    private String handleResult;

    /**
     * 是否删除 默认值 0 未删除
     */
    private String delFlag = "0";

    /**
     * 定损单号
     */
    @Excel(name = "定损单号")
    private String lossNo;

    /**
     * 损坏原因
     */
    @Excel(name = "损坏原因")
    private String damageReason;

    /**
     * 业务类型  维修（10）轮胎（11）保养（12）
     */
    private Integer ledgerType;

    /**
     * 维修部门
     */
    @Excel(name = "维修部门")
    private String maintainDept;

    /**
     * 维修班组
     */
    @Excel(name = "维修班组")
    private String maintainShift;

    /**
     * 镍铁部门
     */
    @Excel(name = "镍铁部门")
    private String niCompany;

    /**
     * 维修人
     */
    @Excel(name = "维修人")
    private String maintainer;

    @Excel(name = "下次保养时间")
    private Date nextUpkeepDate;

    private String timeTypeStr;

    private Long userId;

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


    private String userDept;

    /**
     * 单据状态
     */
    private String orderStatus;

    /**
     * 状态备注
     */
    private String statusRemark;

    /** 运行里程数 */
    private String mileage;




}
