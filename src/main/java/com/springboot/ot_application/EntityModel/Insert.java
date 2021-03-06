package com.springboot.ot_application.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_details")
public class Insert {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_table_id")
	private int id;
	
	@Column(name="employee_name")
	private String name;
	
	@Column(name="employee_code")
	private String employeeCode;
	
	@Column(name="mobile_no")
	private String mobileNo;	
		
	@Column(name="personal_email")
	private String personalEmailId;
	
	@Column(name="company_email")
	private String companyEmailId;	
	
	@Column(name="role_id")
	private int role_id;
	
	@Column(name="designation_id")
	private int designation_id;	

	@Column(name="password", insertable = true, updatable = false)
	private String password;

	@Column(name="is_active", insertable = false, updatable = false)
	private Boolean isActive;
	

	public Insert() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmployeeCode() {
		return employeeCode;
	}


	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getPersonalEmailId() {
		return personalEmailId;
	}


	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}


	public String getCompanyEmailId() {
		return companyEmailId;
	}


	public void setCompanyEmailId(String companyEmailId) {
		this.companyEmailId = companyEmailId;
	}


	public int getRole_id() {
		return role_id;
	}


	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	public int getDesignation_id() {
		return designation_id;
	}


	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "Insert [companyEmailId=" + companyEmailId + ", designation_id=" + designation_id + ", employeeCode="
				+ employeeCode + ", id=" + id + ", isActive=" + isActive + ", mobileNo=" + mobileNo + ", name=" + name
				+ ", password=" + password + ", personalEmailId=" + personalEmailId + ", role_id=" + role_id + "]";
	}

	
}




