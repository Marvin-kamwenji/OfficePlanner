package com.officeplanner.officeplanner.Model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
 /*-----------------------------------------------------------------------------------------------------*/
    //ROLE PROPERTIES
 /*-----------------------------------------------------------------------------------------------------*/
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

  /*----------------------------------------------------------------------------------------------------*/
    //CONSTRUCTORS
  /*----------------------------------------------------------------------------------------------------*/

    //NO ARGS CONSTRUCTOR
    public Role() {
    }

   // CONSTRUCTOR WITH ARGUMENT NAME
    public Role(String name) {
        this.name = name;
    }

    //CONSTRUCTOR WITH ARGUMENT ID
    public Role(Integer id) {
        this.id = id;
    }

    //CONSTRUCTOR WITH ID AND NAME CONSTRUCTORS
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

/*------------------------------------------------------------------------------------------------------*/
    //RELATIONSHIP BETWEEN ROLES AND USERS
/*------------------------------------------------------------------------------------------------------*/
@ManyToMany(mappedBy = "roles")
private List<User> users;

//PRIVILEDGES
@ManyToMany
@JoinTable(
        name = "roles_privileges",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name= "privilege_id")
)
private List<Privilege> privileges;
 /*------------------------------------------------------------------------------------------------------*/
    //GETTERS AND SETTERS
 /*------------------------------------------------------------------------------------------------------*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*------------------------------------------------------------------------------------------------------*/
    //TO STRING
/*------------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
