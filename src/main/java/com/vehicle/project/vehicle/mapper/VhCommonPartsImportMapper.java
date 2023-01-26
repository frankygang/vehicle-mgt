package com.vehicle.project.vehicle.mapper;

import com.vehicle.framework.aspectj.lang.annotation.DataScope;
import com.vehicle.project.vehicle.domain.VhCommonPartsImport;
import java.util.List;

/**
 * 通用配件信息导入Mapper接口
 * 
 * @author onion
 * @date 2020-02-24
 */
public interface VhCommonPartsImportMapper 
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
    @DataScope(deptAlias = "d", userAlias = "u")
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
     * 删除通用配件信息导入
     * 
     * @param id 通用配件信息导入ID
     * @return 结果
     */
    public int deleteVhCommonPartsImportById(Long id);

    /**
     * 批量删除通用配件信息导入
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhCommonPartsImportByIds(Long[] ids);

    void cleanAll(String deptName);
}
