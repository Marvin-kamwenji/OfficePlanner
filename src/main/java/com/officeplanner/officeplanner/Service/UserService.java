package com.officeplanner.officeplanner.Service;

import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    //CREATE
    public void saveUser(User user){
        userRepository.save(user);
    }

    //RETRIEVE
    public List<User> listUsers(){
        return userRepository.findAll();
    }


    //DELETE
    public void deleteUser(Integer user_id){
        userRepository.deleteById(user_id);
    }

    //UPDATE
    public User updateUser(Integer user_id){
        return userRepository.findById(user_id).get();
    }
}
