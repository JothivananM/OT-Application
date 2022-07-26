package com.springboot.ot_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.Designations;


public interface Designations_Repository extends JpaRepository<Designations, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE DESIGNATION SET is_active = false WHERE DESIGNATION_ID=:designationId",
			  nativeQuery = true)
	public void deleteDesignation(int designationId);


}
