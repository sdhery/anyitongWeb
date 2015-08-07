<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/admin/include/includeAdminTaglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
        #golist {display: none;}
        @media (max-device-width: 780px){#golist{display: block !important;}}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=G21c6sdEZhjEaxqvckLCjOzY&v=1.0"></script>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(${item.mapLongitude}, ${item.mapLatitude});
    map.centerAndZoom(point,15);

    var marker = new BMap.Marker(point);        // 创建标注
    map.addOverlay(marker);

    map.addControl(new BMap.ZoomControl()); //添加地图缩放控件
</script>