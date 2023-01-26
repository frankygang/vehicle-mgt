package com.vehicle.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 *
 * @author bobo
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    public static final String TYRE_PREFIX = "LT";
    public static final String TYRE_2 = "LT2-";
    public static final String TYRE_3 = "LT3-";
    public static final String TYRE_4 = "LT4-";
    public static final String TYRE_5 = "LT5-";
    public static final String TYRE_6 = "LT6-";


    public static final String UPKEEP_PREFIX = "BY";
    public static final String UPKEEP_2 = "BY2-";
    public static final String UPKEEP_3 = "BY3-";
    public static final String UPKEEP_4 = "BY4-";
    public static final String UPKEEP_5 = "BY5-";
    public static final String UPKEEP_6 = "BY6-";

    public static final String MAINTAIN_PREFIX = "WX";
    public static final String MAINTAIN_2 = "WX2-";
    public static final String MAINTAIN_3 = "WS3";
    public static final String MAINTAIN_4 = "WX4-";
    public static final String MAINTAIN_5 = "WX5-";
    public static final String MAINTAIN_6 = "WX6-";

    //汽修厂
    public static final String DEPT_1 = "汽修一厂";
    public static final String DEPT_2 = "汽修二厂";
    public static final String DEPT_3 = "汽修三厂";
    public static final String DEPT_4 = "汽修四厂";
    public static final String DEPT_5 = "汽修五厂";
    public static final String DEPT_6 = "汽修六厂";

    public static final int NUM_ONE = 1;


}
