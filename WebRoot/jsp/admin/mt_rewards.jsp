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
		<title>奖惩管理</title>
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
				$("#gt_staff_id").combobox('setValue','0');
				$("#grew_sm").textbox('setValue','');
				$("#grew_je").textbox('setValue','0');
				$("#grew_sj").datebox('setValue','');
				$("#dlg").dialog('open');
			}
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_rewards/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#grew_sm").textbox('setValue',gstr(ob.rew_sm));
					$("#grew_je").textbox('setValue',ob.rew_je);
					$("#grew_sj").datebox('setValue',ob.rew_sj);
					//根据部门，联动获取职位
					$.ajax({
						url:'<%=basePath%>t_post/getdatas',
						type:'post',
						data:{"t_dept_id":ob.t_dept_id},
						dataType:'json',
						success:function(data){
					       var dataList,plist,orgValue,orgNameValue;
					       dataList = [{"value":"0","text":"选择职位"}];
					       plist=data.plist;
					       $.each(plist,function(index,item){
						       orgValue = plist[index].id;
						       orgNameValue = plist[index].post_gwmc;
						       
						       dataList.push({"value": orgValue,"text":orgNameValue});
					       });
					       $("#gt_post_id").combobox("loadData",dataList);
					       $("#gt_post_id").combobox("setValue",ob.t_post_id);
					       //根据职位联动获取员工
					       $.ajax({
								url:'<%=basePath%>t_staff/getdatas',
								type:'post',
								data:{"t_post_id":ob.t_post_id},
								dataType:'json',
								success:function(data){
							       var dataLists,plists,orgValues,orgNameValues;
							       dataLists = [{"value":"0","text":"选择员工"}];
							       plists=data.plist;
							       $.each(plists,function(index,item){
								       orgValues = plists[index].id;
								       orgNameValues = plists[index].sxm;
								       
								       dataLists.push({"value": orgValues,"text":orgNameValues});
							       });
							       $("#gt_staff_id").combobox("loadData",dataLists);
							       $("#gt_staff_id").combobox("setValue",ob.t_staff_id);
							       
								}
							});
						}
					});
				}
			});
		}
		//保存
		function gltj(){
			var t_staff_id=$("#gt_staff_id").combobox('getValue');
			var rew_sm=hstr($("#grew_sm").textbox('getValue'));
			var rew_je=$("#grew_je").textbox('getValue');
			var rew_sj=$("#grew_sj").datebox('getValue');
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(t_staff_id=="0"||rew_sm==""||rew_je==""||rew_sj==""){
				alert('奖惩信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_rewards/add',
					type:'post',
					data:{"t_staff_id":t_staff_id,"rew_sm":rew_sm,"rew_je":rew_je,"rew_sj":rew_sj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_rewards/upd',
					type:'post',
					data:{"id":id,"t_staff_id":t_staff_id,"rew_sm":rew_sm,"rew_je":rew_je,"rew_sj":rew_sj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_rewards/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_rewards/del',
				type:'post',
				dataType:'json',
				data:{"ids":ids},
				success:function(data){
					alert(data.msg);
					rst();
				}
			});
		}
		$(document).ready(function () {
			$("#gt_dept_id").combobox({
				onChange: function (n,o) {			
					$.ajax({
						url:'<%=basePath%>t_post/getdatas',
						type:'post',
						data:{"t_dept_id":n},
						dataType:'json',
						success:function(data){
							//var items = "";
					       var dataList,plist,orgValue,orgNameValue;
					       dataList = [{"value":"0","text":"选择职位"}];
					       plist=data.plist;
					       $.each(plist,function(index,item){
						       orgValue = plist[index].id;
						       orgNameValue = plist[index].post_gwmc;
						       
						       dataList.push({"value": orgValue,"text":orgNameValue});
					       });
					       $("#gt_post_id").combobox("loadData",dataList);
					       $("#gt_post_id").combobox("setValue","0");
						}
					});
				}
			});
			$("#gt_post_id").combobox({
				onChange: function (n,o) {			
					$.ajax({
						url:'<%=basePath%>t_staff/getdatas',
						type:'post',
						data:{"t_post_id":n},
						dataType:'json',
						success:function(data){
							//var items = "";
					       var dataLists,plists,orgValues,orgNameValues;
					       dataLists = [{"value":"0","text":"选择员工"}];
					       plists=data.plist;
					       $.each(plists,function(index,item){
						       orgValues = plists[index].id;
						       orgNameValues = plists[index].sxm;						       
						       dataLists.push({"value": orgValues,"text":orgNameValues});
					       });
					       $("#gt_staff_id").combobox("loadData",dataLists);
					       $("#gt_staff_id").combobox("setValue","0");
						}
					});
				}
			});
			$("#ckdept").combobox({
				onChange: function (n,o) {			
					$.ajax({
						url:'<%=basePath%>t_post/getdatas',
						type:'post',
						data:{"t_dept_id":n},
						dataType:'json',
						success:function(data){
							//var items = "";
					       var dataList,plist,orgValue,orgNameValue;
					       dataList = [{"value":"0","text":"所有职位"}];
					       plist=data.plist;
					       $.each(plist,function(index,item){
						       orgValue = plist[index].id;
						       orgNameValue = plist[index].post_gwmc;
						       
						       dataList.push({"value": orgValue,"text":orgNameValue});
					       });
					       $("#ckpost").combobox("loadData",dataList);
					       $("#ckpost").combobox("setValue","0");
						}
					});
				}
			});
		});
	</script>
</head>
<body>	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE7" style="padding-left: 50px;">奖惩信息管理</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_rewards/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									部门：<select id="ckdept" name="t_dept_id" class="ckinput easyui-combobox">
										<option value="0">所有部门</option>
										<c:forEach var="a" items="${t_deptlist }">
											<option value="${a.id }">${a.dept_bmmc }</option>
										</c:forEach>
									</select>
								</td>
								<td class="STYLE3">
									职位：<select id="ckpost" name="t_post_id" class="ckinput easyui-combobox">
										<option value="0">所有职位</option>
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">部门</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">职位</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">员工</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">说明</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">奖惩金额</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">时间</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.dept_bmmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_gwmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sxm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.rew_sm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.rew_je }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${fn:substring(a.rew_sj,0,10) }</span></div></td>
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
														<td width="40"><a href="<%=basePath%>t_rewards/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_rewards/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_rewards/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_rewards/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
						<div style="margin-bottom:10px">
							<select id="gt_dept_id" class="easyui-combobox" name="gt_staff_id" style="width:100%"  data-options="label:'部门:'">
								<option value="0">选择部门</option>
								<c:forEach var='a' items='${t_deptlist}'>
									<option value="${a.id }">${a.dept_bmmc }</option>
								</c:forEach>
							</select>
						</div>
						<div style="margin-bottom:10px">
							<select id="gt_post_id" class="easyui-combobox" name="gt_staff_id" style="width:100%"  data-options="label:'职位:'">
								<option value="0">选择职位</option>
							</select>
						</div>
						<div style="margin-bottom:10px">
							<select id="gt_staff_id" class="easyui-combobox" name="gt_staff_id" style="width:100%"  data-options="label:'员工:'">
								<option value="0">选择员工</option>
							</select>
						</div>
						<div style="margin-bottom:10px">
							<input id="grew_sm" class="easyui-textbox" name="grew_sm" style="width:100%;height: 70px;"  data-options="label:'说明:',multiline:true"/>
						</div>
						<div style="margin-bottom:10px">
							<input id="grew_je" class="easyui-textbox" name="grew_je" style="width:100%"  data-options="label:'奖惩金额:'"/>
						</div>
						<div style="margin-bottom:10px">
							<input id="grew_sj" class="easyui-datebox" name="grew_sj" style="width:100%"  data-options="label:'时间:'"/>
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
