package com.vehicle.project.monitor.service.impl;

import com.vehicle.common.utils.DateUtils;
import com.vehicle.project.monitor.domain.SysJob;
import com.vehicle.project.monitor.mapper.SysJobMapper;
import com.vehicle.project.monitor.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度Service业务层处理
 *
 * @author bobo
 * @date 2019-12-27
 */
@Service
public class SysJobServiceImpl implements ISysJobService {
    @Autowired
    private SysJobMapper sysJobMapper;

    /**
     * 查询定时任务调度
     *
     * @param jobId 定时任务调度ID
     * @return 定时任务调度
     */
    @Override
    public SysJob selectSysJobById(Long jobId) {
        return sysJobMapper.selectSysJobById(jobId);
    }

    /**
     * 查询定时任务调度列表
     *
     * @param sysJob 定时任务调度
     * @return 定时任务调度
     */
    @Override
    public List<SysJob> selectSysJobList(SysJob sysJob) {
        return sysJobMapper.selectSysJobList(sysJob);
    }

    /**
     * 新增定时任务调度
     *
     * @param sysJob 定时任务调度
     * @return 结果
     */
    @Override
    public int insertSysJob(SysJob sysJob) {
        sysJob.setCreateTime(DateUtils.getNowDate());
        return sysJobMapper.insertSysJob(sysJob);
    }

    /**
     * 修改定时任务调度
     *
     * @param sysJob 定时任务调度
     * @return 结果
     */
    @Override
    public int updateSysJob(SysJob sysJob) {
        sysJob.setUpdateTime(DateUtils.getNowDate());
        return sysJobMapper.updateSysJob(sysJob);
    }

    /**
     * 批量删除定时任务调度
     *
     * @param jobIds 需要删除的定时任务调度ID
     * @return 结果
     */
    @Override
    public int deleteSysJobByIds(Long[] jobIds) {
        return sysJobMapper.deleteSysJobByIds(jobIds);
    }

    /**
     * 删除定时任务调度信息
     *
     * @param jobId 定时任务调度ID
     * @return 结果
     */
    @Override
    public int deleteSysJobById(Long jobId) {
        return sysJobMapper.deleteSysJobById(jobId);
    }
}
