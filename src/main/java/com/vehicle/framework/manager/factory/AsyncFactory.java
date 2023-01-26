package com.vehicle.framework.manager.factory;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.constant.Constants;
import com.vehicle.common.utils.LogUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.ServletUtils;
import com.vehicle.common.utils.ip.AddressUtils;
import com.vehicle.common.utils.ip.IpUtils;
import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.common.utils.spring.SpringUtils;
import com.vehicle.project.common.WebSocketServer;
import com.vehicle.project.monitor.domain.SysLogininfor;
import com.vehicle.project.monitor.domain.SysOperLog;
import com.vehicle.project.monitor.service.ISysLogininforService;
import com.vehicle.project.monitor.service.ISysOperLogService;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.domain.VhCommonPartsImport;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import com.vehicle.project.vehicle.domain.VhVehicleInfo;
import com.vehicle.project.vehicle.service.IVhCommonPartsImportService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;


/**
 * 异步工厂（产生任务用）
 *
 * @author vehicle
 */
@Slf4j
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 通用配件 异步 excel导入
     *
     * @return
     */
    public static TimerTask excelImport(List<VhCommonPartsImport> list, boolean updateSupport, SysUser sysUser) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(IVhCommonPartsImportService.class).commonPartsImport(list, updateSupport, sysUser);
            }
        };
    }

    public static TimerTask vehicleImport(List<VhVehicleInfo> list, SysUser sysUser) {
        return new TimerTask() {
            @Override
            public void run() {
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                try {
                    ConcurrentHashMap<String, Object> resMap = forkJoinPool.invoke(new ForkTask(0, list.size(), list, sysUser));
                    resMap.put("fileName", "车辆导入分析.txt");
                    log.info("{} -- 车辆导入分析： {}", sysUser.getDept().getDeptName(), resMap);
                    ExcelUtil.exportTxt(JSON.toJSONString(resMap, true), "车辆导入分析.txt");
                    WebSocketServer.sendInfo(JSON.toJSONString(resMap, true), "vehicleInfoImport", sysUser.getUserName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                forkJoinPool.shutdown();
            }
        };
    }


    public static TimerTask partsImport(List<VhPartsCode> list, SysUser sysUser) {
        return new TimerTask() {
            @Override
            public void run() {
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                try {
                    Instant start = Instant.now();
                    LinkedHashMap<String, Object> resMap = forkJoinPool.invoke(new PartsImportTask(0, list.size(), list, sysUser));
                    log.info("{} -- 配件代码导入分析： {}", sysUser.getDept().getDeptName(), resMap);
                    Instant end = Instant.now();
                    log.info("导入执行时间：{}", String.valueOf(Duration.between(start, end).getSeconds()));
                    resMap.put("fileName", "配件代码导入分析.txt");
                    ExcelUtil.exportTxt(JSON.toJSONString(resMap, true), "配件代码导入分析.txt");
                    WebSocketServer.sendInfo(JSON.toJSONString(resMap, true), "partsCode", sysUser.getUserName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                forkJoinPool.shutdown();
            }
        };
    }

    public static TimerTask storeImport(List<VhAutopartsStore> list) {
        return new TimerTask() {
            @Override
            public void run() {
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                try {
                    forkJoinPool.invoke(new StoreImportTask(0, list.size(), list, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                forkJoinPool.shutdown();
            }
        };
    }
}
