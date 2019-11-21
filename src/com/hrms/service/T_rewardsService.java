package com.hrms.service;
import java.util.List;
import com.hrms.been.T_rewards;
public interface T_rewardsService {
	//根据id获取对象
	public T_rewards getById(String id); 
	//添加
	public int insertT_rewards(T_rewards t_rewards);
	//修改
	public int updateT_rewards(T_rewards t_rewards);
	//删除
	public int deleteT_rewards(String ids);
	//分页获取信息链表
	public List<T_rewards> getPage(Integer t_staff_id,Integer t_dept_id,Integer t_post_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_staff_id,Integer t_dept_id,Integer t_post_id);
	//获取所有数据
	public List<T_rewards> getAll();
	//获取所有数据
	public List<T_rewards> getByStaff(Integer t_staff_id,String stime,String etime);
}
