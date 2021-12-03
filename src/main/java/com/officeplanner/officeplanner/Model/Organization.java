package com.officeplanner.officeplanner.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "organization")
public class Organization {
/*----------------------------------------------------------------------------------------------------------*/
    //ORGANIZATION PROPERTIES
/*----------------------------------------------------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organization_id;
    private String organization_name;

 /*--------------------------------------------------------------------------------------------------------*/
    //CONSTRUCTOR
 /*--------------------------------------------------------------------------------------------------------*/

    //NO ARGS CONSTRUCTOR
    public Organization() {
    }

    //CONSTRUCTOR WITH ARGUMENTS

    public Organization(String organization_name) {
        this.organization_name = organization_name;
    }


    /*---------------------------------------------------------------------------------------------------------*/
    //RELATIONSHIP BETWEEN BOARDROOM AND RESOURCES
    /*---------------------------------------------------------------------------------------------------------*/
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "organization_boardrooms",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "boardroom_id")
    )
    private Set<Boardroom> boardrooms = new HashSet<>();

    /*---------------------------------------------------------------------------------------------------------*/
    //GETTERS AND SETTERS
 /*---------------------------------------------------------------------------------------------------------*/

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

  /*--------------------------------------------------------------------------------------------------*/
   //TO STRINGS
  /*--------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Organization{" +
                "organization_id=" + organization_id +
                ", organization_name='" + organization_name + '\'' +
                '}';
    }
}
