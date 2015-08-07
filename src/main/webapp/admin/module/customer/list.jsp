<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!Doctype html>
<html>
<t:base type="jquery,easyui,dateformat"></t:base>
<body>
<t:datagrid name="infoGrid"  actionUrl="/admin/customer/findCustomerVoList" fit="true" fitColumns="true" idField="id" queryMode="group" pageSize="20" title="登记表" rowStyler="rowStyler">
        <t:dgCol title="编号" field="customerId" hidden="false"></t:dgCol>
        <t:dgCol title="approvalSysUserId" field="approvalSysUserId" hidden="false"></t:dgCol>
        <t:dgCol title="姓名" sortable="false" field="realName" query="true"></t:dgCol>
        <t:dgCol title="性别" sortable="false" field="sex" replace="男_1,女_2" query="true"></t:dgCol>
        <t:dgCol title="固定电话" sortable="false" field="phone"></t:dgCol>
        <t:dgCol title="移动电话" sortable="false" field="mobilePhone"></t:dgCol>
        <t:dgCol title="住所地址" sortable="false" field="address"></t:dgCol>
        <t:dgCol title="居家状况" sortable="false" field="homeSituation" replace="孤寡_0,独居_1,双老_2,子女同住_3,其他_4"></t:dgCol>
        <t:dgCol title="购买类型" sortable="false" field="purchaseType" replace="普通_0,全额资助_1,半额资助_2,慈善捐助_3,其他_4"></t:dgCol>
        <t:dgCol title="呼叫器ID" sortable="false" field="pagerId"></t:dgCol>
		<t:dgCol title="编辑状态" sortable="false" field="approvalStatus" replace="未编辑_1,正在编辑_2,编辑完成_3,审批不通过_4" query="true" style="color:red"></t:dgCol>
        <t:dgCol title="编辑人" sortable="false" field="editRealName" style="color:blue"></t:dgCol>
        <t:dgCol title="地图经度" sortable="false" field="mapLongitude"></t:dgCol>
        <t:dgCol title="地图纬度" sortable="false" field="mapLatitude"></t:dgCol>
        <t:dgCol title="创建时间" sortable="false" field="createTime"></t:dgCol>
        <t:dgCol title="最后修改时间" sortable="false" field="lastModifiedTime"></t:dgCol>
        <t:dgToolBar title="打印" icon="icon-print"  funname="top.printUrl" url="/admin/customer/print" idFiledName="customerId"></t:dgToolBar>
        <t:dgToolBar title="编辑" icon="icon-redo"  funname="top.approval" url="/admin/customer/print" idFiledName="customerId" width="800" height="500"></t:dgToolBar>
</t:datagrid>
<script>
function rowStyler(index,row){
    if(row.approvalStatus==3){
        return 'background-color:#6293BB;color:#fff;';
    }
}
</script>
</body>
</html>
