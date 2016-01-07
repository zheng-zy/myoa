package oa.service;

import java.util.List;

import oa.pageModel.DataGrid;
import oa.pageModel.Dept;
import oa.pageModel.PageHelper;
import oa.pageModel.SessionInfo;
import oa.pageModel.Tree;


/**
 * 部门Service
 * 
 * @author zhengzy
 * 
 */
public interface DeptServiceI {

	/**
	 * 获取部門数据表格
	 * 
	 * @param dept
	 * @return
	 */
	public DataGrid dataGrid(Dept dept, PageHelper ph);
	
	/**
	 * 添加部门
	 * 
	 * @param user
	 */
	public void add(Dept dept) throws Exception;

	/**
	 * 获得部门对象
	 * 
	 * @param id
	 * @return
	 */
	public Dept get(String id);

	/**
	 * 编辑部门
	 * 
	 * @param dept
	 */
	public void edit(Dept dept) throws Exception;

	/**
	 * 删除部门
	 * 
	 * @param id
	 */
	public void delete(String id);

	
	/**
	 * 获得角色树
	 * 
	 * @return
	 */
	public List<Tree> allTree();
	
	/**
	 * 获得部门树(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	public List<Tree> tree(SessionInfo sessionInfo);

	

}
