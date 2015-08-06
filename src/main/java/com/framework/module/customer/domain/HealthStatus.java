package com.framework.module.customer.domain;

import com.framework.common.domain.BaseDomain;

import java.util.List;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/14 15:24
 * 修改人：Administrator
 * 修改时间：2015/7/14 15:24
 * 修改备注：
 */
public class HealthStatus extends BaseDomain {
    private Integer healthStatusId;
    private Integer customerId;
    private Integer type;//健忘状况类型:0身体状况
    private String statusDes;
    private List<String> statusDesList;

    public Integer getHealthStatusId() {
        return healthStatusId;
    }

    public void setHealthStatusId(Integer healthStatusId) {
        this.healthStatusId = healthStatusId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public List<String> getStatusDesList() {
        return statusDesList;
    }

    public void setStatusDesList(List<String> statusDesList) {
        this.statusDesList = statusDesList;
    }
}
