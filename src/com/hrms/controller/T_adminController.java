package com.hrms.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hrms.been.T_admin;
import com.hrms.service.T_adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_admin")
public class T_adminController {
	@Autowired
	T_adminService t_adminService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("uname", null);
		//获取首页数据
		getPage(session,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_admin.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String uname,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("uname", uname);
		//获取首页数据
		getPage(session, uname, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_admin.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object unames=session.getAttribute("uname");
		String uname=null;
		if(unames!=null){
			uname=unames.toString();
		}
		getPage(session, uname, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_admin.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String uname,String pwd, HttpServletRequest request){
		if(t_adminService.addckUname(uname)){
							return "{\"msg\":\"账号已存在\"}";
		}else{
			T_admin t_admin=new T_admin();
			t_admin.setUname(uname);
			t_admin.setPwd(pwd);
			if(t_adminService.insertT_admin(t_admin)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String uname,String pwd, HttpServletRequest request){
		if(t_adminService.updckUname(uname,id)){
							return "{\"msg\":\"账号已存在\"}";
		}else{
			T_admin t_admin=t_adminService.getById(id);
			t_admin.setUname(uname);
			t_admin.setPwd(pwd);
			if(t_adminService.updateT_admin(t_admin)>0){
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
		if(t_adminService.deleteT_admin(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_admin ad=t_adminService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String uname, String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_adminService.getAllCount(uname);
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
		List<T_admin> alist=t_adminService.getPage(uname, sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
