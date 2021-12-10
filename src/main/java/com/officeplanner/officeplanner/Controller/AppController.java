package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.User;
import com.officeplanner.officeplanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class AppController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dash").setViewName("dashboard");
    }
    /*-----------------------------------------------------------------------------------------*/
    //AUTOWIRING USER SERVICE
    //AUTOWIRING USER REPOSITORY
    /*-----------------------------------------------------------------------------------------*/
    @Autowired
    private UserService userService;
@Autowired
private UserRepository userRepository;

/*------------------------------------------------------------------------------------------------*/
    //MAPPING
/*------------------------------------------------------------------------------------------------*/
//LOGIN MAPPING
    //AND TO PREVENT THE USER FROM GOING BACK TO THE LOGIN PAGE
    //IF THEY ARE ALREADY LOGGED IN
    @GetMapping("/login")
    public String loginPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/dash";
    }

//INDEX MAPPING
    @RequestMapping (value = "/index")
    public String viewHomePage(Model model)
    {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "Registration";
    }

}