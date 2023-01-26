package com.vehicle.project.vehicle.service;

import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhPartsCode;
import com.vehicle.project.vehicle.pojo.PartsImportBo;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 配件代码Service接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface IVhPartsCodeService {
    /**
     * 查询配件代码
     *
     * @param id 配件代码ID
     * @return 配件代码
     */
    public VhPartsCode selectVhPartsCodeById(Long id);

    /**
     * 查询配件代码
     *
     * @param ids 配件代码ID数组
     * @return 配件代码集合
     */
    List<VhPartsCode> selectVhPartsCodeByIds(Long[] ids);

    /**
     * 查询配件代码列表
     *
     * @param vhPartsCode 配件代码
     * @return 配件代码集合
     */
    public List<VhPartsCode> selectVhPartsCodeList(VhPartsCode vhPartsCode);

    /**
     * 新增配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    public int insertVhPartsCode(VhPartsCode vhPartsCode);

    /**
     * 修改配件代码
     *
     * @param vhPartsCode 配件代码
     * @return 结果
     */
    public int updateVhPartsCode(VhPartsCode vhPartsCode);

    /**
     * 批量删除配件代码
     *
     * @param ids 需要删除的配件代码ID
     * @return 结果
     */
    public int deleteVhPartsCodeByIds(Long[] ids);

    /**
     * 删除配件代码信息
     *
     * @param id 配件代码ID
     * @return 结果
     */
    public int deleteVhPartsCodeById(Long id);


    LinkedHashMap<String, Object> importVhPartsCode(List<VhPartsCode> list, boolean isUpdateSupport, SysUser sysUser);

    VhPartsCode selectVhPartsCode(VhPartsCode vhPartsCode);
}
