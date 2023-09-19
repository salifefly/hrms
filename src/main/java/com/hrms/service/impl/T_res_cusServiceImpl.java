package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;

import com.hrms.dao.T_res_cusDao;
import com.hrms.been.T_res_cus;
import com.hrms.service.T_res_cusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_res_cusServiceImpl implements T_res_cusService {
	@Autowired
	private T_res_cusDao t_res_cusDao;
	@Override
	public T_res_cus getById(String id) {
		// TODO Auto-generated method stub
		return t_res_cusDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_res_cus(T_res_cus t_res_cus) {
		// TODO Auto-generated method stub
		return t_res_cusDao.insert(t_res_cus);
	}
	@Override
	public int updateT_res_cus(T_res_cus t_res_cus) {
		// TODO Auto-generated method stub
		return t_res_cusDao.update(t_res_cus);
	}
	@Override
	public int deleteT_res_cus(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_res_cus ad=t_res_cusDao.getById(Integer.parseInt(id[i]));
			num=num+t_res_cusDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_res_cus> getPage(Integer t_resume_id,Integer t_job_id,Integer t_customer_id,String rc_zt,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_res_cusDao.getPage(t_resume_id,t_job_id,t_customer_id,rc_zt, sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_resume_id,Integer t_job_id,Integer t_customer_id,String rc_zt) {
		// TODO Auto-generated method stub
		return t_res_cusDao.getAllCount(t_resume_id,t_job_id,t_customer_id,rc_zt);
	}
	@Override
	public List<T_res_cus> getAll() {
		// TODO Auto-generated method stub
		return t_res_cusDao.getAll();
	}
}
