package com.framework.module.security.service;

import com.framework.common.service.IService;
import com.framework.module.security.domain.SysRole;

import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/22 10:59
 * 修改人：Administrator
 * 修改时间：2015/4/22 10:59
 * 修改备注：
 */
public interface ISysRoleService extends IService<SysRole,Integer> {
    List<SysRole> finRoleByResourceId(Integer resourceId);

    List<SysRole> findSysUserRole(Integer objId);

    public void addRoleDispatcherByObjId(Integer objId);

    public void deleteRoleDispatcherByObjId(Integer objId);

}
