package com.framework.tag.easyui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Random;

/**
 * 项目名称：yishimai
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/1/26 15:19
 * 修改人：Administrator
 * 修改时间：2015/1/26 15:19
 * 修改备注：
 */
public class BaseTag extends TagSupport {
    protected String type = "default";// 加载类型

    public void setType(String type) {
        this.type = type;
    }


    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    public int doEndTag() throws JspException {
        String contextPath = pageContext.getServletContext().getContextPath();
        try {
            JspWriter out = this.pageContext.getOut();
            StringBuilder sb = new StringBuilder();

            String types[] = type.split(",");
            if (isIn("jquery", types)) {
                sb.append("<script src='"+contextPath+"/admin/js/jquery-easyui-1.4.2/jquery.min.js'></script>\n");
            }
            if (isIn("easyui", types)) {
                sb.append("<link href='"+contextPath+"/admin/js/jquery-easyui-1.4.2/themes/default/easyui.css' rel='stylesheet' type='text/css' />\n");
                sb.append("<link href='"+contextPath+"/admin/js/jquery-easyui-1.4.2/themes/color.css' rel='stylesheet' type='text/css' />\n");
                sb.append("<link href='"+contextPath+"/admin/js/jquery-easyui-1.4.2/themes/icon.css' rel='stylesheet' type='text/css' />\n");
                sb.append("<script src='"+contextPath+"/admin/js/jquery-easyui-1.4.2/jquery.easyui.min.js'></script>\n");
                sb.append("<script src='"+contextPath+"/admin/js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js'></script>\n");
            }
            if (isIn("bootstrap3", types)) {
                sb.append("<link href='"+contextPath+"/resources/bootstrap3/css/bootstrap.min.css' rel='stylesheet' type='text/css' />\n");
            }
            if (isIn("tools", types)) {
                sb.append("<script src='"+contextPath+"/admin/js/tools/tool.js?"+ new Random().nextInt()+"'></script>\n");
            }
            if (isIn("dateformat", types)) {
                sb.append("<script src='"+contextPath+"/admin/js/tools/dateformat.js?"+ new Random().nextInt()+"'></script>\n");
            }
            out.print(sb.toString());
        } catch (Exception e) {

        }
        return EVAL_PAGE;
    }

    boolean isIn(String substring, String[] source) {
        if (source == null || source.length == 0) {
            return false;
        }
        for (int i = 0; i < source.length; i++) {
            String aSource = source[i];
            if (aSource.equals(substring)) {
                return true;
            }
        }
        return false;
    }
}
