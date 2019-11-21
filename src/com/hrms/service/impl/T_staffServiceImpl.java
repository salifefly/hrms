package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_staffDao;
import com.hrms.been.T_staff;
import com.hrms.service.T_staffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_staffServiceImpl implements T_staffService {
	@Autowired
	private T_staffDao t_staffDao;
	@Override
	public T_staff getById(String id) {
		// TODO Auto-generated method stub
		return t_staffDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckSzh(String szh) {
		// TODO Auto-generated method stub
		T_staff ad=t_staffDao.getBySzh(szh);
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckSzh(String szh, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_staff> alist=t_staffDao.getListBySzh(szh);
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_staff> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_staff ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public T_staff login(String szh, String spwd) {
		// TODO Auto-generated method stub
		List<T_staff> alist=t_staffDao.getListBySzh(szh);
		Iterator<T_staff> it=alist.iterator();
		T_staff ads=null;
		while(it.hasNext()){
			T_staff ad=it.next();
			if(ad.getSpwd().equals(spwd)){
				ads=ad;
			}
		}
		return ads;
	}
	@Override
	public int insertT_staff(T_staff t_staff) {
		// TODO Auto-generated method stub
		return t_staffDao.insert(t_staff);
	}
	@Override
	public int updateT_staff(T_staff t_staff) {
		// TODO Auto-generated method stub
		return t_staffDao.update(t_staff);
	}
	@Override
	public int deleteT_staff(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_staff ad=t_staffDao.getById(Integer.parseInt(id[i]));
			num=num+t_staffDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_staff> getPage(String szh,Integer t_post_id,Integer t_dept_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_staffDao.getPage(szh,t_post_id,t_dept_id, sindex, scount);
	}
	@Override
	public int getAllCount(String szh,Integer t_post_id,Integer t_dept_id) {
		// TODO Auto-generated method stub
		return t_staffDao.getAllCount(szh,t_post_id,t_dept_id);
	}
	@Override
	public List<T_staff> getAll() {
		// TODO Auto-generated method stub
		return t_staffDao.getAll();
	}
	@Override
	public List<T_staff> getListByPost(Integer t_post_id) {
		// TODO Auto-generated method stub
		return t_staffDao.getListByPost(t_post_id);
	}
}
