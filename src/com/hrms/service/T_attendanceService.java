package com.hrms.service;
import java.util.List;

import com.hrms.been.T_attendance;
public interface T_attendanceService {
	//根据id获取对象
	public T_attendance getById(String id); 
	//添加
	public int insertT_attendance(T_attendance t_attendance);
	//修改
	public int updateT_attendance(T_attendance t_attendance);
	//删除
	public int deleteT_attendance(String ids);
	//分页获取信息链表
	public List<T_attendance> getPage(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,String stime,String etime,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,String stime,String etime);
	//获取所有数据
	public List<T_attendance> getAll();
	//根据员工id和设计获取对象
	public T_attendance getByStaff(Integer t_staff_id,String stime);
}
