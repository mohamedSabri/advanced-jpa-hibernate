package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.DemoJpaHibernateInDepthApplication;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Course;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateInDepthApplication.class)
public class CourseRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Test
	public void contextLoads() {
		logger.info("Test is Running ");
	}

	@Test
	public void findById_basic() {

		Course course = repository.findById(10001l);
		assertNotNull(course);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	/**
	 * we use @DirtiesContext annotation if the test method will change the data
	 * (modified the context). Spring would reset the data after the test so that
	 * for the other tests the data has not changed at all.
	 * 
	 */
	@DirtiesContext
	public void deleteById_basic() {
		Long id = 10002l;
		repository.deleteById(id);
		assertNull(repository.findById(10002l));
	}

}
