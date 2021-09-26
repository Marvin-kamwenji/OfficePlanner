package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
@Autowired
    private UserRepository userRepository;

@GetMapping("")
    public String viewHomePage()
    {
        return "index";
    }

}
