package com.emp.empApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp_table")
public class EmpModel {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	private String empname;
	private String empprofile;
	private long empsalary;
	private String empimg;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpprofile() {
		return empprofile;
	}
	public void setEmpprofile(String empprofile) {
		this.empprofile = empprofile;
	}
	public long getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(long empsalary) {
		this.empsalary = empsalary;
	}
	
	public String getEmpimg() {
		return empimg;
	}
	public void setEmpimg(String empimg) {
		this.empimg = empimg;
	}
	
	
	
}

