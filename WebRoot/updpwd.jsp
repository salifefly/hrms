<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>我的信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">							
			function regtj(){
				var opwd=$("#gopwd").textbox('getValue');
				var npwd=$("#gnpwd").textbox('getValue');
				if(opwd==""||npwd==""){
					alert("请输入新旧密码");
					return;
				}
				$.ajax({
					url:'<%=basePath%>t_staff/updpwd',
					type:'post',
					data:{"opwd":opwd,"npwd":npwd},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		</script>
</head>
<body>	
	<!-- 修改密码 -->
		<div id="dlg" class="easyui-dialog" title="修改密码" data-options="closable:false,modal:true" style="width:100%;max-width:400px;padding:30px 60px;">
			<form id="ff" method="post">
				<div style="margin-bottom:20px">
					<input id="gcus_no" disabled="disabled" value="${myinfo.szh }" class="easyui-textbox" name="gcus_no" style="width:100%"  data-options="label:'账号:'"/>
				</div>
				<div style="margin-bottom:20px">
					<input id="gopwd" class="easyui-textbox" name="gopwd" style="width:100%"  data-options="label:'旧密码:'"/>
				</div>
				<div style="margin-bottom:20px">
					<input id="gnpwd" class="easyui-textbox" name="gnpwd" style="width:100%"  data-options="label:'新密码:'"/>
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="regtj();return false;" style="width:70%;">提  交</a>				
			</div>
		</div>
</body>
</html>
