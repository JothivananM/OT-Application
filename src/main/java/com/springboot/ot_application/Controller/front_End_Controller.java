package com.springboot.ot_application.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import com.springboot.ot_application.Repository.User_Details_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@SessionAttributes("{username}")
public class front_End_Controller {


	@Autowired
	private User_Details_Repository userRepository;

	@RequestMapping("")
	public String home(){
		return "login-page";
	}

	@RequestMapping(value="/index" , method = RequestMethod.GET)
	public String index() {
		return "login-page";
	}


	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public String login(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		HttpSession session,
		ModelMap modelMap)  {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			String thePassword = userRepository.getSessionPassword(username);
			
			boolean isPasswordMatch = passwordEncoder.matches(password, thePassword);
			
			if(isPasswordMatch)
			{
			 String theUser = userRepository.getSession(username, thePassword);
			if(theUser==null) {
				
				// modelMap.put("error", "Invalid Credentials!");
				modelMap.put("error", " ");
				return "login-page";
			}
			else {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				System.out.println(dtf.format(now));
				session.setAttribute("time",now);
				modelMap.put("error", "");
				session.setAttribute("username", theUser);
		
					String[] list = theUser.split(":");
					modelMap.put("error", list);
						session.setAttribute("role_id", list[0]);
						session.setAttribute("employee_name", list[1]);
						session.setAttribute("employee_table_id", list[2]);
						session.setAttribute("designation_name", list[3]);
						
						
					if(list[0].equals("1"))
					{
						return "Admin-Dashboard";}					
					else if(list[0].equals("2"))
					{
						return "Employee-Dashboard";}	
					else
					{
					
						return "login-page";}	
			}
			}
			else
			{
				modelMap.put("error", " ");
				return "login-page";
			}
		}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.removeAttribute("role_id");
		session.removeAttribute("employee_name");
		session.removeAttribute("employee_table_id");
		session.removeAttribute("designation_name");

		session.setAttribute("role_id", null);
		session.setAttribute("employee_name", null);
		session.setAttribute("employee_table_id", null);
		session.setAttribute("designation_name", null);

		return "login-page";
	}

	@RequestMapping("/Profile")
	public String profile(HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else
		{
		return "Profile";
	}}
	
	@RequestMapping("/navbar")
	public String navbar() {
		return "Navbar";
	}
		
	@RequestMapping("adminDashboard")
	public String adminDashboard(HttpSession session) {
		
		if(session.getAttribute("role_id")==null || session.getAttribute("role_id").equals(""))
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("2")){
			return"access-denied";
		}
		else{
			return "Admin-Dashboard";
		}
		
	}
	
	@RequestMapping("userDashboard")
	public String userDashboard(HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("1")){
			return"access-denied";
		}else{
			return "Employee-Dashboard";
		}		
		
	}
	
	
	@RequestMapping("userList")
	public String UserList(HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("2")){
			return"access-denied";
		}else{
			return "Employee-List";
		}
		
	}
	
	@RequestMapping("projectList")
	public String projectList(HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("2")){
			return"access-denied";
		}else{
			return "Project-List";
		}
		
	}
	
	@RequestMapping("ot-management")
	public String otManagement (HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("1")){
			return"access-denied";
		}else{
			return "OT-Management";
		}
		
	}
	
	@RequestMapping("otApproval")
	public String otApproval(HttpSession session) {
		if(session.getAttribute("role_id")==null)
		{
			return "login-page";
		}
		else if(session.getAttribute("role_id").equals("2")){
			return"access-denied";
		}else{
			return "OT-Approval";
		}
		
	}

	
}
