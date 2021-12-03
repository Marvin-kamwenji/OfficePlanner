package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Service.BoardroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardroomController {

    /*---------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*---------------------------------------------------------------------------------------------------*/
    @Autowired
    private BoardroomService boardroomService;
}
