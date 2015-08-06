package com.framework.module.user.web.admin;

import com.framework.common.base.BaseController;
import com.framework.common.service.IService;
import com.framework.module.security.service.ISysRoleService;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/26 16:41
 * 修改人：Administrator
 * 修改时间：2015/7/26 16:41
 * 修改备注：
 */
@Controller
@RequestMapping(value = "/admin/admin")
public class AdminManageController extends BaseController<SysUser,Integer> {
    public final static String prefix="admin/module/admin/";

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService roleService;

    protected IService getBaseService() {
        return sysUserService;
    }

    protected String getPrefix() {
        return prefix;
    }


    @RequestMapping(value="saveAdmin",method= RequestMethod.POST)
    @ResponseBody
    public ModelMap save(SysUser sysUser,Integer roleId){
        ModelMap map = new ModelMap();
        setFailure(map);
        try{
            if(sysUser.getSysUserId()==null){
                sysUserService.addSysUser(sysUser);
                roleService.addRoleDispatcherByObjId(sysUser.getSysUserId());
            }else {
                sysUserService.update(sysUser);
                roleService.deleteRoleDispatcherByObjId(sysUser.getSysUserId());
                roleService.addRoleDispatcherByObjId(sysUser.getSysUserId());
            }
            setSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String form(ModelMap map){
        map.put("roleList",roleService.findAll());
        return ADD;
    }

    @Override
    @RequestMapping(value="/update/{id}")
    public String edit(Model model,@PathVariable("id") Integer id){
        model.addAttribute("item", getBaseService().findById(id));
        return UPDATE;
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
