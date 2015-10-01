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
                <input class="easyui-validatebox textbox" type="text" name="loginId" value="${item.loginId}" data-options="required:true,validType:['checkLoginId','length[4,10]']" ${empty item.loginId ? '' : 'disabled'}/>
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
    $.extend($.fn.validatebox.defaults.rules, {
        checkLoginId: {
            validator: function(value){
                var flag;
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/admin/admin/countByLoginId"/>',
                    data:'loginId='+value,
                    async:false,
                    success: function(data) {
                        if(data.success==true) {
                            flag = true;
                        }else{
                            flag =  false;
                        }
                    }
                });
                return flag;
            },
            message: '您输入的用户名已存在，请更换。'
        }
    });
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