package com.framework.module.customer.service.impl;

import com.framework.common.service.impl.BaseService;
import com.framework.common.util.FileUtil;
import com.framework.module.customer.dao.IContactsDao;
import com.framework.module.customer.dao.IHealthStatusDao;
import com.framework.module.customer.dao.IReliefAgenciesDao;
import com.framework.module.customer.domain.Contacts;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.domain.HealthStatus;
import com.framework.module.customer.domain.ReliefAgencies;
import com.framework.module.customer.service.IContactsService;
import com.framework.module.customer.service.ICustomerService;
import com.framework.module.customer.service.IHealthStatusService;
import com.framework.module.customer.service.IReliefAgenciesService;
import com.framework.module.customer.vo.CustomerVo;
import com.framework.module.customer.vo.CustomerVoList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Service
public class CustomerService extends BaseService<Customer, Integer> implements ICustomerService {

    @Autowired
    private IContactsService contactsService;
    @Autowired
    private IReliefAgenciesService reliefAgenciesService;
    @Autowired
    private IHealthStatusService healthStatusService;


    @Transactional
    public void saveCustomerVo(CustomerVo customerVo,MultipartFile mapPicPath,MultipartFile certPicPath,MultipartFile backCertPicPath){
        Customer customer = customerVo.getCustomer();
        save(customer);
        int customerId = customer.getCustomerId();
        //save mapPicPath
        customer.setMapPicPath(FileUtil.saveFile(customerId, mapPicPath, "_mapPic"));
        customer.setCertPicPath(FileUtil.saveFile(customerId, certPicPath, "_certPic"));
        customer.setBackCertPicPath(FileUtil.saveFile(customerId, backCertPicPath, "_backCertPic"));

        update(customer);
        for (Contacts contacts : customerVo.getContactsList()) {
            contacts.setCustomerId(customerId);
            contactsService.save(contacts);
        }
        for (ReliefAgencies reliefAgencies : customerVo.getReliefAgenciesList()) {
            reliefAgencies.setCustomerId(customerId);
            reliefAgenciesService.save(reliefAgencies);
        }
        for (HealthStatus healthStatus : customerVo.getHealthStatusList()) {
            healthStatus.setCustomerId(customerId);
            List<String> statusDesList = healthStatus.getStatusDesList();
            if(statusDesList!=null && statusDesList.isEmpty()){
                StringBuffer statusDesBuffer = new StringBuffer();
                for(String item : statusDesList){
                    if(StringUtils.isNoneBlank(item)){
                        if(statusDesBuffer.length()>0){
                            statusDesBuffer.append(";").append(item);
                        }else{
                            statusDesBuffer.append(item);
                        }
                     }
                }
                healthStatus.setStatusDes(statusDesBuffer.toString());
            }
            healthStatusService.save(healthStatus);
        }

    }

    public CustomerVo getCustomerVoByCustomerId(Integer customerId) {
        return (CustomerVo)findByObject("findCustomerVo",customerId);
    }

    public void approval(Customer customer) {
        Customer approvalCustomer = findById(customer.getCustomerId());
        approvalCustomer.setApprovalDes(customer.getApprovalDes());
        approvalCustomer.setApprovalStatus(customer.getApprovalStatus());
        approvalCustomer.setApprovalSysUserId(customer.getApprovalSysUserId());
        approvalCustomer.setLastModifiedTime(new Date());
        update("updateCustomerApprovalStatus", approvalCustomer);
    }

    public List<CustomerVoList> findCustomerVoList(Customer customer) {
        return (List<CustomerVoList>)findByObjectList("findCustomerVoList", customer);
    }
}
