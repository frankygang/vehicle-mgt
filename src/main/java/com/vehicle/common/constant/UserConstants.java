package com.vehicle.common.constant;

import com.vehicle.framework.aspectj.lang.enums.BusinessType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户常量信息
 *
 * @author bobo
 */
public class UserConstants {
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_BLOCKED = "1";

    /** 角色封禁状态 */
    public static final String ROLE_BLOCKED = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /** 删除状态 */
    public final static String DELECT = "1";

    public final static String UN_DELECT = "0";

    /** 通过台账类型获取维修项目 */
    public static final Map<Integer, Integer> LEDGER_TYPE_MAP = Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(BusinessType.TYRE.ordinal(), BusinessType.TYRE_CODE.ordinal());
            put(BusinessType.UPKEEP.ordinal(), BusinessType.UPKEEP_CODE.ordinal());
            put(BusinessType.MAINTAIN.ordinal(), BusinessType.MAINTAIN_CODE.ordinal());
        }
    });


    /**
     * 时间检索字符串
     */

    /** select by maintain_date 维修登记日期 */
    public static String MAINTAIN_DATE = "maintain_date";
    /** select by create_time 创建日期 */
    public static String CREATE_TIME = "create_time";
    /** select by update_time 更新日期 */
    public static String UPDATE_TIME = "update_time";
    /** select by DEL_DATE 台账作废日期日期 */
    public static String DEL_DATE = "del_date";

}
