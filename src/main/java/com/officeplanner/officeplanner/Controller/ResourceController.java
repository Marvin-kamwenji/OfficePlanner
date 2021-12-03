package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ResourceController {

    /*----------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*----------------------------------------------------------------------------------------------------*/
    @Autowired
    private ResourceService resourceService;
}
