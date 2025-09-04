package com.Aimers.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles("dev")
public class UserRepositoryImplTests {


    @Autowired
    private UserRepositoryImpl userRepository;


    @Test
    public void testSaveNewUser() {
        Assertions.assertNotNull(userRepository.getUserForSA());
    }

}
