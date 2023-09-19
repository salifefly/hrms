package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_post;
import org.apache.ibatis.annotations.Param;
public interface T_postDao {
	//根据id获取对象
	public T_post getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_post getByPost_gwmc(@Param("post_gwmc")String post_gwmc,@Param("t_dept_id")Integer t_dept_id);
	//根据条件获取对象链表
	public List<T_post> getListByPost_gwmc(@Param("post_gwmc")String post_gwmc,@Param("t_dept_id")Integer t_dept_id);
	//添加
	int insert(T_post t_post);
	//修改
	int update(T_post t_post);
	//删除
	int delete(T_post t_post);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_post> getPage(@Param("post_gwmc")String post_gwmc,@Param("t_dept_id")Integer t_dept_id,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("post_gwmc")String post_gwmc,@Param("t_dept_id")Integer t_dept_id);
	//获取全部信息
	public List<T_post> getAll();
	//根据部门获取职位
	public List<T_post> getListByDept(@Param("t_dept_id")Integer t_dept_id);
}
