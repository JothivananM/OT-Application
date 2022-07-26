package com.springboot.ot_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.OT_Details_Insert;

public interface OT_Details_Insert_Repository extends JpaRepository<OT_Details_Insert, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE OT_DETAILS SET is_active = 'false' WHERE OT_DETAIL_ID=:otId",
			  nativeQuery = true)
	public void changeOt(int otId);
}
