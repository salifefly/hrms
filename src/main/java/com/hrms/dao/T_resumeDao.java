package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_resume;
import org.apache.ibatis.annotations.Param;
public interface T_resumeDao {
	//根据id获取对象
	public T_resume getById(@Param("id")Integer id);
	//添加
	int insert(T_resume t_resume);
	//修改
	int update(T_resume t_resume);
	//删除
	int delete(T_resume t_resume);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_resume> getPage(@Param("t_customer_id")Integer t_customer_id,@Param("res_jlmc")String res_jlmc,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("t_customer_id")Integer t_customer_id,@Param("res_jlmc")String res_jlmc);
	//获取全部信息
	public List<T_resume> getAll(@Param("t_customer_id")Integer t_customer_id);
}
