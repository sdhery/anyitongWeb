package com.framework.common.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/7/11 12:23
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/7/11 12:23
 * �޸ı�ע��
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
