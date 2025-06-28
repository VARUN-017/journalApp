/*
package com.Aimers.journalApp.service;

import com.Aimers.journalApp.entity.User;
import com.Aimers.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest

public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @Disabled
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName("varun")).thenReturn(User.builder().userName("varun").password("ram").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("varun");
        Assertions.assertNotNull(user);
   }
}


// Pure Mockito mock without loading spring context
   */
/* @ExtendWith(MockitoExtension.class)
    public class UserDetailsServiceImplTests {

        @InjectMocks
        private UserDetailsServiceImpl userDetailsService;

        @Mock
        private UserRepository userRepository;

        @Test
        void loadUserByUsernameTest(){
            when(userRepository.findByUserName("varun")).thenReturn(User.builder().userName("varun").password("ram").roles(new ArrayList<>()).build());
            UserDetails user = userDetailsService.loadUserByUsername("varun");
            Assertions.assertNotNull(user);
        }*/
