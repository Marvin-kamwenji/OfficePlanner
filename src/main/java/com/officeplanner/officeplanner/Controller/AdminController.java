package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.User;
import com.officeplanner.officeplanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
/*---------------------------------------------------------------------------------------------*/
//AUTOWIRING USER REPOSITORY
//AUTOWIRING USER SERVICE
/*----------------------------------------------------------------------------------------------*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

/*------------------------------------------------------------------------------------------------*/
//MAPPINGS
/*------------------------------------------------------------------------------------------------*/
//MOVING TO THE DASHBOARD PAGE
    @GetMapping("/dashboard")
    public String index(){
        return "adminDashboard";
    }

//MOVING TO THE DASHBOARD PAGE
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
/*
//MOVING TO THE EVENT MANAGEMENT PAGE
    @RequestMapping("/EventManagement")
    public String EventManagement(){
        return "EventManagement";
    }
*/

//MOVING TO THE NOTIFICATIONS PAGE
    @RequestMapping("/notifications")
    public String notification(){
        return "notifications";
    }

//MOVING TO THE ORGANIZATION MANAGEMENT PAGE
    @RequestMapping("/OrganizationManagement")
    public String OrganizationManagement(){
        return "OrganizationManagement";
    }

//MOVING TO THE PROFILE PAGE
    @RequestMapping("/profile")
    public String profile(){
        return "profile";
    }

//MOVING TO THE SIGN OUT PAGE
    @RequestMapping("/SignOut")
    public String SignOut(){
        return "SignOut";
    }

    /*
//MOVING TO THE USER MANAGEMENT PAGE
    @RequestMapping ("/UserManagement")
    public String UserManagement(){
        return "UserManagement";
    }
*/
/*---------------------------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------------*/
         // ADMIN FUNCTIONS
/*----------------------------------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------*/

}
