package com.vehicle.framework.web.controller;

import com.vehicle.common.constant.HttpStatus;
import com.vehicle.common.utils.DateUtils;
import com.vehicle.common.utils.StringUtils;
import com.vehicle.common.utils.sql.SqlUtil;
import com.vehicle.framework.web.domain.AjaxResult;
import com.vehicle.framework.web.page.PageDomain;
import com.vehicle.framework.web.page.TableDataInfo;
import com.vehicle.framework.web.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author bobo
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     *
     * @return void
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    protected void startPage(Integer pageNum, Integer pageSize) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        if (CollectionUtils.isEmpty(list)) {
            rspData.setTotal(0);
        } else {
            rspData.setTotal(new PageInfo(list).getTotal());
        }
        return rspData;
    }

    protected TableDataInfo getDataTable(List<?> list, int total) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        if (CollectionUtils.isEmpty(list)) {
            rspData.setTotal(0);
        } else {
            PageInfo pageInfo = new PageInfo(list);
            pageInfo.setTotal(total);

            rspData.setTotal(total);
        }
        return rspData;
    }



    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
