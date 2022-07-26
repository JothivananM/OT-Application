package com.springboot.ot_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.Role;

public interface Role_Repository extends JpaRepository<Role, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE ROLE SET is_active = false WHERE ROLE_ID=:roleId",
			  nativeQuery = true)
	public void deleteRole(int roleId);
}
