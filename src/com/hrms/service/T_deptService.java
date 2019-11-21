package com.hrms.service;
import java.util.List;
import com.hrms.been.T_dept;
public interface T_deptService {
	//根据id获取对象
	public T_dept getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckDept_bmmc(String dept_bmmc);
	//修改进行唯一性验证
	public boolean updckDept_bmmc(String dept_bmmc,String id);
	//添加
	public int insertT_dept(T_dept t_dept);
	//修改
	public int updateT_dept(T_dept t_dept);
	//删除
	public int deleteT_dept(String ids);
	//分页获取信息链表
	public List<T_dept> getPage(String dept_bmmc,int sindex,int scount);
	//获取总条数
	public int getAllCount(String dept_bmmc);
	//获取所有数据
	public List<T_dept> getAll();
}
