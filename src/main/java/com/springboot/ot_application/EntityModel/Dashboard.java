package com.springboot.ot_application.EntityModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
@ToString
@Entity
@Transactional
public class Dashboard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="table_id")
	private int table_id;
	private int id;
	
	private int employee_table_id;
	
	private String project_name;
	
	private String employee_name;
	
	private String created_date;
	
	private Boolean user_status;
	
	private Boolean project_status;
	
	private int ot_Total_Count;
	
	private int ot_saved_Count;
	
	private int ot_submitted_Count;
	
	private int ot_approved_Count;
	
	private int ot_rejected_Count;
	
	
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
	private Date ot_date;
	
	private String ot_action;
	
	private int ot_count;
	
	private String ot_slots;
private String ot_description;
	
	
	public String getOt_description() {
		return ot_description;
	}
	public void setOt_description(String ot_description) {
		this.ot_description = ot_description;
	}
	public Dashboard() {
		
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployee_table_id() {
		return employee_table_id;
	}
	public void setEmployee_table_id(int employee_table_id) {
		this.employee_table_id = employee_table_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public Boolean getUser_status() {
		return user_status;
	}
	public void setUser_status(Boolean user_status) {
		this.user_status = user_status;
	}
	
	public Boolean getProject_status() {
		return project_status;
	}
	public void setProject_status(Boolean project_status) {
		this.project_status = project_status;
	}
	public int getOt_Total_Count() {
		return ot_Total_Count;
	}
	public void setOt_Total_Count(int ot_Total_Count) {
		this.ot_Total_Count = ot_Total_Count;
	}
	public int getOt_Count() {
		return ot_Total_Count;
	}
	public void setOt_Count(int ot_Count) {
		this.ot_Total_Count = ot_Count;
	}
	public Date getOt_date() {
		return ot_date;
	}
	public void setOt_date(Date ot_date) {
		this.ot_date = ot_date;
	}
	public String getOt_action() {
		return ot_action;
	}
	public void setOt_action(String ot_action) {
		this.ot_action = ot_action;
	}
	public int getOt_count() {
		return ot_count;
	}
	public void setOt_count(int ot_count) {
		this.ot_count = ot_count;
	}
	public String getOt_slots() {
		return ot_slots;
	}
	public void setOt_slots(String ot_slots) {
		this.ot_slots = ot_slots;
	}

	

	public int getOt_saved_Count() {
		return ot_saved_Count;
	}
	public void setOt_saved_Count(int ot_saved_Count) {
		this.ot_saved_Count = ot_saved_Count;
	}
	public int getOt_submitted_Count() {
		return ot_submitted_Count;
	}
	public void setOt_submitted_Count(int ot_submitted_Count) {
		this.ot_submitted_Count = ot_submitted_Count;
	}
	public int getOt_approved_Count() {
		return ot_approved_Count;
	}
	public void setOt_approved_Count(int ot_approved_Count) {
		this.ot_approved_Count = ot_approved_Count;
	}
	public int getOt_rejected_Count() {
		return ot_rejected_Count;
	}
	public void setOt_rejected_Count(int ot_rejected_Count) {
		this.ot_rejected_Count = ot_rejected_Count;
	}
	
	
	
}








