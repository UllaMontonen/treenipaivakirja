package Harjoitustyo.Treenipaivakirja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.Treenipaivakirja.domain.Category;
import Harjoitustyo.Treenipaivakirja.domain.CategoryRepository;
import Harjoitustyo.Treenipaivakirja.domain.Training;
import Harjoitustyo.Treenipaivakirja.domain.TrainingRepository;
import Harjoitustyo.Treenipaivakirja.domain.ApplicationUser;
import Harjoitustyo.Treenipaivakirja.domain.ApplicationUserRepository;


@SpringBootApplication
public class TreenipaivakirjaApplication {
	private static final Logger log = LoggerFactory.getLogger(TreenipaivakirjaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TreenipaivakirjaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner trainingDemo(TrainingRepository trepository, CategoryRepository crepository, ApplicationUserRepository urepository) {
		return (args) -> {
			log.info("Save a couple of trainings");
			
			// Categories defined
			crepository.save(new Category("Recovery Run"));
			crepository.save(new Category("Base Run"));
			crepository.save(new Category("Long Run"));
			crepository.save(new Category("Progression Run"));
			crepository.save(new Category("Tempo Run"));
			crepository.save(new Category("Intervals"));
			
			// Adding training info to database
			trepository.save(new Training("20.03.2023", "Easy pace", 60, 8, crepository.findByName("Base Run").get(0)));
			trepository.save(new Training("22.03.2023", "Last km tempo run", 45, 7, crepository.findByName("Progression Run").get(0)));
			trepository.save(new Training("26.03.2023", "Hill interval", 45, 5, crepository.findByName("Intervals").get(0)));
			
			// Creating 2 users, with different permissions
			ApplicationUser user1 = new ApplicationUser("Milla", "Mallikas", "ADMIN", "admin", "$2a$10$F9iLLq975Pff5G6X3oQvgujHqXbx4Dgakjwbj827pJsgZQETLxodi");
			ApplicationUser user2 = new ApplicationUser("Matti", "Mallikas", "USER", "user", "$2a$10$pcfwxxN.dS6IJKnGYfHr4.LYpmWhcpnJDDSg6GGEmAW5CjwZ0sIEy");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all trainings");
			for (Training training : trepository.findAll()) {
				log.info(training.toString());
			}
		};
	}
}
