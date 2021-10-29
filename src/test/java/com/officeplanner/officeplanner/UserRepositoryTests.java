package com.officeplanner.officeplanner;

import com.officeplanner.officeplanner.Dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest

//This test is configured to work with the actual database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

//Used to commit the changes
@Rollback(false)
public class UserRepositoryTests {
@Autowired
/*TestEntityManager is a wrapper of JPA's EntityManager so we can use it in test class like a
standard EntityManager*/
    private TestEntityManager entityManager;

@Autowired
    private UserRepository repo;
/*
//test methods go below
@Test
//this test method persists a user object into the database
    public void testCreateUser(){
    User user = new User();
    user.setEmail("muturi@gmail.com");
    user.setPassword("muturi");
    user.setFirstName("Muturi");
    user.setLastName("Kimondo");
    user.setContact(0723146434);



User savedUser = repo.save(user);

User existUser = entityManager.find(User.class, savedUser.getUser_id());

}
*/

}
