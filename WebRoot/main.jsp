<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人力资源管理系统</title>
    <style type="text/css">
        table td {
            border: #C0C0C0 0px solid;
        }

        a:link, a:visited {
            color: #000080;
            text-decoration: none;
        }

        a:hover {
            color: #FF0000;
            text-decoration: none;
        }

        a:active {
            color: #000080;
            text-decoration: none;
        }
    </style>
</head>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(window).resize(Resize);
    });
    function Resize() {
        $("#SubMenu").height(document.documentElement.clientHeight - $("#SubMenu").offset().top - 2);
        $("#SubMenu").width(document.documentElement.clientWidth - $("#SubMenu").offset().left - 30);
    }
</script>
<body style="margin: 0px; padding: 0px;">
    <table style="width: 100%; margin: 0px; border-style: none; padding: 0px; border-width: 0px; border-collapse: collapse; background-repeat: no-repeat;">
    <tr>
        <td style="width: 30px; background-image: url(<%=basePath%>images/formback_1.jpg); background-repeat: no-repeat; padding: 0px; vertical-align: top;">
        </td>
        <td style="background-image: url(<%=basePath%>images/formback_2.jpg); background-repeat:repeat-x; vertical-align: top;">
            <table style="width:100%; font-size:11pt; padding: 0px; border-width: 0px; border-collapse: collapse;">
                <tr>
                    <td colspan="2">
                        <table style="width: 100%; line-height:19px; padding: 0px; border-width: 0px; border-collapse: collapse;">
                            <tr>
                                <td id="mainTitle" style="height:36px; font-family: 微软雅黑; font-size:18pt; color: #fff; white-space: nowrap;" rowspan="2"><div style="display: inline;">人力资源管理系统</div></td>
                                <td style="height:20px; font-family: 微软雅黑; font-size:9pt; color: #fff; text-align: right; white-space: nowrap;">
                                </td>
                            </tr>
                            <tr>
                                <td style="height:17px;font-family: 微软雅黑; font-size:9pt; color: #fff; text-align: right; white-space: nowrap;">
                                	欢迎你，<c:if test="${utype=='t_admin' }">管理员：${myinfo.uname }</c:if>
			                    	<c:if test="${utype=='t_staff' }">员工：${myinfo.sxm }</c:if>
			                    	<c:if test="${utype=='t_customer' }">游客：${myinfo.cus_no }</c:if>.
			                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="font-family: 微软雅黑; font-size: 10pt; white-space : nowrap;">
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<c:if test="${utype=='t_admin' }">
	                    	<a href="<%=basePath %>t_dept/init" target="SubMenu">部门管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_post/init" target="SubMenu">职务管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_admin/init" target="SubMenu">管理员管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_staff/init" target="SubMenu">员工管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_job/init" target="SubMenu">招聘管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_rewards/init" target="SubMenu">奖惩管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_attendance/init" target="SubMenu">考勤查看</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_train/init" target="SubMenu">培训管理</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_train_emp/init" target="SubMenu">员工培训</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_salary/init" target="SubMenu">工资结算</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_reconsider/init" target="SubMenu">复议查看</a>&nbsp;&nbsp;
                    	</c:if>  
                    	<c:if test="${utype=='t_staff' }">
	                    	<a href="<%=basePath %>t_staff/cinit" target="SubMenu">通讯录</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>jsp/customer/mt_attendance.jsp" target="SubMenu">出勤打卡</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_rewards/cinit" target="SubMenu">我的奖惩</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_train_emp/cinit" target="SubMenu">我的培训</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>t_salary/cinit" target="SubMenu">我的薪资</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>myinfo.jsp" target="SubMenu">我的信息</a>&nbsp;&nbsp;
	                    	<a href="<%=basePath %>updpwd.jsp" target="SubMenu">修改密码</a>&nbsp;&nbsp;
                    	</c:if>
                    	<c:if test="${utype=='t_customer' }">
                    		<a href="<%=basePath %>t_job/cinit" target="SubMenu">招聘信息</a>&nbsp;&nbsp;
                    		<a href="<%=basePath %>t_resume/init" target="SubMenu">简历中心</a>&nbsp;&nbsp;
                    		<a href="<%=basePath %>t_res_cus/cinit" target="SubMenu">面试邀请</a>&nbsp;&nbsp;
                    		<a href="<%=basePath %>t_res_cus/ccinit" target="SubMenu">录用通知</a>&nbsp;&nbsp;
                    	</c:if>
                    </td>
                    <td style="height:36px; font-family: 微软雅黑; font-size:10pt; white-space: nowrap;" align="right">
                    	&nbsp;&nbsp;<a href="<%=basePath %>login/logout">安全退出</a>&nbsp;&nbsp;                    	
                    </td>
                </tr>
                <tr><td colspan="2"><div style="height: 7px; margin: 0px;"></div></td></tr>
                <tr><td style="vertical-align: top;" colspan="2">
                    <iframe id="SubMenu" src="<%=basePath %>subm.jsp" name="SubMenu" frameborder="0" style="vertical-align: middle; min-width: 800px;
                        text-align: center; width: 100%; background-color:transparent;" scrolling="auto" allowtransparency="true">
                    </iframe>
                </td></tr>
            </table>
        </td>
        <td style="width: 30px; background-image: url(<%=basePath%>images/formback_3.jpg); background-repeat: no-repeat; padding: 0px;">
        </td>
    </tr>
    </table>
</body>
</html>
<script type="text/javascript">
    if ($.browser.mozilla && navigator.userAgent.indexOf('Trident/7.0') < 0) {
        $("#mainTitle").height(32);
    }
    Resize();
</script>
