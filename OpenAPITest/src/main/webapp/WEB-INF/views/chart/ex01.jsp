<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	#container {
		width: 770px;
		height: 500px;
	}
</style>
</head>
<body>
	<h1>Chart</h1>
	
	<div id="container"></div>
	
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/series-label.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	Highcharts.chart('container', {

	    title: { //제목
	        text: '2023년 체중변화',	
	        align: 'center'
	    },

	    subtitle: { //부제목
	        text: '작년 한해동안의 몸무게 변화를 기록합니다.',
	        align: 'center'
	    },

	    yAxis: { //세로
	        title: {
	            text: '몸무게(kg)'
	        }
	    },

	    xAxis: { //가로
	        accessibility: {
	            rangeDescription: 'Range: 2010 to 2020'
	        }
	    },

	    legend: { //범례
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            },
	            pointStart: 1
	        }
	    },

	    series: [{ //줄 하나
	        name: 'Installation & Developers',
	        data: [43934, 48656, 65165, 81827, 112143, 142383,
	            171533, 165174, 155157, 161454, 154610]
	    }, {
	        name: 'Manufacturing',
	        data: [24916, 37941, 29742, 29851, 32490, 30282,
	            38121, 36885, 33726, 34243, 31050]
	    }, {
	        name: 'Sales & Distribution',
	        data: [11744, 30000, 16005, 19771, 20185, 24377,
	            32147, 30912, 29243, 29213, 25663]
	    }, {
	        name: 'Operations & Maintenance',
	        data: [null, null, null, null, null, null, null,
	            null, 11164, 11218, 10077]
	    }, {
	        name: 'Other',
	        data: [21908, 5548, 8105, 11248, 8989, 11816, 18274,
	            17300, 13053, 11906, 10073]
	    }],
		
	    series: 
		    [
		    {
		        name: '홍길동',
		        data: [75, 74, 76, 73, 72, 70, 68, 66, 67, 70, 75, 80],
		        
		    }
		    ],
		    {
		        name: '아무개',
		        data: [68, 66, 74, 76, 73, 67, 70, 75, 80, 75, 72, 70 ]
		    }
		    ],
	    
	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    }

	});

	</script>
</body>
</html>




