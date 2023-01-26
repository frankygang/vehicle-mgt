package com.vehicle.project.vehicle.mapper;

import com.github.pagehelper.Page;
import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.pojo.PartsUseNumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 汽配库存Mapper接口
 *
 * @author bobo
 * @date 2020-08-24
 */
public interface VhAutopartsStoreMapper {
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
    List<VhAutopartsStore> selectVhAutopartsStoreList(VhAutopartsStore vhAutopartsStore);

    List<VhAutopartsStore> selectVhAutopartsListByCodeAndName(@Param("materialCode") String materialCode);

    Page<VhAutopartsStore> selectVhAutopartsStorePageList(VhAutopartsStore vhAutopartsStore);

    VhAutopartsStore selectVhAutopartsStore(VhAutopartsStore vhAutopartsStore);

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
     * 删除汽配库存
     *
     * @param id 汽配库存ID
     * @return 结果
     */
    public int deleteVhAutopartsStoreById(Long id);

    /**
     * 批量删除汽配库存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVhAutopartsStoreByIds(Long[] ids);

    List<PartsUseNumVo> selectPartsUseNum();

    int batchInsert(List<VhAutopartsStore> insertList);

    int batchUpdate(CopyOnWriteArrayList<VhAutopartsStore> updateList);
}
