package com.springboot.ot_application.EntityModel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="user_details")
public class User_Details {

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

	@Column(name="password")
	private String userPassword;	

	@Column(name="is_active")
	private Boolean isActive;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@Fetch(FetchMode.JOIN)
	private Role role_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designation_id")
	@Fetch(FetchMode.JOIN)
	private Designations designation_id;	

	public User_Details() {
		
	}	
	
	public User_Details(int id, String name, String employeeCode, String mobileNo, String personalEmailId,
			String companyEmailId, Role role_id, Designations designation_id) {
		super();
		this.id = id;
		this.name = name;
		this.employeeCode = employeeCode;
		this.mobileNo = mobileNo;
		this.personalEmailId = personalEmailId;
		this.companyEmailId = companyEmailId;
		this.role_id = role_id;
		this.designation_id = designation_id;
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

	public Designations getDesignation_id() {
		return designation_id;
	}

	public void setDesignation_id(Designations designation_id) {
		this.designation_id = designation_id;
	}

	public Role getRole_id() {
		return role_id;
	}

	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User_Details [id=" + id + ", name=" + name + ", employeeCode=" + employeeCode + ", mobileNo=" + mobileNo
				+ ", personalEmailId=" + personalEmailId + ", companyEmailId=" + companyEmailId + ", userPassword="
				+ userPassword + ", isActive=" + isActive + ", role_id=" + role_id + ", designation_id=" + designation_id + "]";
	}

	
}




