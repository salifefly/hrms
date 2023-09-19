package com.hrms.service.impl;
import java.util.Iterator;
import java.util.List;

import com.hrms.dao.T_rewardsDao;
import com.hrms.been.T_rewards;
import com.hrms.service.T_rewardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class T_rewardsServiceImpl implements T_rewardsService {
	@Autowired
	private T_rewardsDao t_rewardsDao;
	@Override
	public T_rewards getById(String id) {
		// TODO Auto-generated method stub
		return t_rewardsDao.getById(Integer.parseInt(id));
	}
	@Override
	public int insertT_rewards(T_rewards t_rewards) {
		// TODO Auto-generated method stub
		return t_rewardsDao.insert(t_rewards);
	}
	@Override
	public int updateT_rewards(T_rewards t_rewards) {
		// TODO Auto-generated method stub
		return t_rewardsDao.update(t_rewards);
	}
	@Override
	public int deleteT_rewards(String ids) {
		// TODO Auto-generated method stub
		int num=0;
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			T_rewards ad=t_rewardsDao.getById(Integer.parseInt(id[i]));
			num=num+t_rewardsDao.delete(ad);
		}
		return num;
	}
	@Override
	public List<T_rewards> getPage(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,int sindex,int scount) {
		// TODO Auto-generated method stub
		return t_rewardsDao.getPage(t_staff_id,t_dept_id,t_post_id, sindex, scount);
	}
	@Override
	public int getAllCount(Integer t_staff_id,Integer t_dept_id,Integer t_post_id) {
		// TODO Auto-generated method stub
		return t_rewardsDao.getAllCount(t_staff_id,t_dept_id,t_post_id);
	}
	@Override
	public List<T_rewards> getAll() {
		// TODO Auto-generated method stub
		return t_rewardsDao.getAll();
	}
	@Override
	public List<T_rewards> getByStaff(Integer t_staff_id, String stime,
			String etime) {
		// TODO Auto-generated method stub
		return t_rewardsDao.getByStaff(t_staff_id, stime, etime);
	}
}
