package com.Aimers.journalApp.service;


import com.Aimers.journalApp.entity.User;
import com.Aimers.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // we use Slf4j to skip this.,whr we have to create instance every time
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e){
//            log.info("Your trying to save the same user twice brother..! make it unique n save it ");
//            log.warn("haahahahaaa ");
//            log.debug("hahahhahaa ");
//            log.trace("hahahahaha");
            log.error("Error occurred for {} :", user.getUserName(), e);
        }
    }

    public boolean save(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
