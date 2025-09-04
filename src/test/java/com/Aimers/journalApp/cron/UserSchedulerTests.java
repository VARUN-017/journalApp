package com.Aimers.journalApp.cron;

import com.Aimers.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class UserSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUsersAndSendSaEmail(){
        userScheduler.getUsersAndSendSaEmail();
    }
}
