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
@Table(name="role_mapping")
public class Role_Mapping {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="role_table_id")
		    private int roleTableId;
			
			@Column(name="role_id")
		    private int roleId;
			
			@Column(name="url")
		    private String url;
			
			@Column(name="menu_name")
		    private String menuName;

			@Column(name = "icon")
			private String icon;
			

			public Role_Mapping() {
				
			}


			public int getRoleTableId() {
				return roleTableId;
			}


			public void setRoleTableId(int roleTableId) {
				this.roleTableId = roleTableId;
			}


			public int getRoleId() {
				return roleId;
			}


			public void setRoleId(int roleId) {
				this.roleId = roleId;
			}


			public String getUrl() {
				return url;
			}


			public void setUrl(String url) {
				this.url = url;
			}


			public String getMenuName() {
				return menuName;
			}


			public void setMenuName(String menuName) {
				this.menuName = menuName;
			}			
			
			public String getIcon() {
				return icon;
			}

			public void setIcn(String icon) {
				this.icon = icon;
			}
															
	}
