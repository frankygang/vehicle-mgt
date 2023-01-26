package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 汽配库存对象 vh_autoparts_store
 *
 * @date 2020-08-24
 */
@Data
public class VhAutopartsStore extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 库位
     */
    private String storeNo;

    /**
     * 物料编号
     */
    @Excel(name = "物料编号")
    private String materialCode;

    private  String materialName;

    private String spec;

    private String useRemark;

    @Excel(name = "物料描述")
    private String material;

    /**
     * 库存量
     */
    @Excel(name = "库存量")
    private Long stockNum;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String materialUnit;

    /**
     * 参考单价
     */
    @Excel(name = "参考单价")
    private BigDecimal amount;

    /**
     * 金额USD
     */
    @Excel(name = "金额USD")
    private BigDecimal totalAmount;

    /**
     * 上月消耗
     */
    @Excel(name = "上月消耗", type = Excel.Type.EXPORT)
    private int ultimoUseNum;

    /**
     * 近六月消耗
     */
    @Excel(name = "近六月消耗", type = Excel.Type.EXPORT)
    private int sixMonthsNum;

    /**
     * 在线数量
     */
    @Excel(name = "在线数量")
    private Long onlineNum;

    /**
     * 安全库存
     */
    @Excel(name = "安全库存")
    private Long safetyStock;

    private boolean safetyStockFlag;

    private boolean minSafetyStockFlag;

    public VhAutopartsStore() {
    }

    public VhAutopartsStore(String storeNo, String materialCode) {
        this.storeNo = storeNo;
        this.materialCode = materialCode;
    }

    public VhAutopartsStore(String materialCode) {
        this.materialCode = materialCode;
    }
}
