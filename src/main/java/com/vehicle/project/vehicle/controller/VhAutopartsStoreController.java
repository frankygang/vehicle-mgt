package com.vehicle.project.vehicle.controller;

import com.vehicle.common.utils.poi.ExcelUtil;
import com.vehicle.framework.aspectj.lang.annotation.Log;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.manager.AsyncManager;
import com.vehicle.framework.manager.factory.AsyncFactory;
import com.vehicle.framework.redis.RedisCache;
import com.vehicle.framework.web.controller.BaseController;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.pojo.PartsUseNumVo;
import com.vehicle.project.vehicle.service.IVhAutopartsStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 汽配库存Controller
 *
 * @author bobo
 * @date 2020-08-24
 */
@Slf4j
@RestController
@RequestMapping("/vehicle/VhAutopartsStore")
public class VhAutopartsStoreController extends BaseController {
    @Autowired
    private IVhAutopartsStoreService vhAutopartsStoreService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询汽配库存列表
     */
    @GetMapping("/list")
    public TableDataInfo list(VhAutopartsStore vhAutopartsStore) {

        startPage();
        List<VhAutopartsStore> list = vhAutopartsStoreService.selectVhAutopartsStoreList(vhAutopartsStore);
        return getDataTable(list);
    }

    /**
     * 每天0时统计更新 每月消耗 近6月平均消耗
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void test1() {
        List<VhAutopartsStore> list = vhAutopartsStoreService.selectVhAutopartsStoreList(new VhAutopartsStore());
        List<PartsUseNumVo> partsUseNum = vhAutopartsStoreService.selectPartsUseNum();
        list.parallelStream().forEach(obj -> {
            partsUseNum.parallelStream().forEach(numVo -> {
                if (obj.getMaterialCode().equals(numVo.getBusinessCode())) {
                    obj.setUltimoUseNum(numVo.getUltimoUseNum());
                    obj.setSixMonthsNum(numVo.getSixMonthsNum());
                    //obj.setSafetyStock((long) numVo.getSixMonthsNum() * 3);
                    // 个位 四舍五入
                    int six = numVo.getSixMonthsNum() * 3;
                    if (six >= 5) {
                        double temp = six / 10.0;
                        obj.setSafetyStock(Math.round(temp) * 10);
                    } else {
                        obj.setSafetyStock(0L);
                    }

                    vhAutopartsStoreService.updateVhAutopartsStore(obj);
                }
            });
        });
        log.info("定时任务执行： {}", new Date());
    }

    /**
     * 导出汽配库存列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhAutopartsStore:export')")
    @Log(title = "汽配库存", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VhAutopartsStore vhAutopartsStore) {
        List<VhAutopartsStore> list = vhAutopartsStoreService.selectVhAutopartsStoreList(vhAutopartsStore);
        ExcelUtil<VhAutopartsStore> util = new ExcelUtil<VhAutopartsStore>(VhAutopartsStore.class);

        List<PartsUseNumVo> partsUseNumCache = redisCache.getCacheObject("partsUseNum");
        if (CollectionUtils.isEmpty(partsUseNumCache)) {
            List<PartsUseNumVo> partsUseNum = vhAutopartsStoreService.selectPartsUseNum();
            redisCache.setCacheObject("partsUseNum", partsUseNum, 12, TimeUnit.HOURS);
        }

        list.parallelStream().forEach(obj -> {
            partsUseNumCache.parallelStream().forEach(vo -> {
                if (obj.getMaterialCode().equals(vo.getBusinessCode())) {
                    obj.setUltimoUseNum(vo.getUltimoUseNum());
                    obj.setSixMonthsNum(vo.getSixMonthsNum());
                }
            });
        });

        Collections.sort(list, new Comparator<VhAutopartsStore>() {
            @Override
            public int compare(VhAutopartsStore o1, VhAutopartsStore o2) {
                return o2.getSixMonthsNum() - o1.getSixMonthsNum();
            }
        });


        return util.exportExcel(list, "汽修一厂库存清单");
    }

    /**
     * 获取汽配库存详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(vhAutopartsStoreService.selectVhAutopartsStoreById(id));
    }

    /**
     * 新增汽配库存
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhAutopartsStore:add')")
    @Log(title = "汽配库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VhAutopartsStore vhAutopartsStore) {
        return toAjax(vhAutopartsStoreService.insertVhAutopartsStore(vhAutopartsStore));
    }

    /**
     * 修改汽配库存
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhAutopartsStore:edit')")
    @Log(title = "汽配库存", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody VhAutopartsStore vhAutopartsStore) {
        return toAjax(vhAutopartsStoreService.updateVhAutopartsStore(vhAutopartsStore));
    }

    /**
     * 删除汽配库存
     */
    @PreAuthorize("@ss.hasPermi('vehicle:VhAutopartsStore:remove')")
    @Log(title = "汽配库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(vhAutopartsStoreService.deleteVhAutopartsStoreByIds(ids));
    }


    /**
     * 下载Excel模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<VhAutopartsStore> util = new ExcelUtil<>(VhAutopartsStore.class);
        return util.importTemplateExcel("汽配库存数据导入模板");
    }

    /**
     * 导入数据
     *
     * @param file 导入数据文件
     */
    @Log(title = "汽配库存", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('vehicle:VhAutopartsStore:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) {
//        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        ExcelUtil<VhAutopartsStore> util = new ExcelUtil<>(VhAutopartsStore.class);
        try {
            List<VhAutopartsStore> list = util.importExcel(file.getInputStream());
            vhAutopartsStoreService.split(list);
            AsyncManager.me().execute(AsyncFactory.storeImport(list));
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("模板异常，请用系统模板！");
        }
    }


}
