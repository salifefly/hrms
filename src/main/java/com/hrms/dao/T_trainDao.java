package com.hrms.dao;
import java.util.List;
import com.hrms.been.T_train;
import org.apache.ibatis.annotations.Param;
public interface T_trainDao {
	//根据id获取对象
	public T_train getById(@Param("id")Integer id);
	//根据条件获取对象
	public T_train getByTrain_pxzt(@Param("train_pxzt")String train_pxzt);
	//根据条件获取对象链表
	public List<T_train> getListByTrain_pxzt(@Param("train_pxzt")String train_pxzt);
	//添加
	int insert(T_train t_train);
	//修改
	int update(T_train t_train);
	//删除
	int delete(T_train t_train);
	/**
	 *@param 查询条件
	 *@param sindex,开始位置
	 * @param scount，查找条数
	 * @return
	 */
	public List<T_train> getPage(@Param("train_pxzt")String train_pxzt,@Param("sindex")int sindex,@Param("scount")int scount);
	//获取总条数
	public int getAllCount(@Param("train_pxzt")String train_pxzt);
	//获取全部信息
	public List<T_train> getAll();
}
