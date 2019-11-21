package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;

import com.hrms.dao.T_resumeDao;
import com.hrms.been.T_resume;
import com.hrms.service.T_resumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_resumeServiceImpl implements T_resumeService {
	@Autowired
	private T_resumeDao t_resumeDao;
	@Override
	public T_resume getById(String id) {
		// TODO Auto-generated method stub
		return t_resumeDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_resume(T_resume t_resume) {
		// TODO Auto-generated method stub
		return t_resumeDao.insert(t_resume);
	}
	@Override
	public int updateT_resume(T_resume t_resume) {
		// TODO Auto-generated method stub
		return t_resumeDao.update(t_resume);
	}
	@Override
	public int deleteT_resume(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_resume ad=t_resumeDao.getById(Integer.parseInt(id[i]));
			num=num+t_resumeDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_resume> getPage(Integer t_customer_id,String res_jlmc,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_resumeDao.getPage(t_customer_id,res_jlmc, sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_customer_id,String res_jlmc) {
		// TODO Auto-generated method stub
		return t_resumeDao.getAllCount(t_customer_id,res_jlmc);
	}
	@Override
	public List<T_resume> getAll(Integer t_customer_id) {
		// TODO Auto-generated method stub
		return t_resumeDao.getAll(t_customer_id);
	}
}
