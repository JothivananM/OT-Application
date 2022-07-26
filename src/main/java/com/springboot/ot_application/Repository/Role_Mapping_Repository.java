package com.springboot.ot_application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ot_application.EntityModel.Role_Mapping;

public interface Role_Mapping_Repository extends JpaRepository<Role_Mapping, Integer> {
	
	public List<Role_Mapping> findByRoleId(int roleId);

	
}
