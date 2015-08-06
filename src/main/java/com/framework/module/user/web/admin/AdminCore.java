package com.framework.module.user.web.admin;

import com.framework.common.domain.SysTree;
import com.framework.common.service.ISysTreeService;
import com.framework.common.vo.Node;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.*;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/18 16:55
 * 修改人：Administrator
 * 修改时间：2015/4/18 16:55
 * 修改备注：
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminCore {
    @Autowired
    private ISysTreeService sysTreeService;
    @Autowired
    ISysUserService sysUserService;

    /**
     * Spring security 最后登录异常Session名称
     */
    public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    @RequestMapping(value = "login")
    public String login(HttpSession httpSession,ModelMap map){
        Exception springSecurityLastException = (Exception) httpSession.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
        if (springSecurityLastException != null) {
            if (springSecurityLastException instanceof BadCredentialsException) {
                String loginId = ((String) httpSession.getAttribute("SPRING_SECURITY_LAST_USERNAME"));
                //SysUser sysUser = sysUserService.findByAdminLoginId(loginId);
                /*if (sysUser == null) {

                }else{

                }*/
                map.put("error","您填写的用户名或密码错误");
            }else if (springSecurityLastException instanceof DisabledException) {
                map.put("error", "您的账号已被禁用,无法登录!");
            } else if (springSecurityLastException instanceof LockedException) {
                map.put("error", "您的账号已被锁定,无法登录!");
            } else if (springSecurityLastException instanceof AccountExpiredException) {
                map.put("error", "您的账号已过期,无法登录!");
            } else if (springSecurityLastException instanceof AuthenticationServiceException){
                map.put("error", springSecurityLastException.getMessage());
            } else if (springSecurityLastException instanceof SessionAuthenticationException){
                map.put("error", "该用户已经在别处登录,无法登录!");
            } else {
                map.put("error", "出现未知错误,无法登录!");
            }
            httpSession.removeAttribute(SPRING_SECURITY_LAST_EXCEPTION);
        }
        return "admin/login";
    }

    @RequestMapping(value = "main")
    public String main(ModelMap map){
        SysUser sysUser = sysUserService.getLoginAdmin();
        map.put("loginAdmin",sysUser);
        return "admin/main";
    }

    @RequestMapping(value = "mainLeft")
    public String mainLeft(ModelMap map,HttpServletRequest request){
        List<SysTree> sysTrees = sysTreeService.findByPrentId(1);
        map.put("sysTrees",sysTrees);
        return "admin/mainLeft";
    }

    @RequestMapping(value = "loadTree")
    @ResponseBody
    Object loadTree(Integer id, Integer sysTreeId){
        List<Node> result = new ArrayList<Node>();
        int loadId = 0;
        if (sysTreeId != null && id == null) {
            loadId = sysTreeId;
        }else{
            loadId = id;
        }
        List<SysTree> sysTrees = sysTreeService.findByPrentId(loadId);
        Node node=null;
        for (SysTree sysTree : sysTrees) {
            node = new Node();
            node.setId(sysTree.getTreeId());
            node.setName(sysTree.getTreeName());
            node.setText(sysTree.getTreeName());
            node.setUrl(sysTree.getUrl());
            result.add(node);
        }
        return  result;
    }

    private WebInvocationPrivilegeEvaluator getPrivilegeEvaluator(HttpServletRequest request) throws IOException {
        WebInvocationPrivilegeEvaluator privEvaluatorFromRequest = (WebInvocationPrivilegeEvaluator) request
                .getAttribute(WebAttributes.WEB_INVOCATION_PRIVILEGE_EVALUATOR_ATTRIBUTE);
        if (privEvaluatorFromRequest != null) {
            return privEvaluatorFromRequest;
        }

        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(request.getServletContext());
        Map<String, WebInvocationPrivilegeEvaluator> wipes = ctx
                .getBeansOfType(WebInvocationPrivilegeEvaluator.class);

        if (wipes.size() == 0) {
            throw new IOException(
                    "No visible WebInvocationPrivilegeEvaluator instance could be found in the application "
                            + "context. There must be at least one in order to support the use of URL access checks in 'authorize' tags.");
        }

        return (WebInvocationPrivilegeEvaluator) wipes.values().toArray()[0];
    }


}
