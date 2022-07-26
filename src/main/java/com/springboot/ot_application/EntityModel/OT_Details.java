package com.springboot.ot_application.EntityModel;


import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@ToString

@Entity
@Table(name="ot_details")
public class OT_Details {

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
			
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "project_id")
			@Fetch(FetchMode.JOIN)
			private ProjectList projectId;
					
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "employee_id")
			@Fetch(FetchMode.JOIN)
			private User_Details employeeId;
			
						
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "ot_status_id")
			@Fetch(FetchMode.JOIN)
			private OT_Status statusId;
			
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "ot_unit_id")
			@Fetch(FetchMode.JOIN)
			private OT_Units unitId;

			public OT_Details() {
				
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

			public User_Details getEmployeeId() {
				return employeeId;
			}

			public void setEmployeeId(User_Details employeeId) {
				this.employeeId = employeeId;
			}

			public ProjectList getProjectId() {
				return projectId;
			}

			public void setProjectId(ProjectList projectId) {
				this.projectId = projectId;
			}

			public OT_Status getStatusId() {
				return statusId;
			}

			public void setStatusId(OT_Status statusId) {
				this.statusId = statusId;
			}

			public OT_Units getUnitId() {
				return unitId;
			}

			public void setUnitId(OT_Units unitId) {
				this.unitId = unitId;
			}
			
			
			
	}
