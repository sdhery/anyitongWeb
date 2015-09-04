package com.framework.module.customer.service;

import com.framework.common.service.IService;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.vo.CustomerVo;
import com.framework.module.customer.vo.CustomerVoList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ICustomerService extends IService<Customer,Integer> {
    public void saveCustomerVo(CustomerVo customerVo,MultipartFile mapPicPath,MultipartFile certPicPath);

    public CustomerVo getCustomerVoByCustomerId(Integer customerId);

    public void approval(Customer customer);

    public List<CustomerVoList> findCustomerVoList(Customer customer);
}
