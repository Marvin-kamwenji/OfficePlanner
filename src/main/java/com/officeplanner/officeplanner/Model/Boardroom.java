package com.officeplanner.officeplanner.Model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="boardroom")
public class Boardroom {
    /*----------------------------------------------------------------------------------------------*/
    //BOARDROOM PROPERTIES
    /*----------------------------------------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardroom_id;
    private String boardroom_name;
    private Integer boardroom_capacity;


 /*---------------------------------------------------------------------------------------------------------*/
    //RELATIONSHIP BETWEEN BOARDROOM AND RESOURCES
 /*---------------------------------------------------------------------------------------------------------*/
   //BOARDROOMS AND RESOURCES MAPPING
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "boardroom_resources",
            joinColumns = @JoinColumn(name = "boardroom_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources;

   //BOARDROOMS AND MEETING MAPPING
   @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(
           name = "boardroom_meetings",
           joinColumns = @JoinColumn(name = "boardroom_id"),
           inverseJoinColumns = @JoinColumn(name = "meeting_id")
   )
   private Set<Meeting> meetings;


}
