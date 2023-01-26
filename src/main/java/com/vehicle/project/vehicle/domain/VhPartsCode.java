package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 配件代码对象 vh_parts_code
 *
 * @author bobo
 * @date 2020-02-03
 */
@Data
public class VhPartsCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 物料单位 */
    @Excel(name = "物料单位")
    private String materialUnit;

    /** 金额 */
    @Excel(name = "金额")
    private String amount;

    private transient BigDecimal count;

    private String maintainDept;

    private Long userId;

    public VhPartsCode(String materialCode, String maintainDept) {
        this.materialCode = materialCode;
        this.maintainDept = maintainDept;
    }

    public VhPartsCode() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("materialCode", getMaterialCode())
                .append("materialName", getMaterialName())
                .append("materialUnit", getMaterialUnit())
                .append("amount", getAmount())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
