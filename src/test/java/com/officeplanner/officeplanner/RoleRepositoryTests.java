package com.officeplanner.officeplanner;

import com.officeplanner.officeplanner.Dao.RoleRepository;
import com.officeplanner.officeplanner.Model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateRoles(){
        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role officer = new Role("Officer");

        roleRepository.saveAll(List.of(user, admin, officer));
        List<Role> listRoles = roleRepository.findAll();

        assertThat(listRoles.size()).isEqualTo(3);
    }
}
