package com.framework.common.domain;

import java.io.Serializable;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/28 11:18
 * 修改人：Administrator
 * 修改时间：2015/4/28 11:18
 * 修改备注：
 */
public class SysTree extends BaseDomain {
    private Integer treeId;
    private String treeName;
    private Integer treeParentId;
    private String url;

    public Integer getTreeParentId() {
        return treeParentId;
    }

    public void setTreeParentId(Integer treeParentId) {
        this.treeParentId = treeParentId;
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
