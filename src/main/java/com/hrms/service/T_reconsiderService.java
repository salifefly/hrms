package com.hrms.service;
import java.util.List;
import com.hrms.been.T_reconsider;
public interface T_reconsiderService {
	//根据id获取对象
	public T_reconsider getById(String id); 
	//添加
	public int insertT_reconsider(T_reconsider t_reconsider);
	//修改
	public int updateT_reconsider(T_reconsider t_reconsider);
	//删除
	public int deleteT_reconsider(String ids);
	//分页获取信息链表
	public List<T_reconsider> getPage(Integer t_salary_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_salary_id);
	//获取所有数据
	public List<T_reconsider> getAll();
}
