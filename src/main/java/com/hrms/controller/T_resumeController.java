package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_customer;
import com.hrms.been.T_resume;
import com.hrms.service.T_customerService;
import com.hrms.service.T_resumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_resume")
public class T_resumeController {
	@Autowired
	T_resumeService t_resumeService;
	@Autowired
	T_customerService t_customerService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;
		session.setAttribute("t_customer_id", tc.getId());
		session.setAttribute("res_jlmc", null);
		//获取首页数据
		getPage(session,tc.getId(),null, "1");
		request.getRequestDispatcher("/jsp/customer/mt_resume.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String res_jlmc,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;
		session.setAttribute("t_customer_id", tc.getId());
		session.setAttribute("res_jlmc", res_jlmc);		
		//获取首页数据
		getPage(session, tc.getId(),res_jlmc, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_resume.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_customer tc=(T_customer)myinfo;
		Object res_jlmcs=session.getAttribute("res_jlmc");
		String res_jlmc=null;
		if(res_jlmcs!=null){
			res_jlmc=res_jlmcs.toString();
		}
		getPage(session,tc.getId(),res_jlmc, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_resume.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String res_jlmc,String res_xm,String res_xb,String res_csrq,String res_mz,String res_zzmm,String res_xl,String res_jg,String res_yx,String res_lxdh,String res_zy,String res_byyx,String res_wysp,String res_jsjdj,String res_gzjl,String res_zwpj,HttpSession session, HttpServletRequest request){
		//获取查询条件
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			return "{\"msg\":\"请重新登录\"}";
		}
		T_customer tc=(T_customer)myinfo;
		T_resume t_resume=new T_resume();
		t_resume.setRes_jlmc(res_jlmc);
		t_resume.setRes_xm(res_xm);
		t_resume.setRes_xb(res_xb);
		t_resume.setRes_csrq(res_csrq);
		t_resume.setRes_mz(res_mz);
		t_resume.setRes_zzmm(res_zzmm);
		t_resume.setRes_xl(res_xl);
		t_resume.setRes_jg(res_jg);
		t_resume.setRes_yx(res_yx);
		t_resume.setRes_lxdh(res_lxdh);
		t_resume.setRes_zy(res_zy);
		t_resume.setRes_byyx(res_byyx);
		t_resume.setRes_wysp(res_wysp);
		t_resume.setRes_jsjdj(res_jsjdj);
		t_resume.setRes_gzjl(res_gzjl);
		t_resume.setRes_zwpj(res_zwpj);
		t_resume.setT_customer_id(tc.getId());
		if(t_resumeService.insertT_resume(t_resume)>0){
			return "{\"msg\":\"添加成功\"}";
		}else{
			return "{\"msg\":\"添加失败\"}";
		}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String res_jlmc,String res_xm,String res_xb,String res_csrq,String res_mz,String res_zzmm,String res_xl,String res_jg,String res_yx,String res_lxdh,String res_zy,String res_byyx,String res_wysp,String res_jsjdj,String res_gzjl,String res_zwpj,HttpSession session, HttpServletRequest request){
		//获取查询条件
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			return "{\"msg\":\"请重新登录\"}";
		}
		T_customer tc=(T_customer)myinfo;
		T_resume t_resume=t_resumeService.getById(id);
		t_resume.setRes_jlmc(res_jlmc);
		t_resume.setRes_xm(res_xm);
		t_resume.setRes_xb(res_xb);
		t_resume.setRes_csrq(res_csrq);
		t_resume.setRes_mz(res_mz);
		t_resume.setRes_zzmm(res_zzmm);
		t_resume.setRes_xl(res_xl);
		t_resume.setRes_jg(res_jg);
		t_resume.setRes_yx(res_yx);
		t_resume.setRes_lxdh(res_lxdh);
		t_resume.setRes_zy(res_zy);
		t_resume.setRes_byyx(res_byyx);
		t_resume.setRes_wysp(res_wysp);
		t_resume.setRes_jsjdj(res_jsjdj);
		t_resume.setRes_gzjl(res_gzjl);
		t_resume.setRes_zwpj(res_zwpj);
		t_resume.setT_customer_id(tc.getId());
		if(t_resumeService.updateT_resume(t_resume)>0){
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
		if(t_resumeService.deleteT_resume(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_resume ad=t_resumeService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,Integer t_customer_id,String res_jlmc, String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_resumeService.getAllCount(t_customer_id,res_jlmc);
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
		List<T_resume> alist=t_resumeService.getPage(t_customer_id,res_jlmc, sindex, scount);
		session.setAttribute("alist", alist);
//		List<T_customer> t_customerlist=t_customerService.getAll();
//		session.setAttribute("t_customerlist", t_customerlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
