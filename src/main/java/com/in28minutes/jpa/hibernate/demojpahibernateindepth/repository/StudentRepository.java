package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Passport;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		// use merge for insert and update
		em.merge(student);

		return student;
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Student student = new Student("Mike");
		student.setPassport(passport);

		em.persist(student);
	}
}
