package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_job;
import org.apache.ibatis.annotations.Param;
public interface T_jobDao {
	//根据id获取对象
	public T_job getById(@Param("id")Integer id);
	//添加
	int insert(T_job t_job);
	//修改
	int update(T_job t_job);
	//删除
	int delete(T_job t_job);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_job> getPage(@Param("job_zw")String job_zw,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("job_zw")String job_zw);
	//获取全部信息
	public List<T_job> getAll();
}
