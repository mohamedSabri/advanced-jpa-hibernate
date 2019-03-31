package com.in28minutes.jpa.hibernate.demojpahibernateindepth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Course;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository.CourseRepository;

@SpringBootApplication
public class DemoJpaHibernateInDepthApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaHibernateInDepthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/**
		 * whenever you are inside the transaction and you are managing something with the entity
		 * manager when you are updating something or deleting something or you
		 * inserting something in.That particular thing continues to be managed by the
		 * entity manager until the end of the transaction.
		 */
		
//		repository.playWithEntityManager();

		/**
		 * Course course = repository.findById(10001L); logger.info("Course 10001 ->
		 * {}", course); repository.save(new Course("Microservices in 100 steps"));
		 */

		// we comment that because we use this class context to test so this will run
		// before the test and will make test fail because we change the data, make unit
		// test for deleteById

		// repository.deleteById(10001l);

	}

}
