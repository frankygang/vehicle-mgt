package com.vehicle.project.monitor.service;

import com.vehicle.project.monitor.domain.SysJob;

import java.util.List;

/**
 * 定时任务调度Service接口
 *
 * @author vehicle
 * @date 2019-12-27
 */
public interface ISysJobService {
    /**
     * 查询定时任务调度
     *
     * @param jobId 定时任务调度ID
     * @return 定时任务调度
     */
    public SysJob selectSysJobById(Long jobId);

    /**
     * 查询定时任务调度列表
     *
     * @param sysJob 定时任务调度
     * @return 定时任务调度集合
     */
    public List<SysJob> selectSysJobList(SysJob sysJob);

    /**
     * 新增定时任务调度
     *
     * @param sysJob 定时任务调度
     * @return 结果
     */
    public int insertSysJob(SysJob sysJob);

    /**
     * 修改定时任务调度
     *
     * @param sysJob 定时任务调度
     * @return 结果
     */
    public int updateSysJob(SysJob sysJob);

    /**
     * 批量删除定时任务调度
     *
     * @param jobIds 需要删除的定时任务调度ID
     * @return 结果
     */
    public int deleteSysJobByIds(Long[] jobIds);

    /**
     * 删除定时任务调度信息
     *
     * @param jobId 定时任务调度ID
     * @return 结果
     */
    public int deleteSysJobById(Long jobId);
}
