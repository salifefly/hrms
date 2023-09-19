package com.hrms.service;
import java.util.List;
import com.hrms.been.T_res_cus;
public interface T_res_cusService {
	//根据id获取对象
	public T_res_cus getById(String id); 
	//添加
	public int insertT_res_cus(T_res_cus t_res_cus);
	//修改
	public int updateT_res_cus(T_res_cus t_res_cus);
	//删除
	public int deleteT_res_cus(String ids);
	//分页获取信息链表
	public List<T_res_cus> getPage(Integer t_resume_id,Integer t_job_id,Integer t_customer_id,String rc_zt,int sindex,int scount);
	//获取总条数
	public int getAllCount(Integer t_resume_id,Integer t_job_id,Integer t_customer_id,String rc_zt);
	//获取所有数据
	public List<T_res_cus> getAll();
}
