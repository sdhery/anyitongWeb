package com.framework.module.customer.service.impl;

import com.framework.common.service.impl.BaseService;
import com.framework.module.customer.domain.Contacts;
import com.framework.module.customer.service.IContactsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/7/7 11:43
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/7/7 11:43
 * �޸ı�ע��
 */
@Service
public class ContactsService extends BaseService<Contacts,Integer> implements IContactsService {
    public List<Contacts> findByCustomerId(Integer customerId) {
        return baseDao.findByList("findByCustomerId",customerId);
    }
}
