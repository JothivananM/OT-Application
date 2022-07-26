package com.springboot.ot_application.Repository;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.ot_application.EntityModel.User_Details;

public interface User_Details_Repository extends JpaRepository<User_Details, Integer> {
	
	public List<User_Details>findByCompanyEmailIdAndUserPassword(String username, String password);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER_DETAILS SET is_active = false WHERE EMPLOYEE_TABLE_ID=:userId",
			  nativeQuery = true)
	public void deleteUser(int userId);

	@Transactional
	@Modifying
	@Query(value="select * from user_details order by employee_code",
				nativeQuery = true)
	public List<User_Details> findAllEmployee();


	@Transactional
	@Query(value= "select concat(concat(concat(concat(concat(concat(role_id ,':'),employee_name),':'),employee_table_id),':'),designation.designation_name) as retval\r\n"
	+"from user_details inner join designation on  user_details.designation_id =designation.designation_id\r\n"
	+ "where company_email =:username and password=:password and user_details.is_active = 'true'",
			nativeQuery = true)
	public String getSession(String username, String password);

	
	@Transactional
	@Query(value= "select password\r\n"
	+"from user_details \r\n"
	+ "where company_email =:username and user_details.is_active = 'true'",
			nativeQuery = true)
	public String getSessionPassword(String username);

	@Transactional
	@Modifying
	@Query(value="update user_details set password = :newPassword where employee_table_id = :employeeId",
				nativeQuery = true)
	public void changeUserPassword(String newPassword, int employeeId);


}


