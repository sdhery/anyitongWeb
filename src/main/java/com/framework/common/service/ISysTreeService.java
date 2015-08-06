package com.framework.common.service;

import com.framework.common.domain.SysTree;

import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/28 11:35
 * 修改人：Administrator
 * 修改时间：2015/4/28 11:35
 * 修改备注：
 */
public interface ISysTreeService extends IService<SysTree,Integer> {
    List<SysTree> findByPrentId(Integer parentId);
}
