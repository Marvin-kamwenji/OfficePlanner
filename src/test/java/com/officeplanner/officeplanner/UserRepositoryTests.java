package com.officeplanner.officeplanner;

import com.officeplanner.officeplanner.Dao.RoleRepository;
import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.Role;
import com.officeplanner.officeplanner.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

/*-------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
/*-------------------------------------------------------------------------------------------------*/
@Autowired
    private TestEntityManager entityManager;

@Autowired
    private UserRepository userRepository;

@Autowired
private RoleRepository roleRepository;

/*---------------------------------------------------------------------------------------------------*/
    //TEST METHODS
/*---------------------------------------------------------------------------------------------------*/
    //CREATE USER
    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("marvinkamwenjih@gmail.com");
        user.setPassword("marvin");
        user.setFirstName("Marvin");
        user.setLastName("Kamwenji");

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getUser_id());
        assertThat (user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testFindUserByEmail(){
        String email = "marvin@gmail.com";
        User user = userRepository.findByEmail(email);
        assertThat(user).isNotNull();
    }

    //ADD ROLES
    @Test
     public void testAddRoleToNewUser(){
        Role roleAdmin = roleRepository.findByName("Admin");
        User user = new User();
        user.setEmail("massarvinmaffylap@gmail.com");
        user.setPassword("massarvin");
        user.setFirstName("massarvin");
        user.setLastName("maffylap");
        user.setContact(254);

        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    //EXISTING USERS
    @Test
    public void testAddRoleToExistingUser(){
        User user = userRepository.findById(6).get();

        Role roleUser = roleRepository.findByName("User");
        Role roleOfficer = new Role(12);
        user.addRole(roleUser);
        user.addRole(roleOfficer);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }
}
