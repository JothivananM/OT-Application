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
@Table(name="designation")
public class Designations {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="designation_id")
		    private int designationId;
			
			
			@Column(name="designation_name")
		    private String designationName;
			
//			@OneToMany(targetEntity = User_Details.class, mappedBy = "designation_id", orphanRemoval = false, fetch = FetchType.LAZY)
//		    private List<User_Details> userDetails;

			public Designations() {
				
			}	
			
			
			public int getDesignationId() {
				return designationId;
			}


			public void setDesignationId(int designationId) {
				this.designationId = designationId;
			}


			public String getDesignationName() {
				return designationName;
			}

			public void setDesignationName(String designationName) {
				this.designationName = designationName;
			}

//			public List<User_Details> getUserDetails() {
//				return userDetails;
//			}
//
//			public void setUserDetails(List<User_Details> userDetails) {
//				this.userDetails = userDetails;
//			}
//		    
		    
			
	}
