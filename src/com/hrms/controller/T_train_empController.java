package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_dept;
import com.hrms.been.T_staff;
import com.hrms.been.T_train;
import com.hrms.been.T_train_emp;
import com.hrms.service.T_deptService;
import com.hrms.service.T_staffService;
import com.hrms.service.T_trainService;
import com.hrms.service.T_train_empService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_train_emp")
public class T_train_empController {
	@Autowired
	T_train_empService t_train_empService;
	@Autowired
	T_deptService t_deptService;
	@Autowired
	T_trainService t_trainService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_train_id", null);
		//获取首页数据
		getPage(session,null,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_train_emp.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String t_train_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_train_id", t_train_id);
		//获取首页数据
		getPage(session, null,t_train_id, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_train_emp.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object t_train_ids=session.getAttribute("t_train_id");
		String t_train_id=null;
		if(t_train_ids!=null){
			t_train_id=t_train_ids.toString();
		}
		getPage(session, null,t_train_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_train_emp.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String t_staff_id,String t_train_id, HttpServletRequest request){
			T_train_emp t_train_emp=new T_train_emp();
			t_train_emp.setT_staff_id(Integer.parseInt(t_staff_id));
			t_train_emp.setT_train_id(Integer.parseInt(t_train_id));
			if(t_train_empService.insertT_train_emp(t_train_emp)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String t_staff_id,String t_train_id, HttpServletRequest request){
			T_train_emp t_train_emp=t_train_empService.getById(id);
			t_train_emp.setT_staff_id(Integer.parseInt(t_staff_id));
			t_train_emp.setT_train_id(Integer.parseInt(t_train_id));
			if(t_train_empService.updateT_train_emp(t_train_emp)>0){
				return "{\"msg\":\"修改成功\"}";
			}else{
				return "{\"msg\":\"修改失败\"}";
			}
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String del(String ids){
		if(ids.endsWith(",")){
			ids=ids.substring(0, ids.lastIndexOf(","));
		}
		if(t_train_empService.deleteT_train_emp(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_train_emp ad=t_train_empService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String t_staff_id,String t_train_id, String pnum){
		Integer t_staff_ids=null;
		if(t_staff_id!=null&&t_staff_id!=""){
			t_staff_ids=Integer.parseInt(t_staff_id);
		}
		Integer t_train_ids=null;
		if(t_train_id!=null&&t_train_id!=""){
			t_train_ids=Integer.parseInt(t_train_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_train_empService.getAllCount(t_staff_ids,t_train_ids);
		int apnum=allcount/scount;
		if(allcount%scount>0){
			apnum++;
		}
		//要显示的页码
		int pnums=Integer.parseInt(pnum);
		if(pnums>apnum){
			pnums=apnum;
		}
		if(pnums<1){
			pnums=1;
		}
		//开始条数
		int sindex=(pnums-1)*5;
		List<T_train_emp> alist=t_train_empService.getPage(t_staff_ids,t_train_ids, sindex, scount);
		session.setAttribute("alist", alist);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		List<T_train> t_trainlist=t_trainService.getAll();
		session.setAttribute("t_trainlist", t_trainlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	/////////////////////////////我的培训//////////////////////////////////////////////////////
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		//获取首页数据
		getPage(session,String.valueOf(tc.getId()),null, "1");
		request.getRequestDispatcher("/jsp/customer/mt_train_emp.jsp").forward(request, response);
	}
	
	@RequestMapping(value = "/capage", method = RequestMethod.GET)
	@ResponseBody
	public void capage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		getPage(session, String.valueOf(tc.getId()),null, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_train_emp.jsp").forward(request, response);
	}
}
