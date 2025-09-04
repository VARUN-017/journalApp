package com.Aimers.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendEmail("kbvarun43@gmail.com",
                "Testing java mail",
                "Avoid Distractions by isolating in home and build yourself and be confident and daring");
    }


}
