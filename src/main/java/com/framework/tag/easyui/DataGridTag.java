package com.framework.tag.easyui;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

/**
 * 项目名称：yishimai
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/1/26 16:08
 * 修改人：Administrator
 * 修改时间：2015/1/26 16:08
 * 修改备注：
 */
public class DataGridTag extends TagSupport {
    protected String fields = "";// 显示字段
    protected String searchFields = "";// 查询字段  Author:qiulu  Date:20130618 for：添加对区间查询的支持
    protected String name;// 表格标示
    protected String title;// 表格标示
    protected String idField = "id";// 主键字段
    protected boolean treegrid = false;// 是否是树形列表
    protected List<DataGridUrl> urlList = new ArrayList<DataGridUrl>();// 列表操作显示
    protected List<DataGridUrl> toolBarList = new ArrayList<DataGridUrl>();// 工具条列表
    protected List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();// 列表操作显示
    protected List<ColumnValue> columnValueList = new ArrayList<ColumnValue>();// 值替换集合
    protected List<ColumnValue> columnStyleList = new ArrayList<ColumnValue>();// 颜色替换集合
    public Map<String, Object> map;// 封装查询条件
    private String actionUrl;// 分页提交路径
    public int allCount;
    public int curPageNo;
    public int pageSize = 10;
    public boolean pagination = true;// 是否显示分页
    private String width;
    private String height;
    private boolean checkbox = false;// 是否显示复选框
    private boolean showPageList = true;// 定义是否显示页面列表
    private boolean openFirstNode = false;//是不是展开第一个节点
    private boolean fit = true;// 是否允许表格自动缩放，以适应父容器
    private boolean fitColumns = true;// 当为true时，自动展开/合同列的大小，以适应的宽度，防止横向滚动.
    private String sortName;//定义的列进行排序
    private String sortOrder = "asc";//定义列的排序顺序，只能是"递增"或"降序".
    private boolean showRefresh = true;// 定义是否显示刷新按钮
    private boolean showText = true;// 定义是否显示刷新按钮
    private String style = "easyui";// 列表样式easyui,datatables
    private String onLoadSuccess;// 数据加载完成调用方法
    private String onClick;// 单击事件调用方法
    private String onDblClick;// 双击事件调用方法
    private String queryMode = "single";//查询模式
    private String entityName;//对应的实体对象
    private String rowStyler;//rowStyler函数
    private String extendParams;//扩展参数,easyui有的,但是jeecg没有的参数进行扩展
    private boolean autoLoadData = true; // 列表是否自动加载数据
    //private boolean frozenColumn=false; // 是否是冰冻列    默认不是
    //json转换中的系统保留字
    protected static Map<String, String> syscode = new HashMap<String, String>();

    public int doStartTag() throws JspTagException {
        // 清空资源
        urlList.clear();
        toolBarList.clear();
        columnValueList.clear();
        columnStyleList.clear();
        columnList.clear();
        fields = "";
        searchFields = "";
        return EVAL_PAGE;
    }

    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(doBuildEasyUi());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }

    /**
     * <b>Summary: </b> setColumn(设置字段)
     *
     * @param
     * @param
     * @param
     */
    public void setColumn(String title, String field, Integer width, String rowspan, String colspan, String align, boolean sortable, boolean checkbox, String formatter, boolean hidden, String replace, String treefield, boolean image, String imageSize, boolean query, String url, String funname, String arg, String queryMode, String dictionary, boolean frozenColumn, String extend, String style, String downloadName, boolean isAuto, String extendParams, String customFormatter) {
        DataGridColumn dataGridColumn = new DataGridColumn();
        dataGridColumn.setAlign(align);
        dataGridColumn.setCheckbox(checkbox);
        dataGridColumn.setColspan(colspan);
        dataGridColumn.setField(field);
        dataGridColumn.setFormatter(formatter);
        dataGridColumn.setHidden(hidden);
        dataGridColumn.setRowspan(rowspan);
        dataGridColumn.setSortable(sortable);
        dataGridColumn.setTitle(title);
        dataGridColumn.setWidth(width);
        dataGridColumn.setTreefield(treefield);
        dataGridColumn.setImage(image);
        dataGridColumn.setImageSize(imageSize);
        dataGridColumn.setReplace(replace);
        dataGridColumn.setQuery(query);
        dataGridColumn.setUrl(url);
        dataGridColumn.setFunname(funname);
        dataGridColumn.setArg(arg);
        dataGridColumn.setQueryMode(queryMode);
        dataGridColumn.setDictionary(dictionary);
        dataGridColumn.setFrozenColumn(frozenColumn);
        dataGridColumn.setExtend(extend);
        dataGridColumn.setStyle(style);
        dataGridColumn.setDownloadName(downloadName);
        dataGridColumn.setAutocomplete(isAuto);
        dataGridColumn.setExtendParams(extendParams);
        dataGridColumn.setCustomFormatter(customFormatter);
        columnList.add(dataGridColumn);
        if (field != "opt") {
            fields += field + ",";
            if ("group".equals(queryMode)) {
                searchFields += field + "," + field + "_begin," + field + "_end,";
            } else {
                searchFields += field + ",";
            }
        }
        if (replace != null) {
            String[] test = replace.split(",");
            String text = "";
            String value = "";
            for (String string : test) {
                if (string.indexOf("_") != -1) {
                    text += string.substring(0, string.indexOf("_")) + ",";
                    value += string.substring(string.indexOf("_") + 1) + ",";
                }
            }
            setColumn(field, text, value);

        }
        if (!StringUtils.isBlank(dictionary)) {
            /*if (dictionary.contains(",")) {
                String[] dic = dictionary.split(",");
                String text = "";
                String value = "";
                String sql = "select " + dic[1] + " as field," + dic[2]
                        + " as text from " + dic[0];
                systemService = ApplicationContextUtil.getContext().getBean(
                        SystemService.class);
                List<Map<String, Object>> list = systemService.findForJdbc(sql);
                for (Map<String, Object> map : list) {
                    text += map.get("text") + ",";
                    value += map.get("field") + ",";
                }
                if (list.size() > 0)
                    setColumn(field, text, value);
            } else {
                String text = "";
                String value = "";
                List<TSType> typeList = TSTypegroup.allTypes.get(dictionary.toLowerCase());
                if (typeList != null && !typeList.isEmpty()) {
                    for (TSType type : typeList) {
                        text += type.getTypename() + ",";
                        value += type.getTypecode() + ",";
                    }
                    setColumn(field, text, value);
                }
            }*/
        }
        if (StringUtils.isNotEmpty(style)) {
            String[] temp = style.split(",");
            String text = "";
            String value = "";
            if (temp.length == 1 && temp[0].indexOf("_") == -1) {
                text = temp[0];
            } else {
                for (String string : temp) {
                    if (string.indexOf("_") != -1) {
                        text += string.substring(0, string.indexOf("_")) + ",";
                        value += string.substring(string.indexOf("_") + 1) + ",";
                    }
                }
            }
            setStyleColumn(field, text, value);
        }
    }

    /**
     * 设置 颜色替换值
     *
     * @param field
     * @param text
     * @param value
     */
    private void setStyleColumn(String field, String text, String value) {
        ColumnValue columnValue = new ColumnValue();
        columnValue.setName(field);
        columnValue.setText(text);
        columnValue.setValue(value);
        columnStyleList.add(columnValue);
    }

    /**
     * <b>Summary: </b> setColumn(设置字段替换值)
     *
     * @param name
     * @param text
     * @param value
     */
    public void setColumn(String name, String text, String value) {
        ColumnValue columnValue = new ColumnValue();
        columnValue.setName(name);
        columnValue.setText(text);
        columnValue.setValue(value);
        columnValueList.add(columnValue);
    }

    public String doBuildEasyUi() {
        String grid = "";
        StringBuilder html = new StringBuilder();
        width = (width == null) ? "auto" : width;
        height = (height == null) ? "auto" : height;
        html.append("<script type=\"text/javascript\">");
        html.append("$(function(){");
        //html.append(this.getNoAuthOperButton());
        if (treegrid) {
            grid = "treegrid";
            html.append("$(\'#" + name + "\').treegrid({");
            html.append("idField:'id',");
            html.append("treeField:'text',");
        } else {
            grid = "datagrid";
            html.append("$(\'#" + name + "\').datagrid({");
            html.append("idField: '" + idField + "',");
        }
        if (title != null) {
            html.append("title: \'" + title + "\',");
        }
        if(actionUrl.indexOf("?")>0){
            actionUrl=actionUrl+"&";
        }else {
            actionUrl=actionUrl+"?";
        }
        if (autoLoadData)
            html.append("url:\'" + actionUrl + "field=" + fields + "\',");
        else
            html.append("url:\'',");
        if (StringUtils.isNotEmpty(rowStyler)) {
            html.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
        }
        if (StringUtils.isNotEmpty(extendParams)) {
            html.append(extendParams);
        }
        if (fit) {
            html.append("fit:true,");
        } else {
            html.append("fit:false,");
        }
        html.append("loadMsg: \'数据加载中...\',");
        html.append("pageSize: " + pageSize + ",");
        html.append("pagination:" + pagination + ",");
        html.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
        if (StringUtils.isNotBlank(sortName)) {
            html.append("sortName:'" + sortName + "',");
        }
        html.append("sortOrder:'" + sortOrder + "',");
        html.append("rownumbers:true,");
        html.append("singleSelect:" + !checkbox + ",");
        if (fitColumns) {
            html.append("fitColumns:true,");
        } else {
            html.append("fitColumns:false,");
        }
        html.append("showFooter:true,");
        html.append("frozenColumns:[[");
        this.getField(html, 0);
        html.append("]],");

        html.append("columns:[[");
        this.getField(html);
        html.append("]],");
        html.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + grid + "(\"clearSelections\");");
        if (openFirstNode && treegrid) {
            html.append(" if(data==null){");
            html.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
            html.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
        }
        if (StringUtils.isNotEmpty(onLoadSuccess)) {
            html.append(onLoadSuccess + "(data);");
        }
        html.append("},");
        if (StringUtils.isNotEmpty(onDblClick)) {
            html.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
        }
        if (treegrid) {
            html.append("onClickRow:function(rowData){");
        } else {
            html.append("onClickRow:function(rowIndex,rowData){");
        }
        /**行记录赋值*/
        html.append("rowid=rowData.id;");
        html.append("gridname=\'" + name + "\';");
        if (StringUtils.isNotEmpty(onClick)) {
            if (treegrid) {
                html.append("" + onClick + "(rowData);");
            } else {
                html.append("" + onClick + "(rowIndex,rowData);");
            }
        }
        html.append("}");
        html.append("});");
        this.setPager(html, grid);
        html.append("});");
        html.append("function reloadTable(){");
        html.append("try{");
        html.append("	$(\'#\'+gridname).datagrid(\'reload\');");
        html.append("	$(\'#\'+gridname).treegrid(\'reload\');");
        html.append("}catch(ex){}");
        html.append("}");
        html.append("function reloadGird(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
        html.append("function reload" + name + "(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
        html.append("function get" + name + "Selected(field){return getSelected(field);}");
        html.append("function getSelected(field){" + "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value=\'\';" + "}" + "return value;" + "}");
        html.append("function get" + name + "Selections(field){" + "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid + "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');" + "return ids" + "};");
        html.append("function getSelectRows(){");
        html.append("	return $(\'#" + name + "\').datagrid('getChecked');");
        html.append("}");
        if (columnList.size() > 0) {
            html.append("function " + name + "search(){");
            html.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
            html.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
            html.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "field=" + searchFields + "',pageNumber:1});" + "}");

            //高级查询执行方法
            html.append("function dosearch(params){");
            html.append("var jsonparams=$.parseJSON(params);");
            html.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "field=" + searchFields + "',queryParams:jsonparams});" + "}");

            //searchbox框执行方法
            searchboxFun(html, grid);
            //生成重置按钮功能js

            //回车事件
            html.append("function EnterPress(e){");
            html.append("var e = e || window.event;");
            html.append("if(e.keyCode == 13){ ");
            html.append(name + "search();");
            html.append("}}");

            html.append("function searchReset(name){");
            html.append(" $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
            String func = name.trim() + "search();";
            html.append(func);
            html.append("}");
        }
        html.append("</script>");
        html.append("<table width=\"100%\"   id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
        html.append("<div id=\"" + name + "tb\" style=\"padding:3px; height: auto\">");
        if (hasQueryColum(columnList)) {
            html.append("<div name=\"searchColums\">");
            //如果表单是组合查询
            if ("group".equals(getQueryMode())) {
                for (DataGridColumn col : columnList) {
                    if (col.isQuery()) {
                        html.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
                        html.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + col.getTitle() + "：</span>");
                        if ("single".equals(col.getQueryMode())) {
                            if (!StringUtils.isEmpty(col.getReplace())) {
                                html.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                                html.append("<option value =\"\" >---请选择---</option>");
                                String[] test = col.getReplace().split(",");
                                String text = "";
                                String value = "";
                                for (String string : test) {
                                    text = string.split("_")[0];
                                    value = string.split("_")[1];
                                    html.append("<option value =\"" + value + "\">" + text + "</option>");
                                }
                                html.append("</select>");
                            } else if (!StringUtils.isEmpty(col.getDictionary())) {

                            } else if (col.isAutocomplete()) {
                                html.append(getAutoSpan(col.getField().replaceAll("_", "\\."), extendAttribute(col.getExtend())));
                            } else {
                                html.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  " + extendAttribute(col.getExtend()) + " style=\"width: 100px\" />");
                            }
                        } else if ("group".equals(col.getQueryMode())) {
                            html.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 94px\" " + extendAttribute(col.getExtend()) + "/>");
                            html.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                            html.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 94px\" " + extendAttribute(col.getExtend()) + "/>");
                        }
                        html.append("</span>");
                    }
                }
            }
            html.append("</div>");
        }
        if (toolBarList.size() == 0 && !hasQueryColum(columnList)) {
            html.append("<div style=\"height:0px;\" >");
        } else {
            html.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
        }
        html.append("<span style=\"float:left;\" >");
        if (toolBarList.size() > 0) {
            for (DataGridUrl toolBar : toolBarList) {
                html.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
                if (StringUtils.isNotEmpty(toolBar.getOnclick())) {
                    html.append("onclick=" + toolBar.getOnclick() + "");
                } else {
                    html.append("onclick=\"" + toolBar.getFunname() + "(");
                    if (!toolBar.getFunname().equals("doSubmit")) {
                        html.append("\'" + toolBar.getTitle() + "\',");
                    }
                    String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
                    String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
                    html.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height+",\'"+toolBar.getIdFiledName()+"\')\"");
                }
                html.append(">" + toolBar.getTitle() + "</a>");
            }
        }
        html.append("</span>");
        if ("group".equals(getQueryMode()) && hasQueryColum(columnList)) {//如果表单是组合查询
            html.append("<span style=\"float:right\">");
            html.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + name + "search()\">查询</a>");
            html.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"searchReset('" + name + "')\">重置</a>");
            html.append("</span>");
        } else if ("single".equals(getQueryMode()) && hasQueryColum(columnList)) {//如果表单是单查询
            html.append("<span style=\"float:right\">");
            html.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name + "searchbox,prompt:\'请输入关键字\',menu:\'#" + name + "mm\'\"></input>");
            html.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
            for (DataGridColumn col : columnList) {
                if (col.isQuery()) {
                    html.append("<div data-options=\"name:\'" + col.getField().replaceAll("_", "\\.") + "\',iconCls:\'icon-ok\' " + extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
                }
            }
            html.append("</div>");
            html.append("</span>");
        }
        html.append("</div>");
        return html.toString();
    }

    /**
     * 处理否含有json转换中的保留字
     *
     * @param field
     * @param flag  1:转换 2:还原
     * @return
     */
    private String dealSyscode(String field, int flag) {
        String change = field;
        Iterator it = syscode.keySet().iterator();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = String.valueOf(syscode.get(key));
            if (flag == 1) {
                change = field.replaceAll(key, value);
            } else if (flag == 2) {
                change = field.replaceAll(value, key);
            }
        }
        return change;
    }

    /**
     * 生成扩展属性
     *
     * @param field
     * @return
     */
    private String extendAttribute(String field) {
        if (StringUtils.isEmpty(field)) {
            return "";
        }
        field = dealSyscode(field, 1);
        StringBuilder re = new StringBuilder();
        try {
            JSONObject obj = JSONObject.parseObject(field);
            for(java.util.Map.Entry<String,Object> entry:obj.entrySet()){
                //System.out.print(entry.getKey()+"-"+entry.getValue()+"\t");
                String key = entry.getKey();
                JSONObject nextObj = ((JSONObject)entry.getValue());
                re.append(key + "=" + "\"");
                for(java.util.Map.Entry<String,Object> entry1:nextObj.entrySet()){
                    if (nextObj.size() <= 1) {
                        String onlykey = entry1.getKey();
                        if ("value".equals(onlykey)) {
                            re.append(nextObj.get(onlykey) + "");
                        } else {
                            re.append(onlykey + ":" + nextObj.get(onlykey) + "");
                        }
                        break;
                    }else{
                        String multkey = entry1.getKey();
                        String multvalue = nextObj.getString(multkey);
                        re.append(multkey + ":" + multvalue + ",");
                        re.deleteCharAt(re.length() - 1);
                    }
                }
                re.append("\" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return dealSyscode(re.toString(), 2);
    }

    /**
     * 获取自动补全的panel
     *
     * @param filed
     * @return
     * @author JueYue
     */
    private String getAutoSpan(String filed, String extend) {
        String id = filed.replaceAll("\\.", "_");
        StringBuffer nsb = new StringBuffer();
        nsb.append("<script type=\"text/javascript\">");
        nsb.append("$(document).ready(function() {")
                .append("$(\"#" + getEntityName() + "_" + id + "\").autocomplete(\"commonController.do?getAutoList=value\",{")
                .append("max: 5,minChars: 2,width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{")
                .append("featureClass : \"P\",style : \"full\",	maxRows : 10,labelField : \"" + filed + "\",valueField : \"" + filed + "\",")
                .append("searchField : \"" + filed + "\",entityName : \"" + getEntityName() + "\",trem: function(){return $(\"#" + getEntityName() + "_" + id + "\").val();}}");
        nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
        nsb.append(",formatItem:function(row, i, max){return row['" + filed + "'];} ");
        nsb.append("}).result(function (event, row, formatted) {");
        nsb.append("$(\"#" + getEntityName() + "_" + id + "\").val(row['" + filed + "']);}); });")
                .append("</script>")
                .append("<input type=\"text\" id=\"" + getEntityName() + "_" + id + "\" name=\"" + filed + "\" datatype=\"*\" " + extend + " nullmsg=\"\" errormsg=\"输入错误\"/>");
        return nsb.toString();
    }

    /**
     * 判断是否存在查询字段
     *
     * @return hasQuery true表示有查询字段,false表示没有
     */
    protected boolean hasQueryColum(List<DataGridColumn> columnList) {
        boolean hasQuery = false;
        for (DataGridColumn col : columnList) {
            if (col.isQuery()) {
                hasQuery = true;
            }
        }
        return hasQuery;
    }

    //列表查询框函数
    protected void searchboxFun(StringBuilder sb, String grid) {
        sb.append("function " + name + "searchbox(value,name){");
        sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
        sb.append("queryParams[name]=value;queryParams.searchfield=name;$(\'#" + name + "\')." + grid + "(\'reload\');}");
        sb.append("$(\'#" + name + "searchbox\').searchbox({");
        sb.append("searcher:function(value,name){");
        sb.append("" + name + "searchbox(value,name);");
        sb.append("},");
        sb.append("menu:\'#" + name + "mm\',");
        sb.append("prompt:\'请输入查询关键字\'");
        sb.append("});");
    }

    /**
     * 设置分页条信息
     *
     * @param sb
     */
    protected void setPager(StringBuilder sb, String grid) {
        sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
        sb.append("beforePageText:\'\'," + "afterPageText:\'/{pages}\',");
        if (showText) {
            sb.append("displayMsg:\'{from}-{to}共{total}条\',");
        } else {
            sb.append("displayMsg:\'\',");
        }
        if (showPageList == true) {
            sb.append("showPageList:true,");
        } else {
            sb.append("showPageList:false,");
        }
        sb.append("showRefresh:" + showRefresh + "");
        sb.append("});");// end getPager
        sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
        sb.append("onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination(\'loading\');$(this).pagination(\'loaded\'); }");
        sb.append("});");
    }

    /**
     * 拼接字段  普通列
     *
     * @param sb
     */
    protected void getField(StringBuilder sb) {
        getField(sb, 1);
    }

    /**
     * 拼接字段
     *
     * @param sb
     * @frozen 0 冰冻列    1 普通列
     */
    protected void getField(StringBuilder sb, int frozen) {
        // 复选框
        if (checkbox && frozen == 0) {
            sb.append("{field:\'ck\',checkbox:\'true\'},");
        }
        int i = 0;
        for (DataGridColumn column : columnList) {
            i++;
            if ((column.isFrozenColumn() && frozen == 0) || (!column.isFrozenColumn() && frozen == 1)) {
                String field;
                if (treegrid) {
                    field = column.getTreefield();
                } else {
                    field = column.getField();
                }
                sb.append("{field:\'" + field + "\',title:\'" + column.getTitle() + "\'");
                if (column.getWidth() != null) {
                    sb.append(",width:" + column.getWidth());
                }
                if (column.getAlign() != null) {
                    sb.append(",align:\'" + column.getAlign() + "\'");
                }
                if (StringUtils.isNotEmpty(column.getExtendParams())) {
                    sb.append("," + column.getExtendParams().substring(0,
                            column.getExtendParams().length() - 1));
                }
                // 隐藏字段
                if (!column.isHidden()) {
                    sb.append(",hidden:true");
                }
                if (!treegrid) {
                    // 字段排序
                    if ((column.isSortable()) && (field.indexOf("_") <= 0 && field != "opt")) {
                        sb.append(",sortable:" + column.isSortable() + "");
                    }
                }
                // 显示图片
                if (column.isImage()) {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>';}");
                }
                // 自定义显示图片
                if (column.getImageSize() != null) {
                    String[] tld = column.getImageSize().split(",");
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return '<img width=\"" + tld[0]
                            + "\" height=\"" + tld[1]
                            + "\" border=\"0\" src=\"'+value+'\"/>';}");
                    tld = null;
                }
                if (column.getDownloadName() != null) {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return '<a target=\"_blank\" href=\"'+value+'\">"
                            + column.getDownloadName() + "</a>';}");
                }
                // 自定义链接
                if (column.getUrl() != null) {
                    sb.append(",formatter:function(value,rec,index){");
                    this.getFun(sb, column);
                    sb.append("}");
                }
                if (column.getFormatter() != null) {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append("return formatDate(value);}");
                }
                if (column.getCustomFormatter() != null) {
                    sb.append(",formatter:");
                    sb.append(column.getCustomFormatter());
                }
                // 加入操作
                if (column.getField().equals("opt")) {
                    sb.append(",formatter:function(value,rec,index){");
                    // sb.append("return \"");
                    this.getOptUrl(sb);
                    sb.append("}");
                }
                // 值替換
                if (columnValueList.size() > 0 && !column.getField().equals("opt")) {
                    String testString = "";
                    for (ColumnValue columnValue : columnValueList) {
                        if (columnValue.getName().equals(column.getField())) {
                            String[] value = columnValue.getValue().split(",");
                            String[] text = columnValue.getText().split(",");
                            sb.append(",formatter:function(value,rec,index){");
                            sb.append("try{var valArray = value.split(\",\");");
                            sb.append("if(valArray.length > 1){");
                            sb.append("var checkboxValue = \"\";");
                            sb.append("for(var k=0; k<valArray.length; k++){");
                            for (int j = 0; j < value.length; j++) {
                                sb.append("if(valArray[k] == '" + value[j] + "'){ checkboxValue = checkboxValue + \'" + text[j] + "\' + ','}");
                            }
                            sb.append("}");
                            sb.append("return checkboxValue.substring(0,checkboxValue.length-1);");
                            sb.append("}");
                            sb.append("else{");
                            for (int j = 0; j < value.length; j++) {
                                testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\'}";
                            }
                            sb.append(testString);
                            sb.append("else{return value}");
                            sb.append("}}catch(error){}");
                            sb.append("}");
                        }
                    }

                }
                // 背景设置
                if (columnStyleList.size() > 0 && !column.getField().equals("opt")) {
                    String testString = "";
                    for (ColumnValue columnValue : columnStyleList) {
                        if (columnValue.getName().equals(column.getField())) {
                            String[] value = columnValue.getValue().split(",");
                            String[] text = columnValue.getText().split(",");
                            sb.append(",styler:function(value,rec,index){");
                            if ((value.length == 0 || StringUtils.isEmpty(value[0])) && text.length == 1) {
                                if (text[0].indexOf("(") > -1) {
                                    testString = " return \'" + text[0].replace("(", "(value,rec,index") + "\'";
                                } else {
                                    testString = " return \'" + text[0] + "\'";
                                }
                            } else {
                                for (int j = 0; j < value.length; j++) {
                                    testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\'}";
                                }
                            }
                            sb.append(testString);
                            sb.append("}");
                        }
                    }

                }
                sb.append("}");
                // 去除末尾,
                if (i < columnList.size()) {
                    sb.append(",");
                }
            }
        }
    }

    /**
     * 拼装操作地址
     *
     * @param sb
     */
    protected void getOptUrl(StringBuilder sb) {
        //注：操作列表会带入合计列中去，故加此判断
        sb.append("if(!rec.id){return '';}");
        List<DataGridUrl> list = urlList;
        sb.append("var href='';");
        for (DataGridUrl dataGridUrl : list) {
            String url = dataGridUrl.getUrl();
            MessageFormat formatter = new MessageFormat("");
            if (dataGridUrl.getValue() != null) {
                String[] testvalue = dataGridUrl.getValue().split(",");
                List value = new ArrayList<Object>();
                for (String string : testvalue) {
                    value.add("\"+rec." + string + " +\"");
                }
                url = formatter.format(url, value.toArray());
            }
            if (url != null && dataGridUrl.getValue() == null) {

                url = formatUrl(url);
            }
            String exp = dataGridUrl.getExp();// 判断显示表达式
            if (StringUtils.isNotEmpty(exp)) {
                String[] ShowbyFields = exp.split("&&");
                for (String ShowbyField : ShowbyFields) {
                    int beginIndex = ShowbyField.indexOf("#");
                    int endIndex = ShowbyField.lastIndexOf("#");
                    String exptype = ShowbyField.substring(beginIndex + 1, endIndex);// 表达式类型
                    String field = ShowbyField.substring(0, beginIndex);// 判断显示依据字段
                    String[] values = ShowbyField.substring(endIndex + 1, ShowbyField.length()).split(",");// 传入字段值
                    String value = "";
                    for (int i = 0; i < values.length; i++) {
                        value += "'" + "" + values[i] + "" + "'";
                        if (i < values.length - 1) {
                            value += ",";
                        }
                    }
                    if ("eq".equals(exptype)) {
                        sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
                    }
                    if ("ne".equals(exptype)) {
                        sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
                    }
                    if ("empty".equals(exptype) && value.equals("'true'")) {
                        sb.append("if(rec." + field + "==''){");
                    }
                    if ("empty".equals(exptype) && value.equals("'false'")) {
                        sb.append("if(rec." + field + "!=''){");
                    }
                }
            }

            if (OptTypeDirection.Confirm.equals(dataGridUrl.getType())) {
                sb.append("href+=\"[<a href=\'#\' onclick=confirm(\'" + url + "\',\'" + dataGridUrl.getMessage() + "\',\'" + name + "\')> \";");
            }
            if (OptTypeDirection.Del.equals(dataGridUrl.getType())) {
                sb.append("href+=\"[<a href=\'#\' onclick=delObj(\'" + url + "\',\'" + name + "\')>\";");
            }
            if (OptTypeDirection.Fun.equals(dataGridUrl.getType())) {
                String name = TagUtils.getFunction(dataGridUrl.getFunname());
                String parmars = TagUtils.getFunParams(dataGridUrl.getFunname());
                sb.append("href+=\"[<a href=\'#\' onclick=" + name + "(" + parmars + ")>\";");
            }
            if (OptTypeDirection.OpenWin.equals(dataGridUrl.getType())) {
                sb.append("href+=\"[<a href=\'#\' onclick=openwindow('" + dataGridUrl.getTitle() + "','" + url + "','" + name + "'," + dataGridUrl.getWidth() + "," + dataGridUrl.getHeight() + ")>\";");
            }                                                            //update-end--Author:liuht  Date:20130228 for：弹出窗口设置参数不生效
            if (OptTypeDirection.Deff.equals(dataGridUrl.getType())) {
                sb.append("href+=\"[<a href=\'" + url + "' title=\'" + dataGridUrl.getTitle() + "\'>\";");
            }
            if (OptTypeDirection.OpenTab.equals(dataGridUrl.getType())) {
                sb.append("href+=\"[<a href=\'#\' onclick=addOneTab('" + dataGridUrl.getTitle() + "','" + url + "')>\";");
            }
            sb.append("href+=\"" + dataGridUrl.getTitle() + "</a>]\";");

            if (StringUtils.isNotEmpty(exp)) {
                for (int i = 0; i < exp.split("&&").length; i++) {
                    sb.append("}");
                }

            }
        }
        sb.append("return href;");
    }

    /**
     * 列自定义函数
     *
     * @param sb
     * @param column
     */
    protected void getFun(StringBuilder sb, DataGridColumn column) {
        String url = column.getUrl();
        url = formatUrl(url);
        sb.append("var href=\"<a style=\'color:red\' href=\'#\' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + url + "')>\";");
        sb.append("return href+value+\'</a>\';");

    }

    /**
     * 格式化URL
     *
     * @return
     */
    protected String formatUrl(String url) {
        MessageFormat formatter = new MessageFormat("");
        String parurlvalue = "";
        if (url.indexOf("&") >= 0) {
            String beforeurl = url.substring(0, url.indexOf("&"));// 截取请求地址
            String parurl = url.substring(url.indexOf("&") + 1, url.length());// 截取参数
            String[] pras = parurl.split("&");
            List value = new ArrayList<Object>();
            int j = 0;
            for (int i = 0; i < pras.length; i++) {
                if (pras[i].indexOf("{") >= 0 || pras[i].indexOf("#") >= 0) {
                    String field = pras[i].substring(pras[i].indexOf("{") + 1, pras[i].lastIndexOf("}"));
                    parurlvalue += "&" + pras[i].replace("{" + field + "}", "{" + j + "}");
                    value.add("\"+rec." + field + " +\"");
                    j++;
                } else {
                    parurlvalue += "&" + pras[i];
                }
            }
            url = formatter.format(beforeurl + parurlvalue, value.toArray());
        }
        return url;

    }


    /**
     * 描述：组装菜单按钮操作权限
     * dateGridUrl：url
     * operationCode：操作码
     * optList： 操作列表
     *
     * @version 1.0
     */
    private void installOperationCode(DataGridUrl dataGridUrl, String operationCode, List optList) {
        optList.add(dataGridUrl);
    }

    /**
     * 设置询问操作URL
     */
    public void setConfUrl(String url, String title, String message, String exp, String operationCode) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Confirm);
        dataGridUrl.setMessage(message);
        dataGridUrl.setExp(exp);
        installOperationCode(dataGridUrl, operationCode, urlList);
    }

    /**
     * 设置删除操作URL
     */
    public void setDelUrl(String url, String title, String message, String exp, String funname, String operationCode) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Del);
        dataGridUrl.setMessage(message);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        installOperationCode(dataGridUrl, operationCode, urlList);
    }

    /**
     * 设置默认操作URL
     */
    public void setDefUrl(String url, String title, String exp, String operationCode) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Deff);
        dataGridUrl.setExp(exp);
        installOperationCode(dataGridUrl, operationCode, urlList);

    }

    /**
     * 设置工具条
     *
     * @param height2
     * @param width2
     */
    public void setToolbar(String url, String title, String icon, String exp, String onclick, String funname, String operationCode, String width2, String height2,String idFiledName,boolean disabled) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.ToolBar);
        dataGridUrl.setIcon(icon);
        dataGridUrl.setOnclick(onclick);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        dataGridUrl.setWidth(String.valueOf(width2));
        dataGridUrl.setHeight(String.valueOf(height2));
        dataGridUrl.setIdFiledName(idFiledName);
        dataGridUrl.setDisabled(disabled);
        installOperationCode(dataGridUrl, operationCode, toolBarList);

    }

    /**
     * 设置自定义函数操作URL
     */
    public void setFunUrl(String title, String exp, String funname, String operationCode) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setType(OptTypeDirection.Fun);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        installOperationCode(dataGridUrl, operationCode, urlList);

    }

    /**
     * 设置自定义函数操作URL
     */
    public void setOpenUrl(String url, String title, String width, String height, String exp, String operationCode, String openModel) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setWidth(width);
        dataGridUrl.setHeight(height);
        dataGridUrl.setType(OptTypeDirection.valueOf(openModel));
        dataGridUrl.setExp(exp);
        installOperationCode(dataGridUrl, operationCode, urlList);

    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public boolean isTreegrid() {
        return treegrid;
    }

    public void setTreegrid(boolean treegrid) {
        this.treegrid = treegrid;
    }

    public List<DataGridUrl> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<DataGridUrl> urlList) {
        this.urlList = urlList;
    }

    public List<DataGridUrl> getToolBarList() {
        return toolBarList;
    }

    public void setToolBarList(List<DataGridUrl> toolBarList) {
        this.toolBarList = toolBarList;
    }

    public List<DataGridColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<DataGridColumn> columnList) {
        this.columnList = columnList;
    }

    public List<ColumnValue> getColumnValueList() {
        return columnValueList;
    }

    public void setColumnValueList(List<ColumnValue> columnValueList) {
        this.columnValueList = columnValueList;
    }

    public List<ColumnValue> getColumnStyleList() {
        return columnStyleList;
    }

    public void setColumnStyleList(List<ColumnValue> columnStyleList) {
        this.columnStyleList = columnStyleList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getCurPageNo() {
        return curPageNo;
    }

    public void setCurPageNo(int curPageNo) {
        this.curPageNo = curPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isShowPageList() {
        return showPageList;
    }

    public void setShowPageList(boolean showPageList) {
        this.showPageList = showPageList;
    }

    public boolean isOpenFirstNode() {
        return openFirstNode;
    }

    public void setOpenFirstNode(boolean openFirstNode) {
        this.openFirstNode = openFirstNode;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public boolean isFitColumns() {
        return fitColumns;
    }

    public void setFitColumns(boolean fitColumns) {
        this.fitColumns = fitColumns;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isShowRefresh() {
        return showRefresh;
    }

    public void setShowRefresh(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOnLoadSuccess() {
        return onLoadSuccess;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getOnDblClick() {
        return onDblClick;
    }

    public void setOnDblClick(String onDblClick) {
        this.onDblClick = onDblClick;
    }

    public String getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getRowStyler() {
        return rowStyler;
    }

    public void setRowStyler(String rowStyler) {
        this.rowStyler = rowStyler;
    }

    public String getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(String extendParams) {
        this.extendParams = extendParams;
    }

    public boolean isAutoLoadData() {
        return autoLoadData;
    }

    public void setAutoLoadData(boolean autoLoadData) {
        this.autoLoadData = autoLoadData;
    }
}
