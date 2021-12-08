package com.officeplanner.officeplanner.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    /*-----------------------------------------------------------------------------------------------*/
    //USER PROPERTIES
    /*-----------------------------------------------------------------------------------------------*/
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String firstName;
    private String lastName;
    private String fullName;
    @Column(unique = true)
    private String email;
    private Integer contact;
    private String password;
    @Transient
    private String confirmPassword;
    private boolean enabled;
    private String resetPasswordToken;
    @Lob
    private String image;
    @Column (length=64)
    private String verificationCode;

    //LOCKING FAILED LOGIN ATTEMPTS PROPERTIES
    private boolean accountNonLocked;
    private int failedAttempt;
    private Date lockTime;



    /*------------------------------------------------------------------------------------------------------*/
    //RELATION BETWEEN USER AND ROLES TABLE
 /*------------------------------------------------------------------------------------------------------*/
   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(
           name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
      private Set<Role> roles = new HashSet<>();




    public void addRole(Role role)
    {
        this.roles.add(role);
    }


}
