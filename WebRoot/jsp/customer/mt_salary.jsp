<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>工资管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/syscss.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/syssmp.js"></script>
		<script type="text/javascript">
			function updbt(id,yf){
				$("#idi").val(id);
				$("#dlg").dialog('open');
				$("#gyf").textbox('setValue',yf);
			}
			function gltj(){
				var id=$("#idi").val();
				var rec_mark=$("#grec_mark").textbox('getValue');
				if(rec_mark==""||id==""){
					alert("复议信息不完整");
					return;
				}
				$.ajax({
					url:'<%=basePath%>t_reconsider/add',
					type:'post',
					data:{"t_salary_id":id,"rec_mark":hstr(rec_mark)},
					dataType:'json',
					success:function(data){
						alert(data.msg);
						return;
					}
				});
			}
			
		//取消
		function rst(){
			window.location.href="<%=basePath%>t_salary/capage?pnum="+${pagenum};
		}
		//查询
		function cktj(){
			$("#ckf").submit();
		}
		$(function() {
			   $('#ckyf').datebox({
			       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			       onShowPanel: function () {
			          //触发click事件弹出月份层
			          span.trigger('click'); 
			          if (!tds)
			            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
			            setTimeout(function() { 
			                tds = p.find('div.calendar-menu-month-inner td');
			                tds.click(function(e) {
			                   //禁止冒泡执行easyui给月份绑定的事件
			                   e.stopPropagation(); 
			                   //得到年份
			                   var year = /\d{4}/.exec(span.html())[0] ,
			                   //月份
			                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
			                   month = parseInt($(this).attr('abbr'), 10);  

			         //隐藏日期对象                     
			         $('#ckyf').datebox('hidePanel') 
			           //设置日期的值
			           .datebox('setValue', year + '-' + month); 
			                        });
			                    }, 0);
			            },
			            //配置parser，返回选择的日期
			            parser: function (s) {
			                if (!s) return new Date();
			                var arr = s.split('-');
			                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
			            },
			            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
			            formatter: function (d) { 
			                var currentMonth = (d.getMonth()+1);
			                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
			                return d.getFullYear() + '-' + currentMonthStr; 
			            }
			        });

			        //日期选择对象
			        var p = $('#ckyf').datebox('panel'), 
			        //日期选择对象中月份
			        tds = false, 
			        //显示月份层的触发控件
			        span = p.find('span.calendar-text'); 
			        var curr_time = new Date();

			        //设置前当月
			        $("#ckyf").datebox("setValue", myformatter(curr_time));
			});
		//格式化日期
		function myformatter(date) {
		    //获取年份
		    var y = date.getFullYear();
		    //获取月份
		    var m = date.getMonth() + 1;
		    return y + '-' + m;
		}
	</script>
</head>
<body>	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE7" style="padding-left: 50px;">工资信息</td>
					<td style="padding-right:10px;"><div align="right" style="padding-right: 50px;">
						<form id="ckf" action="<%=basePath%>t_salary/cck" method="post">
						<table border="0" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<td class="STYLE3">
									月份：<input id="ckyf" name="yf" class="ckinput easyui-datebox"/>
								</td>
								<td width="60">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="cktj();return false;"><img src="images/search.png" width="14" height="14" /></a></div></td>
											<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="cktj();return false;">查询</a></div></td>
										</tr>
									</table>
								</td>
								<!-- 
								<td width="60"><table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="addbt();return false;"><img src="images/001.gif" width="14" height="14" /></a></div></td>
										<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="addbt();return false;">结算</a></div></td>
									</tr>
								</table></td>
								 -->
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
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">员工</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">部门</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">职位</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">就职状态</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">基本工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">职务津贴</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">奖惩工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">考勤扣款</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">应发工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">实发工资</span></strong></div></td>
									<td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">月份</span></strong></div></td>
									<td width="5%" height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1"></span></strong></div></td>
								</tr>
								<c:forEach var="a" items="${alist }">
									<tr>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sxm }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.dept_bmmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.post_gwmc }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sjzzt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.jbgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.zwjt }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.jcgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.kqkk }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.yfgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.sfgz }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3">${a.yf }</span></div></td>
										<td height="22" bgcolor="#FFFFFF"><div align="center" class="STYLE3">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}','${a.yf }');return false;"><img src="images/114.gif" width="14" height="14" /></a></div></td>
													<td class="STYLE1"><div align="center"><a href="javascript:void(0);" onclick="updbt('${a.id}','${a.yf }');return false;">复议</a></div></td>
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
														<td width="40"><a href="<%=basePath%>t_salary/capage?pnum=1"><img src="images/first.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_salary/capage?pnum=${pagenum-1}"><img src="images/back.gif" width="37" height="15"/></a></td>
														<td width="45"><a href="<%=basePath%>t_salary/capage?pnum=${pagenum+1}"><img src="images/next.gif" width="37" height="15"/></a></td>
														<td width="40"><a href="<%=basePath%>t_salary/capage?pnum=${pagenums}"><img src="images/last.gif" width="37" height="15"/></a></td>
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
	</table>
	<div id="dlg" class="easyui-dialog" title="工资有异议发复议给管理" data-options="closable:true,modal:true,closed:true" style="width:100%;max-width:500px;padding:30px 60px;">
		<form id="ff" method="post">
			<div style="margin-bottom:20px">
				<input id="gyf" class="easyui-textbox" disabled="disabled" name="gyf" style="width:100%"  data-options="label:'月份:'"/>
			</div>
			<div style="margin-bottom:20px">
				<input id="grec_mark" class="easyui-textbox" name="grec_mark" style="width:100%;height: 150px;"  data-options="label:'说明:',multiline:true"/>
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gltj();return false;" style="width:70%;">提  交</a>
		</div>
	</div>
		<input type="hidden" id="hrownums" value="${rownums }" />
		<input type="hidden" id="hpagenum" value="${pagenum }" />
		<input type="hidden" id="hpagenums" value="${pagenums }" />
		<input type="hidden" id="idi" value="" />
		<input type="hidden" id="flagi" value="" />
	</body>
</html>
