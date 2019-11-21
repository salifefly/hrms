package com.hrms.service;
import java.util.List;
import com.hrms.been.T_customer;
public interface T_customerService {
	//根据id获取对象
	public T_customer getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckCus_no(String cus_no);
	//修改进行唯一性验证
	public boolean updckCus_no(String cus_no,String id);
	//登录获取对象信息
	public T_customer login(String uname,String upassword);
	//添加
	public int insertT_customer(T_customer t_customer);
	//修改
	public int updateT_customer(T_customer t_customer);
	//删除
	public int deleteT_customer(String ids);
	//分页获取信息链表
	public List<T_customer> getPage(String cus_no,int sindex,int scount);
	//获取总条数
	public int getAllCount(String cus_no);
	//获取所有数据
	public List<T_customer> getAll();
}
