package com.framework.common.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/11 12:23
 * 修改人：Administrator
 * 修改时间：2015/7/11 12:23
 * 修改备注：
 */
public class ConcurrentHashMapExt<K, V> extends ConcurrentHashMap<K, V> {
    public ConcurrentHashMapExt() {
        super();
    }

    public ConcurrentHashMapExt(int initialCapacity) {
        super(initialCapacity);
    }

    public ConcurrentHashMapExt(Map<? extends K, ? extends V> m) {
        super(m);
    }

    synchronized public V put(K key, V value) {
        if (value != null) {
            return super.put(key, value);
        }else {
            super.remove(key);
        }
        return value;
    }

    public int getSize(){
        return size();
    }
}
