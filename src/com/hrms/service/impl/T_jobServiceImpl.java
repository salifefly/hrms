package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_jobDao;
import com.hrms.been.T_job;
import com.hrms.service.T_jobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_jobServiceImpl implements T_jobService {
	@Autowired
	private T_jobDao t_jobDao;
	@Override
	public T_job getById(String id) {
		// TODO Auto-generated method stub
		return t_jobDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_job(T_job t_job) {
		// TODO Auto-generated method stub
		return t_jobDao.insert(t_job);
	}
	@Override
	public int updateT_job(T_job t_job) {
		// TODO Auto-generated method stub
		return t_jobDao.update(t_job);
	}
	@Override
	public int deleteT_job(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_job ad=t_jobDao.getById(Integer.parseInt(id[i]));
			num=num+t_jobDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_job> getPage(String job_zw,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_jobDao.getPage(job_zw, sindex, scount);
	}
	@Override
	public int getAllCount(String job_zw) {
		// TODO Auto-generated method stub
		return t_jobDao.getAllCount(job_zw);
	}
	@Override
	public List<T_job> getAll() {
		// TODO Auto-generated method stub
		return t_jobDao.getAll();
	}
}
