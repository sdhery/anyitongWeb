<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="include/includeAdminTaglibs.jsp" %>
<!Doctype html>
<html>
<head>
    <title>后台管理</title>
    <t:base type="jquery,easyui,tools"></t:base>
    <script>
        $.adminRealName="${loginAdmin.realName}"
        $.frontPath="${frontPath}"
        $.adminId="${loginAdmin.sysUserId}"
    </script>
    <style>
        .menuButton {
            height: 32px;
            width: 100%;
            border-radius: 0px;
            border-left: 0px;
            border-right: 0px;
        }
         * {
             font-size: 12px;
         }
    </style>
    <script>
        $(function(){
            $("#divLeft .menuButton").bind("click",function(){
                $("#mainLeftAcc").accordion({multiple:true})
                $("#mainLeftTree").tree({url:"/admin/loadTree?id="+$(this).data("systreeid")})
                $("#mainLeftTitle").panel("setTitle",$(this).text())
            })
            $("#mainLeftTree").tree({
                onClick:mainLeftClick
            })
        })
        function mainLeftClick(node){
           /* $("#contentFrame").panel({
                href: "/admin/info/list",
                extractor: function (data) {
                    data = $.fn.panel.defaults.extractor(data);
                    var tmp = $('<div></div>').html(data);
                    data = tmp.find('#content').html();
                    tmp.remove();
                    return data;
                }
            })*/
            document.getElementById("contentIFrame").src=node.url
            //alert(node.text)
        }

    </script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north'" style="height:32px;padding:2px 0px 0px 10px;">
        欢迎，${loginAdmin.realName}<a class="easyui-linkbutton" href="${frontPath}/admin/logout" data-options="iconCls:'icon-logout'" style="float:right;margin:0px 10px 0px 10px;">退出</a>
    </div>
    <div data-options="region:'west'" title="功能菜单" style="width:15%;overflow-x:hidden;background:#eee;" id="divLeft">
    <c:import url="/admin/mainLeft"/>
    </div>
    <div data-options="region:'center'">
        <div  class="easyui-panel" data-options="border:false" fit="true" style="overflow:hidden"><iframe scrolling="auto" frameborder="0" style="width:100%;height:99.6%;" id="contentIFrame" name="contentIFrame" src="about:blank"></iframe></div>
    </div>
</div>

</body>
</html>