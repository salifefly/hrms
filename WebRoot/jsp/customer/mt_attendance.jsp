<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("bpath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上下班打卡</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath%>css/bootstrap.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href=".<%=basePath%>css/bootstrap-theme.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<%=basePath%>css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=basePath%>css/theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="<%=basePath%>js/ie-emulation-modes-warning.js"></script>
	<style type="text/css">
		a:link {		
		 text-decoration: none;
		}
		a:visited {
		 text-decoration: none;
		}
		a:hover {
		 text-decoration: none;
		}
		a:active {
		 text-decoration: none;
		}
	</style>
  </head>

  <body style="margin: 0;padding: 0;">
    <div class="container-fluid" style="margin: 0;padding:0;">
      <div class="row">   
      	  <div class="col-sm-9 col-sm-offset-1 col-md-10">
		      <div class="alert alert-success" role="alert">
		        <div class="col-sm-12 col-md-12">
		        	<strong style="font-size: 14px;font-weight: bold;">考勤说明：</strong>上班时间是上午9点，下班时间下午6点，迟到或早退3小时视为旷工，上班下班不打卡，视为旷工。迟到早退，扣10元工资。每月上班时间够22天，不扣基本工资。
		        </div>		        
			      <div class="col-sm-12 col-md-12" style="margin-right: 100px;text-align:center;">
			      		<h1>
				      		<span class="label label-primary"><a style="color: white;" href="javascript:void(0);" onclick="sbbt();return false;">上班打卡</a></span>
				      		<span class="label label-success"><a style="color: white;" href="javascript:void(0);" onclick="xbbt();return false;">下班打卡</a></span>
			      		</h1>
			      </div>
		      </div>
	      </div>
      </div>
    </div> <!-- /container -->
    <input id="idi" type="hidden"/>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=basePath%>js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript">
    	function sbbt(){
    		$.ajax({
    			url:'<%=basePath%>t_attendance/add',
    			type:'post',
    			dataType:'json',
    			success:function(data){
    				alert(data.msg);
    			}
    		});
    		return false;
    	}
    	function xbbt(){
    		$.ajax({
    			url:'<%=basePath%>t_attendance/upd',
    			type:'post',
    			dataType:'json',
    			success:function(data){
    				alert(data.msg);
    			}
    		});
    		return false;
    	};
    </script>
  </body>
</html>

