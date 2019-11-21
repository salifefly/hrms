package com.hrms.service;
import java.util.List;
import com.hrms.been.T_salary;
public interface T_salaryService {
	//根据id获取对象
	public T_salary getById(String id); 
	//添加
	public int insertT_salary(T_salary t_salary);
	//修改
	public int updateT_salary(T_salary t_salary);
	//删除
	public int deleteT_salary(String yf);
	//分页获取信息链表
	public List<T_salary> getPage(String yf,Integer t_staff_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(String yf,Integer t_staff_id);
	//获取所有数据
	public List<T_salary> getAll();
}
