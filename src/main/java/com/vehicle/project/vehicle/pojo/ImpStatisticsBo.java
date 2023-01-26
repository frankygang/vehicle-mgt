package com.vehicle.project.vehicle.pojo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 导入统计分析
 */
@Data
public class ImpStatisticsBo {

    private Integer sum;
    private Integer success;
    private Integer fail;

    private String messages;
}
