package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;

import com.hrms.dao.T_attendanceDao;
import com.hrms.been.T_attendance;
import com.hrms.service.T_attendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_attendanceServiceImpl implements T_attendanceService {
	@Autowired
	private T_attendanceDao t_attendanceDao;
	@Override
	public T_attendance getById(String id) {
		// TODO Auto-generated method stub
		return t_attendanceDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_attendance(T_attendance t_attendance) {
		// TODO Auto-generated method stub
		return t_attendanceDao.insert(t_attendance);
	}
	@Override
	public int updateT_attendance(T_attendance t_attendance) {
		// TODO Auto-generated method stub
		return t_attendanceDao.update(t_attendance);
	}
	@Override
	public int deleteT_attendance(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_attendance ad=t_attendanceDao.getById(Integer.parseInt(id[i]));
			num=num+t_attendanceDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_attendance> getPage(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,String stime,String etime,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_attendanceDao.getPage(t_staff_id,t_dept_id,t_post_id,stime,etime+" 23:59:59", sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,String stime,String etime) {
		// TODO Auto-generated method stub
		return t_attendanceDao.getAllCount(t_staff_id,t_dept_id,t_post_id,stime,etime+" 23:59:59");
	}
	@Override
	public List<T_attendance> getAll() {
		// TODO Auto-generated method stub
		return t_attendanceDao.getAll();
	}
	@Override
	public T_attendance getByStaff(Integer t_staff_id, String stime) {
		// TODO Auto-generated method stub
		return t_attendanceDao.getByStaff(t_staff_id, stime, stime+" 23:59:59");
	}
}
