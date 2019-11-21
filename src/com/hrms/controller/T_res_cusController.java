package com.hrms.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_customer;
import com.hrms.been.T_dept;
import com.hrms.been.T_job;
import com.hrms.been.T_res_cus;
import com.hrms.been.T_resume;
import com.hrms.been.T_staff;
import com.hrms.service.T_deptService;
import com.hrms.service.T_jobService;
import com.hrms.service.T_res_cusService;
import com.hrms.service.T_resumeService;
import com.hrms.service.T_staffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_res_cus")
public class T_res_cusController {
	@Autowired
	T_res_cusService t_res_cusService;
	@Autowired
	T_resumeService t_resumeService;
	@Autowired
	T_jobService t_jobService;
	@Autowired
	T_deptService t_deptService;
	@Autowired
	T_staffService t_staffService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(String t_job_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_resume_id", null);
		session.setAttribute("t_job_id", t_job_id);
		//获取首页数据
		getPage(session,null,t_job_id, "1");
		request.getRequestDispatcher("/jsp/admin/mt_res_cus.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object t_job_ids=session.getAttribute("t_job_id");
		String t_job_id=null;
		if(t_job_ids!=null){
			t_job_id=t_job_ids.toString();
		}
		getPage(session, null,t_job_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_res_cus.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String t_resume_id,String t_job_id, HttpServletRequest request){
		if(t_res_cusService.getAllCount(Integer.parseInt(t_resume_id), Integer.parseInt(t_job_id),null,null)>0){
			return "{\"msg\":\"该职务，你已投递了该简历，不要重复投递\"}";
		}
		T_res_cus t_res_cus=new T_res_cus();
		t_res_cus.setT_resume_id(Integer.parseInt(t_resume_id));
		t_res_cus.setT_job_id(Integer.parseInt(t_job_id));
		t_res_cus.setRc_zt("已投递");
		if(t_res_cusService.insertT_res_cus(t_res_cus)>0){
			return "{\"msg\":\"申请成功\"}";
		}else{
			return "{\"msg\":\"申请失败\"}";
		}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String rc_zt,String rc_mssm,String rc_mssj, HttpServletRequest request){
			T_res_cus t_res_cus=t_res_cusService.getById(id);
			t_res_cus.setRc_zt(rc_zt);
			t_res_cus.setRc_mssm(rc_mssm);
			t_res_cus.setRc_mssj(rc_mssj);
			if(t_res_cusService.updateT_res_cus(t_res_cus)>0){				
				return "{\"msg\":\"操作成功\"}";
			}else{
				return "{\"msg\":\"操作失败\"}";
			}
	}
	@RequestMapping(value = "/ly", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String ly(String id,String t_dept_id,String t_post_id,String sjzzt,String sygzt, HttpServletRequest request){
			T_res_cus t_res_cus=t_res_cusService.getById(id);
			t_res_cus.setRc_zt("录用");
			if(t_res_cusService.updateT_res_cus(t_res_cus)>0){				
				if(t_staffService.addckSzh(t_res_cus.getRes_lxdh())){
					return "{\"msg\":\"电话已存在\"}";
				}
				T_staff t_staff=new T_staff();
				t_staff.setSzh(t_res_cus.getRes_lxdh());
				t_staff.setSpwd("0");
				t_staff.setSxm(t_res_cus.getRes_xm());
				t_staff.setSxb(t_res_cus.getRes_xb());
				t_staff.setScsrq(t_res_cus.getRes_csrq());
				t_staff.setSmz(t_res_cus.getRes_mz());
				t_staff.setSzzmm(t_res_cus.getRes_zzmm());
				t_staff.setSxl(t_res_cus.getRes_xl());
				t_staff.setSjg(t_res_cus.getRes_jg());
				t_staff.setSyx(t_res_cus.getRes_yx());
				t_staff.setSlxdh(t_res_cus.getRes_lxdh());
				t_staff.setSzy(t_res_cus.getRes_zy());
				t_staff.setSbyyx(t_res_cus.getRes_byyx());
				t_staff.setSwysp(t_res_cus.getRes_wysp());
				t_staff.setSjsjdj(t_res_cus.getRes_jsjdj());
				t_staff.setSjzzt(sjzzt);
				t_staff.setSygzt(sygzt);
				t_staff.setT_post_id(Integer.parseInt(t_post_id));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				t_staff.setSintime(sdf.format(new Date()));
				if(t_staffService.insertT_staff(t_staff)>0){
					return "{\"msg\":\"操作成功\"}";
				}else{
					return "{\"msg\":\"添加员工信息失败\"}";
				}				
			}else{
				return "{\"msg\":\"操作失败\"}";
			}
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String del(String ids){
		if(ids.endsWith(",")){
			ids=ids.substring(0, ids.lastIndexOf(","));
		}
		if(t_res_cusService.deleteT_res_cus(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_res_cus ad=t_res_cusService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String t_resume_id,String t_job_id, String pnum){
		Integer t_resume_ids=null;
		if(t_resume_id!=null&&t_resume_id!=""){
			t_resume_ids=Integer.parseInt(t_resume_id);
		}
		Integer t_job_ids=null;
		if(t_job_id!=null&&t_job_id!=""){
			t_job_ids=Integer.parseInt(t_job_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_res_cusService.getAllCount(t_resume_ids,t_job_ids,null,null);
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
		List<T_res_cus> alist=t_res_cusService.getPage(t_resume_ids,t_job_ids,null,null, sindex, scount);
		session.setAttribute("alist", alist);
		
		List<T_dept> deptlist=t_deptService.getAll();
		session.setAttribute("deptlist", deptlist);
		
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	/////////////////////////////////游客查询面试和录用//////////////////////////////////////////
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(String t_job_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){	
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;
		//获取首页数据
		cgetPage(session,String.valueOf(tc.getId()),"面试", "1");
		request.getRequestDispatcher("/jsp/customer/mt_res_cus_ms.jsp").forward(request, response);
	}
	@RequestMapping(value = "/capage", method = RequestMethod.GET)
	@ResponseBody
	public void capage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){			
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;		
		getPage(session, String.valueOf(tc.getId()),"面试", pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_res_cus_ms.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ccinit", method = RequestMethod.GET)
	@ResponseBody
	public void ccinit(String t_job_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){			
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;
		//获取首页数据
		cgetPage(session,String.valueOf(tc.getId()),"录用", "1");
		request.getRequestDispatcher("/jsp/customer/mt_res_cus_ly.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ccapage", method = RequestMethod.GET)
	@ResponseBody
	public void ccapage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){			
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;		
		getPage(session, String.valueOf(tc.getId()),"录用", pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_res_cus_ly.jsp").forward(request, response);
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void cgetPage(HttpSession session,String t_customer_id,String rc_zt, String pnum){
		Integer t_customer_ids=null;
		if(t_customer_id!=null&&t_customer_id!=""){
			t_customer_ids=Integer.parseInt(t_customer_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_res_cusService.getAllCount(null,null,t_customer_ids,rc_zt);
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
		List<T_res_cus> alist=t_res_cusService.getPage(null,null,t_customer_ids,rc_zt, sindex, scount);
		session.setAttribute("alist", alist);
		
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
