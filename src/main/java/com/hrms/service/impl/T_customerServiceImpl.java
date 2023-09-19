package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;
import com.hrms.dao.T_customerDao;
import com.hrms.been.T_customer;
import com.hrms.service.T_customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_customerServiceImpl implements T_customerService {
	@Autowired
	private T_customerDao t_customerDao;
	@Override
	public T_customer getById(String id) {
		// TODO Auto-generated method stub
		return t_customerDao.getById(Integer.parseInt(id));
	}
	@Override
	public boolean addckCus_no(String cus_no) {
		// TODO Auto-generated method stub
		T_customer ad=t_customerDao.getByCus_no(cus_no);
		if(ad==null){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean updckCus_no(String cus_no, String id) {
		// TODO Auto-generated method stub
		//根据条件获取信息链表
		List<T_customer> alist=t_customerDao.getListByCus_no(cus_no);
		//遍历，找到条件相同，id不同的对象，返回true，否则返回false
		Iterator<T_customer> ait=alist.iterator();
		boolean bl=false;
		while(ait.hasNext()){
			T_customer ad=ait.next();
			if(!id.equals(String.valueOf(ad.getId()))){
				bl=true;
			}
		}
		return bl;
	}
	@Override
	public T_customer login(String cus_no, String cus_pwd) {
		// TODO Auto-generated method stub
		List<T_customer> alist=t_customerDao.getListByCus_no(cus_no);
		Iterator<T_customer> it=alist.iterator();
		T_customer ads=null;
		while(it.hasNext()){
			T_customer ad=it.next();
			if(ad.getCus_pwd().equals(cus_pwd)){
				ads=ad;
			}
		}
		return ads;
	}
	@Override
	public int insertT_customer(T_customer t_customer) {
		// TODO Auto-generated method stub
		return t_customerDao.insert(t_customer);
	}
	@Override
	public int updateT_customer(T_customer t_customer) {
		// TODO Auto-generated method stub
		return t_customerDao.update(t_customer);
	}
	@Override
	public int deleteT_customer(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_customer ad=t_customerDao.getById(Integer.parseInt(id[i]));
			num=num+t_customerDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_customer> getPage(String cus_no,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_customerDao.getPage(cus_no, sindex, scount);
	}
	@Override
	public int getAllCount(String cus_no) {
		// TODO Auto-generated method stub
		return t_customerDao.getAllCount(cus_no);
	}
	@Override
	public List<T_customer> getAll() {
		// TODO Auto-generated method stub
		return t_customerDao.getAll();
	}
}
