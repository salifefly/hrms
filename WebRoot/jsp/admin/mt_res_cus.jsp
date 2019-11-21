<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>简历投递信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">
		function jlbt(id){
			$.ajax({
			url:'<%=basePath%>t_res_cus/getdata',
			type:'post',
			dataType:'json',
			data:{"id":id},
			success:function(data){	
				var ob=data.ob;
				$("#jl").dialog('open');
				//$("#gres_jlmc").textbox('setValue',gstr(ob.res_jlmc));
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
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_res_cus/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gt_resume_id").textbox('setValue',gstr(ob.res_xm));
					$("#gt_job_id").textbox('setValue',gstr(ob.job_zw));
					$("#grc_mssm").textbox('setValue',gstr(ob.rc_mssm));
					$("#grc_mssj").textbox('setValue',gstr(ob.rc_mssj));
				}
			});
		}
			function updbts(id){
				$("#idi").val(id);
				$("#lydlg").dialog('open');
		}
		//保存
		function gltj(){
			var rc_mssm=hstr($("#grc_mssm").textbox('getValue'));
			var rc_mssj=hstr($("#grc_mssj").textbox('getValue'));
			var id=$("#idi").val();
			if(rc_mssm==""||rc_mssj==""){
				alert('邀请信息不完整。');
				return;
			}
			$.ajax({
				url:'<%=basePath%>t_res_cus/upd',
				type:'post',
				data:{"id":id,"rc_zt":"面试","rc_mssm":rc_mssm,"rc_mssj":rc_mssj},
				dataType:'json',
				success:function(data){
					alert(data.msg);
				}
			});
		}
		//录用提交
		function lygltj(){
			var t_dept_id=hstr($("#gt_dept_id").combobox('getValue'));
			var t_post_id=hstr($("#gt_post_id").combobox('getValue'));
			var sjzzt=hstr($("#gsjzzt").combobox('getValue'));
			var sygzt=hstr($("#gsygzt").combobox('getValue'));
			var id=$("#idi").val();
			if(t_dept_id=="0"||t_post_id=="0"||sjzzt==""||sygzt==""){
				alert('录用信息不完整。');
				return;
			}
			$.ajax({
				url:'<%=basePath%>t_res_cus/ly',
				type:'post',
				data:{"id":id,"t_dept_id":t_dept_id,"t_post_id":t_post_id,"sjzzt":sjzzt,"sygzt":sygzt},
				dataType:'json',
				success:function(data){
					alert(data.msg);
				}
			});
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_res_cus/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_res_cus/del',
				type:'post',
				dataType:'json',
				data:{"ids":ids,"tbname":"t_res_cus"},
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
		});

	</script>
</head>
<body>	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE7" style="padding-left: 50px;">简历信息</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td width="52"><table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="delsbt();return false;"><img src="images/083.gif" width="14" height="14" /></a></div></td>										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="delsbt();return false;">删除</a></div></td>
									</tr>
								</table></td>
							</tr>
						</table>
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">姓名</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">状态</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">面试说明</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">面试时间</span></strong></div></td>
									<td colspan="2" width="8%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.job_zw }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.res_xm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.rc_zt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.rc_mssm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.rc_mssj }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="jlbt('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
												<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="jlbt('${a.id}');return false;">简历</a></div></td>
											</tr>
										</table>
										</div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
										<c:if test="${a.rc_zt=='已投递'}">
												<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
														<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}');return false;">邀请</a></div></td>
													</tr>
												</table>
										</c:if>
										<c:if test="${a.rc_zt=='面试' }">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbts('${a.id}');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbts('${a.id}');return false;">录用</a></div></td>
												</tr>
											</table>
										</c:if>
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
														<td width="40"><a href="<%=basePath%>t_res_cus/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_res_cus/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_res_cus/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_res_cus/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
							<input id="gt_job_id" class="easyui-textbox" disabled="disabled" name="gt_job_id" style="width:100%"  data-options="label:'职位:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="gt_resume_id" class="easyui-textbox" disabled="disabled" name="gt_resume_id" style="width:100%"  data-options="label:'姓名:'"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="grc_mssm" class="easyui-textbox" name="grc_mssm" style="width:100%;height: 50px;"  data-options="label:'面试说明:',multiline:true"/>
						</div>
						<div style="margin-bottom:20px">
							<input id="grc_mssj" class="easyui-textbox" name="grc_mssj" style="width:100%"  data-options="label:'面试时间:'"/>
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
		<div id="lydlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true" style="width:100%;max-width:400px;padding:30px 60px;">
			<form id="ff" method="post">
				<div style="margin-bottom:20px">
					<select id="gt_dept_id" class="easyui-combobox" name="gt_dept_id" style="width:100%"  data-options="label:'部门:'">
						<option value="0">选择部门</option>
						<c:forEach var="a" items="${deptlist }">
							<option value="${a.id }">${a.dept_bmmc }</option>
						</c:forEach>
					</select>
				</div>
				<div style="margin-bottom:20px">
					<select id="gt_post_id" class="easyui-combobox" name="gt_post_id" style="width:100%"  data-options="label:'职位:'">
						<option value="0">选择职位</option>
					</select>
				</div>
				<div style="margin-bottom:20px">
					<select id="gsjzzt" class="easyui-combobox" name="gsjzzt" style="width:100%"  data-options="label:'就职状态:'">
						<option>实习</option>
						<option>正式</option>
						<option>离职</option>
					</select>
				</div>
				<div style="margin-bottom:20px">
					<select id="gsygzt" class="easyui-combobox" name="gsygzt" style="width:100%"  data-options="label:'员工状态:'">
						<option>正常</option>
						<option>出差</option>
						<option>请假</option>
						<option>其他</option>
					</select>
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="lygltj();return false;" style="width:80px">提  交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rst();return false;" style="width:80px">退  出</a>
			</div>
		</div>
		
		<div id="jl" class="easyui-dialog" title="简历详情" data-options="modal:true,closed:true" style="width:100%;max-width:800px;padding:30px 60px;">
			<form id="ff" method="post">
				<!-- 
				<div style="margin-bottom:10px">
					<input id="gres_jlmc" class="easyui-textbox" disabled="disabled" name="gres_jlmc" style="width:100%"  data-options="label:'简历名称:'"/>
				</div>
				 -->
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<input id="gres_xm" class="easyui-textbox" disabled="disabled" name="gres_xm" style="width:100%"  data-options="label:'姓名:'"/>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<select id="gres_xb" class="easyui-combobox" disabled="disabled" name="gres_xb" style="width:100%"  data-options="label:'性别:'">
						<option>男</option>
						<option>女</option>
					</select>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<input id="gres_csrq" class="easyui-datebox" disabled="disabled" name="gres_csrq" style="width:100%"  data-options="label:'出生日期:'"/>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<select id="gres_mz" class="easyui-combobox" disabled="disabled" name="gres_mz" style="width:100%"  data-options="label:'民族:'">
						<option>汉族</option>
						<option>回族</option>
						<option>蒙族</option>
						<option>满族</option>
						<option>其他</option>
					</select>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<select id="gres_zzmm" class="easyui-combobox" disabled="disabled" name="gres_zzmm" style="width:100%"  data-options="label:'政治面貌:'">
						<option>无</option>
						<option>党员</option>
						<option>团员</option>
						<option>其他</option>
					</select>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<select id="gres_xl" class="easyui-combobox" disabled="disabled" name="gres_xl" style="width:100%"  data-options="label:'学历:'">
						<option>高中</option>
						<option>大专</option>
						<option>本科</option>
						<option>硕士</option>
						<option>博士</option>
					</select>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<select id="gres_wysp" class="easyui-combobox" disabled="disabled" name="gres_wysp" style="width:100%"  data-options="label:'外语水平:'">
						<option>英语四级</option>
						<option>英语六级</option>
					</select>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<select id="gres_jsjdj" class="easyui-combobox" disabled="disabled" name="gres_jsjdj" style="width:100%"  data-options="label:'计算机等级:'">
						<option>计算机一级</option>
						<option>计算机二级</option>
						<option>计算机三级</option>
						<option>计算机四级</option>
					</select>
				</div>						
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<input id="gres_lxdh" class="easyui-textbox" disabled="disabled" name="gres_lxdh" style="width:100%"  data-options="label:'联系电话:'"/>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<input id="gres_yx" class="easyui-textbox" disabled="disabled" name="gres_yx" style="width:100%"  data-options="label:'邮箱:'"/>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: left;">
					<input id="gres_zy" class="easyui-textbox" disabled="disabled" name="gres_zy" style="width:100%"  data-options="label:'专业:'"/>
				</div>
				<div style="margin-bottom:10px;width: 300px;float: right;">
					<input id="gres_byyx" class="easyui-textbox" disabled="disabled" name="gres_byyx" style="width:100%"  data-options="label:'毕业院校:'"/>
				</div>
				<div style="margin-bottom:10px;">
					<input id="gres_jg" class="easyui-textbox" disabled="disabled" name="gres_jg" style="width:100%"  data-options="label:'籍贯:'"/>
				</div>
				<div style="margin-bottom:10px">
					<input id="gres_gzjl" class="easyui-textbox" name="gres_gzjl" style="width:100%;height: 50px;"  data-options="label:'工作经历:',multiline:true"/>
				</div>
				<div style="margin-bottom:10px">
					<input id="gres_zwpj" class="easyui-textbox" name="gres_zwpj" style="width:100%;height: 50px;"  data-options="label:'自我评价:',multiline:true"/>
				</div>
			</form>
		</div>
	</body>
</html>
