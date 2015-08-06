package com.framework.common.util.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/7 15:15
 * 修改人：Administrator
 * 修改时间：2015/4/7 15:15
 * 修改备注：
 */
public class BeanMapUtil {
    public static Object Map2Bean(Class type, Map<String, Object> map)throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ConvertUtils.register(new DateConvert(), Date.class);

        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        Object obj = type.newInstance();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!(map.containsKey(propertyName.toUpperCase()))) continue;
            try {
                Object value = map.get(propertyName.toUpperCase());
                BeanUtils.setProperty(obj, propertyName, value);
            } catch (Exception e) {
            }
        }
        return obj;
    }

    public static Map<String, Object> bean2Map(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!(propertyName.equals("class"))) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null)
                    returnMap.put(propertyName, result);
                else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
}