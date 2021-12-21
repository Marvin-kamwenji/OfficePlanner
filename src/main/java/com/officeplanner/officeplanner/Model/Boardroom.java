package com.officeplanner.officeplanner.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /*----------------------------------------------------------------------------------------------*/
    //CONSTRUCTORS
    /*----------------------------------------------------------------------------------------------*/
    //NO ARGS CONSTRUCTOR

    public Boardroom() {
    }

    //CONSTRUCTOR WITH ARGS

    public Boardroom(String boardroom_name, Integer boardroom_capacity) {
        this.boardroom_name = boardroom_name;
        this.boardroom_capacity = boardroom_capacity;
    }

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


    /*-----------------------------------------------------------------------------------------------*/
    //GETTERS AND SETTERS
    /*-----------------------------------------------------------------------------------------------*/

    public Integer getBoardroom_id() {

        return boardroom_id;
    }

    public void setBoardroom_id(Integer boardroom_id) {

        this.boardroom_id = boardroom_id;
    }

    public String getBoardroom_name() {

        return boardroom_name;
    }

    public void setBoardroom_name(String boardroom_name) {

        this.boardroom_name = boardroom_name;
    }

    public Integer getBoardroom_capacity() {
        return boardroom_capacity;
    }

    public void setBoardroom_capacity(Integer boardroom_capacity) {
        this.boardroom_capacity = boardroom_capacity;
    }
    /*------------------------------------------------------------------------------------------------------*/
    //TOSTRING
    /*------------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Boardroom{" +
                "boardroom_id=" + boardroom_id +
                ", boardroom_name='" + boardroom_name + '\'' +
                ", boardroom_capacity=" + boardroom_capacity +
                '}';
    }
}
