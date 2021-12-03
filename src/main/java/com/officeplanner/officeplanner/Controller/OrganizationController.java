package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Service.BoardroomService;
import com.officeplanner.officeplanner.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrganizationController {

    /*---------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*---------------------------------------------------------------------------------------------------*/
    @Autowired
    private OrganizationService organizationService;
}
