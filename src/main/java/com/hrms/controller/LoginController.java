package com.hrms.controller;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hrms.dao.T_customerDao;
import com.hrms.been.T_customer;
import com.hrms.service.T_customerService;
import com.hrms.dao.T_staffDao;
import com.hrms.been.T_staff;
import com.hrms.service.T_staffService;
import com.hrms.dao.T_adminDao;
import com.hrms.been.T_admin;
import com.hrms.service.T_adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Resource(name = "t_customerDao")
	T_customerDao t_customerDao;
	@Autowired
	T_customerService t_customerService;
	@Resource(name = "t_staffDao")
	T_staffDao t_staffDao;
	@Autowired
	T_staffService t_staffService;
	@Resource(name = "t_adminDao")
	T_adminDao t_adminDao;
	@Autowired
	T_adminService t_adminService;
	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String login(String uname,String upassword,String utype){
		//获取request
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		//根据用户类型，分别进行登录检测
		boolean bl=true;
		if("t_customer".equals(utype)){//游客信息登录
			bl=false;
			//根据账号和密码获取游客信息用户信息
			T_customer ad=t_customerService.login(uname, upassword);
			//如果登录信息正确，则把登录信息写入到session，并返回1
			if(ad!=null){
				request.getSession(true).setAttribute("myinfo", ad);
				request.getSession(true).setAttribute("utype", utype);
				return "{\"msg\":\"1\"}";
			}else{//如果登录信息不正确，给出提示
				return "{\"msg\":\"账号或密码错误\"}";
			}
		}
		if("t_staff".equals(utype)){//员工信息登录
			bl=false;
			//根据账号和密码获取员工信息用户信息
			T_staff ad=t_staffService.login(uname, upassword);
			//如果登录信息正确，则把登录信息写入到session，并返回1
			if(ad!=null){
				request.getSession(true).setAttribute("myinfo", ad);
				request.getSession(true).setAttribute("utype", utype);
				return "{\"msg\":\"1\"}";
			}else{//如果登录信息不正确，给出提示
				return "{\"msg\":\"账号或密码错误\"}";
			}
		}
		if("t_admin".equals(utype)){//管理员信息登录
			bl=false;
			//根据账号和密码获取管理员信息用户信息
			T_admin ad=t_adminService.login(uname, upassword);
			//如果登录信息正确，则把登录信息写入到session，并返回1
			if(ad!=null){
				request.getSession(true).setAttribute("myinfo", ad);
				request.getSession(true).setAttribute("utype", utype);
				return "{\"msg\":\"1\"}";
			}else{//如果登录信息不正确，给出提示
				return "{\"msg\":\"账号或密码错误\"}";
			}
		}
		if(bl){
			return "{\"msg\":\"系统错误\"}";
		}
		return null;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("myinfo");
		request.getSession().removeAttribute("utype");
		request.getSession().removeAttribute("alist");
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
