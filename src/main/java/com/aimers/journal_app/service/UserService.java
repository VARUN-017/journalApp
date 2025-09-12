package com.aimers.journal_app.service;


import com.aimers.journal_app.dto.UserDTO;
import com.aimers.journal_app.entity.User;
import com.aimers.journal_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();




    public boolean saveNewUser(UserDTO userDTO){
        try {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEmail(userDTO.getEmail());
            user.setSentimentAnalysis(userDTO.isSentimentAnalysis());
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e){
            log.error("Error occurred for {} :", userDTO.getUserName(), e);
        }
        return false;
    }

    public boolean save(User user){
        try {
            user.setUserName(user.getUserName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEmail(user.getEmail());
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error occurred for {} :", user.getUserName(), e);
        }
        return false;
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
