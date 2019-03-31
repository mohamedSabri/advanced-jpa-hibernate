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
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Passport;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateInDepthApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository repository;

	@Autowired
	private EntityManager em;

	// Transaction
	// Session & Session Factory
	// EntityManager & Persistence Context
	// In Hibernate Terminology, Session = Persistence Context

	// In JPA we use entity manager to talk to the Persistence Context and the
	// entity manager is the interface to the persistence context
	// hibernate use terms called session and session factory.
	// If you want to use JPA you don't really need to worry about session and
	// session factory
	// all what we need to worry about is the entity manager and Persistence context
	// and understand the transaction.

	/**
	 * Ideally when we are talking about transaction everything should succeed or
	 * nothing should succeed.So if any operation fails all the changes done to the
	 * database before this should be rolled back.The thing with JPA is as soon as
	 * you define a transaction you would also be creating something called a
	 * persistence context.
	 * 
	 * The persistence context is the place where all the entities which you are
	 * operating upon are being stored.
	 * 
	 * When we are calling a method using entity manager. What we're really playing
	 * with is the persistence context the persistence context is where all the
	 * changes that we are doing are kept track of.
	 * 
	 * Persistent Context is created at the start of the transaction and kill as
	 * soon as the transaction is ended.
	 * 
	 * If There is no @transaction So there is no transaction created. Each call
	 * will act as if it's own transaction at the start of a find method for example
	 * a transaction would we open and close.So Persistent Context is closed as soon
	 * as the find method is executed.If you want to getPassport
	 * without @Transactional we will get an exception.
	 */
	@Transactional
	@Test
	public void someTest() {

		// you can make all this in a method in the StudentRepository which has
		// @Transactional annotation and you can remove @Transactional from the method

		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001l);
		// Persistence Context containing (student)

		// Database Operation 1 - Retrieve passport
		Passport passport = student.getPassport();
		// Persistence Context containing (student,passport)

		// Database Operation 1 - update passport
		passport.setNumber("E123457");
		// Persistence Context containing (student,passport++)

		// Database Operation 1 - update student
		student.setName("Ranga - updated");
		// Persistence Context containing (student++,passport++)

		// only after the transaction is completed the database changes are sent out to
		// the database.
	}

	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {

		Student student = em.find(Student.class, 20001L);

		logger.info("student -> {} ", student);
		// with lazy fetch if there is no @Transactional it will throw
		// org.hibernate.LazyInitializationException because there is no session
		// (session in hibernate like persistence context in JPA)
		logger.info("passport -> {}", student.getPassport());
	}

	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
}
