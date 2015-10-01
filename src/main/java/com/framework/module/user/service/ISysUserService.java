package com.framework.module.user.service;

import com.framework.common.service.IService;
import com.framework.module.user.domain.SysUser;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/10 17:29
 * 修改人：Administrator
 * 修改时间：2015/4/10 17:29
 * 修改备注：
 */
public interface ISysUserService extends IService<SysUser,Integer> {

    /**
     *
     * @param loginId
     * @return
     */
    public SysUser findByAdminLoginId(String loginId);

    public SysUser addSysUser(SysUser sysUser);

    public void updatePassword(Integer sysUserId,String password) throws Exception;

    public SysUser login(String loginId,String password) throws Exception;

    public SysUser getLoginAdmin();

    public Integer countByLoginId(String loginId);

}
