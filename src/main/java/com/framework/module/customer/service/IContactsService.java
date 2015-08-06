package com.framework.module.customer.service;

import com.framework.common.service.IService;
import com.framework.module.customer.domain.Contacts;

import java.util.List;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/7 11:42
 * 修改人：Administrator
 * 修改时间：2015/7/7 11:42
 * 修改备注：
 */
public interface IContactsService extends IService<Contacts,Integer> {
    List<Contacts> findByCustomerId(Integer customerId);
}
