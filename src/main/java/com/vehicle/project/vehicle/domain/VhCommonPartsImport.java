package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 通用配件信息导入对象 vh_common_parts_import
 * 
 * @author onion
 * @date 2020-02-24
 */
@Getter
@Setter
public class VhCommonPartsImport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 单号 */
    @Excel(name = "单号")
    private String businessNo;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 物料单位 */
    @Excel(name = "物料单位")
    private String materialUnit;

    /** 数量 */
    @Excel(name = "数量")
    private String amount;

    @Excel(name = "单价")
    private String cost;

    private String maintainDept;

    private Long userId;


}
