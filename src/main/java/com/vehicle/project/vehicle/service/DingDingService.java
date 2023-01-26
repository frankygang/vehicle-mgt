package com.vehicle.project.vehicle.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vehicle.common.utils.HttpClientUtils;
import com.vehicle.common.utils.json.FastJsonUtils;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.pojo.DingDingUserDetail;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
@Data
public class DingDingService {

    @Value("${dingding.appKey}")
    private String appKey;

    @Value("${dingding.appSecret}")
    private String appSecret;

    @Value("${dingding.corpId}")
    private String corpId;


    /**
     * 获取token
     *
     * @return
     */
    public String accessToken() {
        Map<String, String> params = new HashMap<>();
        String url = "https://oapi.dingtalk.com/gettoken";
        params.put("appkey", this.getAppKey());
        params.put("appsecret", this.getAppSecret());
        try {
            AjaxResult client = HttpClientUtils.doGet(url, params);
            JSONObject jsonObject = JSON.parseObject((String) client.get(AjaxResult.MSG_TAG));
            String accessToken = jsonObject.getString("access_token");
            log.info("access_token: {}",accessToken);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户userid
     *
     * @param code
     * @param accessToken
     * @return
     */
    public String getUserinfo(String code, String accessToken) {
        String url = "https://oapi.dingtalk.com/user/getuserinfo";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("access_token", accessToken);
        try {
            AjaxResult client = HttpClientUtils.doGet(url, params);
            JSONObject jsonObject = JSON.parseObject((String) client.get(AjaxResult.MSG_TAG));
            String userId = jsonObject.getString("userid");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户详情
     *
     * @param accessToken
     * @param userId
     * @return
     */
    public DingDingUserDetail getUserDesc(String accessToken, String userId) {
        String url = "https://oapi.dingtalk.com/user/get";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("userid", userId);
        try {
            AjaxResult client = HttpClientUtils.doGet(url, params);
            return FastJsonUtils.getJsonToBean((String) client.get(AjaxResult.MSG_TAG), DingDingUserDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
