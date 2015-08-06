package com.framework.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/10 17:22
 * 修改人：Administrator
 * 修改时间：2015/4/10 17:22
 * 修改备注：
 */
public interface IService <T, ID extends Serializable>{
    public T findBy(String keyId, Object parameter);

    public List<T> findAll();

    public Integer save(T item);

    public T findById(ID id);

    public Integer update(T item);

    public List<T> findListBy(T item, String sortColumn, String des);

    public List<T> findByList(String keyId, Object parameter);

    public Integer update(String keyId, Object parameter);

    public Object findByObject(String keyId, Object parameter);

    public Object findByObjectList(String keyId, Object parameter);
}
