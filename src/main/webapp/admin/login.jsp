<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="include/includeAdminTaglibs.jsp" %>
<!Doctype html>
<html>
<title>登记管理中心</title>
<head>
    <t:base type="jquery,easyui"></t:base>
    <style>
        body {
            background-color: #eee;
        }
    </style>
</head>
<body>
<table width="100%">
    <tr>
        <td align="center" valign="middle" height="400">
            <div class="easyui-panel" title="登记管理中心" style="width:330px">
                <form method="post" action="/admin/check" id="loginForm" name="loginForm">
                    <table cellpadding="4" width="100%">
                        <tr>
                            <td>用户名:</td>
                            <td><input class="easyui-textbox" type="text" name="username" data-options="required:true" placeholder="请输入用户名"/></td>
                        </tr>
                        <tr>
                            <td>密码:</td>
                            <td><input class="easyui-textbox" type="password" name="password" data-options="required:true" placeholder="请输入密码"/></td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" data-options="iconCls:'icon-ok'">登录</a>
                </div>
            </div>
        </td>
    </tr>
</table>
<script>
    function submitForm() {
        var flag = $("#loginForm").form('validate');
        if(flag){
            $("#loginForm").submit()
        }
    }
    function showMessage(msg, time) {
        $.messager.show({
            title: '系统消息',
            msg: msg,
            timeout: time ? time : 2500,
            style:{
                right:'',
                top:document.body.scrollTop+document.documentElement.scrollTop,
                bottom:''
            }
        });
    }
    <c:if test="${not empty error}">showMessage("${error}")</c:if>
</script>
</body>
</html>