package com.vehicle.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * 基于 fastjson 封装的json转换工具类
 * @author bobo
 */
public class FastJsonUtils {

    /**
     * 功能描述：把JSON字符串数据转换成指定的java对象
     * @param jsonStr JSON字符串
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     * @param object java对象
     * @return JSON字符串数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON字符串数据转换成指定的java对象列表
     * @param jsonStr JSON字符串数据
     * @param clazz 指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

    /**
     * 功能描述：把JSON字符串数据转换成较为复杂的List<Map<String, Object>>
     * @param jsonStr JSON字符串数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonStr) {
        Map<String, Object> stringObjectMap = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
        });
        return JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>() {});
    }
}
