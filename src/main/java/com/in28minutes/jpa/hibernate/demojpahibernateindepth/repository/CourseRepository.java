package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		// you can use merge to insert and update it check if there is an id in the
		// course object it will use update if id is nullF it will insert

		// insert
		if (course.getId() == null) {
			em.persist(course);
		} else {
			// update
			em.merge(course);
		}
		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		logger.info("playWithEntityManager - start");

		/**
		 * The course here I only saved it here what I would changes you are doing to
		 * the course later in the transaction are also being tracked by the entity
		 * manager and they are also persisted.So the updated name will be in the
		 * DataBase because we did the change before the end of the transaction.
		 * 
		 * 
		 */
		Course course = new Course("Web Services in 100 steps");
		em.persist(course);
		course.setName("Web Services in 100 steps - Updated");
	}
}
