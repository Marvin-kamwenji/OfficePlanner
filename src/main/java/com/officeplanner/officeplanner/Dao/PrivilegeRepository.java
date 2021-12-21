package com.officeplanner.officeplanner.Dao;

import com.officeplanner.officeplanner.Model.Meeting;
import com.officeplanner.officeplanner.Model.Privilege;
import com.officeplanner.officeplanner.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    @Query("SELECT p FROM Privilege p WHERE p.name = ?1 ")
    public Privilege findByName(String Name);
}
