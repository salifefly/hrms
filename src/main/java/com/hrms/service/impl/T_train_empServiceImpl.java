package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_train_empDao;
import com.hrms.been.T_train_emp;
import com.hrms.service.T_train_empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_train_empServiceImpl implements T_train_empService {
	@Autowired
	private T_train_empDao t_train_empDao;
	@Override
	public T_train_emp getById(String id) {
		// TODO Auto-generated method stub
		return t_train_empDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_train_emp(T_train_emp t_train_emp) {
		// TODO Auto-generated method stub
		return t_train_empDao.insert(t_train_emp);
	}
	@Override
	public int updateT_train_emp(T_train_emp t_train_emp) {
		// TODO Auto-generated method stub
		return t_train_empDao.update(t_train_emp);
	}
	@Override
	public int deleteT_train_emp(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_train_emp ad=t_train_empDao.getById(Integer.parseInt(id[i]));
			num=num+t_train_empDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_train_emp> getPage(Integer t_staff_id,Integer t_train_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_train_empDao.getPage(t_staff_id,t_train_id, sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_staff_id,Integer t_train_id) {
		// TODO Auto-generated method stub
		return t_train_empDao.getAllCount(t_staff_id,t_train_id);
	}
	@Override
	public List<T_train_emp> getAll() {
		// TODO Auto-generated method stub
		return t_train_empDao.getAll();
	}
}
