package com.officeplanner.officeplanner.Model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    /*-------------------------------------------------------------------------------------------*/
    //RELATIONSHIPS
    /*-------------------------------------------------------------------------------------------*/
    @ManyToMany(mappedBy = "resources")
    private List<Boardroom> boardrooms;



}
