package oa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tdept")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tdept implements java.io.Serializable {
	private String id;
	private Date createdatetime;
	private Date modifydatetime;
	private String cnname;
	private String enname;
	private Tdept parentid;
	private Integer depttype;
	private String deptdescribe;
	
	public Tdept() {
	}
	
	public Tdept(String id, Date createdatetime, Date modifydatetime, String cnname, String enname, Tdept parentid, Integer depttype, String deptdescribe) {
		this.id = id;
		this.createdatetime = createdatetime;
		this.modifydatetime = modifydatetime;
		this.cnname = cnname;
		this.enname = enname;
		this.parentid = parentid;
		this.depttype = depttype;
		this.deptdescribe = deptdescribe;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYDATETIME", length = 19)
	public Date getModifydatetime() {
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	
	@Column(name = "CNNAME", unique = true, nullable = false, length = 100)
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	@Column(name = "ENNAME", unique = true, nullable = false, length = 100)
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTID")
	public Tdept getParentid() {
		return parentid;
	}
	public void setParentid(Tdept parentid) {
		this.parentid = parentid;
	}
	@Column(name = "DEPTTYPE")
	public Integer getDepttype() {
		return depttype;
	}

	public void setDepttype(Integer depttype) {
		this.depttype = depttype;
	}
	@Column(name = "DEPTDESCRIBE", length = 200)
	public String getDeptdescribe() {
		return deptdescribe;
	}

	public void setDeptdescribe(String deptdescribe) {
		this.deptdescribe = deptdescribe;
	}

}
