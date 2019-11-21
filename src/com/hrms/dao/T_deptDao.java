package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_dept;
import org.apache.ibatis.annotations.Param;
public interface T_deptDao {
	//根据id获取对象
	public T_dept getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_dept getByDept_bmmc(@Param("dept_bmmc")String dept_bmmc);
	//根据条件获取对象链表
	public List<T_dept> getListByDept_bmmc(@Param("dept_bmmc")String dept_bmmc);
	//添加
	int insert(T_dept t_dept);
	//修改
	int update(T_dept t_dept);
	//删除
	int delete(T_dept t_dept);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_dept> getPage(@Param("dept_bmmc")String dept_bmmc,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("dept_bmmc")String dept_bmmc);
	//获取全部信息
	public List<T_dept> getAll();
}
