package com.springboot.ot_application.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.ot_application.EntityModel.User_Details_Without_Join;

public interface User_Details_Without_Join_Repository extends JpaRepository<User_Details_Without_Join, Integer> {
	

}


