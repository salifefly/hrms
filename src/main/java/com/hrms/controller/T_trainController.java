package com.hrms.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hrms.been.T_train;
import com.hrms.service.T_trainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value = "/t_train")
public class T_trainController {
	@Autowired
	T_trainService t_trainService;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void init(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("train_pxzt", null);
		//获取首页数据
		getPage(session,null, "1");
		request.getRequestDispatcher("/jsp/admin/mt_train.jsp").forward(request, response);
	}
	@RequestMapping(value = "/ck", method = RequestMethod.POST)
	@ResponseBody
	public void ck(String train_pxzt,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//把查询信息放入session
		session.setAttribute("train_pxzt", train_pxzt);
		//获取首页数据
		getPage(session, train_pxzt, "1");
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_train.jsp").forward(request, response);
	}
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	@ResponseBody
	public void apage(String pnum,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取查询条件
		Object train_pxzts=session.getAttribute("train_pxzt");
		String train_pxzt=null;
		if(train_pxzts!=null){
			train_pxzt=train_pxzts.toString();
		}
		getPage(session, train_pxzt, pnum);
		//跳转到信息管理界面
		request.getRequestDispatcher("/jsp/admin/mt_train.jsp").forward(request, response);
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String add(String train_pxzt,String train_pxnr,String train_pxdd,String train_pxsj, HttpServletRequest request){
		if(t_trainService.addckTrain_pxzt(train_pxzt)){
							return "{\"msg\":\"培训主题已存在\"}";
		}else{
			T_train t_train=new T_train();
			t_train.setTrain_pxzt(train_pxzt);
			t_train.setTrain_pxnr(train_pxnr);
			t_train.setTrain_pxdd(train_pxdd);
			t_train.setTrain_pxsj(train_pxsj);
			if(t_trainService.insertT_train(t_train)>0){
				return "{\"msg\":\"添加成功\"}";
			}else{
				return "{\"msg\":\"添加失败\"}";
			}
			}
	}
	@RequestMapping(value = "/upd", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String upd(String id,String train_pxzt,String train_pxnr,String train_pxdd,String train_pxsj, HttpServletRequest request){
		if(t_trainService.updckTrain_pxzt(train_pxzt,id)){
							return "{\"msg\":\"培训主题已存在\"}";
		}else{
			T_train t_train=t_trainService.getById(id);
			t_train.setTrain_pxzt(train_pxzt);
			t_train.setTrain_pxnr(train_pxnr);
			t_train.setTrain_pxdd(train_pxdd);
			t_train.setTrain_pxsj(train_pxsj);
			if(t_trainService.updateT_train(t_train)>0){
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
		if(t_trainService.deleteT_train(ids)>0){
			return "{\"msg\":\"删除成功\"}";
		}else{
			return "{\"msg\":\"删除失败\"}";
		}
	}
	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getdata(String id){
		T_train ad=t_trainService.getById(id);
		JSONObject json=new JSONObject();
		json.put("ob", ad);
		return json.toString();
	}
	/**
	 * @param request
	 * @param 条件
	 * @param pnum 要显示的页码
	 */
	public void getPage(HttpSession session,String train_pxzt, String pnum){
		//每页显示的条数
		int scount=5;
		//总条数
		int allcount=t_trainService.getAllCount(train_pxzt);
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
		List<T_train> alist=t_trainService.getPage(train_pxzt, sindex, scount);
		session.setAttribute("alist", alist);
		session.setAttribute("allnums", allcount);
		session.setAttribute("pagenums", apnum);
		session.setAttribute("pagenum", pnums);
	}
}
