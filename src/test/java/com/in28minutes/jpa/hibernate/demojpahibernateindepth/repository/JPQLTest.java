package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.DemoJpaHibernateInDepthApplication;
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateInDepthApplication.class)
public class JPQLTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select c From Course c");
		List resultList = query.getResultList();

		logger.info("Select c From Course c -> {}", resultList);
	}

	// Typed queries are always better because they make it easy to read your
	// program.It makes it very clear what results you are expecting back.Here we
	// are expecting a list of courses.
	@Test
	public void jpql_Typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();

		logger.info("Typed Query: Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_Where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%150 steps'", Course.class);
		List<Course> resultList = query.getResultList();

		logger.info("Typed where Query: Select c From Course c -> {}", resultList);
	}
}
