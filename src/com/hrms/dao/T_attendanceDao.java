package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_attendance;
import org.apache.ibatis.annotations.Param;
public interface T_attendanceDao {
	//根据id获取对象
	public T_attendance getById(@Param("id")Integer id);
	//添加
	int insert(T_attendance t_attendance);
	//修改
	int update(T_attendance t_attendance);
	//删除
	int delete(T_attendance t_attendance);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_attendance> getPage(@Param("t_staff_id")Integer t_staff_id,@Param("t_dept_id")Integer t_dept_id,@Param("t_post_id")Integer t_post_id,@Param("stime")String stime,@Param("etime")String etime,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("t_staff_id")Integer t_staff_id,@Param("t_dept_id")Integer t_dept_id,@Param("t_post_id")Integer t_post_id,@Param("stime")String stime,@Param("etime")String etime);
	//获取全部信息
	public List<T_attendance> getAll();
	//根据员工id和时间获取考勤
	public T_attendance getByStaff(@Param("t_staff_id")Integer t_staff_id,@Param("stime")String stime,@Param("etime")String etime);
}
