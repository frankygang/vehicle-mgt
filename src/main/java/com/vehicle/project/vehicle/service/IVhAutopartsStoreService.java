package com.vehicle.project.vehicle.service;

import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.pojo.PartsUseNumVo;

import java.util.List;

/**
 * 汽配库存Service接口
 *
 * @author bobo
 * @date 2020-08-24
 */
public interface IVhAutopartsStoreService {
    /**
     * 查询汽配库存
     *
     * @param id 汽配库存ID
     * @return 汽配库存
     */
    public VhAutopartsStore selectVhAutopartsStoreById(Long id);

    /**
     * 查询汽配库存列表
     *
     * @param vhAutopartsStore 汽配库存
     * @return 汽配库存集合
     */
    public List<VhAutopartsStore>  selectVhAutopartsStoreList(VhAutopartsStore vhAutopartsStore);

    /**
     * 新增汽配库存
     *
     * @param vhAutopartsStore 汽配库存
     * @return 结果
     */
    public int insertVhAutopartsStore(VhAutopartsStore vhAutopartsStore);

    /**
     * 修改汽配库存
     *
     * @param vhAutopartsStore 汽配库存
     * @return 结果
     */
    public int updateVhAutopartsStore(VhAutopartsStore vhAutopartsStore);

    /**
     * 批量删除汽配库存
     *
     * @param ids 需要删除的汽配库存ID
     * @return 结果
     */
    public int deleteVhAutopartsStoreByIds(Long[] ids);

    /**
     * 删除汽配库存信息
     *
     * @param id 汽配库存ID
     * @return 结果
     */
    public int deleteVhAutopartsStoreById(Long id);

    void importList(List<VhAutopartsStore> list);


    List<PartsUseNumVo> selectPartsUseNum();

    List<VhAutopartsStore> selectVhAutopartsListByCodeAndName(String materialCode);

    public void split(List<VhAutopartsStore> list);
}
