<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="include/includeAdminTaglibs.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:forEach items="${sysTrees}" var="sysTree" varStatus="s">
    <c:set value="1" var="isShow"/>
    <c:if test="${s.first}">
        <div class="easyui-panel" style="height:60%;border:none">
            <div class="easyui-accordion" fit="true" border="false" animate="false" id="mainLeftAcc">
                <div title="${sysTree.treeName}" id="mainLeftTitle">
                    <ul class="easyui-tree" method="post" animate="true" url="/admin/loadTree?id=${sysTree.treeId}" id="mainLeftTree">
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${sysTree.treeId eq 3 || sysTree.treeId eq 6}"><sec:authorize access="hasRole('seat')"><c:set value="0" var="isShow"/></sec:authorize></c:if>
    <c:if test="${isShow eq 1}">
    <a class="easyui-linkbutton menuButton" data-options="toggle:true,group:'g1'<c:if test="${s.first}">,selected:true</c:if>" style="text-align:left" data-systreeid="${sysTree.treeId}">${sysTree.treeName}</a>
    </c:if>
</c:forEach>