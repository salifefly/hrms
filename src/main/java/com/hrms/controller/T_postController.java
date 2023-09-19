package com.hrms.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_dept;
import com.hrms.been.T_post;
import com.hrms.service.T_deptService;
import com.hrms.service.T_postService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_post")
public class T_postController {
	@Autowired
	T_postService t_postService;
	@Autowired
	T_deptService t_deptService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("post_gwmc", null);
		session.setAttribute("t_dept_id", null);
		//获取首页数据
		//getPage(session,null,null, "0");
		session.setAttribute("alist", null);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		session.setAttribute("allnums", 0);
		session.setAttribute("pagenums", 0);
		session.setAttribute("pagenum", 0);
		request.getRequestDispatcher("/jsp/admin/mt_post.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String post_gwmc,String t_dept_id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("post_gwmc", post_gwmc);
		session.setAttribute("t_dept_id", t_dept_id);
		//获取首页数据
		getPage(session, post_gwmc,t_dept_id, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_post.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{		
		//获取查询条件
		Object post_gwmcs=session.getAttribute("post_gwmc");
		String post_gwmc=null;
		if(post_gwmcs!=null){
			post_gwmc=post_gwmcs.toString();
		}
		//获取查询条件
		Object t_dept_ids=session.getAttribute("t_dept_id");
		String t_dept_id=null;
		if(t_dept_ids!=null){
			t_dept_id=t_dept_ids.toString();
		}
		getPage(session, post_gwmc,t_dept_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_post.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String post_gwmc,String t_dept_id,String post_sxgz,String post_zsgz,String post_sxjt,String post_zsjt, HttpServletRequest request){
		if(t_postService.addckPost_gwmc(post_gwmc,t_dept_id)){
			return "{\"msg\":\"岗位名称已存在\"}";
		}else{
			T_post t_post=new T_post();
			t_post.setPost_gwmc(post_gwmc);
			t_post.setT_dept_id(Integer.parseInt(t_dept_id));
			t_post.setPost_sxgz(Float.parseFloat(post_sxgz));
			t_post.setPost_zsgz(Float.parseFloat(post_zsgz));
			t_post.setPost_sxjt(Float.parseFloat(post_sxjt));
			t_post.setPost_zsjt(Float.parseFloat(post_zsjt));
			if(t_postService.insertT_post(t_post)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
		}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String post_gwmc,String t_dept_id,String post_sxgz,String post_zsgz,String post_sxjt,String post_zsjt, HttpServletRequest request){
		if(t_postService.updckPost_gwmc(post_gwmc,t_dept_id,id)){
			return "{\"msg\":\"岗位名称已存在\"}";
		}else{
			T_post t_post=t_postService.getById(id);
			t_post.setPost_gwmc(post_gwmc);
			t_post.setT_dept_id(Integer.parseInt(t_dept_id));
			t_post.setPost_sxgz(Float.parseFloat(post_sxgz));
			t_post.setPost_zsgz(Float.parseFloat(post_zsgz));
			t_post.setPost_sxjt(Float.parseFloat(post_sxjt));
			t_post.setPost_zsjt(Float.parseFloat(post_zsjt));
			if(t_postService.updateT_post(t_post)>0){
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
		if(t_postService.deleteT_post(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_post ad=t_postService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	@RequestMapping(value = "/getdatas", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdatas(String t_dept_id){
		List<T_post> ad=t_postService.getListByDept(t_dept_id);
		JSONObject json=new JSONObject();
		json.put("plist", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String post_gwmc,String t_dept_id, String pnum){
		Integer t_dept_ids=null;
		if(t_dept_id!=null&&t_dept_id!=""){
			t_dept_ids=Integer.parseInt(t_dept_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_postService.getAllCount(post_gwmc,t_dept_ids);
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
		List<T_post> alist=t_postService.getPage(post_gwmc,t_dept_ids, sindex, scount);
		session.setAttribute("alist", alist);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
