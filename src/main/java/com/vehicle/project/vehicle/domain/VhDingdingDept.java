package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;
import com.vehicle.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 钉钉部门对象 vh_dingding_dept
 * 
 * @author onion
 * @date 2020-04-17
 */
@Data
public class VhDingdingDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long parentId;

    private Integer createDeptGroup;

    private Integer autoAddUser;

    private String ext;



}
