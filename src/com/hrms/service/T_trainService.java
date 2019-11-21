package com.hrms.service;
import java.util.List;
import com.hrms.been.T_train;
public interface T_trainService {
	//根据id获取对象
	public T_train getById(String id); 
	//根据添加进行唯一性验证
	public boolean addckTrain_pxzt(String train_pxzt);
	//修改进行唯一性验证
	public boolean updckTrain_pxzt(String train_pxzt,String id);
	//添加
	public int insertT_train(T_train t_train);
	//修改
	public int updateT_train(T_train t_train);
	//删除
	public int deleteT_train(String ids);
	//分页获取信息链表
	public List<T_train> getPage(String train_pxzt,int sindex,int scount);
	//获取总条数
	public int getAllCount(String train_pxzt);
	//获取所有数据
	public List<T_train> getAll();
}
