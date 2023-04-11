package Harjoitustyo.Treenipaivakirja;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Harjoitustyo.Treenipaivakirja.web.TrainingController;

@SpringBootTest
class TreenipaivakirjaApplicationTests {

	@Autowired
	TrainingController trainingController;
	
	@Test
	void contextLoads() throws Exception {
		assertThat (trainingController).isNotNull();
	}

}
