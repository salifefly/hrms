package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_staff;
import org.apache.ibatis.annotations.Param;
public interface T_staffDao {
	//根据id获取对象
	public T_staff getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_staff getBySzh(@Param("szh")String szh);
	//根据条件获取对象链表
	public List<T_staff> getListBySzh(@Param("szh")String szh);
	//添加
	int insert(T_staff t_staff);
	//修改
	int update(T_staff t_staff);
	//删除
	int delete(T_staff t_staff);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_staff> getPage(@Param("sxm")String sxm,@Param("t_post_id")Integer t_post_id,@Param("t_dept_id")Integer t_dept_id,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("sxm")String sxm,@Param("t_post_id")Integer t_post_id,@Param("t_dept_id")Integer t_dept_id);
	//获取全部信息
	public List<T_staff> getAll();
	//根据岗位获取信息
	public List<T_staff> getListByPost(@Param("t_post_id")Integer t_post_id);
}
