package com.aimers.journal_app;


import com.aimers.journal_app.entity.User;
import com.aimers.journal_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;



@SpringBootTest
@ActiveProfiles("dev")
class JournalAppApplicationTests {


	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertEquals(5,2+3);
		User user = userRepository.findByUserName("SteveRogers");
        assertFalse(user.getJournalEntries().isEmpty());
	}

}
