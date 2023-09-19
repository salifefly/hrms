package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_customer;
import com.hrms.been.T_dept;
import com.hrms.been.T_post;
import com.hrms.been.T_staff;
import com.hrms.service.T_deptService;
import com.hrms.service.T_postService;
import com.hrms.service.T_staffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_staff")
public class T_staffController {
	@Autowired
	T_staffService t_staffService;
	@Autowired
	T_deptService t_deptService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("sxm", null);
		session.setAttribute("t_dept_id", null);
		session.setAttribute("t_post_id", null);
		//获取首页数据
		getPage(session,null,null,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_staff.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String sxm,String t_post_id,String t_dept_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("sxm", sxm);
		session.setAttribute("t_post_id", t_post_id);
		session.setAttribute("t_dept_id", t_dept_id);
		//获取首页数据
		getPage(session, sxm,t_post_id,t_dept_id, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_staff.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object sxms=session.getAttribute("sxm");
		String sxm=null;
		if(sxms!=null){
			sxm=sxms.toString();
		}
		//获取查询条件
		Object t_post_ids=session.getAttribute("t_post_id");
		String t_post_id=null;
		if(t_post_ids!=null){
			t_post_id=t_post_ids.toString();
		}
		//获取查询条件
		Object t_dept_ids=session.getAttribute("t_dept_id");
		String t_dept_id=null;
		if(t_dept_ids!=null){
			t_dept_id=t_dept_ids.toString();
		}
		getPage(session, sxm,t_post_id,t_dept_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_staff.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String szh,String spwd,String sxm,String sxb,String scsrq,String smz,String szzmm,String sxl,String sjg,String syx,String slxdh,String szy,String sbyyx,String swysp,String sjsjdj,String sjzzt,String sygzt,String t_post_id,String sintime, HttpServletRequest request){
		if(t_staffService.addckSzh(szh)){
			return "{\"msg\":\"账号已存在\"}";
		}else{
			T_staff t_staff=new T_staff();
			t_staff.setSzh(szh);
			t_staff.setSpwd(spwd);
			t_staff.setSxm(sxm);
			t_staff.setSxb(sxb);
			t_staff.setScsrq(scsrq);
			t_staff.setSmz(smz);
			t_staff.setSzzmm(szzmm);
			t_staff.setSxl(sxl);
			t_staff.setSjg(sjg);
			t_staff.setSyx(syx);
			t_staff.setSlxdh(slxdh);
			t_staff.setSzy(szy);
			t_staff.setSbyyx(sbyyx);
			t_staff.setSwysp(swysp);
			t_staff.setSjsjdj(sjsjdj);
			t_staff.setSjzzt(sjzzt);
			t_staff.setSygzt(sygzt);
			t_staff.setT_post_id(Integer.parseInt(t_post_id));
			t_staff.setSintime(sintime);
			if(t_staffService.insertT_staff(t_staff)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String szh,String spwd,String sxm,String sxb,String scsrq,String smz,String szzmm,String sxl,String sjg,String syx,String slxdh,String szy,String sbyyx,String swysp,String sjsjdj,String sjzzt,String sygzt,String t_post_id, HttpServletRequest request){
		if(t_staffService.updckSzh(szh,id)){
							return "{\"msg\":\"账号已存在\"}";
		}else{
			T_staff t_staff=t_staffService.getById(id);
			t_staff.setSzh(szh);
			t_staff.setSpwd(spwd);
			t_staff.setSxm(sxm);
			t_staff.setSxb(sxb);
			t_staff.setScsrq(scsrq);
			t_staff.setSmz(smz);
			t_staff.setSzzmm(szzmm);
			t_staff.setSxl(sxl);
			t_staff.setSjg(sjg);
			t_staff.setSyx(syx);
			t_staff.setSlxdh(slxdh);
			t_staff.setSzy(szy);
			t_staff.setSbyyx(sbyyx);
			t_staff.setSwysp(swysp);
			t_staff.setSjsjdj(sjsjdj);
			t_staff.setSjzzt(sjzzt);
			t_staff.setSygzt(sygzt);
			t_staff.setT_post_id(Integer.parseInt(t_post_id));
			if(t_staffService.updateT_staff(t_staff)>0){
				return "{\"msg\":\"修改成功\"}";
			}else{
				return "{\"msg\":\"修改失败\"}";
			}
			}
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String del(String ids){
		if(ids.endsWith(",")){
			ids=ids.substring(0, ids.lastIndexOf(","));
		}
		if(t_staffService.deleteT_staff(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_staff ad=t_staffService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	@RequestMapping(value = "/getdatas", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdatas(String t_post_id){
		List<T_staff> ad=t_staffService.getListByPost(Integer.parseInt(t_post_id));
		JSONObject json=new JSONObject();
		json.put("plist", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String sxm,String t_post_id,String t_dept_id, String pnum){
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
		int allcount=t_staffService.getAllCount(sxm,t_post_ids,t_dept_ids);
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
		List<T_staff> alist=t_staffService.getPage(sxm,t_post_ids,t_dept_ids, sindex, scount);
		session.setAttribute("alist", alist);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	/////////////////////////////////////通讯录/////////////////////////////////////////
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("sxm", null);
		session.setAttribute("t_dept_id", null);
		session.setAttribute("t_post_id", null);
		//获取首页数据
		cgetPage(session,null,null,null, "1");
		request.getRequestDispatcher("/jsp/customer/mt_staff.jsp").forward(request, response);
	}
	@RequestMapping(value = "/cck", method = RequestMethod.POST)
	@ResponseBody
	public void cck(String sxm,String t_post_id,String t_dept_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("sxm", sxm);
		session.setAttribute("t_post_id", t_post_id);
		session.setAttribute("t_dept_id", t_dept_id);
		//获取首页数据
		cgetPage(session, sxm,t_post_id,t_dept_id, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_staff.jsp").forward(request, response);
	}
	@RequestMapping(value = "/capage", method = RequestMethod.GET)
	@ResponseBody
	public void capage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object sxms=session.getAttribute("sxm");
		String sxm=null;
		if(sxms!=null){
			sxm=sxms.toString();
		}
		//获取查询条件
		Object t_post_ids=session.getAttribute("t_post_id");
		String t_post_id=null;
		if(t_post_ids!=null){
			t_post_id=t_post_ids.toString();
		}
		//获取查询条件
		Object t_dept_ids=session.getAttribute("t_dept_id");
		String t_dept_id=null;
		if(t_dept_ids!=null){
			t_dept_id=t_dept_ids.toString();
		}
		cgetPage(session, sxm,t_post_id,t_dept_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_staff.jsp").forward(request, response);
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void cgetPage(HttpSession session,String sxm,String t_post_id,String t_dept_id, String pnum){
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
		int allcount=t_staffService.getAllCount(sxm,t_post_ids,t_dept_ids);
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
		List<T_staff> alist=t_staffService.getPage(sxm,t_post_ids,t_dept_ids, sindex, scount);
		session.setAttribute("alist", alist);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	///////////////////////////////修改密码/////////////////////////////////
	@RequestMapping(value = "/updpwd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updpwd(String opwd,String npwd,HttpSession session, HttpServletRequest request){
		//把查询信息放入session
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			return "{\"msg\":\"请重新登录\"}";
		}
		T_staff tc=(T_staff)myinfo;
		tc=t_staffService.getById(String.valueOf(tc.getId()));
		if(!opwd.equals(tc.getSpwd())){
			return "{\"msg\":\"旧密码输入有误\"}";
		}else{
			tc.setSpwd(npwd);
			if(t_staffService.updateT_staff(tc)>0){
				return "{\"msg\":\"修改成功\"}";
			}else{
				return "{\"msg\":\"修改失败\"}";
			}
		}
	}
}
