package com.officeplanner.officeplanner.Service;

import com.officeplanner.officeplanner.Dao.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceService {

    /*---------------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
    /*---------------------------------------------------------------------------------------------------------*/
    @Autowired
    private ResourceRepository resourceRepository;
}
