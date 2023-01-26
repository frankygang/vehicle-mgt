package com.vehicle.project.vehicle.pojo;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: onion
 * @Date: 2019/2/24 09:42
 * @Description: 通用配件信息Excel导入
 */
@Getter
@Setter
public class PartsImportBo {

    private String businessNo;

    /** 物料代码 */
    private String materialCode;

    /** 物料名称 */
    private String materialName;

    /** 物料单位 */
    private String materialUnit;

    /** 金额 */
    private String amount;


}
