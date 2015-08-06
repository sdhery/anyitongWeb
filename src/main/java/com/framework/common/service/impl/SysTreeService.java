package com.framework.common.service.impl;

import com.framework.common.domain.SysTree;
import com.framework.common.service.ISysTreeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/28 11:36
 * 修改人：Administrator
 * 修改时间：2015/4/28 11:36
 * 修改备注：
 */
@Service
public class SysTreeService extends BaseService<SysTree,Integer> implements ISysTreeService {
    public List<SysTree> findByPrentId(Integer parentId) {
        return this.baseDao.findByList("findByPrentId",parentId);
    }
}
