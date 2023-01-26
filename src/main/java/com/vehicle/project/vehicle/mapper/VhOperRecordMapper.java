package com.vehicle.project.vehicle.mapper;

import com.vehicle.project.vehicle.domain.VhOperRecord;

import java.util.List;

/**
 * 操作记录Mapper接口
 *
 * @author bobo
 * @date 2020-02-03
 */
public interface VhOperRecordMapper {
    /**
     * 查询操作记录
     *
     * @param id 操作记录ID
     * @return 操作记录
     */
    public VhOperRecord selectVhOperRecordById(Long id);

    /**
     * 查询操作记录列表
     *
     * @param vhOperRecord 操作记录
     * @return 操作记录集合
     */
    public List<VhOperRecord> selectVhOperRecordList(VhOperRecord vhOperRecord);

    /**
     * 新增操作记录
     *
     * @param vhOperRecord 操作记录
     * @return 结果
     */
    public int insertVhOperRecord(VhOperRecord vhOperRecord);

    /**
     * 修改操作记录
     *
     * @param vhOperRecord 操作记录
     * @return 结果
     */
    public int updateVhOperRecord(VhOperRecord vhOperRecord);

    /**
     * 删除操作记录
     *
     * @param id 操作记录ID
     * @return 结果
     */
    public int deleteVhOperRecordById(Long id);

    /**
     * 批量删除操作记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhOperRecordByIds(Long[] ids);
}
