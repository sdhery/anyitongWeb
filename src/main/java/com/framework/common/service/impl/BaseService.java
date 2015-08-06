package com.framework.common.service.impl;

import com.framework.common.dao.IBaseDao;
import com.framework.common.dao.impl.BaseDao;
import com.framework.common.domain.BaseDomain;
import com.framework.common.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/10 17:23
 * 修改人：Administrator
 * 修改时间：2015/4/10 17:23
 * 修改备注：
 */
public abstract class BaseService<T extends BaseDomain, ID extends Serializable> implements IService<T, ID> {
    protected IBaseDao<T,ID> baseDao;

    @Autowired
    public void setBaseDao(IBaseDao<T,ID> baseDao){
        this.baseDao = baseDao;
    }

    public T findBy(String keyId,Object parameter){
        return this.baseDao.findBy(keyId,parameter);
    }

    public Object findByObject(String keyId, Object parameter){
        return this.baseDao.findByObject(keyId, parameter);
    }

    public List<T> findAll(){
        return this.baseDao.findAll();
    }

    public Integer save(T item){
        item.setCreateTime(new Date());
        item.setLastModifiedTime(new Date());
        return baseDao.save(item);
    }

    public T findById(ID id){
        return baseDao.findById(id);
    }

    public Integer update(T item){
        item.setLastModifiedTime(new Date());
        return baseDao.update(item);
    }

    public List<T> findListBy(T item, String sortColumn, String des){
        return baseDao.findListBy(item, sortColumn, des);
    }

    public List<T> findByList(String keyId, Object parameter){
        return baseDao.findByList(keyId, parameter);
    }

    public Integer update(String keyId, Object parameter){
        return baseDao.update(keyId, parameter);
    }

    public Object findByObjectList(String keyId, Object parameter){
        return baseDao.findByObjectList(keyId,parameter);
    }


}
