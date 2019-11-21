package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_customer;
import com.hrms.been.T_dept;
import com.hrms.been.T_rewards;
import com.hrms.been.T_staff;
import com.hrms.service.T_deptService;
import com.hrms.service.T_rewardsService;
import com.hrms.service.T_staffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_rewards")
public class T_rewardsController {
	@Autowired
	T_rewardsService t_rewardsService;
	@Autowired
	T_deptService t_deptService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_staff_id", null);
		session.setAttribute("t_dept_id", null);
		session.setAttribute("t_post_id", null);
		//获取首页数据
		getPage(session,null,null,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_rewards.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String t_staff_id,String t_dept_id,String t_post_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_staff_id", t_staff_id);
		session.setAttribute("t_post_id", t_post_id);
		session.setAttribute("t_dept_id", t_dept_id);
		//获取首页数据
		getPage(session, t_dept_id,t_post_id,t_staff_id, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_rewards.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object t_staff_ids=session.getAttribute("t_staff_id");
		String t_staff_id=null;
		if(t_staff_ids!=null){
			t_staff_id=t_staff_ids.toString();
		}
		Object t_dept_ids=session.getAttribute("t_dept_id");
		String t_dept_id=null;
		if(t_dept_ids!=null){
			t_dept_id=t_dept_ids.toString();
		}
		Object t_post_ids=session.getAttribute("t_post_id");
		String t_post_id=null;
		if(t_post_ids!=null){
			t_post_id=t_post_ids.toString();
		}
		getPage(session,t_dept_id,t_post_id, t_staff_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_rewards.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String t_staff_id,String rew_sm,String rew_je,String rew_sj, HttpServletRequest request){
			T_rewards t_rewards=new T_rewards();
			t_rewards.setT_staff_id(Integer.parseInt(t_staff_id));
			t_rewards.setRew_sm(rew_sm);
			t_rewards.setRew_je(Float.parseFloat(rew_je));
			t_rewards.setRew_sj(rew_sj);
			if(t_rewardsService.insertT_rewards(t_rewards)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String t_staff_id,String rew_sm,String rew_je,String rew_sj, HttpServletRequest request){
			T_rewards t_rewards=t_rewardsService.getById(id);
			t_rewards.setT_staff_id(Integer.parseInt(t_staff_id));
			t_rewards.setRew_sm(rew_sm);
			t_rewards.setRew_je(Float.parseFloat(rew_je));
			t_rewards.setRew_sj(rew_sj);
			if(t_rewardsService.updateT_rewards(t_rewards)>0){
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
		if(t_rewardsService.deleteT_rewards(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_rewards ad=t_rewardsService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String t_dept_id,String t_post_id,String t_staff_id, String pnum){
		Integer t_staff_ids=null;
		if(t_staff_id!=null&&t_staff_id!=""){
			t_staff_ids=Integer.parseInt(t_staff_id);
		}
		Integer t_post_ids=null;
		if(t_post_id!=null&&t_post_id!=""){
			t_post_ids=Integer.parseInt(t_post_id);
		}
		Integer t_dept_ids=null;
		if(t_dept_id!=null&&t_dept_id!=""){
			t_dept_ids=Integer.parseInt(t_dept_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_rewardsService.getAllCount(t_staff_ids,t_dept_ids,t_post_ids);
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
		List<T_rewards> alist=t_rewardsService.getPage(t_staff_ids,t_dept_ids,t_post_ids, sindex, scount);
		session.setAttribute("alist", alist);
		
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	
	//////////////////////////////////我的奖惩//////////////////////////////////////////////
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		//获取首页数据
		getPage(session,null,null,String.valueOf(tc.getId()), "1");
		request.getRequestDispatcher("/jsp/customer/mt_rewards.jsp").forward(request, response);
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
		getPage(session,null,null,String.valueOf(tc.getId()), pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_rewards.jsp").forward(request, response);
	}
}
