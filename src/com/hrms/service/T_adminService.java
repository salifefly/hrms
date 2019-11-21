package com.hrms.service;
import java.util.List;
import com.hrms.been.T_admin;
public interface T_adminService {
	//根据id获取对象
	public T_admin getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckUname(String uname);
	//修改进行唯一性验证
	public boolean updckUname(String uname,String id);
	//登录获取对象信息
	public T_admin login(String uname,String upassword);
	//添加
	public int insertT_admin(T_admin t_admin);
	//修改
	public int updateT_admin(T_admin t_admin);
	//删除
	public int deleteT_admin(String ids);
	//分页获取信息链表
	public List<T_admin> getPage(String uname,int sindex,int scount);
	//获取总条数
	public int getAllCount(String uname);
	//获取所有数据
	public List<T_admin> getAll();
}
