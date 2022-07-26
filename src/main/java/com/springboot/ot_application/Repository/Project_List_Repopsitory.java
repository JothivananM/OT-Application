package com.springboot.ot_application.Repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.springboot.ot_application.EntityModel.ProjectList;

public interface Project_List_Repopsitory extends JpaRepository<ProjectList, Integer> {

	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM project order by id desc",
			  nativeQuery = true)
	List<ProjectList> getAllProjects();
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE project SET is_active=:isActive WHERE id=:Id",
			  nativeQuery = true)
	public int deleteProject(Boolean isActive,int Id);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM project WHERE  project_name=:project_name",
			nativeQuery = true)
	List<Object[]> AddDuplicateCheckProject(String project_name);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM project WHERE  project_id=:project_id",
			nativeQuery = true)
	List<Object[]> AddDuplicateCheckprojectCode(String project_id);
	
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM project WHERE  project_name<>:project_name and id=:id",
			nativeQuery = true)
	List<Object[]> UpdateCheckproject(String project_name,int id);
	
	
	@Transactional
	@Modifying
	@Query(value = "SELECT COUNT(*) FROM project WHERE  project_id<>:project_id and id=:id",
			nativeQuery = true)
	List<Object[]> UpdateCheckid(String project_id,int id);

	
}
