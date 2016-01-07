package oa.service;

import java.util.List;

import oa.model.Tbugtype;


/**
 * 
 * @author 孙宇
 * 
 */
public interface BugTypeServiceI {

	/**
	 * 获得BUG类型列表
	 * 
	 * @return
	 */
	public List<Tbugtype> getBugTypeList();

}
