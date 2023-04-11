package Harjoitustyo.Treenipaivakirja.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Harjoitustyo.Treenipaivakirja.domain.*;

@Controller
public class TrainingController {
	
	@Autowired
	private TrainingRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	// show all trainings
	@GetMapping({"/", "/traininglist"})
	public String trainingList(Model model) {
		model.addAttribute("trainings", repository.findAll());
		return "traininglist";
	}
	
	
	// add new training
	@PreAuthorize("hasAuthority('ADMIN')") // only admin can add trainings
    @GetMapping("/add")
    public String addTraining(Model model){
    	model.addAttribute("training", new Training());
    	model.addAttribute("categories", crepository.findAll());
        return "addtraining";
    }     
	
	
	// Save new training
    @PostMapping("/save")
    public String save(@Valid Training training, BindingResult bindingResult, Model model){
        
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("training", training);
    		model.addAttribute("categories", crepository.findAll());
    		System.out.println("some error happened");
    		return "addtraining";
    	}
    	
    	repository.save(training);
        return "redirect:traininglist";
    }    
	
	
	// Delete training
    @PreAuthorize("hasAuthority('ADMIN')") // only admin can delete trainings
    @GetMapping("/delete/{id}")
    public String deleteTraining(@PathVariable("id") Long trainingId, Model model) {
    	repository.deleteById(trainingId);
        return "redirect:../traininglist";
    }  
	
	
	// Edit training
    @PreAuthorize("hasAuthority('ADMIN')") // only admin can edit trainings
    @GetMapping("/edit/{id}")
    public String editTraining(@PathVariable("id") Long trainingId, Model model) {
    	model.addAttribute("training", repository.findById(trainingId));
    	model.addAttribute("categories", crepository.findAll());
    	return "edittraining";
    }   
	
	
}