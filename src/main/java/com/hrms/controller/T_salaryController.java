package com.hrms.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrms.been.T_attendance;
import com.hrms.been.T_customer;
import com.hrms.been.T_rewards;
import com.hrms.been.T_salary;
import com.hrms.been.T_staff;
import com.hrms.service.T_attendanceService;
import com.hrms.service.T_rewardsService;
import com.hrms.service.T_salaryService;
import com.hrms.service.T_staffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_salary")
public class T_salaryController {
	@Autowired
	T_salaryService t_salaryService;
	@Autowired
	T_staffService t_staffService;
	@Autowired
	T_attendanceService t_attendanceService;
	@Autowired
	T_rewardsService t_rewardsService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("yf", null);
		session.setAttribute("t_staff_id", null);
		//获取首页数据
		//getPage(session,null, "1");
		session.setAttribute("alist", null);
		session.setAttribute("allnums", 0);
		session.setAttribute("pagenums", 0);
		session.setAttribute("pagenum", 0);
		request.getRequestDispatcher("/jsp/admin/mt_salary.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String yf,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("yf", yf);
		//获取首页数据
		getPage(session, yf,null, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_salary.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object yfs=session.getAttribute("yf");
		String yf=null;
		if(yfs!=null){
			yf=yfs.toString();
		}
		Object t_staff_ids=session.getAttribute("t_staff_id");
		String t_staff_id=null;
		if(t_staff_ids!=null){
			t_staff_id=t_staff_ids.toString();
		}
		getPage(session, yf,t_staff_id, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_salary.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String yf, HttpServletRequest request) throws ParseException{
		float jbgz=0;//基本工资
		float zwjt=0;//职位津贴
		float jcgz=0;//奖惩工资
		float kqkk=0;//考勤扣款
		float yfgz=0;//应发工资
		float sfgz=0;//实发工资
		int nums=0;
		//时间格式对象
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		//月份的第一天
		String sdate=yf+"-01";
		//月份最后一天
		String edate="";
		//日历对象
		Calendar c=Calendar.getInstance();
		c.setTime(sdf2.parse(sdate));
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		edate=sdf.format(c.getTime());		
		//清楚该月份的所有工资信息
		t_salaryService.deleteT_salary(yf);
		//获取所有员工
		List<T_staff> stafflist=t_staffService.getAll();
		Iterator<T_staff> staffit=stafflist.iterator();
		//遍历员工
		while(staffit.hasNext()){
			jbgz=0;//基本工资
			zwjt=0;//职位津贴
			jcgz=0;//奖惩工资
			kqkk=0;//考勤扣款
			yfgz=0;//应发工资
			sfgz=0;//实发工资
			T_staff ts=staffit.next();
			//如果员工就职状态为，非离职
			if(!"离职".equals(ts.getSjzzt())){
				if("实习".equals(ts.getSjzzt())){
					jbgz=ts.getPost_sxgz();
					zwjt=ts.getPost_sxjt();
				}else{
					jbgz=ts.getPost_zsgz();
					zwjt=ts.getPost_zsjt();
				}
				//该员工，打卡信息
				List<T_attendance> talist=t_attendanceService.getPage(ts.getId(), null, null, sdate, sdf2.format(c.getTime()), 0, 32);
				//员工出勤天数
				int days=0;
				//员工迟到早退次数
				int qts=0;				
				//遍历打卡信息
				Iterator<T_attendance> tait=talist.iterator();
				while(tait.hasNext()){
					T_attendance tat=tait.next();
					if("旷工".equals(tat.getAtt_sflag())||"旷工".equals(tat.getAtt_eflag())||"".equals(tat.getAtt_sflag())||"".equals(tat.getAtt_eflag())){
						
					}else{//非旷工情况
						days++;
						if("迟到".equals(tat.getAtt_sflag())){
							qts++;
						}
						if("早退".equals(tat.getAtt_eflag())){
							qts++;
						}
					}					
				}
				//旷工扣款
				if(days<22){
					kqkk+=jbgz/22*(22-days);
				}
				//迟到早退扣款
				kqkk+=qts*10;				
				//获取奖惩信息
				List<T_rewards> trlist=t_rewardsService.getByStaff(ts.getId(), sdate, edate);
				Iterator<T_rewards> trid=trlist.iterator();
				while(trid.hasNext()){
					T_rewards tr=trid.next();
					jcgz+=tr.getRew_je();
				}
				//应发工资
				yfgz=jbgz+zwjt;
				//实发工资
				sfgz=yfgz-kqkk+jcgz;
				T_salary t_salary=new T_salary();
				t_salary.setT_staff_id(ts.getId());
				t_salary.setJbgz(jbgz);
				t_salary.setZwjt(zwjt);
				t_salary.setJcgz(jcgz);
				t_salary.setKqkk(kqkk);
				t_salary.setYfgz(yfgz);
				t_salary.setSfgz(sfgz);
				t_salary.setYf(yf);
				nums+=t_salaryService.insertT_salary(t_salary);
			}
		}
		return "{\"msg\":\"结算完成\"}";
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String t_staff_id,String jbgz,String zwjt,String jcgz,String kqkk,String yfgz,String sfgz,String yf, HttpServletRequest request){
			T_salary t_salary=t_salaryService.getById(id);
			t_salary.setT_staff_id(Integer.parseInt(t_staff_id));
			t_salary.setJbgz(Float.parseFloat(jbgz));
			t_salary.setZwjt(Float.parseFloat(zwjt));
			t_salary.setJcgz(Float.parseFloat(jcgz));
			t_salary.setKqkk(Float.parseFloat(kqkk));
			t_salary.setYfgz(Float.parseFloat(yfgz));
			t_salary.setSfgz(Float.parseFloat(sfgz));
			t_salary.setYf(yf);
			if(t_salaryService.updateT_salary(t_salary)>0){
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
		if(t_salaryService.deleteT_salary(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_salary ad=t_salaryService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String yf,String t_staff_id, String pnum){
		Integer t_staff_ids=null;
		if(t_staff_id!=""&&t_staff_id!=null){
			t_staff_ids=Integer.parseInt(t_staff_id);
		}
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_salaryService.getAllCount(yf,t_staff_ids);
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
		List<T_salary> alist=t_salaryService.getPage(yf,t_staff_ids, sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
	///////////////////////////////员工工资查询/////////////////////////////////////////////////////////////
	@RequestMapping(value = "/cinit", method = RequestMethod.GET)
	@ResponseBody
	public void cinit(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("yf", null);
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		session.setAttribute("t_staff_id", tc.getId());
		//获取首页数据
		//getPage(session,null, "1");
		session.setAttribute("alist", null);
		session.setAttribute("allnums", 0);
		session.setAttribute("pagenums", 0);
		session.setAttribute("pagenum", 0);
		request.getRequestDispatcher("/jsp/customer/mt_salary.jsp").forward(request, response);
	}
	@RequestMapping(value = "/cck", method = RequestMethod.POST)
	@ResponseBody
	public void cck(String yf,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("yf", yf);
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		//获取首页数据
		getPage(session, yf,String.valueOf(tc.getId()), "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_salary.jsp").forward(request, response);
	}
	@RequestMapping(value = "/capage", method = RequestMethod.GET)
	@ResponseBody
	public void capage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object yfs=session.getAttribute("yf");
		String yf=null;
		if(yfs!=null){
			yf=yfs.toString();
		}
		Object myinfo=session.getAttribute("myinfo");
		if(myinfo==null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		T_staff tc=(T_staff)myinfo;
		getPage(session, yf,String.valueOf(tc.getId()), pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/customer/mt_salary.jsp").forward(request, response);
	}
}
