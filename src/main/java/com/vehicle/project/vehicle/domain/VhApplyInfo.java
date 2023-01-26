package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * 配件申购对象 vh_apply_info
 *
 * @author .
 * @date 2020-09-19
 */
@Data
public class VhApplyInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 采购单号
     */
    @Excel(name = "采购单号")
    private String purchaseNo;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String materialType;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Integer materialSeq;

    /**
     * 物资名称
     */
    @Excel(name = "物资名称")
    private String materialName;

    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String spec;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String materialUnit;

    /**
     * 申报量
     */
    @Excel(name = "申报量")
    private String declaredQuantity;

    /**
     * 要求到货时间
     */
    @Excel(name = "要求到货时间")
    private String requireTime;

    /**
     * 园区数量
     */
    @Excel(name = "园区数量")
    private Integer stockNum;

    /**
     * 安全库存
     */
    @Excel(name = "安全库存")
    private Integer safetyStock;

    /**
     * 在线数量
     */
    @Excel(name = "在线数量")
    private Integer onlineNum;

    /**
     * 使用设备
     */
    @Excel(name = "使用设备")
    private String useRemark;

    /**
     * 上月消耗
     */
    @Excel(name = "上月消耗")
    private Integer ultimoUseNum;

    /**
     * 近六月平均消耗
     */
    @Excel(name = "近六月平均消耗")
    private Integer sixMonthsNum;

    /**
     * 物资负责人
     */
    @Excel(name = "物资负责人")
    private String materialOwner;

    /**
     * 其他部门是否可调用
     */
    @Excel(name = "其他部门是否可调用")
    private String transferFlag;

    /**
     * 物控部门
     */
    @Excel(name = "物控部门")
    private String materialDept;

    /**
     * 等级
     */
    @Excel(name = "等级")
    private String grade;

    /**
     * 申请日期
     */
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyDate;

    /**
     * 物料代码
     */
    @Excel(name = "物料代码")
    private String materialCode;

    @Excel(name = "到货日期")
    private Date arrivalTime;

    @Excel(name = "到货数量")
    private Integer arrivalNum;

    /**
     * 申购类型（寄售、采购）
     */
    @Excel(name = "申购类型", readConverterExp = "寄售、采购")
    private String applyFlag;

    /**
     * 到货状态查询标志   0 全部到货  1  部分   2 未到货
     */
    private String arrivalStatus;



    public VhApplyInfo(String purchaseNo, Integer materialSeq) {
        this.purchaseNo = purchaseNo;
        this.materialSeq = materialSeq;
    }

    public VhApplyInfo() {
    }
}
