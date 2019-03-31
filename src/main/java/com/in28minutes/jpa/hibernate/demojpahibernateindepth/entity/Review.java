package com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String Description;

	protected Review() {
	}

	public Review(String rating, String description) {
		super();
		this.rating = rating;
		Description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Review [rating=" + rating + ", Description=" + Description + "]";
	}

}
