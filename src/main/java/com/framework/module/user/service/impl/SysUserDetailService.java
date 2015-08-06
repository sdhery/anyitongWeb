package com.framework.module.user.service.impl;

import com.framework.module.security.domain.SysRole;
import com.framework.module.security.service.impl.SysRoleService;
import com.framework.module.user.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/17 16:59
 * 修改人：Administrator
 * 修改时间：2015/4/17 16:59
 * 修改备注：
 */
@Service
public class SysUserDetailService implements UserDetailsService{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByAdminLoginId(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        sysUser.setAuthorities(getUserGrantedAuthority(sysUser));
        return sysUser;
    }

    private List<SimpleGrantedAuthority> getUserGrantedAuthority(SysUser sysUser){
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
        List<SysRole> sysRoleList = sysRoleService.findSysUserRole(sysUser.getSysUserId());
        for(SysRole sysRole : sysRoleList){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(sysRole.getRoleValue()));
        }
        return simpleGrantedAuthorityList;
    }
}
