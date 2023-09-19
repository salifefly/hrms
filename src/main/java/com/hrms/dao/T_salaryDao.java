package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_salary;
import org.apache.ibatis.annotations.Param;
public interface T_salaryDao {
	//根据id获取对象
	public T_salary getById(@Param("id")Integer id);
	//添加
	int insert(T_salary t_salary);
	//修改
	int update(T_salary t_salary);
	//删除
	int delete(String yf);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_salary> getPage(@Param("yf")String yf,@Param("t_staff_id")Integer t_staff_id,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("yf")String yf,@Param("t_staff_id")Integer t_staff_id);
	//获取全部信息
	public List<T_salary> getAll();
}
