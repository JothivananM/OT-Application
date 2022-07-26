package com.springboot.ot_application.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.OT_Details;

public interface OT_Details_Repository extends JpaRepository<OT_Details, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "select *\r\n"
			+ "from ot_details\r\n"			
			+ "inner join project on ot_details.project_id = project.id\r\n"
			+ "inner join user_details on user_details.employee_table_id =  ot_details.employee_id\r\n"
			+ "inner join ot_status on ot_details.ot_status_id = ot_status.status_table_id\r\n"
			+ "where ot_details.is_active = 'true'",
			  nativeQuery = true)
	public List<OT_Details> dateRetrieve();
	
	@Transactional
	@Modifying
	@Query(value="select * from ot_details\r\n"
			+ "where is_active = 'true'"
			+ "ORDER BY ot_detail_id DESC", nativeQuery = true)
	public List<OT_Details> customeSelect();
	
	@Transactional
	@Modifying
	@Query(value="update ot_details\r\n"
			+ "set ot_status_id = :sId\r\n"
			+ "where ot_detail_id = :dId", nativeQuery = true)
	public void updateStatus(int sId, int dId);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM ot_details WHERE  ot_date=:otDate and employee_id =:employeeId and is_active = true",
				nativeQuery = true)

	public List<Object[]> checkOtDate(Date otDate, int employeeId);
	
	
}
