package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.mapper.VhFaultCodeMapper;
import com.vehicle.project.vehicle.service.IVhFaultCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 故障代码Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhFaultCodeServiceImpl implements IVhFaultCodeService {
    @Autowired
    private VhFaultCodeMapper vhFaultCodeMapper;

    /**
     * 查询故障代码
     *
     * @param id 故障代码ID
     * @return 故障代码
     */
    @Override
    public VhFaultCode selectVhFaultCodeById(Long id) {
        return vhFaultCodeMapper.selectVhFaultCodeById(id);
    }

    /**
     * 查询故障代码
     *
     * @param ids 故障代码IDS
     * @return 故障代码
     */
    @Override
    public List<VhFaultCode> selectVhFaultCodeByIds(Long[] ids) {
        return vhFaultCodeMapper.selectVhFaultCodeByIds(ids);
    }

    /**
     * 查询故障代码列表
     *
     * @param vhFaultCode 故障代码
     * @return 故障代码
     */
    @Override
    public List<VhFaultCode> selectVhFaultCodeList(VhFaultCode vhFaultCode) {
        return vhFaultCodeMapper.selectVhFaultCodeList(vhFaultCode);
    }

    /**
     * 新增故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    @Override
    public int insertVhFaultCode(VhFaultCode vhFaultCode) {
        vhFaultCode.setCreateTime(DateUtils.getNowDate());
        return vhFaultCodeMapper.insertVhFaultCode(vhFaultCode);
    }

    /**
     * 修改故障代码
     *
     * @param vhFaultCode 故障代码
     * @return 结果
     */
    @Override
    public int updateVhFaultCode(VhFaultCode vhFaultCode) {
        vhFaultCode.setUpdateTime(DateUtils.getNowDate());
        return vhFaultCodeMapper.updateVhFaultCode(vhFaultCode);
    }

    /**
     * 批量删除故障代码
     *
     * @param ids 需要删除的故障代码ID
     * @return 结果
     */
    @Override
    public int deleteVhFaultCodeByIds(Long[] ids) {
        return vhFaultCodeMapper.deleteVhFaultCodeByIds(ids);
    }

    /**
     * 删除故障代码信息
     *
     * @param id 故障代码ID
     * @return 结果
     */
    @Override
    public int deleteVhFaultCodeById(Long id) {
        return vhFaultCodeMapper.deleteVhFaultCodeById(id);
    }


    @Override
    public String importVhFaultCode(List<VhFaultCode> list, boolean isUpdateSupport, String username) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入故障代码数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();

        for (VhFaultCode obj : list) {
            try {
                // 验证是否存在这个故障代码的数据
//                VhFaultCode faultCode = vhFaultCodeMapper.selectVhMaintainCode(new VhFaultCode(obj.getFaultCode(), sysUser.getDept().getDeptName()));
                VhFaultCode faultCode = vhFaultCodeMapper.selectVhFaultCodeByFaultCodeAndDept(obj.getFaultCode(), sysUser.getDept().getDeptName());
                if (faultCode == null) {
                    obj.setCreateBy(username);
                    obj.setMaintainDept(sysUser.getDept().getDeptName());
                    obj.setUserId(sysUser.getUserId());
                    this.insertVhFaultCode(obj);
                    successNum++;
                } else if (isUpdateSupport) {
                    faultCode.setCodeExplain(obj.getCodeExplain());
                    faultCode.setUpdateBy(username);
                    this.updateVhFaultCode(faultCode);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、故障代码>> " + faultCode.getFaultCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、故障代码>> " + faultCode.getFaultCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、故障代码>> " + obj.getFaultCode() + " 导入失败：";
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

    @Override
    public VhFaultCode selectVhMaintainCode(VhFaultCode vhFaultCode) {
        return vhFaultCodeMapper.selectVhMaintainCode(vhFaultCode);
    }
}
