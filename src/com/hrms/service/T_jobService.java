package com.hrms.service;
import java.util.List;
import com.hrms.been.T_job;
public interface T_jobService {
	//根据id获取对象
	public T_job getById(String id); 
	//添加
	public int insertT_job(T_job t_job);
	//修改
	public int updateT_job(T_job t_job);
	//删除
	public int deleteT_job(String ids);
	//分页获取信息链表
	public List<T_job> getPage(String job_zw,int sindex,int scount);
	//获取总条数
	public int getAllCount(String job_zw);
	//获取所有数据
	public List<T_job> getAll();
}
