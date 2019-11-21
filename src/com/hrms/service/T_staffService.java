package com.hrms.service;
import java.util.List;
import com.hrms.been.T_staff;
public interface T_staffService {
	//根据id获取对象
	public T_staff getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckSzh(String szh);
	//修改进行唯一性验证
	public boolean updckSzh(String szh,String id);
	//登录获取对象信息
	public T_staff login(String uname,String upassword);
	//添加
	public int insertT_staff(T_staff t_staff);
	//修改
	public int updateT_staff(T_staff t_staff);
	//删除
	public int deleteT_staff(String ids);
	//分页获取信息链表
	public List<T_staff> getPage(String sxm,Integer t_post_id,Integer t_dept_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(String sxm,Integer t_post_id,Integer t_dept_id);
	//获取所有数据
	public List<T_staff> getAll();
	//根据职位，获取数据
	public List<T_staff> getListByPost(Integer t_post_id);
}
