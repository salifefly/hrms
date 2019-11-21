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
    
    <title>招聘信息</title>
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
		      <c:forEach var="a" items="${alist }">
		      <div class="alert alert-success" role="alert">
		      	<div class="col-sm-12 col-md-3">
			        <strong style="font-size: 14px;font-weight: bold;">职位：</strong>${a.job_zw }
		      	</div>
		      	<div class="col-sm-12 col-md-3">
			        <strong style="font-size: 14px;font-weight: bold;">公司：</strong>${a.job_gs }
		      	</div>
		      	<div class="col-sm-12 col-md-3">
		        	<strong style="font-size: 14px;font-weight: bold;">地区：</strong>${a.job_dq }
		        </div>
		        <div class="col-sm-12 col-md-3">
		        	<strong style="font-size: 14px;font-weight: bold;">薪资：</strong>${a.job_xz }
		        </div>
		        <div class="col-sm-12 col-md-12">
		        	<strong style="font-size: 14px;font-weight: bold;">任职要求：</strong>${a.job_rzyq }
		        </div>
		        <div class="col-sm-12 col-md-12">
		        	<strong style="font-size: 14px;font-weight: bold;">职位信息：</strong>${a.job_zwxx }
		        </div>
		        <div class="col-sm-12 col-md-12">
		        	<strong style="font-size: 14px;font-weight: bold;">联系我们：</strong>${a.job_lxwm }
		        </div>
		        <span style="float: right;font-size: 14px;font-weight: bold;margin-right: 100px;">
		        	<a href="javascript:void(0);" onclick="sqbt('${a.id}');return false;">职位申请</a>
		        </span>.
		      </div>
		      </c:forEach>
		      <div class="col-sm-12 col-md-12" style="margin-right: 100px;text-align: right;">
		      		<span class="label label-default">${pagenum }/${pagenums }</span>
		      		<span class="label label-default"><a style="color: white;" href="<%=basePath%>t_job/capage?pnum=1">首页</a></span>
		      		<span class="label label-default"><a style="color: white;" href="<%=basePath%>t_job/capage?pnum=${pagenum-1}">上一页</a></span>
		      		<span class="label label-default"><a style="color: white;" href="<%=basePath%>t_job/capage?pnum=${pagenum+1}">下一页</a></span>
		      		<span class="label label-default"><a style="color: white;" href="<%=basePath%>t_job/capage?pnum=${pagenums}">尾页</a></span>
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
    	function sqbt(zpid){
    		<c:if test="${empty myinfo}">
    			alert("请登录");
    		</c:if>
    		<c:if test="${not empty myinfo}">
	    		$("#idi").val(zpid);
	    		$("#dlg").dialog('open');
    		</c:if>
    		return false;
    	}
    	function gltj(){
    		var zpid=$("#idi").val();
    		var jlid=$("#gjlid").combobox('getValue');
    		if(zpid==""||jlid==0){
    			alert("信息不完整");
    			return;
    		}
    		$.ajax({
    			url:'<%=basePath%>t_res_cus/add',
    			type:'post',
    			data:{"t_resume_id":jlid,"t_job_id":zpid},
    			dataType:'json',
    			success:function(data){
    				alert(data.msg);
    			}
    		});
    	};
    </script>
    <div id="dlg" class="easyui-dialog" title=" " data-options="modal:true,closed:true" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" method="post">
			<div style="margin-bottom:20px">
				<select id="gjlid" class="easyui-combobox" name="gjlid" style="width:100%"  data-options="label:'简历:'">
					<option value="0">选择简历</option>
					<c:forEach var="a" items="${trlist }">
						<option value="${a.id }">${a.res_jlmc}</option>
					</c:forEach>
				</select>
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gltj();return false;" style="width:70%">提  交</a>
		</div>
	</div>
  </body>
</html>

