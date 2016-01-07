package oa.pageModel;

import java.util.Date;


public class Dept implements java.io.Serializable {

	private String id;
	private String parentid;
	private String parentname;
	private String cnname;
	private String enname;
	private Integer depttype;
	private String deptdescribe;
	
	private Date createdatetime;
	private Date modifydatetime;

	private Date createdatetimeStart;
	private Date createdatetimeEnd;
	private Date modifydatetimeStart;
	private Date modifydatetimeEnd;
	
	private String resourceIds;
	private String resourceNames;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public Integer getDepttype() {
		return depttype;
	}
	public void setDepttype(Integer depttype) {
		this.depttype = depttype;
	}
	public String getDeptdescribe() {
		return deptdescribe;
	}
	public void setDeptdescribe(String deptdescribe) {
		this.deptdescribe = deptdescribe;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Date getModifydatetime() {
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	public Date getCreatedatetimeStart() {
		return createdatetimeStart;
	}
	public void setCreatedatetimeStart(Date createdatetimeStart) {
		this.createdatetimeStart = createdatetimeStart;
	}
	public Date getCreatedatetimeEnd() {
		return createdatetimeEnd;
	}
	public void setCreatedatetimeEnd(Date createdatetimeEnd) {
		this.createdatetimeEnd = createdatetimeEnd;
	}
	public Date getModifydatetimeStart() {
		return modifydatetimeStart;
	}
	public void setModifydatetimeStart(Date modifydatetimeStart) {
		this.modifydatetimeStart = modifydatetimeStart;
	}
	public Date getModifydatetimeEnd() {
		return modifydatetimeEnd;
	}
	public void setModifydatetimeEnd(Date modifydatetimeEnd) {
		this.modifydatetimeEnd = modifydatetimeEnd;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String getResourceNames() {
		return resourceNames;
	}
	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}
}
