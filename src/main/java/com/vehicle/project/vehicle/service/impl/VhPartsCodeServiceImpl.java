package com.vehicle.project.vehicle.service.impl;

import com.alibaba.fastjson.JSON;
import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import com.vehicle.project.vehicle.mapper.VhBusinessInfoMapper;
import com.vehicle.project.vehicle.mapper.VhPartsCodeMapper;
import com.vehicle.project.vehicle.service.IVhPartsCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 配件代码Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhPartsCodeServiceImpl implements IVhPartsCodeService {
    @Autowired
    private VhPartsCodeMapper vhPartsCodeMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    /**
     * 查询配件代码
     *
     * @param id 配件代码ID
     * @return 配件代码
     */
    @Override
    public VhPartsCode selectVhPartsCodeById(Long id) {
        return vhPartsCodeMapper.selectVhPartsCodeById(id);
    }

    @Override
    public List<VhPartsCode> selectVhPartsCodeByIds(Long[] ids) {
        return vhPartsCodeMapper.selectVhPartsCodeByIds(ids);
    }

    /**
     * 查询配件代码列表
     *
     * @param vhPartsCode 配件代码
     * @return 配件代码
     */
    @Override
    public List<VhPartsCode> selectVhPartsCodeList(VhPartsCode vhPartsCode) {
        return vhPartsCodeMapper.selectVhPartsCodeList(vhPartsCode);
    }

    /**
     * 新增配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    @Override
    public int insertVhPartsCode(VhPartsCode vhPartsCode) {
        vhPartsCode.setCreateTime(DateUtils.getNowDate());
        return vhPartsCodeMapper.insertVhPartsCode(vhPartsCode);
    }

    /**
     * 修改配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    @Override
    public int updateVhPartsCode(VhPartsCode vhPartsCode) {
        vhPartsCode.setUpdateTime(DateUtils.getNowDate());
        return vhPartsCodeMapper.updateVhPartsCode(vhPartsCode);
    }

    /**
     * 批量删除配件代码
     *
     * @param ids 需要删除的配件代码ID
     * @return 结果
     */
    @Override
    public int deleteVhPartsCodeByIds(Long[] ids) {
        return vhPartsCodeMapper.deleteVhPartsCodeByIds(ids);
    }

    /**
     * 删除配件代码信息
     *
     * @param id 配件代码ID
     * @return 结果
     */
    @Override
    public int deleteVhPartsCodeById(Long id) {
        return vhPartsCodeMapper.deleteVhPartsCodeById(id);
    }


    @Override
    public LinkedHashMap<String, Object> importVhPartsCode(List<VhPartsCode> list, boolean isUpdateSupport, SysUser sysUser) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入配件代码数据不能为空！");
        }


        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        AtomicInteger successNum = new AtomicInteger();
        AtomicInteger failureNum = new AtomicInteger();


        StringBuffer successMsg = new StringBuffer();
        StringBuffer failureMsg = new StringBuffer();

        CopyOnWriteArrayList<VhPartsCode> insertList = new CopyOnWriteArrayList();
        CopyOnWriteArrayList<VhPartsCode> updateList = new CopyOnWriteArrayList();
        for (VhPartsCode obj : list) {
            try {
                if ("".equals(obj.getMaterialCode().trim())) {
                    failureNum.getAndIncrement();
                    continue;
                }
                // 验证是否存在这个配件代码的数据
//                VhPartsCode partsCode = vhPartsCodeMapper.selectVhPartsCode(new VhPartsCode(obj.getMaterialCode(), sysUser.getDept().getDeptName()));
                VhPartsCode partsCode = vhPartsCodeMapper.selectVhPartsCodeByPartsCodeAndDept(obj.getMaterialCode(), sysUser.getDept().getDeptName());
                if (partsCode == null) {

                    obj.setCreateBy(sysUser.getUserName());
                    obj.setMaintainDept(sysUser.getDept().getDeptName());
                    obj.setUserId(sysUser.getUserId());
                    insertList.add(obj);
//                    this.insertVhPartsCode(obj);
                    successNum.getAndIncrement();
                } else if (isUpdateSupport) {
                    obj.setId(partsCode.getId());
                    obj.setUpdateBy(sysUser.getUserName());
//                    this.updateVhPartsCode(obj);
                    updateList.add(obj);
                    successNum.getAndIncrement();
                }
            } catch (Exception e) {
                failureNum.getAndIncrement();
                String msg = "<br/>" + failureNum + "、配件代码>> " + obj.getMaterialCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        log.info("{} 单次导入数量 {}", sysUser.getDept().getDeptName(), String.valueOf(list.size()));
        if (!CollectionUtils.isEmpty(insertList)) {
            int insertRows = vhPartsCodeMapper.batchInsert(insertList);
            log.info("单次写入数量：{}", insertRows);
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            int updateRows = vhPartsCodeMapper.batchUpdate(updateList);
            log.info("单次更新数量：{}", updateRows);
        }

        map.put("sum", successNum.get() + failureNum.get());
        map.put("success", successNum.get());
        map.put("fail", failureNum.get());
        log.info(JSON.toJSONString(map, true));

        return map;
    }

    @Override
    public VhPartsCode selectVhPartsCode(VhPartsCode vhPartsCode) {

        return vhPartsCodeMapper.selectVhPartsCode(vhPartsCode);
    }
}
