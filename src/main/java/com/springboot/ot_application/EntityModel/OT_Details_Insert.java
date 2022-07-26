package com.springboot.ot_application.EntityModel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@ToString

@Entity
@Table(name="ot_details")
public class OT_Details_Insert {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="ot_detail_id")
		    private int detailId;			
			
			//@DateTimeFormat(pattern = "yyyy-MM-dd")
			@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
			@Column(name="ot_date")
		    private Date otDate;
			
			@Column(name="ot_description")
		    private String otDescription;
					
			
			@Column(name = "employee_id", insertable=true, updatable=false)
			private int employeeId;
			
			
			@Column(name = "project_id", insertable=true, updatable=false)
			private int projectId;
			
			
			@Column(name = "ot_status_id")
			private int statusId;
			
			
			@Column(name = "ot_unit_id")
			private int unitId;

			public OT_Details_Insert() {
				
			}

			public int getDetailId() {
				return detailId;
			}

			public void setDetailId(int detailId) {
				this.detailId = detailId;
			}

			public Date getOtDate() {
				return otDate;
			}

			public void setOtDate(Date otDate) {
				this.otDate = otDate;
			}

			public String getOtDescription() {
				return otDescription;
			}

			public void setOtDescription(String otDescription) {
				this.otDescription = otDescription;
			}

			public int getEmployeeId() {
				return employeeId;
			}

			public void setEmployeeId(int employeeId) {
				this.employeeId = employeeId;
			}

			public int getProjectId() {
				return projectId;
			}

			public void setProjectId(int projectId) {
				this.projectId = projectId;
			}

			public int getStatusId() {
				return statusId;
			}

			public void setStatusId(int statusId) {
				this.statusId = statusId;
			}

			public int getUnitId() {
				return unitId;
			}

			public void setUnitId(int unitId) {
				this.unitId = unitId;
			}

			
			
	}
