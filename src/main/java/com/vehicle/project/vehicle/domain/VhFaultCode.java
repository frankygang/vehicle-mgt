package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 故障代码对象 vh_fault_code
 *
 * @author bobo
 * @date 2020-02-03
 */
@Data
public class VhFaultCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 故障代码 */
    @Excel(name = "故障代码")
    private String faultCode;

    /** 代码说明 */
    @Excel(name = "代码说明")
    private String codeExplain;

    private String maintainDept;

    private Long userId;

    public VhFaultCode(String faultCode, String maintainDept) {
        this.faultCode = faultCode;
        this.maintainDept = maintainDept;
    }

    public VhFaultCode() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("faultCode", getFaultCode())
                .append("codeExplain", getCodeExplain())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
