package com.vehicle.framework.aspectj.lang.enums;

/**
 * 业务操作类型
 *
 * @author bobo
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,

    /** 维修台账  10 */
    MAINTAIN,
    /**轮胎台账 11 */
    TYRE,
    /**保养台账  12 */
    UPKEEP,


    /**车辆信息  13 */
    VEHICLE,

    /** 维修代码  14 */
    MAINTAIN_CODE,

    /** 配件代码  15 */
    PARTS_CODE,

    /** 故障代码  16 */
    FAULT_CODE,

    /** 轮胎代码  17 */
    TYRE_CODE,

    /** 保养     18 */
    UPKEEP_CODE,


}
