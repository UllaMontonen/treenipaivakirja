package Harjoitustyo.Treenipaivakirja.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;


//database created for trainings
@Entity
public class Training {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//day already exist, therefore used dday variable
	@NotEmpty(message = " Day cannot be empty")
	private String dday;
	
	private String comment;
	private int time, km;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid")
	private Category category;

	
	
	public Training() {
		super();
	}


	public Training(String dday, String comment, int time, int km, Category category) {
		super();
		this.dday = dday;
		this.comment = comment;
		this.time = time;
		this.km = km;
		this.category = category;
	}


	public Training(String dday, String comment, int time, int km) {
		super();
		this.dday = dday;
		this.comment = comment;
		this.time = time;
		this.km = km;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDday() {
		return dday;
	}


	public void setDday(String dday) {
		this.dday = dday;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public int getKm() {
		return km;
	}


	public void setKm(int km) {
		this.km = km;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Training [id=" + id + ", day=" + dday + ", comment=" + comment + ", time=" + time + ", km=" + km + "]";
	}
	
	
	
	
	
	
}