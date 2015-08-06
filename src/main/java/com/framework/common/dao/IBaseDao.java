package com.framework.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/7 11:09
 * 修改人：Administrator
 * 修改时间：2015/4/7 11:09
 * 修改备注：
 */
public interface IBaseDao<T, ID extends Serializable> {
    /**
     * 功能说明：保存实体
     * @updated
     * @param item 要保存的实体对象
     * @return 返回执行成功的记录数
     */
    public Integer save(T item);
    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——插入成功的记录数
     */
    public Integer save(String keyId, Object parameter) ;
    /**
     * 功能说明：更新实体
     * @updated
     * @param item  要保存的实体对象
     * @return 返回执行成功的记录
     */
    public Integer update(T item);
    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——更新成功的记录数
     */
    public Integer update(String keyId, Object parameter) ;
    /**
     * 功能说明：根据id获取业务实体对象
     * @updated
     * @param id 唯一标示符
     * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null
     */
    public T findById(ID id);

    public Object findByObjectList(String keyId, Object parameter);

    public List<T> findByList(String keyId, Object parameter);
    /**
     * 功能说明：根据自定义sqlId 查询单一对象
     * @author ducc
     * @created 2014年6月28日 上午7:37:13
     * @updated
     * @param keyId
     * @return
     */
    public T findBy(String keyId, Object parameter);

    public Object findByObject(String keyId, Object parameter);
    /**
     * 功能说明：根据命名空间，id，查询map
     * @updated
     * @param namespace 对应查询所在的命名空间
     * @param keyId 对应查询的id
     * @param map  查询条件map
     * @return 返回查询结果的map
     */
    public Map<String,Object> findMapBy(String namespace, String keyId, Map<String, Object> map);
    /**
     * 功能说明：根据业务id删除数据库中的记录
     * @updated
     * @param id 唯一标示符
     * @return 返回更新成功的数量
     */
    public Integer deleteById(ID id);
    /**
     * 功能说明：批量删除记录
     * @updated
     * @param ids id数组
     * @return  返回成功的数量
     */
    public Integer deleteByIds(ID[] ids);
    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——删除成功的记录数
     */
    public int deleteBy(String keyId, Object parameter) ;
    /**
     * 功能说明：获取满足查询条件的记录总数
     * @updated
     * @param item 查询参数封装的实体
     * @return 查询的记录数
     */
    public Integer findCountBy(T item);

    /**
     * 查找所有实体
     * @return
     */
    public List<T> findAll();
    /**
     * 功能说明：不分页查询
     * @updated
     * @param item 实体封装的查询参数
     * @param sortColumn  排序字段
     * @param des 排序方式（升序(asc)或降序(desc)
     * @return
     */
    public List<T> findListBy(T item, String sortColumn, String des);
    /**
     * 功能说明：根据map查询列表
     * @author ducc
     * @updated
     * @param map
     * @return
     */
    public List<T> findListByMap(Map<String, Object> map);
    /**
     * 功能说明：不分页查询 不带排序字段
     * @updated
     * @param item 查询的实体
     * @return
     */
    public List<T> findListBy(T item);
    /**
     * 功能说明：根据传入的命名空间和keyId 查询
     * @updated
     * @param namespace
     * @param keyId
     * @param item
     * @return
     */
    public List<T> findListBy(String namespace, String keyId, T item);
    /**
     * 功能说明：根据namespace,keyId返回list<Map>集合
     * @updated
     * @return
     * @throws SQLException
     */
    public List<Map<String,Object>> findListMapBy(String namespace, String keyId, Map<String, Object> map);
    /**
     * 功能说明： 不分页查询，不带条件，不带排序
     * @author ducc
     * @created 2014年6月13日 下午9:29:15
     * @updated
     * @return
     */
    public List<T> findList();
    /**
     * 功能说明：根据keyId,sql语句查询
     * @updated
     * @param keyId
     * @param sql
     * @return
     */
    public List<T> findListBySql(String keyId, String sql);
    /**
     * 功能说明：根据自定义sql Id返回自定义的 list<T>集合
     * @updated
     * @param keyId
     * @param parameter
     * @return
     */
    public List<T> selectList(String keyId, Object parameter);
    /**
     * 功能说明：根据自定义sqlId 返回list<Map<String,Object>>
     * @updated
     * @param keyId
     * @param parameter
     * @return
     */
    public List<Map<String,Object>> selectListMap(String keyId, Object parameter);
    /**
     * 功能说明：根据sql返回list集合
     * @updated
     * @return
     * @throws SQLException
     */
    public List<Map<String,Object>> findListBySql(String sql, Object... obj)throws SQLException;
    /**
     * 功能说明：根据sql查询map集合
     * @updated
     * @param sql
     * @param obj
     * @return
     * @throws SQLException
     */
    public Map<String,Object> findMapBySql(String sql, Object... obj)throws SQLException;
    /**
     * 功能说明：根据sql 执行更新
     * @updated
     * @param sql
     * @param obj
     * @return
     * @throws SQLException
     */
    public Integer updateBySql(String sql, Object... obj)throws SQLException;
    /**
     * 功能说明：根据sql执行delete
     * @updated
     * @param sql
     * @param obj
     * @return
     * @throws SQLException
     */
    public Integer deleteBySql(String sql, Object... obj)throws SQLException;
}
