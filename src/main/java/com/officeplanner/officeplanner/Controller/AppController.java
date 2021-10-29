package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
 @Autowired
    private UserService userService;

private final UserRepository userRepository;
@Autowired
    public AppController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping ("/index")
    public String viewHomePage()
    {
        return "index";
    }
    /*
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
     */
}