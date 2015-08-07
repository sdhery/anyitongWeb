<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!Doctype html>
<t:base type="jquery,easyui,dateformat"></t:base>
<t:datagrid name="infoGrid"  actionUrl="/admin/info/list" fit="true" fitColumns="true" idField="id" queryMode="group" pageSize="20">
        <t:dgCol title="编号" field="articleId" hidden="false"></t:dgCol>
        <t:dgCol title="标题" sortable="false" field="title"></t:dgCol>
        <t:dgCol title="创建时间" sortable="false" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
        <t:dgCol title="修改时间" sortable="false" field="lastModifiedTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
        <t:dgToolBar title="添加信息" icon="icon-add"  funname="top.openIframeDialog" url="/admin/info/add"></t:dgToolBar>
        <t:dgToolBar title="修改信息" icon="icon-edit"  funname="top.updateIframeDialog" url="/admin/info/update" idFiledName="articleId"></t:dgToolBar>
</t:datagrid>
