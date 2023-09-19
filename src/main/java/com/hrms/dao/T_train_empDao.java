package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_train_emp;
import org.apache.ibatis.annotations.Param;
public interface T_train_empDao {
	//根据id获取对象
	public T_train_emp getById(@Param("id")Integer id);
	//添加
	int insert(T_train_emp t_train_emp);
	//修改
	int update(T_train_emp t_train_emp);
	//删除
	int delete(T_train_emp t_train_emp);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_train_emp> getPage(@Param("t_staff_id")Integer t_staff_id,@Param("t_train_id")Integer t_train_id,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("t_staff_id")Integer t_staff_id,@Param("t_train_id")Integer t_train_id);
	//获取全部信息
	public List<T_train_emp> getAll();
}
