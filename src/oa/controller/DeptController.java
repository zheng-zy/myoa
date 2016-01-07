package oa.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oa.pageModel.DataGrid;
import oa.pageModel.Dept;
import oa.pageModel.Json;
import oa.pageModel.PageHelper;
import oa.pageModel.Tree;
import oa.service.DeptServiceI;
import oa.service.ResourceServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 部门控制器
 * 
 * @author zhengzy
 * 
 */
@Controller
@RequestMapping("/deptController")
public class DeptController extends BaseController {

	@Autowired
	private DeptServiceI deptService;

	@Autowired
	private ResourceServiceI resourceService;

	/**
	 * 跳转到部门管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/dept";
	}

	/**
	 * 获取部门数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Dept dept, PageHelper ph) {
		return deptService.dataGrid(dept, ph);
	}
	
	/**
	 * 跳转到添加部门页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Dept dept = new Dept();
		dept.setId(UUID.randomUUID().toString());
		request.setAttribute("dept", dept);
		return "/admin/deptAdd";
	}
	
	/**
	 * 部门树
	 * 
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() {
		return deptService.allTree();
	}
	
	/**
	 * 添加部门
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Dept dept) {
		Json j = new Json();
		try {
			deptService.add(dept);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(dept);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 跳转到部门修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		Dept d = deptService.get(id);
		request.setAttribute("dept", d);
		return "/admin/deptEdit";
	}

	/**
	 * 修改部门
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Dept dept) {
		Json j = new Json();
		try {
			deptService.edit(dept);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			j.setObj(dept);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id, HttpSession session) {
		Json j = new Json();
		deptService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

	/**
	 * 批量删除部门
	 * 
	 * @param ids
	 *            ('0','1','2')
	 * @return
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public Json batchDelete(String ids, HttpSession session) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.delete(id, session);
				}
			}
		}
		j.setMsg("批量删除成功！");
		j.setSuccess(true);
		return j;
	}

}
