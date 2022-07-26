package com.springboot.ot_application.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ot_application.EntityModel.OT_Details_List;

@Transactional
public interface OT_List_Repository extends JpaRepository<OT_Details_List, Integer> {
	
	@Query(value="select ot_detail_id, ot_date, ot_description, project_id, employee_id,"
			+ "ot_status_id, ot_unit_id from ot_details",
					nativeQuery = true)
	public List<OT_Details_List> getAll();
	
	
	@Query(value="select ot_detail_id, reject_reason, ot_date, ot_description,'' as employee_name, 0 as ot_count, ot_status.ot_action, ot_details.project_id, \r\n"
			+ "employee_id, ot_status_id, ot_unit_id,project.project_name \r\n"
			+ "					from ot_details \r\n"
			+ "					INNER JOIN project on ot_details.project_id = project.id\r\n"
			+ "					INNER JOIN ot_status on ot_details.ot_status_id = ot_status.status_table_id\r\n"
			+ "					where (:pId = 0 or ot_details.project_id=:pId) and\r\n"
			+ "					(:eId = 0 or ot_details.employee_id=:eId) and\r\n"
			+ "					(:sId = 0 or ot_status_id=:sId) and\r\n"
			+ "					ot_date BETWEEN :fromDate AND :toDate\r\n"
			+ "					and ot_details.is_active = 'true'\r\n"
			+ "					ORDER BY ot_detail_id DESC",
			nativeQuery = true)
	public List<OT_Details_List> getFilter(int pId, int eId, int sId, Date fromDate, Date toDate);
	
	
	@Query(value="select ot_detail_id, project.project_name,'' as reject_reason, user_details.employee_name, ot_date, \r\n"
			+ "		ot_units.ot_count, ot_status.status_table_id,\r\n"
			+ "		ot_description, ot_status.ot_action, ot_details.project_id, \r\n"
			+ "		employee_id, ot_status_id, ot_details.ot_unit_id\r\n"
			+ "					from ot_details \r\n"
			+ "					INNER JOIN ot_units on ot_details.ot_unit_id =ot_units.ot_unit_id \r\n"
			+ "					INNER JOIN user_details on ot_details.employee_id = user_details.employee_table_id\r\n"
			+ "					INNER JOIN project on ot_details.project_id = project.id\r\n"
			+ "					INNER JOIN ot_status on ot_details.ot_status_id = ot_status.status_table_id\r\n"
			+ "					where (:pId = 0 or ot_details.project_id=:pId) and\r\n"
			+ "					ot_status_id > 1 and\r\n"
			+ "					 (:eId = 0 or ot_details.employee_id=:eId) and\r\n"
			+ "					(:sId = 0 or ot_status_id=:sId) and\r\n"
			+ "					ot_date BETWEEN :fromDate AND :toDate\r\n"
			+ "					and ot_details.is_active = 'true'"
			+ "					ORDER BY ot_detail_id DESC",
			nativeQuery = true)
	public List<OT_Details_List> getFilterApproval(int pId,int eId, int sId, Date fromDate, Date toDate);
	
	@Modifying
	@Query(value = "UPDATE public.ot_details\r\n"
			+ "SET reject_reason=:reason\r\n"
			+ ",ot_status_id=:sId\r\n"
			+ "WHERE ot_detail_id =:id", nativeQuery = true)
	public void reasonSave(String reason, int sId, int id);
	
	@Modifying
	@Query(value="UPDATE public.ot_details"
			+ "	SET ot_status_id=:sId"
			+ "	WHERE ot_detail_id=:dId",
	nativeQuery = true)
	public void statusWithdraw(int sId, int dId);

	@Modifying
	@Query(value="SELECT  coalesce(sum(ot_units.ot_count),0)  FROM ot_details\n"
					+"inner join ot_units\n"
					+"on ot_details.ot_unit_id = ot_units.ot_unit_id\n"
					+"WHERE  ot_details.ot_date=:otDate and ot_details.employee_id = :empId and ot_details.ot_status_id<>4 and ot_details.is_active = true",
				nativeQuery = true)
	public List<Object[]> otDateCount(Date otDate, int empId);
}
