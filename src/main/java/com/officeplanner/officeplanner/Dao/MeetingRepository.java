package com.officeplanner.officeplanner.Dao;

import com.officeplanner.officeplanner.Model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
}
