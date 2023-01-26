package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 保养代码对象 vh_upkeep_code
 *
 * @author bobo
 * @date 2020-02-03
 */
@Data
public class VhUpkeepCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 保养代码
     */
    @Excel(name = "保养代码")
    private String upkeepCode;

    /**
     * 保养类型
     */
    @Excel(name = "保养类型")
    private String upkeepType;

    /**
     * 保养内容
     */
    @Excel(name = "保养内容")
    private String upkeepContent;
    /**
     * 工时
     */
    @Excel(name = "工时")
    private String maintainHour;
    /**
     * 工时费
     */
    @Excel(name = "工时费")
    private String crashBook;

    private String maintainDept;

    private Long userId;

    private transient BigDecimal count;


    public VhUpkeepCode(String upkeepCode, String maintainDept) {
        this.upkeepCode = upkeepCode;
        this.maintainDept = maintainDept;
    }

    public VhUpkeepCode() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("upkeepCode", getUpkeepCode())
                .append("upkeepType", getUpkeepType())
                .append("upkeepContent", getUpkeepContent())
                .append("maintainHour", getMaintainHour())
                .append("crashBook", getCrashBook())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
