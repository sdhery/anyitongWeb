<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <t:base type="jquery,easyui"></t:base>
	<style>
* {
  font-size: 12px;
}
</style>
</head>
<body>
<form method="post" id="formObj" name="formObj">
    <input type="hidden" name="sysUserId" value="${sysUser.sysUserId}"/>
    <table cellpadding="5">
        <tr>
            <td>用户名:</td>
            <td>
                ${sysUser.loginId}
            </td>
        </tr>
        <tr>
            <td>真实姓名:</td>
            <td>
                ${sysUser.realName}
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td>
                <input class="easyui-textbox" type="password" name="password" data-options="required:true"/>
            </td>
        </tr>
    </table>
</form>
<script>
    $("#formObj").form({
        url: '${frontPath}/admin/user/updatePassword',
        onSubmit: function () {
            var flag = $(this).form('validate');
            return flag;
        },
        success: function (resultObj) {
            var tip="成功"
            resultObj=$.parseJSON(resultObj)
            if(!resultObj.success){
                tip="失败"
            }
            top.showMessage("修改密码"+tip)
            top.reloadContentGird();
            top.closeDialog()
        }
    })
    function submitForm(){
        $("#formObj").submit()
    }
</script>
</body>
</html>