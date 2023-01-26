package com.vehicle.project.vehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.project.vehicle.mapper.VhOperRecordMapper;
import com.vehicle.project.vehicle.domain.VhOperRecord;
import com.vehicle.project.vehicle.service.IVhOperRecordService;

/**
 * 操作记录Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Service
public class VhOperRecordServiceImpl implements IVhOperRecordService {
    @Autowired
    private VhOperRecordMapper vhOperRecordMapper;

    /**
     * 查询操作记录
     *
     * @param id 操作记录ID
     * @return 操作记录
     */
    @Override
    public VhOperRecord selectVhOperRecordById(Long id) {
        return vhOperRecordMapper.selectVhOperRecordById(id);
    }

    /**
     * 查询操作记录列表
     *
     * @param vhOperRecord 操作记录
     * @return 操作记录
     */
    @Override
    public List<VhOperRecord> selectVhOperRecordList(VhOperRecord vhOperRecord) {
        return vhOperRecordMapper.selectVhOperRecordList(vhOperRecord);
    }

    /**
     * 新增操作记录
     *
     * @param vhOperRecord 操作记录
     * @return 结果
     */
    @Override
    public int insertVhOperRecord(VhOperRecord vhOperRecord) {
        return vhOperRecordMapper.insertVhOperRecord(vhOperRecord);
    }

    /**
     * 修改操作记录
     *
     * @param vhOperRecord 操作记录
     * @return 结果
     */
    @Override
    public int updateVhOperRecord(VhOperRecord vhOperRecord) {
        return vhOperRecordMapper.updateVhOperRecord(vhOperRecord);
    }

    /**
     * 批量删除操作记录
     *
     * @param ids 需要删除的操作记录ID
     * @return 结果
     */
    @Override
    public int deleteVhOperRecordByIds(Long[] ids) {
        return vhOperRecordMapper.deleteVhOperRecordByIds(ids);
    }

    /**
     * 删除操作记录信息
     *
     * @param id 操作记录ID
     * @return 结果
     */
    @Override
    public int deleteVhOperRecordById(Long id) {
        return vhOperRecordMapper.deleteVhOperRecordById(id);
    }
}
