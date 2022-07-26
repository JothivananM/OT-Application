package com.springboot.ot_application.EntityModel;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import lombok.ToString;

@ToString

@Entity
@Table(name="ot_status")
public class OT_Status {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="status_table_id")
		    private int statusId;
			
			
			@Column(name="ot_action")
		    private String otAction;
			

			public OT_Status() {
				
			}

			public int getStatusId() {
				return statusId;
			}

			public void setStatusId(int statusId) {
				this.statusId = statusId;
			}

			public String getOtAction() {
				return otAction;
			}

			public void setOtAction(String otAction) {
				this.otAction = otAction;
			}	
			
						
	}
