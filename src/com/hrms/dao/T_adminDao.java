package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_admin;
import org.apache.ibatis.annotations.Param;
public interface T_adminDao {
	//根据id获取对象
	public T_admin getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_admin getByUname(@Param("uname")String uname);
	//根据条件获取对象链表
	public List<T_admin> getListByUname(@Param("uname")String uname);
	//添加
	int insert(T_admin t_admin);
	//修改
	int update(T_admin t_admin);
	//删除
	int delete(T_admin t_admin);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_admin> getPage(@Param("uname")String uname,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("uname")String uname);
	//获取全部信息
	public List<T_admin> getAll();
}
