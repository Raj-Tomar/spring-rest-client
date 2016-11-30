<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Line-Charts</title>

<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.1.0.min.js"/>"></script>
<!-- High-Charts Libraries Start-->
<script type="text/javascript" src="<c:url value="/resources/js/high-chart/highcharts-4.2.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/high-chart/high-chart-exporting-4.2.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/high-chart/highcharts-3d-4.2.5.js"/>"></script>
<!-- High-Charts Libraries End-->
<script type="text/javascript" src="<c:url value="/resources/js/high-chart/user/line-charts.js"/>"></script>
</head>
<body>

<h3 align="center">Basic Line Chart</h3>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<h3 align="center">Ajax Loaded Line Chart</h3>
<input type="hidden" id="highChart-LineChartUrl" value="<c:url value="/highChartData"/>">
<div id="ajax-loaded-data" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
$(document).ready(function(){
	LineChart.BasicLineChart();
	LineChart.AjaxLoadedLineChart();
})
</script>
</body>
</html>