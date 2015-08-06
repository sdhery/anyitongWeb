package com.framework.module.user.web.admin;

import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/admin/user")
public class AdminSysUserController extends BaseController<SysUser,Integer> {
    public final static String prefix="admin/module/user/";

    @Autowired
    private ISysUserService sysUserService;

    protected IService getBaseService() {
        return sysUserService;
    }

    protected String getPrefix() {
        return prefix;
    }

    @Override
    @RequestMapping(value="save",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap save(SysUser sysUser){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            if(sysUser.getSysUserId()==null){
                sysUserService.addSysUser(sysUser);
            }else {
                sysUserService.update(sysUser);
            }

            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.GET)
    public String list(ModelMap map,Integer sysUserId){
        map.put("sysUser",sysUserService.findById(sysUserId));
        return getPrefix()+"updatePassword";
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap updatePassword(SysUser sysUser){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            sysUserService.updatePassword(sysUser.getSysUserId(),sysUser.getPassword());
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
