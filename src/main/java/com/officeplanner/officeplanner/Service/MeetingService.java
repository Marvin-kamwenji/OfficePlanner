package com.officeplanner.officeplanner.Service;

import com.officeplanner.officeplanner.Dao.MeetingRepository;
import com.officeplanner.officeplanner.Model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeetingService {

    /*-------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*-------------------------------------------------------------------------------------------*/

    @Autowired
    private MeetingRepository meetingRepository;


    /*--------------------------------------------------------------------------------------------*/
    //MAPPINGS
    /*--------------------------------------------------------------------------------------------*/
    //LISTING THE MEETINGS SET

    public List<Meeting> listAll(){
        return meetingRepository.findAll();
    }

    // SAVING MEETINGS
    public void saveMeeting(Meeting meeting){
        meetingRepository.save(meeting);
    }

    //UPDATING/EDITING MEETINGS
    public Meeting editedMeeting(Integer meeting_id){
        return meetingRepository.findById(meeting_id).get();
    }

    //DELETING MEETING
    public void deleteMeeting(Integer meeting_id){
        meetingRepository.deleteById(meeting_id);
    }
}
