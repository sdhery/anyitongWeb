package com.framework.module.security.service.impl;

import com.framework.common.service.impl.BaseService;
import com.framework.module.security.service.ISysRoleService;
import com.framework.module.security.domain.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/22 11:07
 * 修改人：Administrator
 * 修改时间：2015/4/22 11:07
 * 修改备注：
 */
@Service
public class SysRoleService extends BaseService<SysRole,Integer> implements ISysRoleService {
    public List<SysRole> finRoleByResourceId(Integer resourceId) {
        return this.baseDao.findByList("finRoleByResourceId",resourceId);
    }

    public List<SysRole> findSysUserRole(Integer objId) {
        return this.baseDao.findByList("findSysUserRole",objId);
    }

    public void addRoleDispatcherByObjId(Integer objId) {
        this.baseDao.save("addRoleDispatcherByObjId",objId);
    }

    public void deleteRoleDispatcherByObjId(Integer objId) {
        this.baseDao.deleteBy("deleteRoleDispatcherByObjId",objId);
    }
}
