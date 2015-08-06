package com.framework.module.customer.vo;

import com.framework.module.customer.domain.Customer;

import java.io.Serializable;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/8/2 17:20
 * 修改人：Administrator
 * 修改时间：2015/8/2 17:20
 * 修改备注：
 */
public class CustomerVoList extends Customer implements Serializable {
    private String editRealName;

    public String getEditRealName() {
        return editRealName;
    }

    public void setEditRealName(String editRealName) {
        this.editRealName = editRealName;
    }
}
