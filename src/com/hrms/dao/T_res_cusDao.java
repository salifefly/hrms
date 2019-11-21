package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_res_cus;
import org.apache.ibatis.annotations.Param;
public interface T_res_cusDao {
	//根据id获取对象
	public T_res_cus getById(@Param("id")Integer id);
	//添加
	int insert(T_res_cus t_res_cus);
	//修改
	int update(T_res_cus t_res_cus);
	//删除
	int delete(T_res_cus t_res_cus);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_res_cus> getPage(@Param("t_resume_id")Integer t_resume_id,@Param("t_job_id")Integer t_job_id,@Param("t_customer_id")Integer t_customer_id,@Param("rc_zt")String rc_zt,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("t_resume_id")Integer t_resume_id,@Param("t_job_id")Integer t_job_id,@Param("t_customer_id")Integer t_customer_id,@Param("rc_zt")String rc_zt);
	//获取全部信息
	public List<T_res_cus> getAll();
}
