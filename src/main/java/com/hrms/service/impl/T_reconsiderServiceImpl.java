package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_reconsiderDao;
import com.hrms.been.T_reconsider;
import com.hrms.service.T_reconsiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_reconsiderServiceImpl implements T_reconsiderService {
	@Autowired
	private T_reconsiderDao t_reconsiderDao;
	@Override
	public T_reconsider getById(String id) {
		// TODO Auto-generated method stub
		return t_reconsiderDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_reconsider(T_reconsider t_reconsider) {
		// TODO Auto-generated method stub
		return t_reconsiderDao.insert(t_reconsider);
	}
	@Override
	public int updateT_reconsider(T_reconsider t_reconsider) {
		// TODO Auto-generated method stub
		return t_reconsiderDao.update(t_reconsider);
	}
	@Override
	public int deleteT_reconsider(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_reconsider ad=t_reconsiderDao.getById(Integer.parseInt(id[i]));
			num=num+t_reconsiderDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_reconsider> getPage(Integer t_salary_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_reconsiderDao.getPage(t_salary_id, sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_salary_id) {
		// TODO Auto-generated method stub
		return t_reconsiderDao.getAllCount(t_salary_id);
	}
	@Override
	public List<T_reconsider> getAll() {
		// TODO Auto-generated method stub
		return t_reconsiderDao.getAll();
	}
}
