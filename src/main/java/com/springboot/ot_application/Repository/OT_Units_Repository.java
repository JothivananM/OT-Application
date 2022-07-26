package com.springboot.ot_application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.OT_Units;

public interface OT_Units_Repository extends JpaRepository<OT_Units, Integer> {
	
	
public List<OT_Units> findAllByOrderByUnitIdAsc();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE OT_UNITS SET is_active = false WHERE OT_UNIT_ID =:unitId",
			  nativeQuery = true)
	public void deleteUnitId(int unitId);
	
	
}
