<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>招聘管理</title>
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
				$("#gjob_zw").textbox('setValue','');
				$("#gjob_gs").textbox('setValue','');
				$("#gjob_dq").textbox('setValue','');
				$("#gjob_xz").textbox('setValue','');
				$("#gjob_rzyq").textbox('setValue','');
				$("#gjob_zwxx").textbox('setValue','');
				$("#gjob_lxwm").textbox('setValue','');
				$("#dlg").dialog('open');
			}
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_job/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gjob_zw").textbox('setValue',gstr(ob.job_zw));
					$("#gjob_gs").textbox('setValue',gstr(ob.job_gs));
					$("#gjob_dq").textbox('setValue',gstr(ob.job_dq));
					$("#gjob_xz").textbox('setValue',gstr(ob.job_xz));
					$("#gjob_rzyq").textbox('setValue',gstr(ob.job_rzyq));
					$("#gjob_zwxx").textbox('setValue',gstr(ob.job_zwxx));
					$("#gjob_lxwm").textbox('setValue',gstr(ob.job_lxwm));
				}
			});
		}
		//保存
		function gltj(){
			var job_zw=hstr($("#gjob_zw").textbox('getValue'));
			var job_gs=hstr($("#gjob_gs").textbox('getValue'));
			var job_dq=hstr($("#gjob_dq").textbox('getValue'));
			var job_xz=hstr($("#gjob_xz").textbox('getValue'));
			var job_rzyq=hstr($("#gjob_rzyq").textbox('getValue'));
			var job_zwxx=hstr($("#gjob_zwxx").textbox('getValue'));
			var job_lxwm=hstr($("#gjob_lxwm").textbox('getValue'));
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(job_zw==""||job_gs==""||job_dq==""||job_xz==""||job_rzyq==""||job_zwxx==""||job_lxwm==""){
				alert('招聘信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_job/add',
					type:'post',
					data:{"job_zw":job_zw,"job_gs":job_gs,"job_dq":job_dq,"job_xz":job_xz,"job_rzyq":job_rzyq,"job_zwxx":job_zwxx,"job_lxwm":job_lxwm},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_job/upd',
					type:'post',
					data:{"id":id,"job_zw":job_zw,"job_gs":job_gs,"job_dq":job_dq,"job_xz":job_xz,"job_rzyq":job_rzyq,"job_zwxx":job_zwxx,"job_lxwm":job_lxwm},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_job/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_job/del',
				type:'post',
				dataType:'json',
				data:{"ids":ids,"tbname":"t_job"},
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
					<td class="STYLE7" style="padding-left: 50px;">招聘信息管理</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_job/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									职位：<input id="ckuname" name="job_zw" class="ckinput easyui-textbox"/>
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">职位</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">公司</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">地区</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">薪资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">联系我们</span></strong></div></td>
									<td colspan="2" width="8%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_zw }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_gs }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_dq }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_xz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_lxwm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;">修改</a></div></td>
												</tr>
											</table>
										</div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="<%=basePath%>t_res_cus/init?t_job_id=${a.id}"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="<%=basePath%>t_res_cus/init?t_job_id=${a.id}">收简历</a></div></td>
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
														<td width="40"><a href="<%=basePath%>t_job/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_job/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_job/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_job/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
				<div id="dlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true"  style="width:100%;max-width:700px;padding:30px 60px;">
					<form id="ff" method="post">
						<div style="margin-bottom:20px;width: 49%;float: left;margin-right: 5px;">
							<input id="gjob_zw" class="easyui-textbox" name="gjob_zw" style="width:100%"  data-options="label:'职位:'"/>
						</div>
						<div style="margin-bottom:20px;width: 49%;float: left;">
							<input id="gjob_gs" class="easyui-textbox" name="gjob_gs" style="width:100%"  data-options="label:'公司:'"/>
						</div>
						<div style="margin-bottom:20px;width: 49%;float: left;margin-right: 5px;">
							<input id="gjob_dq" class="easyui-textbox" name="gjob_dq" style="width:100%"  data-options="label:'地区:'"/>
						</div>
						<div style="margin-bottom:20px;width: 49%;float: left;">
							<input id="gjob_xz" class="easyui-textbox" name="gjob_xz" style="width:100%"  data-options="label:'薪资:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gjob_rzyq" class="easyui-textbox" name="gjob_rzyq" style="width:100%;height: 90px;"  data-options="label:'任职要求:',multiline:true"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gjob_zwxx" class="easyui-textbox" name="gjob_zwxx" style="width:100%;height: 90px;"  data-options="label:'职位信息:',multiline:true"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gjob_lxwm" class="easyui-textbox" name="gjob_lxwm" style="width:100%"  data-options="label:'联系我们:'"/>
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
		<div style="display: none;">
			<form id="fm" action="" method="post">
				<input id="pt" name="tbname"/>
			</form>
		</div>
	</body>
</html>
