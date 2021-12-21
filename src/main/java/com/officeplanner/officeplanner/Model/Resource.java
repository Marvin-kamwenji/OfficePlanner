package com.officeplanner.officeplanner.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="resource")
public class Resource {
    /*---------------------------------------------------------------------------------------*/
    //RESOURCES ATTRIBUTES
    /*---------------------------------------------------------------------------------------*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resource_id;
    private String resource_name;
    private Integer resource_quantity;
    private boolean resource_available;


    /*-----------------------------------------------------------------------------------------*/
    //CONSTRUCTORS
    /*-----------------------------------------------------------------------------------------*/

    public Resource() {
    }

    public Resource(String resource_name, Integer resource_quantity, boolean resource_available) {
        this.resource_name = resource_name;
        this.resource_quantity = resource_quantity;
        this.resource_available = resource_available;
    }

    /*-------------------------------------------------------------------------------------------*/
    //RELATIONSHIPS
    /*-------------------------------------------------------------------------------------------*/
    @ManyToMany(mappedBy = "resources")
    private List<Boardroom> boardrooms;


    /*------------------------------------------------------------------------------------------*/
    //GETTERS AND SETTERS
    /*------------------------------------------------------------------------------------------*/

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public Integer getResource_quantity() {
        return resource_quantity;
    }

    public void setResource_quantity(Integer resource_quantity) {
        this.resource_quantity = resource_quantity;
    }

    public boolean isResource_available() {
        return resource_available;
    }

    public void setResource_available(boolean resource_available) {
        this.resource_available = resource_available;
    }

    /*--------------------------------------------------------------------------------------------*/
    //TOSTRING
    /*--------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Resource{" +
                "resource_id=" + resource_id +
                ", resource_name='" + resource_name + '\'' +
                ", resource_quantity=" + resource_quantity +
                ", resource_available=" + resource_available +
                '}';
    }
}
