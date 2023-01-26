package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.utils.DateUtils;
import com.vehicle.project.vehicle.domain.VhAutopartsStore;
import com.vehicle.project.vehicle.mapper.VhAutopartsStoreMapper;
import com.vehicle.project.vehicle.pojo.PartsUseNumVo;
import com.vehicle.project.vehicle.service.IVhAutopartsStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 汽配库存Service业务层处理
 *
 * @author bobo
 * @date 2020-08-24
 */
@Service
@Slf4j
public class VhAutopartsStoreServiceImpl implements IVhAutopartsStoreService {

    @Autowired
    private VhAutopartsStoreMapper vhAutopartsStoreMapper;

    /**
     * 查询汽配库存
     *
     * @param id 汽配库存ID
     * @return 汽配库存
     */
    @Override
    public VhAutopartsStore selectVhAutopartsStoreById(Long id) {
        return vhAutopartsStoreMapper.selectVhAutopartsStoreById(id);
    }

    /**
     * 查询汽配库存列表
     *
     * @param vhAutopartsStore 汽配库存
     * @return 汽配库存
     */
    @Override
    public List<VhAutopartsStore> selectVhAutopartsStoreList(VhAutopartsStore vhAutopartsStore) {
        return vhAutopartsStoreMapper.selectVhAutopartsStoreList(vhAutopartsStore);
    }

    /**
     * 新增汽配库存
     *
     * @param vhAutopartsStore 汽配库存
     * @return 结果
     */
    @Override
    public int insertVhAutopartsStore(VhAutopartsStore vhAutopartsStore) {
        vhAutopartsStore.setCreateTime(DateUtils.getNowDate());
        return vhAutopartsStoreMapper.insertVhAutopartsStore(vhAutopartsStore);
    }

    /**
     * 修改汽配库存
     *
     * @param vhAutopartsStore 汽配库存
     * @return 结果
     */
    @Override
    public int updateVhAutopartsStore(VhAutopartsStore vhAutopartsStore) {
        vhAutopartsStore.setUpdateTime(DateUtils.getNowDate());
        return vhAutopartsStoreMapper.updateVhAutopartsStore(vhAutopartsStore);
    }

    /**
     * 批量删除汽配库存
     *
     * @param ids 需要删除的汽配库存ID
     * @return 结果
     */
    @Override
    public int deleteVhAutopartsStoreByIds(Long[] ids) {
        return vhAutopartsStoreMapper.deleteVhAutopartsStoreByIds(ids);
    }

    /**
     * 删除汽配库存信息
     *
     * @param id 汽配库存ID
     * @return 结果
     */
    @Override
    public int deleteVhAutopartsStoreById(Long id) {
        return vhAutopartsStoreMapper.deleteVhAutopartsStoreById(id);
    }

    @Override
    public void importList(List<VhAutopartsStore> list) {
        CopyOnWriteArrayList<VhAutopartsStore> insertList = new CopyOnWriteArrayList();
        list.forEach(obj -> {
            VhAutopartsStore vhAutopartsStore = vhAutopartsStoreMapper.selectVhAutopartsStore(new VhAutopartsStore(obj.getMaterialCode()));
            if (vhAutopartsStore != null) {
                obj.setId(vhAutopartsStore.getId());
                vhAutopartsStoreMapper.updateVhAutopartsStore(obj);
            } else {
                insertList.add(obj);
            }
        });

        if (!CollectionUtils.isEmpty(insertList)) {
            int insertRows = vhAutopartsStoreMapper.batchInsert(insertList);
        }
    }

    @Override
    public void split(List<VhAutopartsStore> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(obj -> {
                if (obj.getMaterial().contains(" ")) {
                    String[] strings = obj.getMaterial().split(" ");
                    if (strings.length == 1) {
                        obj.setMaterialName(obj.getMaterial());
                    } else if (strings.length == 2) {
                        obj.setMaterialName(strings[0]);
                        obj.setUseRemark(strings[1]);
                    } else if (strings.length == 3) {
                        obj.setMaterialName(strings[0]);
                        obj.setSpec(strings[1]);
                        obj.setUseRemark(strings[2]);
                    } else {
                        obj.setMaterialName(strings[0]);
                        StringBuffer spec = new StringBuffer();
                        for (int i = 1; i < strings.length - 1; i++) {
                            spec.append(strings[i]).append(" ");
                        }
                        obj.setSpec(spec.toString());
                        obj.setUseRemark(strings[strings.length - 1]);
                    }
                }
            });
        }
    }

    @Override
    public List<PartsUseNumVo> selectPartsUseNum() {
        return vhAutopartsStoreMapper.selectPartsUseNum();
    }

    @Override
    public List<VhAutopartsStore> selectVhAutopartsListByCodeAndName(String materialCode) {
        return vhAutopartsStoreMapper.selectVhAutopartsListByCodeAndName(materialCode);
    }
}
