package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_salaryDao;
import com.hrms.been.T_salary;
import com.hrms.service.T_salaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_salaryServiceImpl implements T_salaryService {
	@Autowired
	private T_salaryDao t_salaryDao;
	@Override
	public T_salary getById(String id) {
		// TODO Auto-generated method stub
		return t_salaryDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_salary(T_salary t_salary) {
		// TODO Auto-generated method stub
		return t_salaryDao.insert(t_salary);
	}
	@Override
	public int updateT_salary(T_salary t_salary) {
		// TODO Auto-generated method stub
		return t_salaryDao.update(t_salary);
	}
	@Override
	public int deleteT_salary(String yf) {
		// TODO Auto-generated method stub
		int num=t_salaryDao.delete(yf);
		return num;
	}
	@Override
	public List<T_salary> getPage(String yf,Integer t_staff_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_salaryDao.getPage(yf,t_staff_id, sindex, scount);
	}
	@Override
	public int getAllCount(String yf,Integer t_staff_id) {
		// TODO Auto-generated method stub
		return t_salaryDao.getAllCount(yf,t_staff_id);
	}
	@Override
	public List<T_salary> getAll() {
		// TODO Auto-generated method stub
		return t_salaryDao.getAll();
	}
}
