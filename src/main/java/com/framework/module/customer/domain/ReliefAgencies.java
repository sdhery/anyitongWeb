package com.framework.module.customer.domain;

import com.framework.common.domain.BaseDomain;

/**
 * 项目名称：anyitongWeb
 * 类名称：救助机构
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/14 11:41
 * 修改人：Administrator
 * 修改时间：2015/7/14 11:41
 * 修改备注：
 */
public class ReliefAgencies extends BaseDomain {
    private Integer reliefAgenciesId;
    private Integer customerId;
    private String name;//名称
    private String contact;//联系人/部门
    private String phone;//联系电话
    private String address;//地址
    private Integer type;//救助机构类型:0街道/镇,1派出所,2村，3服务中心，4医院，5急救中心

    public Integer getReliefAgenciesId() {
        return reliefAgenciesId;
    }

    public void setReliefAgenciesId(Integer reliefAgenciesId) {
        this.reliefAgenciesId = reliefAgenciesId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
