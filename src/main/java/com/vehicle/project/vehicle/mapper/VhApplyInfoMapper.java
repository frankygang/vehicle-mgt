package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.domain.VhApplyInfo;
import java.util.List;

/**
 * 配件申购Mapper接口
 * 
 * @author .
 * @date 2020-09-19
 */
public interface VhApplyInfoMapper 
{
    /**
     * 查询配件申购
     * 
     * @param id 配件申购ID
     * @return 配件申购
     */
    public VhApplyInfo selectVhApplyInfoById(Long id);

    /**
     * 查询配件申购列表
     * 
     * @param vhApplyInfo 配件申购
     * @return 配件申购集合
     */
    public List<VhApplyInfo> selectVhApplyInfoList(VhApplyInfo vhApplyInfo);

    /**
     * 新增配件申购
     * 
     * @param vhApplyInfo 配件申购
     * @return 结果
     */
    public int insertVhApplyInfo(VhApplyInfo vhApplyInfo);

    /**
     * 修改配件申购
     * 
     * @param vhApplyInfo 配件申购
     * @return 结果
     */
    public int updateVhApplyInfo(VhApplyInfo vhApplyInfo);

    /**
     * 删除配件申购
     * 
     * @param id 配件申购ID
     * @return 结果
     */
    public int deleteVhApplyInfoById(Long id);

    /**
     * 批量删除配件申购
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhApplyInfoByIds(Long[] ids);

    VhApplyInfo genPurchaseNo();
}
