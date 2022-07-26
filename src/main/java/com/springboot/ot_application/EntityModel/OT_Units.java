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
@Table(name="ot_units")
public class OT_Units {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="ot_unit_id")
		    private int unitId;
			
			@Column(name="ot_desc")
		    private String otDesc;
			
			@Column(name="ot_slots")
		    private String otSlots;
			
			@Column(name="ot_count")
		    private int otCount;
			

			public OT_Units() {
				
			}


			public int getUnitId() {
				return unitId;
			}


			public void setUnitId(int unitId) {
				this.unitId = unitId;
			}


			public String getOtDesc() {
				return otDesc;
			}


			public void setOtDesc(String otDesc) {
				this.otDesc = otDesc;
			}


			public String getOtSlots() {
				return otSlots;
			}


			public void setOtSlots(String otSlots) {
				this.otSlots = otSlots;
			}


			public int getOtCount() {
				return otCount;
			}


			public void setOtCount(int otCount) {
				this.otCount = otCount;
			}


												
	}
