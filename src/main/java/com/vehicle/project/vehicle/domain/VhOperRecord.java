package com.vehicle.project.vehicle.domain;

import com.vehicle.framework.aspectj.lang.annotation.Excel;

import java.util.Date;

import com.vehicle.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 操作记录对象 vh_oper_record
 *
 * @author bobo
 * @date 2020-02-03
 */
public class VhOperRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operDate;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operPeople;

    /** 操作记录 */
    @Excel(name = "操作记录")
    private String operRecord;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperPeople(String operPeople) {
        this.operPeople = operPeople;
    }

    public String getOperPeople() {
        return operPeople;
    }

    public void setOperRecord(String operRecord) {
        this.operRecord = operRecord;
    }

    public String getOperRecord() {
        return operRecord;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("operDate", getOperDate())
                .append("operPeople", getOperPeople())
                .append("operRecord", getOperRecord())
                .toString();
    }
}
