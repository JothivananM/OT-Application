package com.springboot.ot_application.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.Insert;




public interface Insert_Repository extends JpaRepository<Insert, Integer> {
	
	// @Transactional
	// @Modifying
	// @Query(value = "UPDATE USER_DETAILS SET is_active = false WHERE EMPLOYEE_TABLE_ID=:userId",
	// 		  nativeQuery = true)
	// public void deleteUser(int userId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE USER_DETAILS SET is_active=:isActive WHERE employee_table_id=:Id",
			  nativeQuery = true)
	public int deleteEmployee(Boolean isActive,int Id);
	
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  company_email=:company_email",
			nativeQuery = true)
	List<Object[]> AddduplicateCheckMail(String company_email);

	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  mobile_no=:mobile_no",
			nativeQuery = true)
	List<Object[]> AddduplicateCheckMobileno(String mobile_no);

	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  employee_code=:employee_code",
			nativeQuery = true)
	List<Object[]> AddduplicateCheckEmployeeCode(String employee_code);
	
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  company_email=:company_email and employee_table_id<>:employee_table_id",
			nativeQuery = true)
	List<Object[]> UpdateCheckMail(String company_email,int employee_table_id);
	
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  mobile_no=:mobile_no and employee_table_id<>:employee_table_id",
			nativeQuery = true)
	List<Object[]> UpdateCheckMobileno(String mobile_no,int employee_table_id);

	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM user_details WHERE  employee_code=:employee_code and employee_table_id=:employee_table_id",
			nativeQuery = true)
	List<Object[]> UpdateCheckEmployeeCode(String employee_code,int employee_table_id);

}
