package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.DemoJpaHibernateInDepthApplication;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateInDepthApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository repository;

	@Autowired
	private EntityManager em;

	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {

		Student student = em.find(Student.class, 20001l);

		logger.info("student -> {} ", student);
		// with lazy fetch if there is no @Transactional it will throw
		// org.hibernate.LazyInitializationException because there is no session
		logger.info("passport -> {}", student.getPassport());
	}

}
