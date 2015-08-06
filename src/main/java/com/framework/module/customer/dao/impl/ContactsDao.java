package com.framework.module.customer.dao.impl;

import com.framework.common.dao.impl.BaseDao;
import com.framework.module.customer.dao.IContactsDao;
import com.framework.module.customer.domain.Contacts;
import org.springframework.stereotype.Service;


@Service
public class ContactsDao extends BaseDao<Contacts,Integer> implements IContactsDao {
}
