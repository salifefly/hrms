package com.hrms.service;
import java.util.List;
import com.hrms.been.T_train_emp;
public interface T_train_empService {
	//根据id获取对象
	public T_train_emp getById(String id); 
	//添加
	public int insertT_train_emp(T_train_emp t_train_emp);
	//修改
	public int updateT_train_emp(T_train_emp t_train_emp);
	//删除
	public int deleteT_train_emp(String ids);
	//分页获取信息链表
	public List<T_train_emp> getPage(Integer t_staff_id,Integer t_train_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_staff_id,Integer t_train_id);
	//获取所有数据
	public List<T_train_emp> getAll();
}
