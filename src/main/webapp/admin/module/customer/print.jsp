<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!DOCTYPE html>
<HTML>
<title>登记打印</title>
<link href="${frontPath}/resources/bootstrap3/css/bootstrap.min.css" rel="stylesheet"/>

</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td align="center">
            <h4>安 颐 通 呼 援 服 务 用 户 登 记 表</h4>
        </td>
    </tr>
</table>
<table class="table table-bordered table-condensed" style="margin-bottom:4px;">
    <tr>
        <th>基本资料</th>
        <td>姓名</td>
        <td>${item.customer.realName}</td>
        <td>性别</td>
        <td>${item.customer.sex eq 1 ? '男' : '女'}</td>
        <td>用户编号</td>
        <td></td>
        <td>街道/镇</td>
        <td></td>
    </tr>
    <tr>
        <td>身份证号码</td>
        <td colspan="6">${item.customer.idCard}</td>
        <td>村/居委</td>
        <td></td>
    </tr>
    <tr>
        <td>住所地址</td>
        <td colspan="8">${item.customer.address}</td>
    </tr>
    <tr>
        <td>交通道路</td>
        <td colspan="8">${item.customer.roadTraffic}</td>
    </tr>
    <tr>
        <td>指示标志</td>
        <td colspan="8"></td>
    </tr>
    <tr>
        <td>固定电话</td>
        <td colspan="2">${item.customer.phone}</td>
        <td>移动电话</td>
        <td colspan="2">${item.customer.mobilePhone}</td>
        <td>语言</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>居家状况</td>
        <td colspan="4"><c:choose><c:when test="${item.customer.homeSituation eq 0}">孤寡</c:when><c:when test="${item.customer.homeSituation eq 1}">独居</c:when><c:when test="${item.customer.homeSituation eq 2}">双老</c:when><c:when test="${item.customer.homeSituation eq 3}">子女同住</c:when><c:when test="${item.customer.homeSituation eq 4}">其他</c:when></c:choose></td>
        <td>其他情况</td>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td>购买类型</td>
        <td colspan="4"><c:choose><c:when test="${item.customer.purchaseType eq 0}">普通</c:when><c:when test="${item.customer.purchaseType eq 1}">全额资助</c:when><c:when test="${item.customer.purchaseType eq 2}">半额资助</c:when><c:when test="${item.customer.purchaseType eq 3}">慈善捐助</c:when><c:when test="${item.customer.purchaseType eq 4}">其他</c:when></c:choose></td>
        <td>服务类型代码</td>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td colspan="9"></td>
    </tr>
    <tr>
        <th>联络人</th>
        <th>姓名</th>
        <th>性别</th>
        <th>关系</th>
        <th>固定电话</th>
        <th>移动电话</th>
        <th>住址/说明</th>
        <th colspan="2">门匙</th>
    </tr>
    <c:forEach items="${item.contactsList}" varStatus="s" var="contacts">
    <tr>
        <td>联络人${s.count}</td>
        <td>${contacts.realName}</td>
        <td>${contacts.sex eq 1 ? '男' : '女'}</td>
        <td>${contacts.bind}</td>
        <td>${contacts.phone}</td>
        <td>${contacts.mobilePhone}</td>
        <td>${contacts.address}</td>
        <td colspan="2"></td>
    </tr>
    </c:forEach>
	<tr>
        <td>联络人4</td>
        <td>${contacts.realName}</td>
        <td>${contacts.sex eq 1 ? '男' : '女'}</td>
        <td>${contacts.bind}</td>
        <td>${contacts.phone}</td>
        <td>${contacts.mobilePhone}</td>
        <td>${contacts.address}</td>
        <td colspan="2"></td>
    </tr>
	<tr>
        <td>联络人4</td>
        <td>${contacts.realName}</td>
        <td>${contacts.sex eq 1 ? '男' : '女'}</td>
        <td>${contacts.bind}</td>
        <td>${contacts.phone}</td>
        <td>${contacts.mobilePhone}</td>
        <td>${contacts.address}</td>
        <td colspan="2"></td>
    </tr>
	<tr>
        <td>联络人5</td>
        <td>${contacts.realName}</td>
        <td>${contacts.sex eq 1 ? '男' : '女'}</td>
        <td>${contacts.bind}</td>
        <td>${contacts.phone}</td>
        <td>${contacts.mobilePhone}</td>
        <td>${contacts.address}</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="9"></td>
    </tr>
    <tr>
        <th>救助机构</th>
        <th colspan="2">名称</th>
        <th colspan="2">联系人/部门</th>
        <th colspan="2">联系电话</th>
        <th colspan="2">地址/移动电话</th>
    </tr>
    <c:forEach items="${item.reliefAgenciesList}" varStatus="s" var="reliefAgencies">
    <tr>
        <td><c:choose><c:when test="${reliefAgencies.type eq 0}">街道/镇</c:when><c:when test="${reliefAgencies.type eq 1}">派出所</c:when><c:when test="${reliefAgencies.type eq 2}">村/居委</c:when><c:when test="${reliefAgencies.type eq 3}">服务中心</c:when><c:when test="${reliefAgencies.type eq 4}">医  院</c:when><c:when test="${reliefAgencies.type eq 5}">急救中心</c:when></c:choose></td>
        <td colspan="2">${reliefAgencies.name}</td>
        <td colspan="2">${reliefAgencies.contact}</td>
        <td colspan="2">${reliefAgencies.phone}</td>
        <td colspan="2">${reliefAgencies.address}</td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="9"></td>
    </tr>
    <tr>
        <th>健康状况</th>
        <td>身体状况</td>
        <td colspan="5">${sdk:getHealthStatusByType(item.healthStatusList,0).statusDes}</td>
        <td>血型</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">现有疾病</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>个人病史</td>
        <td colspan="8"></td>
    </tr>
    <tr>
        <td>病况摘要</td>
        <td colspan="8"></td>
    </tr>
    <tr>
        <td>常用药</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>禁忌药</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>过敏史</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>挂钩医院</td>
        <td colspan="2"></td>
        <td>诊疗卡号/病历编号</td>
        <td colspan="2"></td>
        <td>付费方式</td>
        <td colspan="2"></td>
    </tr>
	<tr>
        <td>设备安装</td>
        <td colspan="2"></td>
        <td>设备ID</td>
        <td colspan="2"></td>
        <td>附加设备</td>
        <td colspan="2"></td>
    </tr>
</table>
</body>
</HTML>