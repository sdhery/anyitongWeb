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
<body style="overflow: hidden">
<form method="post" id="formObj" name="formObj">
    <input type="hidden" name="isAdmin" value="1"/>
    <input type="hidden" name="updateId" value="${item.sysUserId}"/>
    <input type="hidden" name="sysUserId" value="${item.sysUserId}"/>
    <table cellpadding="5">
        <tr>
            <td>用户名:</td>
            <td>
                <input class="easyui-textbox" type="text" name="loginId" value="${item.loginId}" data-options="required:true" ${empty item.loginId ? '' : 'disabled'}/>
            </td>
        </tr>
        <tr>
            <td>真实姓名:</td>
            <td>
                <input class="easyui-textbox" type="text" name="realName" value="${item.realName}" data-options="required:true"/>
            </td>
        </tr>
        <c:if test="${empty item}">
            <tr>
                <td>密码:</td>
                <td>
                    <input class="easyui-textbox" type="password" name="password" data-options="required:true"/>
                </td>
            </tr>
        </c:if>
    </table>
</form>
<script>
    $("#formObj").form({
        url: '${frontPath}/admin/admin/saveAdmin',
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
            top.showMessage("${empty item.loginId ? '添加' : '修改'}管理员"+tip)
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