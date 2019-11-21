package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_deptDao;
import com.hrms.been.T_dept;
import com.hrms.service.T_deptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_deptServiceImpl implements T_deptService {
	@Autowired
	private T_deptDao t_deptDao;
	@Override
	public T_dept getById(String id) {
		// TODO Auto-generated method stub
		return t_deptDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckDept_bmmc(String dept_bmmc) {
		// TODO Auto-generated method stub
		T_dept ad=t_deptDao.getByDept_bmmc(dept_bmmc);
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckDept_bmmc(String dept_bmmc, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_dept> alist=t_deptDao.getListByDept_bmmc(dept_bmmc);
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_dept> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_dept ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public int insertT_dept(T_dept t_dept) {
		// TODO Auto-generated method stub
		return t_deptDao.insert(t_dept);
	}
	@Override
	public int updateT_dept(T_dept t_dept) {
		// TODO Auto-generated method stub
		return t_deptDao.update(t_dept);
	}
	@Override
	public int deleteT_dept(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_dept ad=t_deptDao.getById(Integer.parseInt(id[i]));
			num=num+t_deptDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_dept> getPage(String dept_bmmc,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_deptDao.getPage(dept_bmmc, sindex, scount);
	}
	@Override
	public int getAllCount(String dept_bmmc) {
		// TODO Auto-generated method stub
		return t_deptDao.getAllCount(dept_bmmc);
	}
	@Override
	public List<T_dept> getAll() {
		// TODO Auto-generated method stub
		return t_deptDao.getAll();
	}
}
