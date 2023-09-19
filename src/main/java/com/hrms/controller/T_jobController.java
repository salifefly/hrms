package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_customer;
import com.hrms.been.T_job;
import com.hrms.been.T_resume;
import com.hrms.service.T_jobService;
import com.hrms.service.T_resumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_job")
public class T_jobController {
	@Autowired
	T_jobService t_jobService;
	@Autowired
	T_resumeService t_resumeService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("job_zw", null);
		//获取首页数据
		getPage(session, null,"1");
		request.getRequestDispatcher("/jsp/admin/mt_job.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String job_zw,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("job_zw", job_zw);
		//获取首页数据
		getPage(session, job_zw, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_job.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object job_zws=session.getAttribute("job_zw");
		String job_zw=null;
		if(job_zws!=null){
			job_zw=job_zws.toString();
		}
		getPage(session, job_zw, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_job.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String job_zw,String job_gs,String job_dq,String job_xz,String job_rzyq,String job_zwxx,String job_lxwm, HttpServletRequest request){
			T_job t_job=new T_job();
			t_job.setJob_zw(job_zw);
			t_job.setJob_gs(job_gs);
			t_job.setJob_dq(job_dq);
			t_job.setJob_xz(job_xz);
			t_job.setJob_rzyq(job_rzyq);
			t_job.setJob_zwxx(job_zwxx);
			t_job.setJob_lxwm(job_lxwm);
			if(t_jobService.insertT_job(t_job)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String job_zw,String job_gs,String job_dq,String job_xz,String job_rzyq,String job_zwxx,String job_lxwm, HttpServletRequest request){
			T_job t_job=t_jobService.getById(id);
			t_job.setJob_zw(job_zw);
			t_job.setJob_gs(job_gs);
			t_job.setJob_dq(job_dq);
			t_job.setJob_xz(job_xz);
			t_job.setJob_rzyq(job_rzyq);
			t_job.setJob_zwxx(job_zwxx);
			t_job.setJob_lxwm(job_lxwm);
			if(t_jobService.updateT_job(t_job)>0){
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
		if(t_jobService.deleteT_job(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_job ad=t_jobService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session, String job_zw,String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_jobService.getAllCount(job_zw);
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
		List<T_job> alist=t_jobService.getPage(job_zw,sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	
	/////////////////////////////////游客查看招聘信息//////////////////////////////////////////////////
	
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("job_zw", null);
		//获取首页数据
		cgetPage(session, null,"1");
		request.getRequestDispatcher("/jsp/customer/mt_job.jsp").forward(request, response);
	}
	@RequestMapping(value = "/cck", method = RequestMethod.POST)
	@ResponseBody
	public void cck(String job_zw,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("job_zw", job_zw);
		//获取首页数据
		cgetPage(session, job_zw, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_job.jsp").forward(request, response);
	}
	@RequestMapping(value = "/capage", method = RequestMethod.GET)
	@ResponseBody
	public void capage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object job_zws=session.getAttribute("job_zw");
		String job_zw=null;
		if(job_zws!=null){
			job_zw=job_zws.toString();
		}
		cgetPage(session, job_zw, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_job.jsp").forward(request, response);
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void cgetPage(HttpSession session, String job_zw,String pnum){
		//每页显示的条数
		int scount=3;
		//总条数
		int allcount=t_jobService.getAllCount(job_zw);
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
		int sindex=(pnums-1)*scount;
		List<T_job> alist=t_jobService.getPage(job_zw,sindex, scount);
		session.setAttribute("alist", alist);
		
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo!=null){
			T_customer tc=(T_customer)myinfo;
			List<T_resume> trlist=t_resumeService.getAll(tc.getId());
			session.setAttribute("trlist", trlist);
		}
		
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
