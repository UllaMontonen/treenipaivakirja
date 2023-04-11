package Harjoitustyo.Treenipaivakirja.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Harjoitustyo.Treenipaivakirja.domain.Training;
import Harjoitustyo.Treenipaivakirja.domain.TrainingRepository;

//Rest CRUD methods
@RestController
public class RestTrainingController {
	
	@Autowired
	TrainingRepository trainingRepository;
	
	// return list of trainings
	@GetMapping("/trainings")
	public Iterable<Training> getTrainings() {
		return trainingRepository.findAll();
	}
	
	//return certain training
    @GetMapping("/training/{id}")
    public Optional<Training> findTrainingRest(@PathVariable("id") Long trainingId) {	
    	return trainingRepository.findById(trainingId);
    }
    
    //add new training
    @PostMapping("trainings")
    Training newTraining(@RequestBody Training newTraining) {
    	return trainingRepository.save(newTraining);
    }
    
    //edit existing training
    @PutMapping("/training/{id}")
    Training editTraining(@RequestBody Training editTraining, @PathVariable long id) {
    	editTraining.setId(id);
    	return trainingRepository.save(editTraining);
    }
    
    //delete training
    @DeleteMapping("/trainings/{id}")
    public Iterable<Training> deleteTraining(@PathVariable Long id) {
    	trainingRepository.deleteById(id);
    	return trainingRepository.findAll();
    }
	
	
}