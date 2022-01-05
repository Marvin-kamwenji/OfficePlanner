package com.officeplanner.officeplanner.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    /*-------------------------------------------------------------------------------------------*/
    //RELATIONSHIPS
    /*-------------------------------------------------------------------------------------------*/
    @ManyToMany(mappedBy = "meetings")
    private List<Boardroom> boardrooms;


}
