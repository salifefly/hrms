package com.hrms.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_reconsider;
import com.hrms.been.T_salary;
import com.hrms.service.T_reconsiderService;
import com.hrms.service.T_salaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_reconsider")
public class T_reconsiderController {
	@Autowired
	T_reconsiderService t_reconsiderService;
	@Autowired
	T_salaryService t_salaryService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_salary_id", null);
		//获取首页数据
		getPage(session,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_reconsider.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		//session.setAttribute("t_salary_id", t_salary_id);
		//获取首页数据
		getPage(session, null, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_reconsider.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
//		Object t_salary_ids=session.getAttribute("t_salary_id");
//		String t_salary_id=null;
//		if(t_salary_ids!=null){
//			t_salary_id=t_salary_ids.toString();
//		}
		getPage(session, null, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_reconsider.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String t_salary_id,String rec_mark, HttpServletRequest request){
		T_reconsider t_reconsider=new T_reconsider();
		t_reconsider.setT_salary_id(Integer.parseInt(t_salary_id));
		t_reconsider.setRec_mark(rec_mark);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t_reconsider.setRec_time(sdf.format(new Date()));
		if(t_reconsiderService.insertT_reconsider(t_reconsider)>0){
			return "{\"msg\":\"提交成功\"}";
		}else{
			return "{\"msg\":\"提交失败\"}";
		}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String t_salary_id,String rec_mark,String rec_time, HttpServletRequest request){
			T_reconsider t_reconsider=t_reconsiderService.getById(id);
			t_reconsider.setT_salary_id(Integer.parseInt(t_salary_id));
			t_reconsider.setRec_mark(rec_mark);
			t_reconsider.setRec_time(rec_time);
			if(t_reconsiderService.updateT_reconsider(t_reconsider)>0){
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
		if(t_reconsiderService.deleteT_reconsider(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_reconsider ad=t_reconsiderService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String t_salary_id, String pnum){
		Integer t_salary_ids=null;
		if(t_salary_id!=null&&t_salary_id!=""){
			t_salary_ids=Integer.parseInt(t_salary_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_reconsiderService.getAllCount(t_salary_ids);
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
		List<T_reconsider> alist=t_reconsiderService.getPage(t_salary_ids, sindex, scount);
		session.setAttribute("alist", alist);
//		List<T_salary> t_salarylist=t_salaryService.getAll();
//		session.setAttribute("t_salarylist", t_salarylist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
