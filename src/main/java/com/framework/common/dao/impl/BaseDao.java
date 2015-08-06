package com.framework.common.dao.impl;

import com.framework.common.dao.IBaseDao;
import com.framework.common.util.bean.BeanMapUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/7 11:16
 * 修改人：Administrator
 * 修改时间：2015/4/7 11:16
 * 修改备注：
 */
public abstract class BaseDao <T, ID extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<T, ID> {
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> entityClass;
    private static Map<Class, Boolean> idFieldMp = new Hashtable();
    public static final String SQLNAME_SEPARATOR = ".";

    public static final String SQL_SAVE = "save";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_FINDBYID = "findById";
    public static final String SQL_DELETEBYID = "deleteById";
    public static final String SQL_DELETEBYIDS = "deleteByIds";
    public static final String SQL_FINDLISTBY = "findListBy";
    public static final String SQL_FINDLISTBYMAP = "findListByMap";
    public static final String SQL_FINDCOUNTBY = "findCountBy";
    public static final String SQL_FINDALL="findAll";

    private static final String SORT_NAME = "SORT";

    private static final String DIR_NAME = "DIR";
    /** 不能用于SQL中的非法字符（主要用于排序字段名） */
    public static final String[] ILLEGAL_CHARS_FOR_SQL = {",", ";", " ", "\"", "%"};
    public BaseDao() {
        Class typeCls = getClass();
        Type genType = typeCls.getGenericSuperclass();

        while (!(genType instanceof ParameterizedType)) {
            typeCls = typeCls.getSuperclass();
            genType = typeCls.getGenericSuperclass();
        }
        this.entityClass = ((Class) ((ParameterizedType) genType).getActualTypeArguments()[0]);
    }


    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 功能说明：获取默认SqlMapping命名空间
     *       <br/>使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
     *       <br/>如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
     * @author ducc
     * @created 2014年6月13日 上午10:57:36
     * @updated
     * @return 返回命名空间字符串
     */
    protected String getDefaultSqlNamespace() {
       return getClass().getName();
    }
    /**
     * 获取 SqlMapping 的命名空间
     */
    private String sqlNamespace = getDefaultSqlNamespace();

    /**
     * 功能说明：将SqlMapping命名空间与给定的SqlMapping名组合在一起。
     * @author ducc
     * @created 2014年6月13日 上午11:00:57
     * @updated
     * @param sqlName  SqlMapping名
     * @return 组合了SqlMapping命名空间后的完整SqlMapping名
     */
    protected String getSqlName(String sqlName) {
        return sqlNamespace+ SQLNAME_SEPARATOR + sqlName;
    }

    /**
     * 功能说明：获取 SqlMapping 的命名空间
     * @updated
     * @return  SqlMapping命名空间
     */
    public String getSqlNamespace() {
        return sqlNamespace;
    }
    /**
     * 功能说明：设置SqlMapping命名空间。
     *       此方法只用于注入SqlMapping命名空间，以改变默认的SqlMapping命名空间，
     *       不能滥用此方法随意改变SqlMapping命名空间。
     * @updated
     * @param sqlNamespace 要设置的sqlMapping命名空间的名称
     */
    public void setSqlNamespace(String sqlNamespace) {
        this.sqlNamespace = sqlNamespace;
    }

    public Integer save(T item) {
        return this.getSqlSession().insert(getSqlName(SQL_SAVE),item);
    }
    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——插入成功的记录数
     */
    public Integer save(String keyId, Object parameter) {
        return this.getSqlSession().insert(getSqlName(keyId), parameter);
    }

    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @return 执行结果——插入成功的记录数
     */
    protected Integer save(String keyId) {
        return this.getSqlSession().insert(getSqlName(keyId));
    }

    public Integer update(T item) {
        return this.getSqlSession().update(getSqlName(SQL_UPDATE), item);
    }

    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——更新成功的记录数
     */
    public Integer update(String keyId, Object parameter) {
        return this.getSqlSession().update(getSqlName(keyId), parameter);
    }

    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用。
     * @updated
     * @param keyId 映射的语句ID
     * @return 执行结果——更新成功的记录数
     */
    protected int update(String keyId) {
        return this.getSqlSession().update(getSqlName(keyId));
    }

    public List<T> findAll(){
        return (List<T>)this.getSqlSession().selectList(getSqlName(SQL_FINDALL));
    }

    public T findById(ID id) {
        return (T)this.getSqlSession().selectOne(getSqlName(SQL_FINDBYID), id);
    }

    public List<T> findByList(String keyId,Object parameter) {
        return (List<T>)this.getSqlSession().selectList(getSqlName(keyId), parameter);
    }

    public Object findByObjectList(String keyId, Object parameter){
        return this.getSqlSession().selectList(getSqlName(keyId), parameter);
    }

    public T findBy(String keyId,Object parameter) {
        return (T)this.getSqlSession().selectOne(getSqlName(keyId), parameter);
    }

    public Object findByObject(String keyId, Object parameter){
        return this.getSqlSession().selectOne(getSqlName(keyId), parameter);
    }

    public Map<String,Object> findMapBy(String namespace,String keyId,Map<String,Object> map){
        return this.getSqlSession().selectOne(namespace+"."+keyId, map);
    }

    public Integer deleteById(ID id) {
        return this.getSqlSession().delete(getSqlName(SQL_DELETEBYID), id);
    }

    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @return 执行结果——删除成功的记录数
     */
    public int deleteBy(String keyId, Object parameter) {
        return this.getSqlSession().delete(getSqlName(keyId), parameter);
    }

    /**
     * 功能说明：将statement包装了命名空间，方便DAO子类调用
     * @updated
     * @return
     */
    protected int delete(String statement) {
        return this.getSqlSession().delete(getSqlName(statement));
    }

    public Integer deleteByIds(ID[] ids){
        return this.getSqlSession().delete(getSqlName(SQL_DELETEBYIDS), ids);
    }

    /**
     * 功能说明：从给定字符串中将指定的非法字符串数组中各字符串过滤掉。
     * @author ducc
     * @created 2014年6月13日 下午4:34:22
     * @updated
     * @param str 待过滤的字符串
     * @param filterChars 指定的非法字符串数组
     * @return 过滤后的字符串
     */
    protected String filterIllegalChars(String str, String[] filterChars) {
        String rs = str;
        if (rs != null && filterChars != null) {
            for (String fc : filterChars) {
                if (fc != null && fc.length() > 0) {
                    str = str.replaceAll(fc, "");
                }
            }
        }
        return rs;
    }

    public Integer findCountBy(T item) {
        return this.getSqlSession().selectOne(getSqlName(SQL_FINDCOUNTBY), item);
    }

    protected List<T> selectList(String keyId, Object parameter, RowBounds rowBounds) {
        return this.getSqlSession().selectList(getSqlName(keyId), parameter, rowBounds);
    }

    public List<T> findListBy(T item, String sortColumn, String des) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        try{
            if(item!=null){
                paramMap = BeanMapUtil.bean2Map(item);
            }
        }catch(Exception e){
            throw new RuntimeException("获取参数失败", e);
        }
        if (sortColumn != null) {
            // 排序字段不为空，过滤其中可能存在的非法字符
            sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
        }
        if (StringUtils.isNotEmpty(sortColumn)) {
            paramMap.put(SORT_NAME, sortColumn);
        }
        if (StringUtils.isNotEmpty(des)) {
            paramMap.put(DIR_NAME, des);
        }

        return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBY), paramMap);
    }
    public List<T> findListBySql(String keyId,String sql){
        return this.getSqlSession().selectList(getSqlName(keyId) , new SQLAdapter(sql));
    }
    public List<T> selectList(String keyId, Object parameter) {
        return this.getSqlSession().selectList(getSqlName(keyId), parameter);
    }
    public List<Map<String,Object>> selectListMap(String keyId, Object parameter) {
        return this.getSqlSession().selectList(getSqlName(keyId), parameter);
    }
    protected List<T> selectList(String keyId) {
        return this.getSqlSession().selectList(getSqlName(keyId));
    }
    protected Object selectOne(String keyId, Object parameter) {
        return this.getSqlSession().selectOne(getSqlName(keyId), parameter);
    }
    protected Object selectOne(String keyId) {
        return this.getSqlSession().selectOne(getSqlName(keyId));
    }
    protected Map<String,Object> selectMap(String keyId, Object parameter, String mapKey,RowBounds rowBounds) {
        return this.getSqlSession().selectMap(getSqlName(keyId),parameter, mapKey, rowBounds);
    }
    /**
     * 功能说明：
     * @author ducc
     * @created 2014年6月13日 下午5:00:35
     * @updated
     * @param keyId 映射的语句ID
     * @param parameter 参数
     * @param mapKey 数据mapKey
     * @return
     */
    protected Map<String,Object> selectMap(String keyId, Object parameter, String mapKey) {
        return this.getSqlSession().selectMap(getSqlName(keyId), parameter, mapKey);
    }
    protected void select(String keyId, Object parameter, RowBounds rowBounds,ResultHandler handler) {
        this.getSqlSession().select(getSqlName(keyId),parameter, rowBounds, handler);
    }
    protected void select(String keyId, Object parameter, ResultHandler handler) {
        this.getSqlSession().select(getSqlName(keyId), parameter, handler);
    }
    protected void select(String keyId, ResultHandler handler) {
        this.getSqlSession().select(getSqlName(keyId), handler);
    }
    public List<T> findListBy(T item){
        return findListBy(item, null, null);
    }
    public List<T> findListByMap(Map<String,Object> map){
        return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYMAP), map);
    }
    public List<T> findListBy(String namespace,String keyId,T item){
        return findListBy(namespace,keyId,item,null,null);
    }
    public List<T> findListBy(String namespace,String keyId,T item,String sortColumn,String des){
        Map<String,Object> paramMap=new HashMap<String,Object>();
        try{
            if(item!=null){
                paramMap = BeanMapUtil.bean2Map(item);
            }
        }catch(Exception e){
            throw new RuntimeException("获取参数失败", e);
        }
        if (sortColumn != null) {
            // 排序字段不为空，过滤其中可能存在的非法字符
            sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
        }
        if (StringUtils.isNotEmpty(sortColumn) && StringUtils.isNotEmpty(des)) {
            paramMap.put(SORT_NAME, sortColumn);
            paramMap.put(DIR_NAME, des);
        }
        return this.getSqlSession().selectList(namespace+"."+keyId, paramMap);
    }
    public List<Map<String,Object>> findListMapBy(String namespace,String keyId,Map<String,Object> map){
        return findListMapBy(namespace,keyId,map,null,null);
    }
    public List<Map<String,Object>> findListMapBy(String namespace,String keyId,Map<String,Object> map,String sortColumn,String des){

        Map<String,Object> paramMap=new HashMap<String,Object>();
        if(map!=null&&map.size()>0){
            paramMap.putAll(map);
        }
        if (sortColumn != null) {
            // 排序字段不为空，过滤其中可能存在的非法字符
            sortColumn = filterIllegalChars(sortColumn, ILLEGAL_CHARS_FOR_SQL);
        }
        if (StringUtils.isNotEmpty(sortColumn) && StringUtils.isNotEmpty(des)) {
            paramMap.put(SORT_NAME, sortColumn);
            paramMap.put(DIR_NAME, des);
        }
        return this.getSqlSession().selectList(namespace+"."+keyId, paramMap);
    }
    @Override
    public List<T> findList() {
        return findListBy(null);
    }
    public List<Map<String,Object>> findListBySql(String sql,Object... obj) throws SQLException {
        SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
        return sr.selectAll(sql, obj);
    }
    public Map<String,Object> findMapBySql(String sql,Object... obj )throws SQLException{
        SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
        return sr.selectOne(sql, obj);
    }
    public Integer updateBySql(String sql,Object... obj)throws SQLException{
        SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
        return sr.update(sql, obj);
    }
    public Integer deleteBySql(String sql,Object... obj)throws SQLException{
        SqlRunner sr=new SqlRunner(getSqlSession().getConnection());
        return sr.delete(sql, obj);
    }
}
