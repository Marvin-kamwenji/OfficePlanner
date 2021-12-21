package com.officeplanner.officeplanner.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "meeting")
public class Meeting {


    /*-------------------------------------------------------------------------------------------*/
    //MEETING PROPERTIES
    /*-------------------------------------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meeting_id;
    private String topic;
    private String description;
    private Integer attendees;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    /*--------------------------------------------------------------------------------------------*/
    //CONSTRUCTORS
    /*---------------------------------------------------------------------------------------------*/
    //NO ARGS CONSTRUCTOR

    public Meeting() {
    }

    //CONSTRUCTOR WITH ARGS

    public Meeting(Integer meeting_id, String topic, LocalDate startDate, LocalTime startTime, LocalTime endTime, String description, Integer attendees) {
        this.meeting_id = meeting_id;
        this.topic = topic;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.attendees = attendees;
    }
    /*-------------------------------------------------------------------------------------------*/
    //RELATIONSHIPS
    /*-------------------------------------------------------------------------------------------*/
    @ManyToMany(mappedBy = "meetings")
    private List<Boardroom> boardrooms;

    /*-------------------------------------------------------------------------------------------------*/
    //GETTERS AND SETTERS
    /*-------------------------------------------------------------------------------------------------*/

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAttendees() {
        return attendees;
    }

    public void setAttendees(Integer attendees) {
        this.attendees = attendees;
    }

    /*----------------------------------------------------------------------------------------------*/
    //TO STRING
    /*----------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Meeting{" +
                "meeting_id=" + meeting_id +
                ", topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", attendees=" + attendees +
                '}';
    }
}
