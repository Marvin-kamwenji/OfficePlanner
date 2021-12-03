package com.officeplanner.officeplanner.Dao;

import com.officeplanner.officeplanner.Model.Boardroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardroomRepository extends JpaRepository<Boardroom, Integer> {
}
