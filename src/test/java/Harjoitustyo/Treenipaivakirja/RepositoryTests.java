package Harjoitustyo.Treenipaivakirja;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import Harjoitustyo.Treenipaivakirja.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepositoryTests {
	
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	// Finding trainings with the date 20.03.2023. Results should be 1
	@Test
	public void findByDday() {
		List<Training> trainings = trainingRepository.findByDday("20.03.2023");
		assertEquals(trainings.size(), 1);
	}
	
	// Testing that Base Run category exist one time
	@Test
	public void findByName() {
		List<Category> trainings = categoryRepository.findByName("Base Run");
		assertEquals(trainings.size(), 1);
	}
	
	// Testing to add new training
	@Test
	public void insertNewTraining() {
		Training training = new Training("10.03.2023", "Easy pace", 10, 1);
		trainingRepository.save(training);
		assertNotNull(training.getId());
	}
	// Testing to delete a training
	@Test
	public void deleteTraining() {
		Optional<Training> deleted = trainingRepository.findById((long) 7);
		trainingRepository.deleteById(deleted.get().getId());
	}
	
	// Testing if training id 8 equals to the day 22.03.2023
	@Test
	public void findTraining() {
		Training training = trainingRepository.findById((long) 8).get();
		assertEquals(training.getDday(), "22.03.2023");
	}
	
	
}
