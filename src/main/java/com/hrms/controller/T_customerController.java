package com.hrms.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hrms.been.T_customer;
import com.hrms.service.T_customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_customer")
public class T_customerController {
	@Autowired
	T_customerService t_customerService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("cus_no", null);
		//获取首页数据
		getPage(session,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_customer.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String cus_no,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("cus_no", cus_no);
		//获取首页数据
		getPage(session, cus_no, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_customer.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object cus_nos=session.getAttribute("cus_no");
		String cus_no=null;
		if(cus_nos!=null){
			cus_no=cus_nos.toString();
		}
		getPage(session, cus_no, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_customer.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String cus_no,String cus_pwd, HttpServletRequest request){
		if(t_customerService.addckCus_no(cus_no)){
							return "{\"msg\":\"账号已存在\"}";
		}else{
			T_customer t_customer=new T_customer();
			t_customer.setCus_no(cus_no);
			t_customer.setCus_pwd(cus_pwd);
			if(t_customerService.insertT_customer(t_customer)>0){
				return "{\"msg\":\"注册成功\"}";
			}else{
				return "{\"msg\":\"注册失败\"}";
			}
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String cus_no,String cus_pwd, HttpServletRequest request){
		if(t_customerService.updckCus_no(cus_no,id)){
							return "{\"msg\":\"账号已存在\"}";
		}else{
			T_customer t_customer=t_customerService.getById(id);
			t_customer.setCus_no(cus_no);
			t_customer.setCus_pwd(cus_pwd);
			if(t_customerService.updateT_customer(t_customer)>0){
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
		if(t_customerService.deleteT_customer(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_customer ad=t_customerService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String cus_no, String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_customerService.getAllCount(cus_no);
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
		List<T_customer> alist=t_customerService.getPage(cus_no, sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
