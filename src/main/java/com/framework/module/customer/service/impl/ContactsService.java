package com.framework.module.customer.service.impl;

import com.framework.common.service.impl.BaseService;
import com.framework.module.customer.domain.Contacts;
import com.framework.module.customer.service.IContactsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/7 11:43
 * 修改人：Administrator
 * 修改时间：2015/7/7 11:43
 * 修改备注：
 */
@Service
public class ContactsService extends BaseService<Contacts,Integer> implements IContactsService {
    public List<Contacts> findByCustomerId(Integer customerId) {
        return baseDao.findByList("findByCustomerId",customerId);
    }
}
