package com.vehicle.project.vehicle.service;

import com.vehicle.project.system.domain.SysUser;
import com.vehicle.project.vehicle.domain.VhCommonPartsImport;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 通用配件信息导入Service接口
 * 
 * @author onion
 * @date 2020-02-24
 */
public interface IVhCommonPartsImportService 
{
    /**
     * 查询通用配件信息导入
     * 
     * @param id 通用配件信息导入ID
     * @return 通用配件信息导入
     */
    public VhCommonPartsImport selectVhCommonPartsImportById(Long id);

    /**
     * 查询通用配件信息导入列表
     * 
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 通用配件信息导入集合
     */
    public List<VhCommonPartsImport> selectVhCommonPartsImportList(VhCommonPartsImport vhCommonPartsImport);

    /**
     * 新增通用配件信息导入
     * 
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 结果
     */
    public int insertVhCommonPartsImport(VhCommonPartsImport vhCommonPartsImport);

    /**
     * 修改通用配件信息导入
     * 
     * @param vhCommonPartsImport 通用配件信息导入
     * @return 结果
     */
    public int updateVhCommonPartsImport(VhCommonPartsImport vhCommonPartsImport);

    /**
     * 批量删除通用配件信息导入
     * 
     * @param ids 需要删除的通用配件信息导入ID
     * @return 结果
     */
    int deleteVhCommonPartsImportByIds(Long[] ids);

    /**
     * 删除通用配件信息导入信息
     * 
     * @param id 通用配件信息导入ID
     * @return 结果
     */
    int deleteVhCommonPartsImportById(Long id);

    /**
     * 导入 通用配件信息导入信息
     *
     * @param list 通用配件信息
     * @return 结果
     */
    LinkedHashMap<String, Object> commonPartsImport(List<VhCommonPartsImport> list, boolean isUpdateSupport, SysUser sysUser);

    /**
     * 删除所有通用配件信息导入信息
     */
    void cleanAll(String deptName);
}
