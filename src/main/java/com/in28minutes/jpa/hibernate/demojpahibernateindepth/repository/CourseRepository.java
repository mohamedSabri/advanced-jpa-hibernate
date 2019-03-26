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
		 * 
		 * Entity manager within a transaction keeps track of all the things that are
		 * managed by it. So when you call entity manager.persist() or entity
		 * manager.merge() or any of this methods on a specific object on a specific
		 * entity. What ever the changes that are done to that entity in the rest of the
		 * transaction are all also tracked by the entity manager and persisted to the
		 * database.
		 * 
		 * The course I only saved it here what changes you are doing to the course
		 * later in the transaction are also being tracked by the entity manager and
		 * they are also persisted.So the updated name will be in the DataBase because
		 * we did the change before the end of the transaction.
		 * 
		 * 
		 */
		Course course = new Course("Web Services in 100 steps");
		em.persist(course);
		course.setName("Web Services in 100 steps - Updated");

		/**
		 * what entity manager.flush() method does?
		 * 
		 * The changes which are done until then they would be sent out to the database
		 * so I can call entity manager.flush() at multiple places so that the changes
		 * up to that point are sent out of the database. So now you can see this
		 * specific transaction as four steps. first we creating course1 then updating
		 * course1 and we creating course 2 and updating course2.
		 * 
		 * let's say I don't want the course2 changes to be going to the database How
		 * can I do that?
		 * 
		 * There is another method in the entity manager called detach take an object of
		 * the entity and Remove the given entity from the persistence context,Once the
		 * entity is detached Unflushed changes made to the entity if any (including
		 * removal of the entity),will not be synchronized to the database.
		 */
		Course course1 = new Course("Angular Js in 100 steps");
		em.persist(course1);
		em.flush();

		em.detach(course1);

		course1.setName("Angular Js in 100 steps - Updated");
		em.flush();

		Course course2 = new Course("React Native in 100 steps");
		em.persist(course2);
		em.flush();

		/**
		 * 
		 * because of detach() method course2 are no longer tracked by the entity
		 * manager. Any changes to course2 will not being saved in the database. So, the
		 * course2 name will still "React Native in 100 steps" not "React Native in 100
		 * steps - Updated"
		 */
		em.detach(course2);

		course2.setName("React Native in 100 steps - Updated");
		em.flush();

		/**
		 * instead of calling detach() on everything You can also say entity
		 * manager.clear() So this would clear everything that is there in the entity
		 * manager so it would clear out everything that is being tracked by entity
		 * manager and everything including course1 and course2 will not be tracked and
		 * whatever changes you make to them will not be flushed out will not be saved
		 * to the database.
		 * 
		 * em.clear() will Clear the persistence context, causing all managed entities
		 * to become detached. Changes made to entities that have not been flushed to
		 * the database will not be persisted.
		 */

		Course course3 = new Course("IOS in 100 steps");
		em.persist(course3);

		Course course4 = new Course("Vue JS in 100 steps");
		em.persist(course4);

		em.flush();

		em.clear();

		course3.setName("IOS in 100 steps - Updated");
		course4.setName("Vue Js in 100 steps - Updated");

		em.flush();

	}
}
