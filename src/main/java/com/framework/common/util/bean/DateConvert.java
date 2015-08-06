package com.framework.common.util.bean;

import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;

/**
 * 项目名称：sdheryFramework-project
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/4/3 16:10
 * 修改人：Administrator
 * 修改时间：2015/4/3 16:10
 * 修改备注：
 */
public class DateConvert implements Converter {

    public Object convert(Class arg0, Object arg1) {
        String p = (String) arg1;
        if ((p == null) || (p.trim().length() == 0))
            return null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(p.trim());
        } catch (Exception e) {
        }
        return null;
    }
}
