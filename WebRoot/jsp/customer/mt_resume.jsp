<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>简历管理</title>
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
				$("#gres_jlmc").textbox('setValue','');
				$("#gres_xm").textbox('setValue','');
				$("#gres_xb").combobox('setValue','男');
				$("#gres_csrq").datebox('setValue','');
				$("#gres_mz").combobox('setValue','');
				$("#gres_zzmm").combobox('setValue','');
				$("#gres_xl").combobox('setValue','');
				$("#gres_jg").textbox('setValue','');
				$("#gres_yx").textbox('setValue','');
				$("#gres_lxdh").textbox('setValue','');
				$("#gres_zy").textbox('setValue','');
				$("#gres_byyx").textbox('setValue','');
				$("#gres_wysp").combobox('setValue','');
				$("#gres_jsjdj").combobox('setValue','');
				$("#gres_gzjl").textbox('setValue','');
				$("#gres_zwpj").textbox('setValue','');
				$("#dlg").dialog('open');
			}
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_resume/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gres_jlmc").textbox('setValue',gstr(ob.res_jlmc));
					$("#gres_xm").textbox('setValue',gstr(ob.res_xm));
					$("#gres_xb").combobox('setValue',gstr(ob.res_xb));
					$("#gres_csrq").datebox('setValue',gstr(ob.res_csrq));
					$("#gres_mz").combobox('setValue',gstr(ob.res_mz));
					$("#gres_zzmm").combobox('setValue',gstr(ob.res_zzmm));
					$("#gres_xl").combobox('setValue',gstr(ob.res_xl));
					$("#gres_jg").textbox('setValue',gstr(ob.res_jg));
					$("#gres_yx").textbox('setValue',gstr(ob.res_yx));
					$("#gres_lxdh").textbox('setValue',gstr(ob.res_lxdh));
					$("#gres_zy").textbox('setValue',gstr(ob.res_zy));
					$("#gres_byyx").textbox('setValue',gstr(ob.res_byyx));
					$("#gres_wysp").combobox('setValue',gstr(ob.res_wysp));
					$("#gres_jsjdj").combobox('setValue',gstr(ob.res_jsjdj));
					$("#gres_gzjl").textbox('setValue',gstr(ob.res_gzjl));
					$("#gres_zwpj").textbox('setValue',gstr(ob.res_zwpj));
				}
			});
		}
		//保存
		function gltj(){
			var res_jlmc=hstr($("#gres_jlmc").textbox('getValue'));
			var res_xm=hstr($("#gres_xm").textbox('getValue'));
			var res_xb=hstr($("#gres_xb").textbox('getValue'));
			var res_csrq=hstr($("#gres_csrq").textbox('getValue'));
			var res_mz=hstr($("#gres_mz").textbox('getValue'));
			var res_zzmm=hstr($("#gres_zzmm").textbox('getValue'));
			var res_xl=hstr($("#gres_xl").textbox('getValue'));
			var res_jg=hstr($("#gres_jg").textbox('getValue'));
			var res_yx=hstr($("#gres_yx").textbox('getValue'));
			var res_lxdh=hstr($("#gres_lxdh").textbox('getValue'));
			var res_zy=hstr($("#gres_zy").textbox('getValue'));
			var res_byyx=hstr($("#gres_byyx").textbox('getValue'));
			var res_wysp=hstr($("#gres_wysp").textbox('getValue'));
			var res_jsjdj=hstr($("#gres_jsjdj").textbox('getValue'));
			var res_gzjl=hstr($("#gres_gzjl").textbox('getValue'));
			var res_zwpj=hstr($("#gres_zwpj").textbox('getValue'));
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(res_jlmc==""||res_xm==""||res_xb==""||res_csrq==""||res_mz==""||res_zzmm==""||res_xl==""||res_jg==""||res_yx==""||res_lxdh==""||res_zy==""||res_byyx==""||res_wysp==""||res_jsjdj==""||res_gzjl==""||res_zwpj==""){
				alert('简历信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_resume/add',
					type:'post',
					data:{"res_jlmc":res_jlmc,"res_xm":res_xm,"res_xb":res_xb,"res_csrq":res_csrq,"res_mz":res_mz,"res_zzmm":res_zzmm,"res_xl":res_xl,"res_jg":res_jg,"res_yx":res_yx,"res_lxdh":res_lxdh,"res_zy":res_zy,"res_byyx":res_byyx,"res_wysp":res_wysp,"res_jsjdj":res_jsjdj,"res_gzjl":res_gzjl,"res_zwpj":res_zwpj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_resume/upd',
					type:'post',
					data:{"id":id,"res_jlmc":res_jlmc,"res_xm":res_xm,"res_xb":res_xb,"res_csrq":res_csrq,"res_mz":res_mz,"res_zzmm":res_zzmm,"res_xl":res_xl,"res_jg":res_jg,"res_yx":res_yx,"res_lxdh":res_lxdh,"res_zy":res_zy,"res_byyx":res_byyx,"res_wysp":res_wysp,"res_jsjdj":res_jsjdj,"res_gzjl":res_gzjl,"res_zwpj":res_zwpj},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_resume/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_resume/del',
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
					<td class="STYLE7" style="padding-left: 50px;">我的简历</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_resume/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									简历名称：<input id="ckuname" name="res_jlmc" class="ckinput easyui-textbox"/>
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">简历名称</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">姓名</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">联系电话</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">专业</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">毕业院校</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">外语水平</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">计算机等级</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_jlmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_xm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_lxdh }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_zy }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_byyx }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_wysp }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_jsjdj }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;">详情</a></div></td>
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
														<td width="40"><a href="<%=basePath%>t_resume/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_resume/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_resume/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_resume/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
				<div id="dlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true" style="width:100%;max-width:800px;padding:30px 60px;">
					<form id="ff" method="post">
						<div style="margin-bottom:10px">
							<input id="gres_jlmc" class="easyui-textbox" name="gres_jlmc" style="width:100%"  data-options="label:'简历名称:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gres_xm" class="easyui-textbox" name="gres_xm" style="width:100%"  data-options="label:'姓名:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gres_xb" class="easyui-combobox" name="gres_xb" style="width:100%"  data-options="label:'性别:'">
								<option>男</option>
								<option>女</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gres_csrq" class="easyui-datebox" name="gres_csrq" style="width:100%"  data-options="label:'出生日期:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gres_mz" class="easyui-combobox" name="gres_mz" style="width:100%"  data-options="label:'民族:'">
								<option>汉族</option>
								<option>回族</option>
								<option>蒙族</option>
								<option>满族</option>
								<option>其他</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gres_zzmm" class="easyui-combobox" name="gres_zzmm" style="width:100%"  data-options="label:'政治面貌:'">
								<option>无</option>
								<option>党员</option>
								<option>团员</option>
								<option>其他</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gres_xl" class="easyui-combobox" name="gres_xl" style="width:100%"  data-options="label:'学历:'">
								<option>高中</option>
								<option>大专</option>
								<option>本科</option>
								<option>硕士</option>
								<option>博士</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gres_wysp" class="easyui-combobox" name="gres_wysp" style="width:100%"  data-options="label:'外语水平:'">
								<option>英语四级</option>
								<option>英语六级</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gres_jsjdj" class="easyui-combobox" name="gres_jsjdj" style="width:100%"  data-options="label:'计算机等级:'">
								<option>计算机一级</option>
								<option>计算机二级</option>
								<option>计算机三级</option>
								<option>计算机四级</option>
							</select>
						</div>						
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gres_lxdh" class="easyui-textbox" name="gres_lxdh" style="width:100%"  data-options="label:'联系电话:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<input id="gres_yx" class="easyui-textbox" name="gres_yx" style="width:100%"  data-options="label:'邮箱:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gres_zy" class="easyui-textbox" name="gres_zy" style="width:100%"  data-options="label:'专业:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<input id="gres_byyx" class="easyui-textbox" name="gres_byyx" style="width:100%"  data-options="label:'毕业院校:'"/>
						</div>
						<div style="margin-bottom:10px;">
							<input id="gres_jg" class="easyui-textbox" name="gres_jg" style="width:100%"  data-options="label:'籍贯:'"/>
						</div>
						<div style="margin-bottom:10px">
							<input id="gres_gzjl" class="easyui-textbox" name="gres_gzjl" style="width:100%;height: 50px;"  data-options="label:'工作经历:',multiline:true"/>
						</div>
						<div style="margin-bottom:10px">
							<input id="gres_zwpj" class="easyui-textbox" name="gres_zwpj" style="width:100%;height: 50px;"  data-options="label:'自我评价:',multiline:true"/>
						</div>
					</form>
					<div style="text-align:center;padding:5px 0">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gltj();return false;" style="width:80px">保  存</a>
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
