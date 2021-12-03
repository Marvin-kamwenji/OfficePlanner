package com.officeplanner.officeplanner.Service;

import com.officeplanner.officeplanner.Dao.BoardroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardroomService {
    /*----------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*----------------------------------------------------------------------------------------------------*/
    @Autowired
    private BoardroomRepository boardroomRepository;
}
