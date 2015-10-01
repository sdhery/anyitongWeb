package com.framework.module.customer.vo;

import com.framework.module.customer.domain.Customer;

import java.io.Serializable;


public class CustomerVoList extends Customer implements Serializable {
    private String editRealName;
    private String businessName;

    public String getEditRealName() {
        return editRealName;
    }

    public void setEditRealName(String editRealName) {
        this.editRealName = editRealName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
