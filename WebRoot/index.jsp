<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人力资源管理系统登录</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				$(window).resize(Resize);
				Resize();
			});
			function Resize() {
				var wintop = ($(window).height() - 350) / 2;
				var winleft = ($(window).width() - 550) / 2;
				if (wintop > 0) {
					$("#win").css("top", wintop + "px");
				}
				if (winleft > 0) {
					$("#win").css("left", winleft + "px");
				}
			}
			function lgtj(){
				var uname=$("#uname").textbox('getValue');
				var upassword=$("#upassword").textbox('getValue');
				var utype=$("#utype").combobox('getValue');
				if(uname==""||upassword==""){
					alert("请输入账号和密码");
					return;
				}
				$.ajax({
					url:'<%=basePath%>login/login',
					type:'post',
					data:{"uname":uname,"upassword":upassword,"utype":utype},
					dataType:'json',
					success:function(data){
						if(data.msg==1){
							window.location.href="<%=basePath%>main.jsp";
						}else{
							alert(data.msg);
						}
					}
				});
			}
			function regbt(){
				$("#dlg").dialog('open');
			}
			//保存
			function regtj(){
				var cus_no=$("#gcus_no").textbox('getValue');
				var cus_pwd=$("#gcus_pwd").textbox('getValue');
				if(cus_no==""||cus_pwd==""){
					alert('游客信息不完整。');
					return;
				}
				$.ajax({
					url:'<%=basePath%>t_customer/add',
					type:'post',
					data:{"cus_no":cus_no,"cus_pwd":cus_pwd},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		</script>
	</head>
	<body style="background-image: url('<%=basePath %>images/lunbo.jpg');background-size:cover; ">
		<div id="win" class="easyui-panel" title="" style="width:550px;height:350px;padding:10px;position: absolute;text-align: center;vertical-align: middle;background-size:cover;background-image: url('<%=basePath%>images/bj.png');">
			<div style="font-family: 隶书;font-size: 40px;margin-top: 30px;margin-bottom: 30px;">人力资源管理系统</div>
			<div style="margin-bottom:10px;">
				<input class="easyui-textbox" id="uname" name="uname" label="账 号：" style="width:50%;height:30px;font-size: 14px;" data-options="prompt:'输入账号',iconCls:'icon-man',iconWidth:28">
			</div>
			<div style="margin-bottom:10px;">
				<input class="easyui-passwordbox" id="upassword" name="upassword" label="密 码：" style="width:50%;height:30px;font-size: 14px;" data-options="prompt:'输入密码',iconCls:'icon-lock',iconWidth:28">
			</div>
			<div style="margin-bottom:20px;">
				<select class="easyui-combobox" id="utype" name="utype" label="角 色：" style="width:50%;height: 30px;font-size: 14px;">
					<option value="t_customer">游客</option>
					<option value="t_staff">员工</option>
					<option value="t_admin">管理员</option>
				</select>
			</div>
			<div>
				<a href="#" onclick="lgtj();return false;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:24%;margin-right: 5px;">
					<span style="font-size:18px;">登&nbsp;&nbsp;录</span>
				</a>
				<a href="#" onclick="regbt();return false;" class="easyui-linkbutton" style="padding:5px 0px;width:24%;">
					<span style="font-size:18px;">游客注册</span>
				</a>
			</div>
		</div>
		
		<!-- 游客注册 -->
		<div id="dlg" class="easyui-dialog" title="游客注册" data-options="closable:true,closed:true,modal:true" style="width:100%;max-width:400px;padding:30px 60px;">
			<form id="ff" method="post">
				<div style="margin-bottom:20px">
					<input id="gcus_no" class="easyui-textbox" name="gcus_no" style="width:100%"  data-options="label:'账号:'"/>
				</div>
				<div style="margin-bottom:20px">
					<input id="gcus_pwd" class="easyui-textbox" name="gcus_pwd" style="width:100%"  data-options="label:'密码:'"/>
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="regtj();return false;" style="width:70%;">提  交</a>				
			</div>
		</div>
	</body>
</html>
