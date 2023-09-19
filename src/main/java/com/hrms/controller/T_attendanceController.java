package com.hrms.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_attendance;
import com.hrms.been.T_customer;
import com.hrms.been.T_dept;
import com.hrms.been.T_staff;
import com.hrms.service.T_attendanceService;
import com.hrms.service.T_deptService;
import com.hrms.service.T_staffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_attendance")
public class T_attendanceController {
	@Autowired
	T_attendanceService t_attendanceService;
	@Autowired
	T_deptService t_deptService;
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_staff_id", null);
		session.setAttribute("t_dept_id", null);
		session.setAttribute("t_post_id", null);
		session.setAttribute("stime", null);
		session.setAttribute("etime", null);
		//获取首页数据
		getPage(session,null,null,null,null,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_attendance.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String t_dept_id,String t_post_id,String t_staff_id,String stime,String etime,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("t_staff_id", t_staff_id);
		session.setAttribute("t_dept_id", t_dept_id);
		session.setAttribute("t_post_id", t_post_id);
		session.setAttribute("stime", stime);
		session.setAttribute("etime", etime);
		//获取首页数据
		getPage(session, t_dept_id,t_post_id,t_staff_id,stime,etime, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_attendance.jsp").forward(request, response);
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
		//获取查询条件
		Object t_dept_ids=session.getAttribute("t_dept_id");
		String t_dept_id=null;
		if(t_dept_ids!=null){
			t_dept_id=t_dept_ids.toString();
		}
		//获取查询条件
		Object t_post_ids=session.getAttribute("t_post_id");
		String t_post_id=null;
		if(t_post_ids!=null){
			t_post_id=t_post_ids.toString();
		}
		//获取查询条件
		Object stimes=session.getAttribute("stime");
		String stime=null;
		if(stimes!=null){
			stime=stimes.toString();
		}
		//获取查询条件
		Object etimes=session.getAttribute("etime");
		String etime=null;
		if(etimes!=null){
			etime=etimes.toString();
		}
		getPage(session,t_dept_id,t_post_id,t_staff_id,stime,etime, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_attendance.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){			
			return "{\"msg\":\"请登录\"}";
		}
		T_staff tc=(T_staff)myinfo;		
		//时间格式对象
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		//当前时间
		Date sdate=new Date();
		T_attendance ta=t_attendanceService.getByStaff(tc.getId(), sdf2.format(sdate));
		if(ta!=null){
			return "{\"msg\":\"你已经打过卡，不要重复操作\"}";
		}else{
			T_attendance t_attendance=new T_attendance();
			t_attendance.setT_staff_id(tc.getId());
			String now=sdf.format(sdate);
			t_attendance.setAtt_stime(now);
			//日历
			Calendar c=Calendar.getInstance();
			Calendar c2=Calendar.getInstance();
			//当天9点
			Date d1=sdf.parse(sdf2.format(new Date())+" 09:00:00");
			c.setTime(sdate);
			c2.setTime(d1);
			//考勤标识
			String sflag="正常";
			if(sdate.getTime()>d1.getTime()){
				sflag="迟到";
			}
			if(c.get(Calendar.HOUR_OF_DAY)-c2.get(Calendar.HOUR_OF_DAY)>=3){
				sflag="旷工";
			}
			t_attendance.setAtt_sflag(sflag);
			if(t_attendanceService.insertT_attendance(t_attendance)>0){
				return "{\"msg\":\"操作成功\"}";
			}else{
				return "{\"msg\":\"操作失败\"}";
			}
		}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){			
			return "{\"msg\":\"请登录\"}";
		}
		T_staff tc=(T_staff)myinfo;		
		//时间格式对象
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		//当前时间
		Date sdate=new Date();
		T_attendance ta=t_attendanceService.getByStaff(tc.getId(), sdf2.format(sdate));
		if(ta!=null){
			
			String now=sdf.format(sdate);
			ta.setAtt_etime(now);
			//日历
			Calendar c=Calendar.getInstance();
			Calendar c2=Calendar.getInstance();
			//当天9点
			Date d1=sdf.parse(sdf2.format(new Date())+" 18:00:00");
			c.setTime(sdate);
			c2.setTime(d1);
			//考勤标识
			String sflag="正常";
			if(sdate.getTime()<d1.getTime()){
				sflag="早退";
			}
			if(c2.get(Calendar.HOUR_OF_DAY)-c.get(Calendar.HOUR_OF_DAY)>=3){
				sflag="旷工";
			}
			ta.setAtt_eflag(sflag);
			
			if(t_attendanceService.updateT_attendance(ta)>0){
				return "{\"msg\":\"操作成功\"}";
			}else{
				return "{\"msg\":\"操作失败\"}";
			}
		}else{
			return "{\"msg\":\"你没有上班打卡记录，算是旷工，打卡失败\"}";
		}
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String del(String ids){
		if(ids.endsWith(",")){
			ids=ids.substring(0, ids.lastIndexOf(","));
		}
		if(t_attendanceService.deleteT_attendance(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_attendance ad=t_attendanceService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String t_dept_id,String t_post_id,String t_staff_id,String stime,String etime, String pnum){
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
		if("".equals(stime)){
			stime=null;
		}
		if("".equals(etime)){
			etime=null;
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_attendanceService.getAllCount(t_staff_ids,t_dept_ids,t_post_ids,stime,etime);
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
		List<T_attendance> alist=t_attendanceService.getPage(t_staff_ids,t_dept_ids,t_post_ids,stime,etime, sindex, scount);
		session.setAttribute("alist", alist);
		List<T_dept> t_deptlist=t_deptService.getAll();
		session.setAttribute("t_deptlist", t_deptlist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
