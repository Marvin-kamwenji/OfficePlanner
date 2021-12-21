package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Authentication.CustomUserDetails;
import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.Role;
import com.officeplanner.officeplanner.Model.User;
import com.officeplanner.officeplanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class UserController {

    /*-----------------------------------------------------------------------------------*/
    //AUTOWIRING THE USER SERVICE
    //AUTOWIRING THE USER REPOSITORY
    /*------------------------------------------------------------------------------------*/
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /*-------------------------------------------------------------------------------------------*/
    //MAPPINGS
    //LISTING USERS IN THE USER MANAGEMENT PAGE

/*---------------------------------------------------------------------------------------------*/
                   /*FOR NEW USERS BEING REGISTERED*/

    //VIEWING USERS
    @RequestMapping("/UserManagement")
    public String viewUsersList(Model model) {

        //New Registered Users
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        //Current Users
        List<User> currentUsers = userService.listCurrentUsers();
        model.addAttribute("currentUsers", currentUsers);
        return "UserManagement";
    }

  //CREATING NEW USER
    @RequestMapping("/new")
    public String createNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "newUser";
    }


    //SAVING THE NEW USER THAT HAS BEEN CREATED

    @PostMapping( "/save")
    public String saveUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String encoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        userService.saveUser(user);

        return "redirect:/UserManagement";
    }



// EDITING THE USER
    @GetMapping("/edit/{user_id}")
    /*
    public String editUser(@PathVariable("user_id") int user_id, Model model){
        User user = userService.editedUser(user_id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "editUser";
    }
*/
    public ModelAndView editUser(@PathVariable(name="user_id")int user_id){
        ModelAndView umv = new ModelAndView("editUser");
        User user = userService.editedUser(user_id);
        List<Role> listRoles = userService.listRoles();
        umv.addObject("user",user);
        umv.addObject("listRoles", listRoles);
        return umv;
    }



//DELETING THE USER

    @RequestMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable(name="user_id")Integer user_id)
    {
        userService.deleteUser(user_id);
        return "redirect:/UserManagement";

    }

/*-------------------------------------------------------------------------------------------------------*/
    //FOR REGISTERING USERS
/*-------------------------------------------------------------------------------------------------------*/
    @GetMapping("/processRegister")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "Registration";
    }

    @PostMapping("/processRegister")
    public String processRegister(User user, HttpServletRequest request)
    throws UnsupportedEncodingException, MessagingException
    {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    userService.register(user, getSiteURL(request));

    userRepository.save(user);

    return "register_success";
  }

  private String getSiteURL (HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(),"");

  }

  @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
     if (userService.verify(code)){
         return "verify_success";
     }  else {
         return "verify_fail";
     }
  }


}
