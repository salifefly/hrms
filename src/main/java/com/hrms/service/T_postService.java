package com.hrms.service;
import java.util.List;
import com.hrms.been.T_post;
public interface T_postService {
	//根据id获取对象
	public T_post getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckPost_gwmc(String post_gwmc,String t_dept_id);
	//修改进行唯一性验证
	public boolean updckPost_gwmc(String post_gwmc,String t_dept_id,String id);
	//添加
	public int insertT_post(T_post t_post);
	//修改
	public int updateT_post(T_post t_post);
	//删除
	public int deleteT_post(String ids);
	//分页获取信息链表
	public List<T_post> getPage(String post_gwmc,Integer t_dept_id,int sindex,int scount);
	//获取总条数
	public int getAllCount(String post_gwmc,Integer t_dept_id);
	//获取所有数据
	public List<T_post> getAll();
	//获取所有数据
	public List<T_post> getListByDept(String t_dept_id);
}
