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
@Table (name = "organization")
public class Organization {
/*----------------------------------------------------------------------------------------------------------*/
    //ORGANIZATION PROPERTIES
/*----------------------------------------------------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organization_id;
    private String organization_name;


    /*---------------------------------------------------------------------------------------------------------*/
    //RELATIONSHIP BETWEEN BOARDROOM AND RESOURCES
    /*---------------------------------------------------------------------------------------------------------*/
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "organization_boardrooms",
            joinColumns = {@JoinColumn(name = "organization_id")},
            inverseJoinColumns = {@JoinColumn(name = "boardroom_id")}
    )
    private Set<Boardroom> boardrooms = new HashSet<>();


}
