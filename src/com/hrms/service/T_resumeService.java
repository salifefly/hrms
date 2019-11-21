package com.hrms.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrms.been.T_resume;
public interface T_resumeService {
	//根据id获取对象
	public T_resume getById(String id); 
	//添加
	public int insertT_resume(T_resume t_resume);
	//修改
	public int updateT_resume(T_resume t_resume);
	//删除
	public int deleteT_resume(String ids);
	//分页获取信息链表
	public List<T_resume> getPage(Integer t_customer_id,String res_jlmc,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_customer_id,String res_jlmc);
	//获取所有数据
	public List<T_resume> getAll(Integer t_customer_id);
}
