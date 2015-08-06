package com.framework.module.customer.web.frontend;

import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.service.ICustomerService;
import com.framework.module.customer.vo.CustomerVo;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController<Customer,Integer> {
    public final static String prefix="customer/";
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ISysUserService sysUserService;

    protected IService getBaseService() {
        return customerService;
    }

    protected String getPrefix() {
        return prefix;
    }


    @RequestMapping(value="login",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap login(SysUser sysUser){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            sysUser = sysUserService.login(sysUser.getLoginId(), sysUser.getPassword());
            map.put("realName",sysUser.getRealName());
            map.put("sysUserId",sysUser.getSysUserId());
            setSuccess(map);
        }catch (Exception e){
            setErrMsg(map,e.getMessage());
        }
        return map;
    }

    @RequestMapping(value="register",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap register(CustomerVo customerVo){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            customerService.saveCustomerVo(customerVo);
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
            setErrMsg(map,e.getMessage());
        }
        return map;
    }

}
