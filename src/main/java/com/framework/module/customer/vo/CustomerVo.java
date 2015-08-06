package com.framework.module.customer.vo;

import com.framework.module.customer.domain.Contacts;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.domain.HealthStatus;
import com.framework.module.customer.domain.ReliefAgencies;
import com.framework.module.user.domain.SysUser;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/14 15:49
 * 修改人：Administrator
 * 修改时间：2015/7/14 15:49
 * 修改备注：
 */
public class CustomerVo implements Serializable{
    private Customer customer;
    private List<Contacts> contactsList;
    private List<ReliefAgencies> reliefAgenciesList;
    private List<HealthStatus> healthStatusList;
    private SysUser sysUser;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    public List<ReliefAgencies> getReliefAgenciesList() {
        return reliefAgenciesList;
    }

    public void setReliefAgenciesList(List<ReliefAgencies> reliefAgenciesList) {
        this.reliefAgenciesList = reliefAgenciesList;
    }

    public List<HealthStatus> getHealthStatusList() {
        return healthStatusList;
    }

    public void setHealthStatusList(List<HealthStatus> healthStatusList) {
        this.healthStatusList = healthStatusList;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
