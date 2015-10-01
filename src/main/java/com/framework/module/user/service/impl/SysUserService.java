package com.framework.module.user.service.impl;

import com.framework.common.base.ConcurrentHashMapExt;
import com.framework.common.service.impl.BaseService;
import com.framework.common.util.DigestUtil;
import com.framework.common.util.MD5;
import com.framework.module.user.domain.SysUser;
import com.framework.module.user.service.ISysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/10 17:34
 * 修改人：Administrator
 * 修改时间：2015/4/10 17:34
 * 修改备注：
 */
@Service
public class SysUserService extends BaseService<SysUser, Integer> implements ISysUserService {
    public SysUser findByAdminLoginId(String loginId) {
        return findBy("findByAdminLoginId", loginId);
    }

    public SysUser addSysUser(SysUser sysUser) {
        try {
            String passWord = MD5.convert(sysUser.getPassword());
            sysUser.setPassword(passWord);
            save(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public void updatePassword(Integer sysUserId,String password) throws Exception{
        SysUser sysUser = findById(sysUserId);
        if(sysUser==null){
            throw new Exception("用户不存在");
        }
        password = MD5.convert(password);

        sysUser.setPassword(password);
        sysUser.setLastModifiedTime(new Date());
        update("updatePassword", sysUser);
    }

    public SysUser login(String loginId, String password) throws Exception {
        SysUser sysUser = findBy("findByMemberLoginId", loginId);
        if(sysUser==null){
            throw new Exception("用户不存在");
        }
        password = MD5.convert(password);
        if(!password.equals(sysUser.getPassword())){
            throw new Exception("用户或者密码错误");
        }
        sysUser.setLastLoginTime(new Date());
        update("updateLastLoginTime", sysUser);
        return sysUser;
    }

    public SysUser getLoginAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null || !(principal instanceof SysUser)) {
            return null;
        } else {
            return (SysUser) principal;
        }
    }

    public Integer countByLoginId(String loginId) {
        return (Integer) findByObject("countByLoginId",loginId);
    }
}
