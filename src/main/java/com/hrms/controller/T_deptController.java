package com.hrms.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hrms.been.T_dept;
import com.hrms.service.T_deptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_dept")
public class T_deptController {
	@Autowired
	T_deptService t_deptService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("dept_bmmc", null);
		//获取首页数据
		getPage(session,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_dept.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String dept_bmmc,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("dept_bmmc", dept_bmmc);
		//获取首页数据
		getPage(session, dept_bmmc, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_dept.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object dept_bmmcs=session.getAttribute("dept_bmmc");
		String dept_bmmc=null;
		if(dept_bmmcs!=null){
			dept_bmmc=dept_bmmcs.toString();
		}
		getPage(session, dept_bmmc, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_dept.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String dept_bmmc,String dept_cjsj, HttpServletRequest request){
		if(t_deptService.addckDept_bmmc(dept_bmmc)){
							return "{\"msg\":\"部门名称已存在\"}";
		}else{
			T_dept t_dept=new T_dept();
			t_dept.setDept_bmmc(dept_bmmc);
			t_dept.setDept_cjsj(dept_cjsj);
			if(t_deptService.insertT_dept(t_dept)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String dept_bmmc,String dept_cjsj, HttpServletRequest request){
		if(t_deptService.updckDept_bmmc(dept_bmmc,id)){
							return "{\"msg\":\"部门名称已存在\"}";
		}else{
			T_dept t_dept=t_deptService.getById(id);
			t_dept.setDept_bmmc(dept_bmmc);
			t_dept.setDept_cjsj(dept_cjsj);
			if(t_deptService.updateT_dept(t_dept)>0){
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
		if(t_deptService.deleteT_dept(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_dept ad=t_deptService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String dept_bmmc, String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_deptService.getAllCount(dept_bmmc);
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
		List<T_dept> alist=t_deptService.getPage(dept_bmmc, sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
