package com.springboot.ot_application.EntityModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import lombok.ToString;

@ToString
@Entity
@Transactional
public class Profile {	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employee_table_id;
	
	
	private String password;

	public int getEmployee_table_id() {
		return employee_table_id;
	}

	public void setEmployee_table_id(int employee_table_id) {
		this.employee_table_id = employee_table_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
