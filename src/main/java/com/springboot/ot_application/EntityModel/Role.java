package com.springboot.ot_application.EntityModel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="role")
public class Role {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="role_id")
		    private int roleId;
			
			
			@Column(name="role")
		    private String role;
			
//			@OneToMany(targetEntity = User_Details.class, mappedBy = "role_id", orphanRemoval = false, fetch = FetchType.LAZY)
//		    private List<User_Details> userDetails;

			public Role() {
				
			}			
			
			
			
			public int getRoleId() {
				return roleId;
			}



			public void setRoleId(int roleId) {
				this.roleId = roleId;
			}



			public String getRole() {
				return role;
			}

			public void setRole(String role) {
				this.role = role;
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
