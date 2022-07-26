package com.springboot.ot_application.EntityModel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@ToString
@Entity
@Transactional
public class OT_Details_List {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="ot_detail_id")
		    private int ot_detail_id;			
			
			//@DateTimeFormat(pattern = "yyyy-MM-dd")
			@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
		    private Date ot_date;			
			
		    private String ot_description;			
			
			private int project_id;
			
			private String project_name;					
			
			private int employee_id;
			
			private String employee_name;
			
			private int ot_count;
			
			private String ot_action;			
			
			@Column(insertable=true, updatable=false)
			private int ot_status_id;			
			
			private int ot_unit_id;

			private String reject_reason;

					
			public OT_Details_List() {
				
			}

			public int getOt_detail_id() {
				return ot_detail_id;
			}

			public void setOt_detail_id(int ot_detail_id) {
				this.ot_detail_id = ot_detail_id;
			}

			public Date getOt_date() {
				return ot_date;
			}

			public void setOt_date(Date ot_date) {
				this.ot_date = ot_date;
			}

			public String getOt_description() {
				return ot_description;
			}

			public void setOt_description(String ot_description) {
				this.ot_description = ot_description;
			}

			public int getId() {
				return project_id;
			}

			public void setId(int id) {
				this.project_id = id;
			}

			public int getEmployee_id() {
				return employee_id;
			}

			public void setEmployee_id(int employee_id) {
				this.employee_id = employee_id;
			}

			public int getProject_id() {
				return project_id;
			}

			public void setProject_id(int project_id) {
				this.project_id = project_id;
			}

			public int getOt_status_id() {
				return ot_status_id;
			}

			public void setOt_status_id(int ot_status_id) {
				this.ot_status_id = ot_status_id;
			}

			public int getOt_unit_id() {
				return ot_unit_id;
			}

			public void setOt_unit_id(int ot_unit_id) {
				this.ot_unit_id = ot_unit_id;
			}

			public String getProject_name() {
				return project_name;
			}

			public void setProject_name(String project_name) {
				this.project_name = project_name;
			}

			public String getOt_action() {
				return ot_action;
			}

			public void setOt_action(String ot_action) {
				this.ot_action = ot_action;
			}

			public String getEmployee_name() {
				return employee_name;
			}

			public void setEmployee_name(String employee_name) {
				this.employee_name = employee_name;
			}

			public int getOt_count() {
				return ot_count;
			}

			public void setOt_count(int ot_count) {
				this.ot_count = ot_count;
			}

			public String getReject_reason() {
				return reject_reason;
			}

			public void setReject_reason(String reject_reason) {
				this.reject_reason = reject_reason;
			}
						
			
	}
