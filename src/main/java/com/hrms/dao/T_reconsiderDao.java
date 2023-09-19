package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_reconsider;
import org.apache.ibatis.annotations.Param;
public interface T_reconsiderDao {
	//根据id获取对象
	public T_reconsider getById(@Param("id")Integer id);
	//添加
	int insert(T_reconsider t_reconsider);
	//修改
	int update(T_reconsider t_reconsider);
	//删除
	int delete(T_reconsider t_reconsider);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_reconsider> getPage(@Param("t_salary_id")Integer t_salary_id,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("t_salary_id")Integer t_salary_id);
	//获取全部信息
	public List<T_reconsider> getAll();
}
