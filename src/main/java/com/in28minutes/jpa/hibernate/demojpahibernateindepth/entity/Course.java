package com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Course")
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
		@NamedQuery(name = "query_get_150_steps_courses", query = "Select c From Course c where name like '%150 steps'") })
//@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c")
//@NamedQuery(name = "query_get_150_steps_courses", query = "Select c From Course c where name like '%150 steps'")
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

	// insertable indicate if that column needs to be included in the insert
	// statements that are sent.
	// updatable indicate if that column needs to be included in the update
	// statements that are sent.
	@Column(name = "name", nullable = false)
	private String name;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

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
