package com.springboot.ot_application.Repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.ot_application.EntityModel.Dashboard;

public interface DashboardsRepository extends JpaRepository<Dashboard, Integer> {



@Query(value = "SELECT dtl.ot_description, dtl.ot_detail_id as table_id,1 as id, p.project_name, p.created_date,u.ot_slots,\n"
+ "u.ot_count,s.ot_action,dtl.ot_date,p.is_active as project_status ,0 as ot_Total_Count,\n"
+ "user_details.employee_name,0 as employee_table_id,0 as ot_saved_Count, 0 as ot_submitted_Count,\n"
+ "0 as ot_approved_Count,0 as ot_rejected_Count,0 as user_status, 0 as project_status from ot_details dtl\n"
+ "	INNER JOIN ot_status s on dtl.ot_status_id = s.status_table_id\n"
+ "	INNER JOIN user_details on dtl.employee_id = user_details.employee_table_id\n"
+ "	INNER JOIN ot_units u on dtl.ot_unit_id = u.ot_unit_id\n"
+ "	INNER JOIN project p on dtl.project_id = p.id\n"
+ "	where  dtl.employee_id=:employee_id and (:project_id = 0 or p.id=:project_id) and\n"
+ "	dtl.ot_date BETWEEN :fromDate AND :toDate and dtl.is_active='true'", nativeQuery = true)
List<Dashboard> getAll1(int project_id,int employee_id, Date fromDate, Date toDate);


//@Query(value="SELECT ROW_NUMBER() OVER(ORDER BY usr.employee_table_id) as table_id,usr.employee_table_id, \r\n"
//	+ "usr.employee_name,p.id, p.project_name, p.created_date, \r\n"
//	+"usr.is_active as user_status ,p.is_active as project_status ,SUM(ot_count) as ot_Total_Count, \r\n"
//	+"SUM(case when dtl.ot_status_id=1 then ot_count else 0 end) as ot_saved_Count, \r\n"
//	+"SUM(case when dtl.ot_status_id=2 then ot_count else 0 end) as ot_submitted_Count, \r\n"
//	+"SUM(case when dtl.ot_status_id=3 then ot_count else 0 end) as ot_approved_Count,\r\n"
//	+"SUM(case when dtl.ot_status_id=4 then ot_count else 0 end) as ot_rejected_Count,\r\n"
//		+"0 as ot_count,now() as ot_date, '' as ot_action, '' as ot_slots,'' as ot_description from ot_details dtl INNER JOIN project p on \r\n"
//		+"dtl.project_id=p.id INNER JOIN ot_units units on dtl.ot_unit_id =units.ot_unit_id \r\n"
//		+"INNER JOIN user_details usr on dtl.employee_id=usr.employee_table_id \r\n"
//		 +"where dtl.is_active=true and (:project_id = 0 or p.id=:project_id) and \r\n"
//		 +"(:employee_table_id = 0 or usr.employee_table_id=:employee_table_id) and\r\n"
//		+"dtl.ot_date BETWEEN :fromDate AND :toDate \r\n"  		
//			+"	and(:is_active=0 or (:is_active=1 and p.is_active='true') or (:is_active=2 and p.is_active='false')) \r\n"
//		+"group by usr.employee_table_id,usr.employee_name,p.id,p.project_name,p.created_date,\r\n"
//		 +"usr.is_active,p.is_active\r\n",
//		 nativeQuery = true)
//      public List<Dashboard> getFilter(int project_id,int employee_table_id, int is_active, Date fromDate,Date toDate);


@Query(value="SELECT ROW_NUMBER() OVER(ORDER BY usr.employee_table_id) as table_id,usr.employee_table_id,\n"
		+ "	usr.employee_name, \n"
		+ "	usr.is_active as user_status ,SUM(ot_count) as ot_Total_Count,\n"
		+ "	SUM(case when dtl.ot_status_id=1 then ot_count else 0 end) as ot_saved_Count,\n"
		+ "	SUM(case when dtl.ot_status_id=2 then ot_count else 0 end) as ot_submitted_Count, \n"
		+ "	SUM(case when dtl.ot_status_id=3 then ot_count else 0 end) as ot_approved_Count,\n"
		+ "	SUM(case when dtl.ot_status_id=4 then ot_count else 0 end) as ot_rejected_Count,\n"
		+ "		0 as ot_count,now() as ot_date, 0 as id, '' as ot_action, ''as created_date, '' as project_name, '' as ot_slots,  0 as project_status, '' as ot_description\n"
		+ "from ot_details dtl INNER JOIN project p on dtl.project_id=p.id \n"
		+ "		 INNER JOIN ot_units units on dtl.ot_unit_id =units.ot_unit_id \n"
		+ "		INNER JOIN user_details usr on dtl.employee_id=usr.employee_table_id \n"
		+ "		 where dtl.is_active=true and (:project_id = 0 or p.id=:project_id) and \n"
		+"(:employee_table_id = 0 or usr.employee_table_id=:employee_table_id) and\r\n"
		+"dtl.ot_date BETWEEN :fromDate AND :toDate \r\n"  		
		+ "		group by usr.employee_table_id,usr.employee_name,\n"
		+ "		 usr.is_active",
			 nativeQuery = true)
	      public List<Dashboard> getFilter1(int project_id,int employee_table_id, Date fromDate,Date toDate);




	
}
