package com.framework.module.customer.dao.impl;

import com.framework.common.dao.impl.BaseDao;
import com.framework.module.customer.dao.ICustomerDao;
import com.framework.module.customer.domain.Customer;
import org.springframework.stereotype.Service;


@Service
public class CustomerDao extends BaseDao<Customer,Integer> implements ICustomerDao {
}
