package com.framework.module.customer.vo;

import com.framework.module.customer.domain.Customer;

import java.io.Serializable;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/8/2 17:20
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/8/2 17:20
 * �޸ı�ע��
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
