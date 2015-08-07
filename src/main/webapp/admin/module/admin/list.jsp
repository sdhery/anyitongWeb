<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!Doctype html>
<t:base type="jquery,easyui,dateformat"></t:base>
<t:datagrid name="infoGrid" actionUrl="/admin/user/list?isAdmin=1" fit="true" fitColumns="true" idField="id" queryMode="group" pageSize="20" title="管理员列表">
    <t:dgCol title="编号" field="sysUserId" hidden="false"></t:dgCol>
    <t:dgCol title="用户名" sortable="false" field="loginId"></t:dgCol>
    <t:dgCol title="真实姓名" sortable="false" field="realName"></t:dgCol>
    <t:dgCol title="创建时间" sortable="false" field="createTime"></t:dgCol>
    <t:dgCol title="修改时间" sortable="false" field="lastModifiedTime"></t:dgCol>
    <t:dgCol title="最后登录时间" sortable="false" field="lastLoginTime"></t:dgCol>
    <t:dgToolBar title="添加管理员" icon="icon-add" funname="top.openIframeDialog" url="/admin/admin/add" width="28%"></t:dgToolBar>
    <t:dgToolBar title="修改管理员" icon="icon-edit" funname="updateIframeDialog" url="/admin/admin/update" width="26%" idFiledName="sysUserId"></t:dgToolBar>
    <t:dgToolBar title="修改密码" icon="icon-lock" funname="updatePwd" url="/admin/admin/updatePassword" idFiledName="sysUserId" width="26%"></t:dgToolBar>
</t:datagrid>
<script>
    function updateIframeDialog(title, url, gridId, width, height, idFiledName){
        var rowsData =$("#" + gridId).datagrid('getSelected');
        if(rowsData[idFiledName]==1){
            top.tip('超级管理员不允许修改');
            return
        }
        top.updateIframeDialog(title,url,gridId,width,height,idFiledName)
    }
    function updatePwd(title, url, gridId, width, height, idFiledName) {
        var rowsData = top.$W.contentIFrame.$("#" + gridId).datagrid('getSelected');
        if (rowsData==null) {
            top.tip('请选择修改密码的管理员');
            return;
        }
        url += '?sysUserId=' + rowsData[idFiledName]
        top.openIframeDialog(title, url, gridId, width, height)
    }
</script>