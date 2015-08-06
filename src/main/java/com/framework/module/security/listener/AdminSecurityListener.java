package com.framework.module.security.listener;

import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/15 21:56
 * 修改人：Administrator
 * 修改时间：2015/7/15 21:56
 * 修改备注：
 */
@Component
public class AdminSecurityListener implements ApplicationListener {

    @Autowired
    ISysUserService sysUserService;

    public void onApplicationEvent(ApplicationEvent event) {
        //更新后台管理员最后登录时间
        // 登录成功：记录登录IP、清除登录失败次数
        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            String loginIp = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
            SysUser user = (SysUser) authentication.getPrincipal();
            user.setLastLoginTime(new Date());
            sysUserService.update("updateLastLoginTime",user);
        }
    }
}
