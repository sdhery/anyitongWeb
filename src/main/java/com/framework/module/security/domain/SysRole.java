package com.framework.module.security.domain;

import com.framework.common.domain.BaseDomain;

import java.io.Serializable;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/22 10:30
 * 修改人：Administrator
 * 修改时间：2015/4/22 10:30
 * 修改备注：
 */
public class SysRole extends BaseDomain {
    private Integer roleId;
    private String roleName;
    private String roleValue;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }
}
