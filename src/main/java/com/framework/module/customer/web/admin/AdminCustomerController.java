package com.framework.module.customer.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.common.service.impl.BaseService;
import com.framework.module.article.domain.InfArticle;
import com.framework.module.article.service.IInfArticleService;
import com.framework.module.customer.domain.Customer;
import com.framework.module.customer.service.IContactsService;
import com.framework.module.customer.service.ICustomerService;
import com.framework.module.customer.vo.CustomerVoList;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import com.framework.tag.easyui.DataGrid;
import com.framework.tag.easyui.TagUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/30 16:05
 * 修改人：Administrator
 * 修改时间：2015/4/30 16:05
 * 修改备注：
 */
@Controller
@RequestMapping(value = "/admin/customer")
public class AdminCustomerController extends BaseController<Customer,Integer>{
    public final static String prefix="admin/module/customer/";
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IContactsService contactsService;
    @Autowired
    private ISysUserService sysUserService;

    protected IService getBaseService() {
        return customerService;
    }

    protected String getPrefix() {
        return prefix;
    }

    @RequestMapping(value="/print/{id}")
    public String printCustomer(ModelMap map,@PathVariable("id") Integer id,Boolean editing){
        Customer customer = customerService.findById(id);
        if(editing!=null && customer!=null && editing){
            SysUser admin = sysUserService.getLoginAdmin();
            customer.setApprovalSysUserId(admin.getSysUserId());
            customer.setApprovalStatus(2);
            customerService.approval(customer);
        }
        map.put("item", customerService.getCustomerVoByCustomerId(id));
        return getPrefix()+"print";
    }
    @RequestMapping(value="/map/{id}")
    public String map(ModelMap map,@PathVariable("id") Integer id){
        map.put("item", customerService.findById(id));
        return getPrefix()+"map";
    }

    @RequestMapping(value="/cert/{id}")
    public String cert(ModelMap map,@PathVariable("id") Integer id){
        map.put("item", customerService.findById(id));
        return getPrefix()+"cert";
    }

    @RequestMapping(value="/backCert/{id}")
    public String backCert(ModelMap map,@PathVariable("id") Integer id){
        map.put("item", customerService.findById(id));
        return getPrefix()+"backCert";
    }

    @RequestMapping(value="/approval")
    @ResponseBody
    public ModelMap approval(Customer customer){
        ModelMap map = new ModelMap();
        setFailure(map);

        try{
            customerService.approval(customer);
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "findCustomerVoList",method = RequestMethod.GET)
    public String findCustomerVoList(){
        return LIST;
    }

    @RequestMapping(value = "findCustomerVoList",method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void findCustomerVoList(DataGrid dataGrid,Customer customer){
        Page<CustomerVoList> page = PageHelper.startPage(dataGrid.getPage(), dataGrid.getRows());
        List<CustomerVoList> result = customerService.findCustomerVoList(customer);
        dataGrid.setResults(result);
        dataGrid.setTotal((int) page.getTotal());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-store");
        JSONObject object = TagUtils.getJson(dataGrid);
        try {
            PrintWriter pw=response.getWriter();
            pw.write(object.toString());
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
