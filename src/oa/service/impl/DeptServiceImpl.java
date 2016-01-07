package oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import oa.dao.DeptDaoI;
import oa.dao.ResourceDaoI;
import oa.dao.RoleDaoI;
import oa.dao.UserDaoI;
import oa.model.Tdept;
import oa.model.Tresource;
import oa.model.Trole;
import oa.model.Tuser;
import oa.pageModel.DataGrid;
import oa.pageModel.Dept;
import oa.pageModel.PageHelper;
import oa.pageModel.SessionInfo;
import oa.pageModel.Tree;
import oa.pageModel.User;
import oa.service.DeptServiceI;
import oa.service.UserServiceI;
import oa.util.MD5Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptServiceI {

	@Autowired
	private DeptDaoI deptDao;

	@Autowired
	private ResourceDaoI resourceDao;

	@Override
	public DataGrid dataGrid(Dept dept, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<Dept> dl = new ArrayList<Dept>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tdept t ";
		List<Tdept> l = deptDao.find(hql + whereHql(dept, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		if (l != null && l.size() > 0) {
			for (Tdept t : l) {
				Dept d = new Dept();
				BeanUtils.copyProperties(t, d);
				Tdept tdept = t.getParentid();
				if (tdept != null) {
					d.setParentid(tdept.getId());
					d.setParentname(tdept.getCnname());
				}
				dl.add(d);
			}
		}
		dg.setRows(dl);
		dg.setTotal(deptDao.count("select count(*) " + hql + whereHql(dept, params), params));
		return dg;
	}

	private String whereHql(Dept dept, Map<String, Object> params) {
		String hql = "";
		if (dept != null) {
			hql += " where 1=1 ";

			if (dept.getCnname() != null) {
				hql += " and t.cnname like :cnname";
				params.put("cnname", "%%" + dept.getCnname() + "%%");
			}
			if (dept.getEnname() != null) {
				hql += " and t.enname like :enname";
				params.put("enname", "%%" + dept.getEnname() + "%%");
			}
		}
		return hql;
	}

	private String orderHql(PageHelper ph) {
		String orderString = "";
		if (ph.getSort() != null && ph.getOrder() != null) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	synchronized public void add(Dept dept) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cnname", dept.getCnname());
		if (deptDao.count("select count(*) from Tdept t where t.cnname = :cnname ", params) > 0) {
			throw new Exception("中文名已存在！");
		}
		params.clear();
		params.put("enname", dept.getEnname());
		if (deptDao.count("select count(*) from Tdept t where t.enname = :enname", params) > 0) {
			throw new Exception("英文名已存在！");
		}

		Tdept d = new Tdept();
		if (dept.getParentid() != null) {
			Tdept pd = deptDao.get(Tdept.class, dept.getParentid());
			d.setParentid(pd);
			d.setDepttype(pd.getDepttype() + 1);
		}
		BeanUtils.copyProperties(dept, d, new String[] { "depttype" });
		d.setCreatedatetime(new Date());
		deptDao.save(d);

	}

	@Override
	public Dept get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tdept t = deptDao.get("select distinct t from Tdept t where t.id = :id", params);
		Dept d = new Dept();
		BeanUtils.copyProperties(t, d);
		return d;
	}

	@Override
	synchronized public void edit(Dept dept) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", dept.getId());
		params.put("cnname", dept.getCnname());
		if (deptDao.count("select count(*) from Tdept t where t.cnname = :cnname and t.id != :id", params) > 0) {
			throw new Exception("部门中文名称已存在！");
		}
		params.clear();
		params.put("id", dept.getId());
		params.put("enname", dept.getEnname());
		if (deptDao.count("select count(*) from Tdept t where t.enname = :enname and t.id != :id", params) > 0) {
			throw new Exception("部门英文名称已存在！");
		}
		Tdept d = deptDao.get(Tdept.class, dept.getId());
		if (!"".equals(dept.getParentid()) && dept.getParentid() != null) {
			Tdept pd = deptDao.get(Tdept.class, dept.getParentid());
			d.setParentid(pd);
			d.setDepttype(pd.getDepttype() + 1);
		}
		// 第三个参数是代表不赋值的字段
		// new String[] { "pwd", "createdatetime" }
		BeanUtils.copyProperties(dept, d, new String[] { "createdatetime", "depttype" });
		d.setModifydatetime(new Date());
	}

	@Override
	public void delete(String id) {
		deptDao.delete(deptDao.get(Tdept.class, id));
	}

	@Override
	public List<Tree> allTree() {
		List<Tdept> d = null;
		List<Tree> lt = new ArrayList<Tree>();
		d = deptDao.find("from Tdept t order by t.depttype");

		if (d != null && d.size() > 0) {
			for (Tdept t : d) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(t, tree);
				tree.setText(t.getCnname());
				tree.setIconCls("status_online");
				if (t.getParentid() != null) {
					tree.setPid(t.getParentid().getId());
				}
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		return null;
	}

}
