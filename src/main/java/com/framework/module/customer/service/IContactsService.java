package com.framework.module.customer.service;

import com.framework.common.service.IService;
import com.framework.module.customer.domain.Contacts;

import java.util.List;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/7/7 11:42
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/7/7 11:42
 * �޸ı�ע��
 */
public interface IContactsService extends IService<Contacts,Integer> {
    List<Contacts> findByCustomerId(Integer customerId);
}
