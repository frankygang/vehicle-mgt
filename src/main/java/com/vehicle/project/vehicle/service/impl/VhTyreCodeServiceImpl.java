package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhTyreCode;
import com.vehicle.project.vehicle.mapper.VhTyreCodeMapper;
import com.vehicle.project.vehicle.service.IVhTyreCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮胎代码Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhTyreCodeServiceImpl implements IVhTyreCodeService {
    @Autowired
    private VhTyreCodeMapper vhTyreCodeMapper;

    /**
     * 查询轮胎代码
     *
     * @param id 轮胎代码ID
     * @return 轮胎代码
     */
    @Override
    public VhTyreCode selectVhTyreCodeById(Long id) {
        return vhTyreCodeMapper.selectVhTyreCodeById(id);
    }

    @Override
    public List<VhTyreCode> selectVhTyreCodeByIds(Long[] ids) {
        return vhTyreCodeMapper.selectVhTyreCodeByIds(ids);
    }

    @Override
    public VhTyreCode selectVhTyreCode(VhTyreCode vhTyreCode) {
        return vhTyreCodeMapper.selectVhTyreCode(vhTyreCode);
    }

    /**
     * 查询轮胎代码列表
     *
     * @param vhTyreCode 轮胎代码
     * @return 轮胎代码
     */
    @Override
    public List<VhTyreCode> selectVhTyreCodeList(VhTyreCode vhTyreCode) {
        return vhTyreCodeMapper.selectVhTyreCodeList(vhTyreCode);
    }

    /**
     * 新增轮胎代码
     *
     * @param vhTyreCode 轮胎代码
     * @return 结果
     */
    @Override
    public int insertVhTyreCode(VhTyreCode vhTyreCode) {
        vhTyreCode.setCreateTime(DateUtils.getNowDate());
        return vhTyreCodeMapper.insertVhTyreCode(vhTyreCode);
    }

    /**
     * 修改轮胎代码
     *
     * @param vhTyreCode 轮胎代码
     * @return 结果
     */
    @Override
    public int updateVhTyreCode(VhTyreCode vhTyreCode) {
        vhTyreCode.setUpdateTime(DateUtils.getNowDate());
        return vhTyreCodeMapper.updateVhTyreCode(vhTyreCode);
    }

    /**
     * 批量删除轮胎代码
     *
     * @param ids 需要删除的轮胎代码ID
     * @return 结果
     */
    @Override
    public int deleteVhTyreCodeByIds(Long[] ids) {
        return vhTyreCodeMapper.deleteVhTyreCodeByIds(ids);
    }

    /**
     * 删除轮胎代码信息
     *
     * @param id 轮胎代码ID
     * @return 结果
     */
    @Override
    public int deleteVhTyreCodeById(Long id) {
        return vhTyreCodeMapper.deleteVhTyreCodeById(id);
    }


    @Override
    public String importVhTyreCode(List<VhTyreCode> list, boolean isUpdateSupport, String username) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入轮胎代码数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        for (VhTyreCode obj : list) {
            try {
                // 验证是否存在这个轮胎代码的数据
//                VhTyreCode tyreCode = vhTyreCodeMapper.selectVhTyreCode(new VhTyreCode(obj.getTyreCode(), sysUser.getDept().getDeptName()));
                VhTyreCode tyreCode = vhTyreCodeMapper.selectVhTyreCodeByCodeAndDept(obj.getTyreCode(), sysUser.getDept().getDeptName());
                if (tyreCode == null) {
                    obj.setCreateBy(username);
                    obj.setMaintainDept(sysUser.getDept().getDeptName());
                    obj.setUserId(sysUser.getUserId());
                    this.insertVhTyreCode(obj);
                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、轮胎代码>> " + obj.getTyreCode() + " 导入成功");
                } else if (isUpdateSupport) {
                    obj.setId(tyreCode.getId());
                    obj.setUpdateBy(username);
                    this.updateVhTyreCode(obj);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、轮胎代码>> " + tyreCode.getTyreCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、轮胎代码>> " + tyreCode.getTyreCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、轮胎代码>> " + obj.getTyreCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
