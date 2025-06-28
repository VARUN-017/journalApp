/*
package com.Aimers.journalApp.service;

import com.Aimers.journalApp.entity.User;
import com.Aimers.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
 public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

   @Autowired
   private UserService userService;

    @Test
    @Disabled
    // @Disabled // to disable the particular test method when whole class test method are running
    public void addTest(){
        assertEquals(5,2+3);
    }

    @Test
    @Disabled
    public void testFindByUsername(){
        assertNotNull(userRepository.findByUserName("PeterParker"));
    }

    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {"SteveRogers","TonyStark","PeterParker"}) // it will only pass based on the values in db
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name));
    }

    @ParameterizedTest
    @Disabled
    @ArgumentsSource(UserArgumentProvider.class)
    public void testFindByUserNames(User user){
        assertTrue(userService.save(user));
    }




    @Test
    @Disabled
    public void testFindJournalEntries(){
        User user = userRepository.findByUserName("SteveRogers");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @Disabled
    @CsvSource({"2,2,4","12,18,30","4,4,8"})
    public void test(int a, int b, int expected){
        assertEquals(expected,a + b);
    }


}
*/
