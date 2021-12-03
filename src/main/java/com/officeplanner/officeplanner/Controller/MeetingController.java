package com.officeplanner.officeplanner.Controller;

import com.officeplanner.officeplanner.Model.Meeting;
import com.officeplanner.officeplanner.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MeetingController {
    /*------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*------------------------------------------------------------------------------------------*/
    @Autowired
    private MeetingService meetingService;

    /*------------------------------------------------------------------------------------------*/
    //MAPPING
    /*-------------------------------------------------------------------------------------------*/
    //VIEW MEETINGS
    @RequestMapping("/EventManagement")
    public String ViewMeetings (Model model)
    {
        List<Meeting> meetingList = meetingService.listAll();
        model.addAttribute("meetingList", meetingList);

        return "EventManagement";
    }

    //CREATE NEW MEETINGS

    @RequestMapping("/newmeeting")
    public String createMeeting(Model model){
        Meeting meeting = new Meeting();
        model.addAttribute("meeting", meeting);

        return "createMeeting";
    }

    //SAVING THE NEW MEETING
    @PostMapping( "/savemeeting")
    public String saveMeeting(Meeting meeting){
        meetingService.saveMeeting(meeting);

        return "EventManagement";
    }

    //EDITING/UPDATING MEETING
    @RequestMapping("/editmeeting/{meeting_id}")
    public ModelAndView editedMeeting(@PathVariable(name = "meeting_id") int meeting_id){
        ModelAndView mav = new ModelAndView("editMeeting");
        Meeting meeting = meetingService.editedMeeting(meeting_id);
        mav.addObject("meeting", meeting);

        return mav;
    }

    //DELETING MEETING
    @RequestMapping("/deletemeeting/{meeting_id}")
    public String deleteMeeting(@PathVariable(name = "meeting_id") Integer meeting_id){
        meetingService.deleteMeeting(meeting_id);
        return "EventManagement";
    }

}
