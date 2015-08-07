<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!DOCTYPE html>
<t:base type="jquery,easyui,bootstrap3"></t:base>
<script src="${frontPath}/admin/js/ckeditor/ckeditor.js"></script>
<form method="post" id="formObj" name="formObj">
    <input type="hidden" name="articleId" value="${item.articleId}"/>
    <input type="hidden" name="updateId" value="${item.articleId}"/>
    <table cellpadding="5">
        <tr>
            <td>标题:</td>
            <td>
                <input class="easyui-textbox" type="text" name="title" value="${item.title}"/>
            </td>
        <tr>
            <td>Name:</td>
            <td>
                <textarea class="ckeditor" name="content">${item.content}</textarea>
            </td>
        </tr>
    </table>
</form>
<script>
    $("#formObj").form({
        url: '${frontPath}/admin/info/save',
        onSubmit: function () {
            return true;
        },
        success: function (resultObj) {
            top.showMessage("添加成功")
			top.reloadContentGird();
			top.closeDialog()
        }
    })
    function submitForm(){
        $("#formObj").submit()
    }
</script>