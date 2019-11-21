<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>职位管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">
			function addbt(){
				$("#flagi").val("add");
				$("#gpost_gwmc").textbox('setValue','');
				$("#gt_dept_id").combobox('setValue','0');
				$("#gpost_sxgz").textbox('setValue','0');
				$("#gpost_zsgz").textbox('setValue','0');
				$("#gpost_sxjt").textbox('setValue','0');
				$("#gpost_zsjt").textbox('setValue','0');
				$("#dlg").dialog('open');
			}
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_post/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gpost_gwmc").textbox('setValue',gstr(ob.post_gwmc));
					$("#gt_dept_id").combobox('setValue',gstr(ob.t_dept_id));
					$("#gpost_sxgz").textbox('setValue',gstr(ob.post_sxgz));
					$("#gpost_zsgz").textbox('setValue',gstr(ob.post_zsgz));
					$("#gpost_sxjt").textbox('setValue',gstr(ob.post_sxjt));
					$("#gpost_zsjt").textbox('setValue',gstr(ob.post_zsjt));
				}
			});
		}
		//保存
		function gltj(){
			var post_gwmc=hstr($("#gpost_gwmc").textbox('getValue'));
			var t_dept_id=hstr($("#gt_dept_id").combobox('getValue'));
			var post_sxgz=hstr($("#gpost_sxgz").textbox('getValue'));
			var post_zsgz=hstr($("#gpost_zsgz").textbox('getValue'));
			var post_sxjt=hstr($("#gpost_sxjt").textbox('getValue'));
			var post_zsjt=hstr($("#gpost_zsjt").textbox('getValue'));
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(post_gwmc==""||t_dept_id=="0"||post_sxgz==""||post_zsgz==""||post_sxjt==""||post_zsjt==""){
				alert('职位信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_post/add',
					type:'post',
					data:{"post_gwmc":post_gwmc,"t_dept_id":t_dept_id,"post_sxgz":post_sxgz,"post_zsgz":post_zsgz,"post_sxjt":post_sxjt,"post_zsjt":post_zsjt},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_post/upd',
					type:'post',
					data:{"id":id,"post_gwmc":post_gwmc,"t_dept_id":t_dept_id,"post_sxgz":post_sxgz,"post_zsgz":post_zsgz,"post_sxjt":post_sxjt,"post_zsjt":post_zsjt},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_post/apage?pnum="+${pagenum};
		}
		//查询
		function cktj(){
			$("#ckf").submit();
		}
		function delsbt(){
			var ids="";
			$("input:checkbox").each(
				function(){
					if($(this).prop("checked")){
						var nm=$(this).prop("name");
						if(nm.indexOf("delid")==0){
							ids+=$(this).val()+",";
						}
					}
				}
			);
			if(ids==""){
				alert("请选择所要删除的数据.");
				return;
			};
			$.ajax({
				url:'<%=basePath%>t_post/del',
				type:'post',
				dataType:'json',
				data:{"ids":ids},
				success:function(data){
					alert(data.msg);
					rst();
				}
			});
		}
	</script>
</head>
<body>	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE7" style="padding-left: 50px;">职位信息管理</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_post/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									职位名称：<input id="ckuname" name="post_gwmc" class="ckinput easyui-textbox"/>
								</td>
								<td class="STYLE3">
									部门：<select id="ckbm" name="t_dept_id" class="ckinput easyui-combobox">
										<option value="0">所有部门</option>
										<c:forEach var="a" items="${t_deptlist }">
											<option value="${a.id }">${a.dept_bmmc }</option>
										</c:forEach>
									</select>
								</td>
								<td width="60">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="cktj();return false;"><img src="images/search.png" width="14" height="14" /></a></div></td>
											<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="cktj();return false;">查询</a></div></td>
										</tr>
									</table>
								</td>
								<td width="60"><table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="addbt();return false;"><img src="images/001.gif" width="14" height="14" /></a></div></td>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="addbt();return false;">新增</a></div></td>
									</tr>
								</table></td>
								<td width="52"><table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="delsbt();return false;"><img src="images/083.gif" width="14" height="14" /></a></div></td>										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="delsbt();return false;">删除</a></div></td>
									</tr>
								</table></td>
							</tr>
						</table>
						</form>
					</div></td>
				</tr>
			</table></td>
		</tr>
		<tr>
			<td style="width: 100%;">
				<div id="nrdiv">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
								<tr>
									<td width="5%" align="center" bgcolor="#FFFFFF" style="font-size: 12px;">
									</td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">岗位名称</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">所属部门</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">实习工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">正式工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">实习津贴</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">正式津贴</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_gwmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.dept_bmmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_sxgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_zsgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_sxjt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_zsjt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;">修改</a></div></td>
												</tr>
											</table>
										</div></td>
									</tr>
								</c:forEach>
							</table></td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="12" height="35"></td>
										<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="STYLE4">&nbsp;&nbsp;共有 ${allnums } 条记录，当前第 ${pagenum }/${pagenums } 页</td>
												<td><table border="0" align="right" cellpadding="0" cellspacing="0">
													<tr>
														<td width="40"><a href="<%=basePath%>t_post/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_post/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_post/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_post/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
														<td width="100"><div align="center"><span class="STYLE1"> </span></div></td>
														<td width="40"></td>
													</tr>
												</table></td>
											</tr>
										</table></td>
										<td width="16"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="width: 100%;" align="center">
				<div id="dlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true" style="width:100%;max-width:400px;padding:30px 60px;">
					<form id="ff" method="post">
						<div style="margin-bottom:20px">
							<input id="gpost_gwmc" class="easyui-textbox" name="gpost_gwmc" style="width:100%"  data-options="label:'岗位名称:'"/>
						</div>
						<div style="margin-bottom:20px">
							<select id="gt_dept_id" class="easyui-combobox" name="gt_dept_id" style="width:100%"  data-options="label:'所属部门:'">
								<option value="0">选择部门</option>
								<c:forEach var='a' items='${t_deptlist}'>
									<option value="${a.id }">${a.dept_bmmc }</option>
								</c:forEach>
							</select>
						</div>
						<div style="margin-bottom:20px">
							<input id="gpost_sxgz" class="easyui-textbox" name="gpost_sxgz" style="width:100%"  data-options="label:'实习工资:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gpost_zsgz" class="easyui-textbox" name="gpost_zsgz" style="width:100%"  data-options="label:'正式工资:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gpost_sxjt" class="easyui-textbox" name="gpost_sxjt" style="width:100%"  data-options="label:'实习津贴:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gpost_zsjt" class="easyui-textbox" name="gpost_zsjt" style="width:100%"  data-options="label:'正式津贴:'"/>
						</div>
					</form>
					<div style="text-align:center;padding:5px 0">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gltj();return false;" style="width:80px">提  交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rst();return false;" style="width:80px">退  出</a>
					</div>
				</div>
			</td>
		</tr>
	</table>
		<input type="hidden" id="hrownums" value="${rownums }" />
		<input type="hidden" id="hpagenum" value="${pagenum }" />
		<input type="hidden" id="hpagenums" value="${pagenums }" />
		<input type="hidden" id="idi" value="" />
		<input type="hidden" id="flagi" value="" />
	</body>
</html>
