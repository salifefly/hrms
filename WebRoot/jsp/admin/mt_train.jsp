<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>培训信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">
			function addbt(){
				$("#flagi").val("add");
				$("#gtrain_pxzt").textbox('setValue','');
				$("#gtrain_pxnr").textbox('setValue','');
				$("#gtrain_pxdd").textbox('setValue','');
				$("#gtrain_pxsj").textbox('setValue','');
				$("#dlg").dialog('open');
			}
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_train/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gtrain_pxzt").textbox('setValue',gstr(ob.train_pxzt));
					$("#gtrain_pxnr").textbox('setValue',gstr(ob.train_pxnr));
					$("#gtrain_pxdd").textbox('setValue',gstr(ob.train_pxdd));
					$("#gtrain_pxsj").textbox('setValue',gstr(ob.train_pxsj));
				}
			});
		}
		//保存
		function gltj(){
			var train_pxzt=hstr($("#gtrain_pxzt").textbox('getValue'));
			var train_pxnr=hstr($("#gtrain_pxnr").textbox('getValue'));
			var train_pxdd=hstr($("#gtrain_pxdd").textbox('getValue'));
			var train_pxsj=hstr($("#gtrain_pxsj").textbox('getValue'));
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(train_pxzt==""||train_pxnr==""||train_pxdd==""||train_pxsj==""){
				alert('培训信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_train/add',
					type:'post',
					data:{"train_pxzt":train_pxzt,"train_pxnr":train_pxnr,"train_pxdd":train_pxdd,"train_pxsj":train_pxsj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_train/upd',
					type:'post',
					data:{"id":id,"train_pxzt":train_pxzt,"train_pxnr":train_pxnr,"train_pxdd":train_pxdd,"train_pxsj":train_pxsj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_train/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_train/del',
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
					<td class="STYLE7" style="padding-left: 50px;">培训信息管理</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_train/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									培训主题：<input id="ckuname" name="train_pxzt" class="ckinput easyui-textbox"/>
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
									<td width="300px" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">培训主题</span></strong></div></td>
									<td width="400px" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">培训地点</span></strong></div></td>
									<td width="0" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">培训时间</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.train_pxzt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.train_pxdd }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.train_pxsj }</span></div></td>
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
														<td width="40"><a href="<%=basePath%>t_train/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_train/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_train/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_train/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
				<div id="dlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true" style="width:100%;max-width:500px;padding:30px 60px;">
					<form id="ff" method="post">
						<div style="margin-bottom:20px">
							<input id="gtrain_pxzt" class="easyui-textbox" name="gtrain_pxzt" style="width:100%"  data-options="label:'培训主题:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gtrain_pxnr" class="easyui-textbox" name="gtrain_pxnr" style="width:100%;height: 100px;"  data-options="label:'培训内容:',multiline:true"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gtrain_pxdd" class="easyui-textbox" name="gtrain_pxdd" style="width:100%"  data-options="label:'培训地点:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gtrain_pxsj" class="easyui-textbox" name="gtrain_pxsj" style="width:100%"  data-options="label:'培训时间:'"/>
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
