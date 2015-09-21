package com.framework.module.customer.web.frontend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.service.ICustomerService;
import com.framework.module.customer.vo.CustomerVo;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


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

    /**
     *
     * @param customerVo
     * @param customerVoList
     * @param mapPicPath
     * @param certPicPath
     * @return
     */
    @RequestMapping(value="register",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap register(CustomerVo customerVo,String customerVoList,MultipartFile mapPicPath,MultipartFile certPicPath,MultipartFile backCertPicPath){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            if(StringUtils.isNotBlank(customerVoList)){
                JSONArray jsonArray = JSON.parseArray(customerVoList);
                int size = jsonArray.size();
                for(int i=0;i<size;i++){
                    customerVo = JSON.parseObject(((JSONObject) jsonArray.get(i)).toJSONString(),CustomerVo.class);
                    customerService.saveCustomerVo(customerVo, mapPicPath, certPicPath, backCertPicPath);
                }
            }else{
                customerService.saveCustomerVo(customerVo, mapPicPath, certPicPath, backCertPicPath);
            }
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
            setErrMsg(map,e.getMessage());
        }
        return map;
    }

}
