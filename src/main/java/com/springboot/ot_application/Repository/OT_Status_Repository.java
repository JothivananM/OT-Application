package com.springboot.ot_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.OT_Status;

public interface OT_Status_Repository extends JpaRepository<OT_Status, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE OT_STATUS SET is_active = false WHERE STATUS_TABLE_ID=:statusId",
			  nativeQuery = true)
	public void deleteOtStatus(int statusId);


	
}
