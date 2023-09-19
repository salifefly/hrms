package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_customer;
import org.apache.ibatis.annotations.Param;
public interface T_customerDao {
	//根据id获取对象
	public T_customer getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_customer getByCus_no(@Param("cus_no")String cus_no);
	//根据条件获取对象链表
	public List<T_customer> getListByCus_no(@Param("cus_no")String cus_no);
	//添加
	int insert(T_customer t_customer);
	//修改
	int update(T_customer t_customer);
	//删除
	int delete(T_customer t_customer);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_customer> getPage(@Param("cus_no")String cus_no,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("cus_no")String cus_no);
	//获取全部信息
	public List<T_customer> getAll();
}
