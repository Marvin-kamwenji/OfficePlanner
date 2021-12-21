package com.officeplanner.officeplanner.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue
    private Long privilege_id;

    private String name;

    /*---------------------------------------------------------------------------------------*/
    //RELATIONSHIP BETWEEN ROLES AND PRIVILEGES
    /*---------------------------------------------------------------------------------------*/
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
