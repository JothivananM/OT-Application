package com.springboot.ot_application.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
//@Where(clause = "is_active = false")
public class ProjectList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	
	@Column(name="project_id")
	private String projectCode;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="created_date")
	private String startDate;
	
	@Column(name="project_description")
	private String description;
	
	@Column(name="is_active", insertable = false, updatable = false)
	private Boolean isActive;
	
	public ProjectList()
	{
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ProjectList [Id=" + Id + ", projectCode=" + projectCode + ", projectName=" + projectName
				+ ", startDate=" + startDate + ", description=" + description + ", isActive=" + isActive + "]";
	}

	
	
}
