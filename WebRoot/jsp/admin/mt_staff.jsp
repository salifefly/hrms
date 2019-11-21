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
		<title>员工管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">				
			function updbt(id){
				$.ajax({
				url:'<%=basePath%>t_staff/getdata',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(data){	
					var ob=data.ob;
					$("#flagi").val("upd");
					$("#idi").val(id);
					$("#dlg").dialog('open');
					$("#gszh").textbox('setValue',gstr(ob.szh));
					$("#gspwd").textbox('setValue',gstr(ob.spwd));
					$("#gsxm").textbox('setValue',gstr(ob.sxm));
					$("#gsxb").combobox('setValue',gstr(ob.sxb));
					$("#gscsrq").datebox('setValue',gstr(ob.scsrq));
					$("#gsmz").combobox('setValue',gstr(ob.smz));
					$("#gszzmm").combobox('setValue',gstr(ob.szzmm));
					$("#gsxl").combobox('setValue',gstr(ob.sxl));
					$("#gsjg").textbox('setValue',gstr(ob.sjg));
					$("#gsyx").textbox('setValue',gstr(ob.syx));
					$("#gslxdh").textbox('setValue',gstr(ob.slxdh));
					$("#gszy").textbox('setValue',gstr(ob.szy));
					$("#gsbyyx").textbox('setValue',gstr(ob.sbyyx));
					$("#gswysp").combobox('setValue',gstr(ob.swysp));
					$("#gsjsjdj").combobox('setValue',gstr(ob.sjsjdj));
					$("#gsjzzt").combobox('setValue',gstr(ob.sjzzt));
					$("#gsygzt").combobox('setValue',gstr(ob.sygzt));
					$("#gt_dept_id").combobox('setValue',gstr(ob.t_dept_id));
					$.ajax({
						url:'<%=basePath%>t_post/getdatas',
						type:'post',
						data:{"t_dept_id":ob.t_dept_id},
						dataType:'json',
						success:function(data){
					       var dataList,plist,orgValue,orgNameValue;
					       dataList = [{"value":"0","text":"所有职位"}];
					       plist=data.plist;
					       $.each(plist,function(index,item){
						       orgValue = plist[index].id;
						       orgNameValue = plist[index].post_gwmc;
						       
						       dataList.push({"value": orgValue,"text":orgNameValue});
					       });
					       $("#gt_post_id").combobox("loadData",dataList);
					       $("#gt_post_id").combobox("setValue",ob.t_post_id);
						}
					});
				}
			});
		}
		//保存
		function gltj(){
			var szh=hstr($("#gszh").textbox('getValue'));
			var spwd=hstr($("#gspwd").textbox('getValue'));
			var sxm=hstr($("#gsxm").textbox('getValue'));
			var sxb=hstr($("#gsxb").combobox('getValue'));
			var scsrq=hstr($("#gscsrq").datebox('getValue'));
			var smz=hstr($("#gsmz").combobox('getValue'));
			var szzmm=hstr($("#gszzmm").combobox('getValue'));
			var sxl=hstr($("#gsxl").combobox('getValue'));
			var sjg=hstr($("#gsjg").textbox('getValue'));
			var syx=hstr($("#gsyx").textbox('getValue'));
			var slxdh=hstr($("#gslxdh").textbox('getValue'));
			var szy=hstr($("#gszy").textbox('getValue'));
			var sbyyx=hstr($("#gsbyyx").textbox('getValue'));
			var swysp=hstr($("#gswysp").combobox('getValue'));
			var sjsjdj=hstr($("#gsjsjdj").combobox('getValue'));
			var sjzzt=hstr($("#gsjzzt").combobox('getValue'));
			var sygzt=hstr($("#gsygzt").combobox('getValue'));
			var t_post_id=hstr($("#gt_post_id").combobox('getValue'));
			var id=$("#idi").val();
			var flag=$("#flagi").val();
			if(szh==""||spwd==""||sxm==""||sxb==""||scsrq==""||smz==""||szzmm==""||sxl==""||sjg==""||syx==""||slxdh==""||szy==""||sbyyx==""||swysp==""||sjsjdj==""||sjzzt==""||sygzt==""||t_post_id=="0"){
				alert('员工信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'<%=basePath%>t_staff/add',
					type:'post',
					data:{"szh":szh,"spwd":spwd,"sxm":sxm,"sxb":sxb,"scsrq":scsrq,"smz":smz,"szzmm":szzmm,"sxl":sxl,"sjg":sjg,"syx":syx,"slxdh":slxdh,"szy":szy,"sbyyx":sbyyx,"swysp":swysp,"sjsjdj":sjsjdj,"sjzzt":sjzzt,"sygzt":sygzt,"t_post_id":t_post_id},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'<%=basePath%>t_staff/upd',
					type:'post',
					data:{"id":id,"szh":szh,"spwd":spwd,"sxm":sxm,"sxb":sxb,"scsrq":scsrq,"smz":smz,"szzmm":szzmm,"sxl":sxl,"sjg":sjg,"syx":syx,"slxdh":slxdh,"szy":szy,"sbyyx":sbyyx,"swysp":swysp,"sjsjdj":sjsjdj,"sjzzt":sjzzt,"sygzt":sygzt,"t_post_id":t_post_id},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_staff/apage?pnum="+${pagenum};
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
				url:'<%=basePath%>t_staff/del',
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
					<td class="STYLE7" style="padding-left: 50px;">员工信息管理</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_staff/ck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									姓名：<input id="ckuname" name="sxm" class="ckinput easyui-textbox"/>
								</td>
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">账号</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">密码</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">姓名</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">性别</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">出生日期</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">民族</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">入职时间</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td align="center" height="22" bgcolor="#FFFFFF">
											<input name="delid" type="checkbox" value="${a.id }"/>
										</td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.szh }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.spwd }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sxm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sxb }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.scsrq }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.smz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${fn:substring(a.sintime,0,10) }</span></div></td>
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
														<td width="40"><a href="<%=basePath%>t_staff/apage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_staff/apage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_staff/apage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_staff/apage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
		<tr><!-- data-options="closable:false,modal:true,closed:true" -->
			<td style="width: 100%;" align="center">
				<div id="dlg" class="easyui-dialog" title=" " data-options="closable:false,modal:true,closed:true" style="width:100%;max-width:800px;padding:30px 60px;">
					<form id="ff" method="post">
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gszh" class="easyui-textbox" name="gszh" style="width:100%"  data-options="label:'账号:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<input id="gspwd" class="easyui-textbox" name="gspwd" style="width:100%"  data-options="label:'密码:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gsxm" class="easyui-textbox" name="gsxm" style="width:100%"  data-options="label:'姓名:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gsxb" class="easyui-combobox" name="gsxb" style="width:100%"  data-options="label:'性别:'">
								<option>男</option>
								<option>女</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gscsrq" class="easyui-datebox" name="gscsrq" style="width:100%"  data-options="label:'出生日期:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gsmz" class="easyui-combobox" name="gsmz" style="width:100%"  data-options="label:'民族:'">
								<option>汉族</option>
								<option>回族</option>
								<option>蒙族</option>
								<option>满族</option>
								<option>其他</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gszzmm" class="easyui-combobox" name="gszzmm" style="width:100%"  data-options="label:'政治面貌:'">
								<option>无</option>
								<option>党员</option>
								<option>团员</option>
								<option>其他</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gsxl" class="easyui-combobox" name="gsxl" style="width:100%"  data-options="label:'学历:'">
								<option>高中</option>
								<option>大专</option>
								<option>本科</option>
								<option>硕士</option>
								<option>博士</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gsjg" class="easyui-textbox" name="gsjg" style="width:100%"  data-options="label:'籍贯:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<input id="gsyx" class="easyui-textbox" name="gsyx" style="width:100%"  data-options="label:'邮箱:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gslxdh" class="easyui-textbox" name="gslxdh" style="width:100%"  data-options="label:'联系电话:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<input id="gszy" class="easyui-textbox" name="gszy" style="width:100%"  data-options="label:'专业:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<input id="gsbyyx" class="easyui-textbox" name="gsbyyx" style="width:100%"  data-options="label:'毕业院校:'"/>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gswysp" class="easyui-combobox" name="gswysp" style="width:100%"  data-options="label:'外语水平:'">
								<option>英语四级</option>
								<option>英语六级</option>
							</select>							
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gsjsjdj" class="easyui-combobox" name="gsjsjdj" style="width:100%"  data-options="label:'计算机等级:'">
								<option>计算机一级</option>
								<option>计算机二级</option>
								<option>计算机三级</option>
								<option>计算机四级</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gsjzzt" class="easyui-combobox" name="gsjzzt" style="width:100%"  data-options="label:'就职状态:'">
								<option>实习</option>
								<option>正式</option>
								<option>离职</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gsygzt" class="easyui-combobox" name="gsygzt" style="width:100%"  data-options="label:'员工状态:'">
								<option>正常</option>
								<option>出差</option>
								<option>请假</option>
								<option>其他</option>
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: right;">
							<select id="gt_dept_id" class="easyui-combobox" name="gt_dept_id" style="width:100%"  data-options="label:'部门:'">
								<option value="0">选择部门</option>
								<c:forEach var="a" items="${t_deptlist }">
									<option value="${a.id }">${a.dept_bmmc }</option>
								</c:forEach>								
							</select>
						</div>
						<div style="margin-bottom:10px;width: 300px;float: left;">
							<select id="gt_post_id" class="easyui-combobox" name="gt_post_id" style="width:100%"  data-options="label:'职位:'">
								<option value="0">选择职位</option>								
							</select>
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
