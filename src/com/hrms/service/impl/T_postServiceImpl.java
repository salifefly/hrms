package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_postDao;
import com.hrms.been.T_post;
import com.hrms.service.T_postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_postServiceImpl implements T_postService {
	@Autowired
	private T_postDao t_postDao;
	@Override
	public T_post getById(String id) {
		// TODO Auto-generated method stub
		return t_postDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckPost_gwmc(String post_gwmc,String t_dept_id) {
		// TODO Auto-generated method stub
		T_post ad=t_postDao.getByPost_gwmc(post_gwmc,Integer.parseInt(t_dept_id));
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckPost_gwmc(String post_gwmc,String t_dept_id, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_post> alist=t_postDao.getListByPost_gwmc(post_gwmc,Integer.parseInt(t_dept_id));
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_post> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_post ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public int insertT_post(T_post t_post) {
		// TODO Auto-generated method stub
		return t_postDao.insert(t_post);
	}
	@Override
	public int updateT_post(T_post t_post) {
		// TODO Auto-generated method stub
		return t_postDao.update(t_post);
	}
	@Override
	public int deleteT_post(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_post ad=t_postDao.getById(Integer.parseInt(id[i]));
			num=num+t_postDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_post> getPage(String post_gwmc,Integer t_dept_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_postDao.getPage(post_gwmc,t_dept_id, sindex, scount);
	}
	@Override
	public int getAllCount(String post_gwmc,Integer t_dept_id) {
		// TODO Auto-generated method stub
		return t_postDao.getAllCount(post_gwmc,t_dept_id);
	}
	@Override
	public List<T_post> getAll() {
		// TODO Auto-generated method stub
		return t_postDao.getAll();
	}
	@Override
	public List<T_post> getListByDept(String t_dept_id) {
		// TODO Auto-generated method stub
		return t_postDao.getListByDept(Integer.parseInt(t_dept_id));
	}
}
