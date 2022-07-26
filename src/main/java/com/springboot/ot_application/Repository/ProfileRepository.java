package com.springboot.ot_application.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.ot_application.EntityModel.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE USER_DETAILS SET password=:newpassword WHERE employee_table_id=:employee_id",
			  nativeQuery = true)
	public void updatePassword(int employee_id,String newpassword);
	

}
