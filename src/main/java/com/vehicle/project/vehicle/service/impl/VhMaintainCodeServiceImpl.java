package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhFaultCode;
import com.vehicle.project.vehicle.domain.VhMaintainCode;
import com.vehicle.project.vehicle.mapper.VhMaintainCodeMapper;
import com.vehicle.project.vehicle.service.IVhMaintainCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 维修代码Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhMaintainCodeServiceImpl implements IVhMaintainCodeService {
    @Autowired
    private VhMaintainCodeMapper vhMaintainCodeMapper;

    /**
     * 查询维修代码
     *
     * @param id 维修代码ID
     * @return 维修代码
     */
    @Override
    public VhMaintainCode selectVhMaintainCodeById(Long id) {
        return vhMaintainCodeMapper.selectVhMaintainCodeById(id);
    }

    @Override
    public List<VhMaintainCode> selectVhMaintainCodeByIds(Long[] ids) {
        return vhMaintainCodeMapper.selectVhMaintainCodeByIds(ids);
    }

    /**
     * 查询维修代码列表
     *
     * @param vhMaintainCode 维修代码
     * @return 维修代码
     */
    @Override
    public List<VhMaintainCode> selectVhMaintainCodeList(VhMaintainCode vhMaintainCode) {
        return vhMaintainCodeMapper.selectVhMaintainCodeList(vhMaintainCode);
    }

    @Override
    public VhMaintainCode selectVhMaintainCode(VhMaintainCode vhMaintainCode) {
        return vhMaintainCodeMapper.selectVhMaintainCode(vhMaintainCode);
    }

    /**
     * 新增维修代码
     *
     * @param vhMaintainCode 维修代码
     * @return 结果
     */
    @Override
    public int insertVhMaintainCode(VhMaintainCode vhMaintainCode) {
        vhMaintainCode.setCreateTime(DateUtils.getNowDate());
        return vhMaintainCodeMapper.insertVhMaintainCode(vhMaintainCode);
    }

    /**
     * 修改维修代码
     *
     * @param vhMaintainCode 维修代码
     * @return 结果
     */
    @Override
    public int updateVhMaintainCode(VhMaintainCode vhMaintainCode) {
        vhMaintainCode.setUpdateTime(DateUtils.getNowDate());
        return vhMaintainCodeMapper.updateVhMaintainCode(vhMaintainCode);
    }

    /**
     * 批量删除维修代码
     *
     * @param ids 需要删除的维修代码ID
     * @return 结果
     */
    @Override
    public int deleteVhMaintainCodeByIds(Long[] ids) {
        return vhMaintainCodeMapper.deleteVhMaintainCodeByIds(ids);
    }

    /**
     * 删除维修代码信息
     *
     * @param id 维修代码ID
     * @return 结果
     */
    @Override
    public int deleteVhMaintainCodeById(Long id) {
        return vhMaintainCodeMapper.deleteVhMaintainCodeById(id);
    }


    @Override
    public String importVhMaintainCode(List<VhMaintainCode> list, boolean isUpdateSupport, String username) {

        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入维修代码数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        for (VhMaintainCode obj : list) {
            try {
                // 验证是否存在这个维修代码的数据
                // VhMaintainCode maintainCode = vhMaintainCodeMapper.selectVhMaintainCode(new VhMaintainCode(obj.getMaintainCode(), sysUser.getDept().getDeptName()));
                VhMaintainCode maintainCode = vhMaintainCodeMapper.selectVhMaintainItemByMaintainCodeAndMaintainDept(obj.getMaintainCode(), sysUser.getDept().getDeptName());
                if (maintainCode == null) {
                    obj.setCreateBy(username);
                    obj.setMaintainDept(sysUser.getDept().getDeptName());
                    obj.setUserId(sysUser.getUserId());
                    this.insertVhMaintainCode(obj);
                    successNum++;
                } else if (isUpdateSupport) {
                    obj.setUpdateBy(username);
                    obj.setId(maintainCode.getId());
                    this.updateVhMaintainCode(obj);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、维修代码>> " + maintainCode.getMaintainCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、维修代码>> " + maintainCode.getMaintainCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、维修代码>> " + obj.getMaintainCode() + " 导入失败：";
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
    public List<VhFaultCode> getFaultCode(List<String> code) {
        if (!CollectionUtils.isEmpty(code)) {
            List<String> list = code.stream().map(item -> item.split("-")[0]).collect(Collectors.toList());
            return vhMaintainCodeMapper.getFaultCode(list);
        }
        return null;
    }

}
