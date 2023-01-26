package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 维修代码对象 vh_maintain_code
 *
 * @author bobo
 * @date 2020-02-03
 */
@Data
public class VhMaintainCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 维修代码
     */
    @Excel(name = "维修代码")
    private String maintainCode;

    /**
     * 维修项目
     */
    @Excel(name = "维修项目")
    private String maintainItem;

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

    private transient BigDecimal count;

    private String maintainDept;

    private Long userId;

    public VhMaintainCode(String maintainCode, String maintainDept) {
        this.maintainCode = maintainCode;
        this.maintainDept = maintainDept;
    }

    public VhMaintainCode() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("maintainCode", getMaintainCode())
                .append("maintainItem", getMaintainItem())
                .append("maintainHour", getMaintainHour())
                .append("crashBook", getCrashBook())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
