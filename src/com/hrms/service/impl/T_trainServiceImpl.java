package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_trainDao;
import com.hrms.been.T_train;
import com.hrms.service.T_trainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_trainServiceImpl implements T_trainService {
	@Autowired
	private T_trainDao t_trainDao;
	@Override
	public T_train getById(String id) {
		// TODO Auto-generated method stub
		return t_trainDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckTrain_pxzt(String train_pxzt) {
		// TODO Auto-generated method stub
		T_train ad=t_trainDao.getByTrain_pxzt(train_pxzt);
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckTrain_pxzt(String train_pxzt, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_train> alist=t_trainDao.getListByTrain_pxzt(train_pxzt);
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_train> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_train ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public int insertT_train(T_train t_train) {
		// TODO Auto-generated method stub
		return t_trainDao.insert(t_train);
	}
	@Override
	public int updateT_train(T_train t_train) {
		// TODO Auto-generated method stub
		return t_trainDao.update(t_train);
	}
	@Override
	public int deleteT_train(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_train ad=t_trainDao.getById(Integer.parseInt(id[i]));
			num=num+t_trainDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_train> getPage(String train_pxzt,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_trainDao.getPage(train_pxzt, sindex, scount);
	}
	@Override
	public int getAllCount(String train_pxzt) {
		// TODO Auto-generated method stub
		return t_trainDao.getAllCount(train_pxzt);
	}
	@Override
	public List<T_train> getAll() {
		// TODO Auto-generated method stub
		return t_trainDao.getAll();
	}
}
