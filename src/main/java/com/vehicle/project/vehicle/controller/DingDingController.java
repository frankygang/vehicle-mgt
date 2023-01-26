package com.vehicle.project.vehicle.controller;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.utils.ServletUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.common.utils.ip.IpUtils;
import com.vehicle.framework.aspectj.lang.enums.BusinessStatus;
import com.vehicle.framework.manager.AsyncManager;
import com.vehicle.framework.manager.factory.AsyncFactory;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.monitor.domain.SysOperLog;
import com.vehicle.project.vehicle.domain.VhDingdingDept;
import com.vehicle.project.vehicle.pojo.DingDingUserDetail;
import com.vehicle.project.vehicle.service.DingDingService;
import com.vehicle.project.vehicle.service.IVhDingdingDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉钉 用户登录 controller
 *
 * @author hh
 */

@Slf4j
@RestController
public class DingDingController {

    @Autowired
    private DingDingService dingDingService;

    @Autowired
    private IVhDingdingDeptService dingdingDeptService;

    @GetMapping("/dingDingLogin")
    public AjaxResult getUserDesc(@RequestParam("authCode") String authCode) {
        SysOperLog operLog = new SysOperLog();
        // 请求的地址
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        operLog.setOperIp(ip);
        operLog.setTitle("数据查询");

        try {
            String accessToken = dingDingService.accessToken();
            String userId = dingDingService.getUserinfo(authCode, accessToken);
            DingDingUserDetail userDesc = dingDingService.getUserDesc(accessToken, userId);
            Long deptId = userDesc.getDepartment().get(0);
            VhDingdingDept dingdingDept = dingdingDeptService.selectVhDingdingDeptById(deptId);
            VhDingdingDept target = dingdingDeptService.selectVhDingdingDeptById(dingdingDept.getParentId());

            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // 返回参数
            operLog.setJsonResult(JSON.toJSONString(target));
            operLog.setOperName(userDesc.getName() + " - " + target.getName());
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
            return AjaxResult.success(target);
        } catch (Exception e) {
            operLog.setStatus(BusinessStatus.FAIL.ordinal());
            operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
            return AjaxResult.error("钉钉信息获取失败！");
        }
    }
}
