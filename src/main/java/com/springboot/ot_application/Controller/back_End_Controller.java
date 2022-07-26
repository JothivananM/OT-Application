package com.springboot.ot_application.Controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ot_application.EntityModel.Dashboard;
import com.springboot.ot_application.EntityModel.Designations;
import com.springboot.ot_application.EntityModel.Insert;
import com.springboot.ot_application.EntityModel.OT_Details;
import com.springboot.ot_application.EntityModel.OT_Details_Insert;
import com.springboot.ot_application.EntityModel.OT_Details_List;
import com.springboot.ot_application.EntityModel.OT_Status;
import com.springboot.ot_application.EntityModel.OT_Units;
import com.springboot.ot_application.EntityModel.ProjectList;
import com.springboot.ot_application.EntityModel.Role;
import com.springboot.ot_application.EntityModel.Role_Mapping;
import com.springboot.ot_application.EntityModel.User_Details;
import com.springboot.ot_application.Repository.DashboardsRepository;
import com.springboot.ot_application.Repository.Designations_Repository;
import com.springboot.ot_application.Repository.Insert_Repository;
import com.springboot.ot_application.Repository.OT_Details_Insert_Repository;
import com.springboot.ot_application.Repository.OT_Details_Repository;
import com.springboot.ot_application.Repository.OT_List_Repository;
import com.springboot.ot_application.Repository.OT_Status_Repository;
import com.springboot.ot_application.Repository.OT_Units_Repository;
import com.springboot.ot_application.Repository.ProfileRepository;
import com.springboot.ot_application.Repository.Project_List_Repopsitory;
import com.springboot.ot_application.Repository.Role_Mapping_Repository;
import com.springboot.ot_application.Repository.Role_Repository;
import com.springboot.ot_application.Repository.User_Details_Repository;


@RestController
public class back_End_Controller { 
	
	@Autowired
	private User_Details_Repository userRepository;
	
	@Autowired
	private Project_List_Repopsitory project_List_Repopsitory;
	
	@Autowired
	private Insert_Repository insert_Repository;
	
	@Autowired
	private Designations_Repository designationRepository;
	
	@Autowired
	private Role_Repository roleRepository;
	
	@Autowired
	private OT_Status_Repository otStatusRepository;
	
	@Autowired
	private OT_Units_Repository otUnitsRepository;
	
	@Autowired
	private OT_Details_Repository otDetailsRepository;
	
	@Autowired
	private OT_Details_Insert_Repository otDetailsInsertRepository;
	
	
	@Autowired
	private DashboardsRepository dashboardsRepository;	
	
		
	@Autowired
	private OT_List_Repository listRepo;

	@Autowired
	private Role_Mapping_Repository roleMapping_Repository;

	@Autowired
	private ProfileRepository profileRepository;
	

	@GetMapping("mapping/")
	public List<Role_Mapping> getRoleId(@RequestParam("roleId") int roleId){
		return roleMapping_Repository.findByRoleId(roleId);
	}
	
	@GetMapping("ot/list")
	public List<OT_Details_List> getAll() {
		return listRepo.getAll();
	}

	@GetMapping("otDetail/checkcount")
	public Object checkCount (@RequestParam ("ot_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date otDate,
											@RequestParam ("employee_id") int employeeId){
			return listRepo.otDateCount(otDate, employeeId);
		}

	@GetMapping("ot/filter")
	public List<OT_Details_List> getOtFilter(@RequestParam("pId") int pId, @RequestParam("eId") int eId,
			@RequestParam("sId") int sId, 
	@RequestParam("fromDate") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
    @RequestParam("toDate") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate ){
		return listRepo.getFilter(pId, eId, sId, fromDate, toDate);
		
	}
	
	//OT APPROVAL ENTITY
		@GetMapping("ot/approval")
		public List<OT_Details_List> getFilterApproval(@RequestParam("pId") int pId,@RequestParam("eId") int eId,
				@RequestParam("sId") int sId, 
		@RequestParam("fromDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	    @RequestParam("toDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate ){
			return listRepo.getFilterApproval(pId, eId, sId, fromDate, toDate);
			
		}
		
		@PutMapping("ot/reject")
		public void saveReason(@RequestParam("reason")String reason, 
				@RequestParam("sId") int sId, @RequestParam("id") int id) {
			 listRepo.reasonSave(reason, sId, id);
		}
		
		@PutMapping("ot/withdraw")
		public void statusWithdraw (@RequestParam("sId") int sId, @RequestParam("dId") int dId) {
			listRepo.statusWithdraw(sId, dId);
		}
	
	//USER ENTITY
	
	@GetMapping("user/list")
	
    public List<User_Details> getAllUsers() {
        return userRepository.findAllEmployee();
    }

	@GetMapping("user/")
	public List<User_Details> getUsernamePassword(@RequestParam("username")String username, 
														@RequestParam("password")String password, HttpSession session) {

		List<User_Details> theUser = userRepository.findByCompanyEmailIdAndUserPassword(username, password);

		return theUser;
	}
    
    
    @GetMapping("user/{userId}")
	public Optional<User_Details> getUser(@PathVariable int userId) {
		Optional<User_Details> theUser = userRepository.findById(userId);	
		
		
		if(theUser == null) {
			throw new RuntimeException ("No employee @ " + userId);
		}
		
		return theUser;
	}	
		
	@PostMapping("user/save")
	public Insert addUser(@RequestBody Insert theInsert) {
		
		theInsert.setId(0);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String password = theInsert.getPassword();
		
		String encodedPassword = passwordEncoder.encode(password);
	
		theInsert.setPassword(encodedPassword);
		
		insert_Repository.save(theInsert);
		
		return theInsert;
	}
	
	@PutMapping("user/update")
	public Insert updateUser (@RequestBody Insert update) {
		insert_Repository.save(update);
		return update;
	}
	
	@PutMapping("user/delete/{isActive}/{Id}")
	public int deleteUser (@PathVariable Boolean isActive,@PathVariable int Id) {			
   		return insert_Repository.deleteEmployee(isActive, Id);
		
	}

	@PutMapping("user/update-password")
	public void userPassword(@RequestParam("newPassword") String newPassword, @RequestParam("employeeId") int employeeId){
		
		userRepository.changeUserPassword(newPassword, employeeId);
		
	}

	@GetMapping("user/login")

	public String getSession(@RequestParam("username") String username,@RequestParam("password") String password, HttpSession session){

			
		String b = userRepository.getSession(username,password);
		
		
		return b;
		}
	
	//duplicate check for employee already in table
	
	@GetMapping("employeeList/emailcheck")
   	public List<Object[]> addDuplicateCheckmailid(@RequestParam("company_email") String company_email)
	{
        return insert_Repository.AddduplicateCheckMail(company_email);
    }
   	
   	@GetMapping("employeeList/mobilenocheck")
   	public List<Object[]> addDuplicateCheckmobileno(@RequestParam("mobile_no")String mobile_no)
	{
        return insert_Repository.AddduplicateCheckMobileno(mobile_no);
    }

	@GetMapping("employeeList/employeeCodecheck")
   	public List<Object[]> addDuplicateEmployeeCode(@RequestParam("employee_code") String employee_code)
	{
        return insert_Repository.AddduplicateCheckEmployeeCode(employee_code);
    }
   	
   	@GetMapping("updateEmployeeList/emailcheck")
   	public List<Object[]> updateCheckmailid(@RequestParam("company_email") String company_email,
   			@RequestParam("employee_table_id") int employee_table_id)
	{
        return insert_Repository.UpdateCheckMail(company_email,employee_table_id);
    }
   	
   	@GetMapping("updateEmployeeList/mobilenocheck")
	public List<Object[]> updateCheckmobileno(@RequestParam("mobile_no") String mobile_no,
   			@RequestParam("employee_table_id") int employee_table_id)
	{
        return insert_Repository.UpdateCheckMobileno(mobile_no,employee_table_id);
    }

	@GetMapping("updateEmployeeList/employeeCodecheck")
	public List<Object[]> updateCheckEmployeeCode(@RequestParam("employee_code") String employee_code,
   			@RequestParam("employee_table_id") int employee_table_id)
	{
        return insert_Repository.UpdateCheckEmployeeCode(employee_code,employee_table_id);
    }


    
  
   //DESIGNATION ENTITY
    
    @GetMapping("designation/list")
    public List<Designations> getAllDesignation() {
        return designationRepository.findAll();
    }
    
	
	 @GetMapping("designation/{designationId}")
	    public Optional<Designations> getDesignation(@PathVariable int designationId) {
			Optional<Designations> thedesignation = designationRepository.findById(designationId);
			
			if(thedesignation == null) {
				throw new RuntimeException ("No Designation @ " + designationId);
			}
			
			return thedesignation;
		}
	 
	 
	 @PostMapping("designation/save")
	 public Designations addDesignation (@RequestBody Designations theDesignation) {
		 
		 theDesignation.setDesignationId(0);
		 
		 designationRepository.save(theDesignation);
		 
		 return theDesignation;
	 }
	 
	 
	 @PutMapping("designation/update")
		public Designations updateDesignation (@RequestBody Designations update) {
			
			designationRepository.save(update);
			return update;
		}
	 
	 
	 @PutMapping("designation/delete/{id}")
		public int deleteDesignation (@PathVariable int id) {
			
			designationRepository.deleteDesignation(id);
			return id;
		}	 
	 
	 
	 //ROLE ENTITY
	 
	 @GetMapping("role/list")
	    public List<Role> getAllRole(){
	    	return roleRepository.findAll();
	    }
	    	 
	 @GetMapping("role/{roleId}")
	 public Optional<Role> getRole(@PathVariable int roleId){
		 Optional<Role> theRole = roleRepository.findById(roleId);
		 
		 if(theRole == null) {
			 throw new RuntimeException ("No Role @ " + roleId);
		 }
		 return theRole;
	 }
	 
	 
	 @PostMapping("role/save")
	 public Role addRole (@RequestBody Role theRole) {
		 
		 theRole.setRoleId(0);
		 
		 roleRepository.save(theRole);
		 
		 return theRole;
	 }
	 
	@PutMapping("role/update")
	public Role updateRole (@RequestBody Role update) {
		
				roleRepository.save(update);
				return update;		
		}
		
	@PutMapping("role/delete/{id}")
	public int deleteRole (@PathVariable int id) {
			
			roleRepository.deleteRole(id);
			return id;
		}	
	
	
	//PROJECT ENTITY
	
	@GetMapping("project/list")
    public List<ProjectList> getProject() {
        return project_List_Repopsitory.getAllProjects();
    }
	
	
    @GetMapping("project/{projectId}")
   	public Optional<ProjectList> getProject(@PathVariable int projectId) {
   		Optional<ProjectList> theProject = project_List_Repopsitory.findById(projectId);
   		
   		if(theProject == null) {
   			throw new RuntimeException ("No employee @ " + projectId);
   		}
   		
   		return theProject;
   	}
   	
   	@PostMapping("project/save")
   	public ProjectList addProject(@RequestBody ProjectList theProjectList) {
   		
   		theProjectList.setId(0);

   		project_List_Repopsitory.save(theProjectList);
   		
   		return theProjectList;
   	}
   	
   	@PutMapping("project/update")
   	public ProjectList change(@RequestBody ProjectList theProjectList) {
   		
		project_List_Repopsitory.save(theProjectList);
   		
   		return theProjectList;
   	}
   	
   	
   	@DeleteMapping("project/{projectId}")
   	public String deleteProject(@PathVariable int projectId) {
   		
   		Optional<ProjectList> temp = project_List_Repopsitory.findById(projectId);
   		
   		if(temp == null) {
   			throw new RuntimeException("Project Not Found @ " + projectId);
   		}
   		
   		project_List_Repopsitory.deleteById(projectId);
   		
   		return "Deleted Project id - " + projectId;
   	}
   	
   	
	@PutMapping("project/delete/{Status}/{id}")
	public int deleteProjectList (@PathVariable Boolean Status,@PathVariable int id) {			
   		return project_List_Repopsitory.deleteProject(Status, id);
		 
	}
   	
	//duplicate check project already in table
	
	@GetMapping("projectList/namecheck")
   	public List<Object[]> addDuplicateCheckname(@RequestParam("project_name") String project_name)
	{
        return project_List_Repopsitory.AddDuplicateCheckProject(project_name);
    }
   	
   	@GetMapping("projectList/idcheck")
   	public List<Object[]> addDuplicateCheckid(@RequestParam("project_id")String project_id)
	{
        return project_List_Repopsitory.AddDuplicateCheckprojectCode(project_id);
    }
   	
   	@GetMapping("updateProjectList/namecheck")
   	public List<Object[]> updateCheckname(@RequestParam("project_name") String company_email,
   			@RequestParam("id") int id)
	{
        return project_List_Repopsitory.UpdateCheckproject(company_email,id);
    }
   	
   	@GetMapping("updateProjectList/idcheck")
	public List<Object[]> updateCheckid(@RequestParam("project_id") String mobile_no,
   			@RequestParam("id") int id)
	{
        return project_List_Repopsitory.UpdateCheckid(mobile_no,id);
    }
	
   	
	//OT_STATUS ENTITY      	
    
		 @GetMapping("otStatus/list")
		    public List<OT_Status> getAllStatus(){
		    	return otStatusRepository.findAll();
		    }
		    
	    @GetMapping("otStatus/{statusId}")
	   	public Optional<OT_Status> getStatus(@PathVariable int statusId) {

	    	Optional<OT_Status> theStatus = otStatusRepository.findById(statusId);
	    	
	    	if(theStatus == null) {
	    		throw new RuntimeException ("No Status @ " + statusId);
	    	}
	    	
	    	return theStatus;
	    	
	    	}
	   	
	    @PostMapping("otStatus/save")
		 public OT_Status addStatus (@RequestBody OT_Status theStatus) {
			 
			 theStatus.setStatusId(0);
			 
			 otStatusRepository.save(theStatus);
			 
			 return theStatus;
		 }
	    
	    
		 @PutMapping("otStatus/update")
			public OT_Status updateStatus (@RequestBody OT_Status update) {
				
			 otStatusRepository.save(update);
			 
				return update;
			}
		 
		 
		 @PutMapping("otStatus/delete/{id}")
			public int deleteStatus (@PathVariable int id) {
				
			 otStatusRepository.deleteOtStatus(id);
				
				return id;
			}
		 
		    
			//OT_UNIT ENTITY      	
		    
		 @GetMapping("otUnits/list")
		    public List<OT_Units> getAllUnits(){
		    	return otUnitsRepository.findAllByOrderByUnitIdAsc();
		    }
		    
		    @GetMapping("otUnits/{statusId}")
		   	public Optional<OT_Units> getUnit(@PathVariable int unitId) {

		    	Optional<OT_Units> theUnit = otUnitsRepository.findById(unitId);
		    	
		    	if(theUnit == null) {
		    		throw new RuntimeException ("No Unit @ " + unitId);
		    	}
		    	
		    	return theUnit;
		    	
		    	}
		   	
	   	
		    @PostMapping("otUnits/save")
			 public OT_Units addUnit (@RequestBody OT_Units theUnit) {
				 
		    	theUnit.setUnitId(0);
				 
		    	otUnitsRepository.save(theUnit);
				 
				 return theUnit;
			 }
		    
		    @PutMapping("otUnits/update")
			public OT_Units updateUnit (@RequestBody OT_Units update) {
				
		    	otUnitsRepository.save(update);
			 
				return update;
			}
		    
		    
		 @PutMapping("otUnits/delete/{id}")
			public int deleteUnit (@PathVariable int id) {
				
			 otUnitsRepository.deleteUnitId(id);
				
				return id;
			}	 
		 
		 
		 //OT_DETAILS_ENTITY
		 
		 @GetMapping("otDetails/list")
		    public List<OT_Details> getAllDetails(){
		    	return otDetailsRepository.customeSelect();
		    }
	    
		    @GetMapping("otDetails/{otId}")
			public Optional<OT_Details> getOt(@PathVariable int otId) {
				Optional<OT_Details> theOt = otDetailsRepository.findById(otId);
				
				if(theOt == null) {
					throw new RuntimeException ("No OT @ " + otId);
				}
				
				return theOt;
			}
			
			@PostMapping("otDetails/save")
			public OT_Details_Insert addOt(@RequestBody OT_Details_Insert theInsert) {
				
				theInsert.setDetailId(0);
				
				otDetailsInsertRepository.save(theInsert);
				
				return theInsert;
			}
			
			
			@PutMapping("otDetails/update")
			public OT_Details_Insert updateOt (@RequestBody OT_Details_Insert update) {
				otDetailsInsertRepository.save(update);
				return update;
			}
			
			@PutMapping("otDetails/delete/{id}")
			public int changeOt (@PathVariable int id) {
				
				otDetailsInsertRepository.changeOt(id);
				
				return id;
			}
			
			@GetMapping("otDetails/getDate")
			public List<OT_Details> getDate() {
				return otDetailsRepository.dateRetrieve();
			}
			
			@PutMapping("otDetails/updateStatus")
			public void updateStatus(@RequestParam("sId") int sId, @RequestParam("dId") int dId) {
				otDetailsRepository.updateStatus(sId, dId);
			}

			@GetMapping("otDetails/checkDate")
			public List<Object[]> checkDate (@RequestParam ("otDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date otDate, 
												@RequestParam ("employeeId") int employeeId){
				return otDetailsRepository.checkOtDate(otDate, employeeId);
			} 
			
			//PROJECT ASSIGN ENTITY
			
	   	
	   	
	   	@GetMapping("userdashboard/lists/")
		public List<Dashboard> dashboard(@RequestParam("project_id") int project_id,
				@RequestParam("employee_id") int employee_id,
		@RequestParam("fromDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	    @RequestParam("toDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate )
		
	   	{
			return dashboardsRepository.getAll1(project_id,employee_id, fromDate, toDate);
			
		}
	   	
	   	
	  
	   	@GetMapping("dashboard/filters/")
		public List<Dashboard> getFilter(@RequestParam("project_id") int project_id,
		@RequestParam("employee_table_id") int employee_table_id,
		@RequestParam("fromDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	    @RequestParam("toDate") 
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate )
		
	   	{
			return dashboardsRepository.getFilter1(project_id,employee_table_id, fromDate, toDate);
			
		}
	   	
		//Session
		@GetMapping("/session")
		public String getSession(HttpSession session , @RequestParam("sessionRequest") int sessionRequest){
			if (sessionRequest == 1){
				return session.getAttribute("role_id").toString();
			}else if(sessionRequest == 2){
				return session.getAttribute("employee_name").toString();
			}else if(sessionRequest == 3){
			return  session.getAttribute("employee_table_id").toString();
			}
			else if(sessionRequest == 4){
				return  session.getAttribute("designation_name").toString();
				}
			else{
			return "Session Not Found!";
			}
		}
		
		//change password api
		@PutMapping("user/changepassword")
		public void savePassword(@RequestParam("employee_id") int employee_id,
				@RequestParam("newpassword")String changepassword) {
			
			BCryptPasswordEncoder passwordChangeEncoder = new BCryptPasswordEncoder();
			
			String encodedChangePassword = passwordChangeEncoder.encode(changepassword);
			
			profileRepository.updatePassword(employee_id,encodedChangePassword);
		}
				
}
	
	
	
