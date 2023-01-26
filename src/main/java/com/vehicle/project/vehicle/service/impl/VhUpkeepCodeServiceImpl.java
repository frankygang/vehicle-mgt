package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.exception.CustomException;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhUpkeepCode;
import com.vehicle.project.vehicle.mapper.VhUpkeepCodeMapper;
import com.vehicle.project.vehicle.service.IVhUpkeepCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保养代码Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Service
public class VhUpkeepCodeServiceImpl implements IVhUpkeepCodeService {

    private static final Logger log = LoggerFactory.getLogger(VhUpkeepCodeServiceImpl.class);

    @Autowired
    private VhUpkeepCodeMapper vhUpkeepCodeMapper;

    /**
     * 查询保养代码
     *
     * @param id 保养代码ID
     * @return 保养代码
     */
    @Override
    public VhUpkeepCode selectVhUpkeepCodeById(Long id) {
        return vhUpkeepCodeMapper.selectVhUpkeepCodeById(id);
    }

    /**
     * 查询保养代码
     *
     * @param upkeepCode 保养代码
     * @return 保养代码
     */
    @Override
    public VhUpkeepCode selectVhUpkeepCodeByCode(String upkeepCode) {
        return vhUpkeepCodeMapper.selectVhUpkeepCodeByCode(upkeepCode);
    }

    @Override
    public List<VhUpkeepCode> selectVhUpkeepCodeByIds(Long[] ids) {
        return vhUpkeepCodeMapper.selectVhUpkeepCodeByIds(ids);
    }

    /**
     * 查询保养代码列表
     *
     * @param vhUpkeepCode 保养代码
     * @return 保养代码
     */
    @Override
    public List<VhUpkeepCode> selectVhUpkeepCodeList(VhUpkeepCode vhUpkeepCode) {
        return vhUpkeepCodeMapper.selectVhUpkeepCodeList(vhUpkeepCode);
    }

    @Override
    public VhUpkeepCode selectVhUpkeepCode(VhUpkeepCode vhUpkeepCode) {
        return vhUpkeepCodeMapper.selectVhUpkeepCode(vhUpkeepCode);
    }

    /**
     * 新增保养代码
     *
     * @param vhUpkeepCode 保养代码
     * @return 结果
     */
    @Override
    public int insertVhUpkeepCode(VhUpkeepCode vhUpkeepCode) {
        vhUpkeepCode.setCreateTime(DateUtils.getNowDate());
        return vhUpkeepCodeMapper.insertVhUpkeepCode(vhUpkeepCode);
    }

    /**
     * 修改保养代码
     *
     * @param vhUpkeepCode 保养代码
     * @return 结果
     */
    @Override
    public int updateVhUpkeepCode(VhUpkeepCode vhUpkeepCode) {
        vhUpkeepCode.setUpdateTime(DateUtils.getNowDate());
        return vhUpkeepCodeMapper.updateVhUpkeepCode(vhUpkeepCode);
    }

    /**
     * 批量删除保养代码
     *
     * @param ids 需要删除的保养代码ID
     * @return 结果
     */
    @Override
    public int deleteVhUpkeepCodeByIds(Long[] ids) {
        return vhUpkeepCodeMapper.deleteVhUpkeepCodeByIds(ids);
    }

    /**
     * 删除保养代码信息
     *
     * @param id 保养代码ID
     * @return 结果
     */
    @Override
    public int deleteVhUpkeepCodeById(Long id) {
        return vhUpkeepCodeMapper.deleteVhUpkeepCodeById(id);
    }

    /**
     * 导入 保养代码信息
     *
     * @param vhUpkeepCodeList 保养代码数据列表
     * @param isUpdateSupport  是否更新支持，如果已存在，则进行更新数据
     * @param username         操作用户
     * @return 结果
     */
    @Override
    public String importVhUpKeepCode(List<VhUpkeepCode> vhUpkeepCodeList, boolean isUpdateSupport, String username) {
        if (StringUtils.isNull(vhUpkeepCodeList) || vhUpkeepCodeList.size() == 0) {
            throw new CustomException("导入保养代码信息数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();

        for (VhUpkeepCode vhUpkeepCode : vhUpkeepCodeList) {
            try {
                // 验证是否存在这个保养代码的数据
//                VhUpkeepCode s = vhUpkeepCodeMapper.selectVhUpkeepCode(new VhUpkeepCode(vhUpkeepCode.getUpkeepCode(), sysUser.getDept().getDeptName()));
                VhUpkeepCode s = vhUpkeepCodeMapper.selectVhUpkeepCodeByCodeAndDept(vhUpkeepCode.getUpkeepCode(), sysUser.getDept().getDeptName());
                if (StringUtils.isNull(s)) {
                    vhUpkeepCode.setCreateBy(username);
                    vhUpkeepCode.setMaintainDept(sysUser.getDept().getDeptName());
                    vhUpkeepCode.setUserId(sysUser.getUserId());
                    this.insertVhUpkeepCode(vhUpkeepCode);
                    successNum++;
                } else if (isUpdateSupport) {
                    vhUpkeepCode.setId(s.getId());
                    vhUpkeepCode.setUpdateBy(username);
                    this.updateVhUpkeepCode(vhUpkeepCode);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、保养代码>> " + vhUpkeepCode.getUpkeepCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、保养代码>> " + vhUpkeepCode.getUpkeepCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、保养代码>> " + vhUpkeepCode.getUpkeepCode() + " 导入失败：";
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
