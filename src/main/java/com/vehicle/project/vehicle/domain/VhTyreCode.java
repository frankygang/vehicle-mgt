package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 轮胎代码对象 vh_tyre_code
 *
 * @author bobo
 * @date 2020-02-03
 */
@Data
public class VhTyreCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 轮胎代码 */
    @Excel(name = "轮胎代码")
    private String tyreCode;

    /** 轮胎项目 */
    @Excel(name = "轮胎项目")
    private String tyreItem;
    /**
     * 工时
     */
    @Excel(name = "工时")
    private String maintainHour;
    /** 工时费 */
    @Excel(name = "工时费")
    private String crashBook;

    private transient BigDecimal count;

    private String maintainDept;

    private Long userId;

    public VhTyreCode(String tyreCode, String maintainDept) {
        this.tyreCode = tyreCode;
        this.maintainDept = maintainDept;
    }

    public VhTyreCode() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tyreCode", getTyreCode())
                .append("tyreItem", getTyreItem())
                .append("maintainHour", getMaintainHour())
                .append("crashBook", getCrashBook())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
