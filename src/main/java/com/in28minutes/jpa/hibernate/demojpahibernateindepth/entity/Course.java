package com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {

	@Id

	/**
	 * what hibernate does when it see @GeneratedValue it uses the default of
	 * creating a sequence and you can give it a strategy type.
	 * 
	 * hibernate will create a sequence then use the sequence to find the next value
	 * for the ID. So it first called the sequence it get the value back. So it uses
	 * that ID to insert the data in.
	 */
	@GeneratedValue
	private Long id;
	private String name;

	protected Course() {
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
