package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_adminDao;
import com.hrms.been.T_admin;
import com.hrms.service.T_adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_adminServiceImpl implements T_adminService {
	@Autowired
	private T_adminDao t_adminDao;
	@Override
	public T_admin getById(String id) {
		// TODO Auto-generated method stub
		return t_adminDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckUname(String uname) {
		// TODO Auto-generated method stub
		T_admin ad=t_adminDao.getByUname(uname);
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckUname(String uname, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_admin> alist=t_adminDao.getListByUname(uname);
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_admin> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_admin ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public T_admin login(String uname, String pwd) {
		// TODO Auto-generated method stub
		List<T_admin> alist=t_adminDao.getListByUname(uname);
		Iterator<T_admin> it=alist.iterator();
		T_admin ads=null;
		while(it.hasNext()){
			T_admin ad=it.next();
			if(ad.getPwd().equals(pwd)){
				ads=ad;
			}
		}
		return ads;
	}
	@Override
	public int insertT_admin(T_admin t_admin) {
		// TODO Auto-generated method stub
		return t_adminDao.insert(t_admin);
	}
	@Override
	public int updateT_admin(T_admin t_admin) {
		// TODO Auto-generated method stub
		return t_adminDao.update(t_admin);
	}
	@Override
	public int deleteT_admin(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_admin ad=t_adminDao.getById(Integer.parseInt(id[i]));
			num=num+t_adminDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_admin> getPage(String uname,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_adminDao.getPage(uname, sindex, scount);
	}
	@Override
	public int getAllCount(String uname) {
		// TODO Auto-generated method stub
		return t_adminDao.getAllCount(uname);
	}
	@Override
	public List<T_admin> getAll() {
		// TODO Auto-generated method stub
		return t_adminDao.getAll();
	}
}
