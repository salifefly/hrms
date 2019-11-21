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
		
		</script>
</head>
<body>	
	<div id="dlg" class="easyui-dialog" title="员工信息" data-options="closable:false,modal:true" style="width:100%;max-width:800px;padding:30px 60px;">
		<form id="ff" method="post">
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gszh" disabled="disabled" value="${myinfo.szh }" class="easyui-textbox" name="gszh" style="width:100%"  data-options="label:'账号:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gspwd" disabled="disabled" value="${myinfo.spwd }" class="easyui-textbox" name="gspwd" style="width:100%"  data-options="label:'密码:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gsxm" disabled="disabled" value="${myinfo.sxm }" class="easyui-textbox" name="gsxm" style="width:100%"  data-options="label:'姓名:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gsxb" disabled="disabled" value="${myinfo.sxb }" class="easyui-textbox" name="gsxb" style="width:100%"  data-options="label:'性别:'"/>					
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gscsrq" disabled="disabled" value="${myinfo.scsrq }" class="easyui-datebox" name="gscsrq" style="width:100%"  data-options="label:'出生日期:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gsmz" disabled="disabled" value="${myinfo.smz }" class="easyui-textbox" name="gsmz" style="width:100%"  data-options="label:'民族:'"/>					
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gszzmm" disabled="disabled" value="${myinfo.szzmm }" class="easyui-textbox" name="gszzmm" style="width:100%"  data-options="label:'政治面貌:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gsxl" disabled="disabled" value="${myinfo.sxl }" class="easyui-textbox" name="gsxl" style="width:100%"  data-options="label:'学历:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gsjg" disabled="disabled" value="${myinfo.sjg }" class="easyui-textbox" name="gsjg" style="width:100%"  data-options="label:'籍贯:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gsyx" disabled="disabled" value="${myinfo.syx }" class="easyui-textbox" name="gsyx" style="width:100%"  data-options="label:'邮箱:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gslxdh" disabled="disabled" value="${myinfo.slxdh }" class="easyui-textbox" name="gslxdh" style="width:100%"  data-options="label:'联系电话:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gszy" disabled="disabled" value="${myinfo.szy }" class="easyui-textbox" name="gszy" style="width:100%"  data-options="label:'专业:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gsbyyx" disabled="disabled" value="${myinfo.sbyyx }" class="easyui-textbox" name="gsbyyx" style="width:100%"  data-options="label:'毕业院校:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gswysp" disabled="disabled" value="${myinfo.swysp }" class="easyui-textbox" name="gswysp" style="width:100%"  data-options="label:'外语水平:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gsjsjdj" disabled="disabled" value="${myinfo.sjsjdj }" class="easyui-textbox" name="gsjsjdj" style="width:100%"  data-options="label:'计算机等级:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gsjzzt" disabled="disabled" value="${myinfo.sjzzt }" class="easyui-textbox" name="gsjzzt" style="width:100%"  data-options="label:'就职状态:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gsygzt" disabled="disabled" value="${myinfo.sygzt }" class="easyui-textbox" name="gsygzt" style="width:100%"  data-options="label:'员工状态:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: right;">
				<input id="gt_dept_id" disabled="disabled" value="${myinfo.dept_bmmc }" class="easyui-textbox" name="gt_dept_id" style="width:100%"  data-options="label:'部门:'"/>
			</div>
			<div style="margin-bottom:10px;width: 300px;float: left;">
				<input id="gt_post_id" disabled="disabled" value="${myinfo.post_gwmc }" class="easyui-textbox" name="gt_post_id" style="width:100%"  data-options="label:'职位:'"/>
			</div>
		</form>
		<!-- 
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gltj();return false;" style="width:80px">提  交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rst();return false;" style="width:80px">退  出</a>
		</div>
		 -->
	</div>
</body>
</html>
