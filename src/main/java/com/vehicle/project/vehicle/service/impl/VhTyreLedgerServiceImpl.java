package com.vehicle.project.vehicle.service.impl;

import com.vehicle.common.utils.SecurityUtils;
import com.vehicle.framework.aspectj.lang.enums.BusinessType;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.project.vehicle.domain.*;
import com.vehicle.project.vehicle.mapper.*;
import com.vehicle.project.vehicle.pojo.CommonLedgerBo;
import com.vehicle.project.vehicle.service.IVhTyreLedgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 轮胎台账Service业务层处理
 *
 * @author bobo
 * @date 2020-02-03
 */
@Slf4j
@Service
public class VhTyreLedgerServiceImpl implements IVhTyreLedgerService {
    @Autowired
    private VhTyreLedgerMapper vhTyreLedgerMapper;

    @Autowired
    private VhTyreCodeMapper vhTyreCodeMapper;

    @Autowired
    private VhBusinessInfoMapper businessInfoMapper;

    @Autowired
    private VhLedgerMainMapper ledgerMainMapper;

    @Autowired
    private VhVehicleInfoMapper vehicleInfoMapper;

    /**
     * 查询轮胎台账
     *
     * @param id 轮胎台账ID
     * @return 轮胎台账
     */
    @Override
    public VhTyreLedger selectVhTyreLedgerById(Long id) {
        return vhTyreLedgerMapper.selectVhTyreLedgerById(id);
    }


    /**
     * 新增轮胎台账
     *
     * @param vhTyreLedger 轮胎台账
     * @return 结果
     */
    @Override
    public int insertVhTyreLedger(VhTyreLedger vhTyreLedger) {
        return vhTyreLedgerMapper.insertVhTyreLedger(vhTyreLedger);
    }


    /**
     * 修改轮胎台账
     *
     * @param vhTyreLedger 轮胎台账
     * @return 结果
     */
    @Override
    public int updateVhTyreLedger(VhTyreLedger vhTyreLedger) {
        return vhTyreLedgerMapper.updateVhTyreLedger(vhTyreLedger);
    }

    /**
     * 批量删除轮胎台账
     *
     * @param ids 需要删除的轮胎台账ID
     * @return 结果
     */
    @Override
    public int deleteVhTyreLedgerByIds(Long[] ids) {
        return vhTyreLedgerMapper.deleteVhTyreLedgerByIds(ids);
    }

    /**
     * 删除轮胎台账信息
     *
     * @param id 轮胎台账ID
     * @return 结果
     */
    @Override
    public int deleteVhTyreLedgerById(Long id) {
        return vhTyreLedgerMapper.deleteVhTyreLedgerById(id);
    }



}
