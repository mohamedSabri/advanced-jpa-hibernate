package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em ;
	
	 public Course findById(Long id) {
		 return em.find(Course.class, id);
	 }
	
	// public Course save(Course course) --> insert or update
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
}
