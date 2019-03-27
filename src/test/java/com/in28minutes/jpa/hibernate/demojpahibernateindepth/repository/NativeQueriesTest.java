package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
public class NativeQueriesTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	@Test
	public void native_queries_basic() {

		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List resultList = query.getResultList();

		assertNotNull(resultList);
		assertFalse(resultList.isEmpty());
		assertEquals(4, resultList.size());

		logger.info("SELECT * FROM COURSE -> {}", resultList);
		// SELECT * FROM COURSE -> [Course [name=Web Services in 150 steps], Course
		// [name=JPA in 50 Steps - Updated], Course [name=Spring in 50 Steps], Course
		// [name=Spring Boot in 50 Steps]]
	}

	@Test
	public void native_queries_with_parameter() {

		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id= ?", Course.class);
		query.setParameter(1, 10001l);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE id= ? -> {}", resultList);

		assertNotNull(resultList);
		assertFalse(resultList.isEmpty());
		String courseName = ((Course) resultList.get(0)).getName();
//		assertEquals("JPA in 50 Steps", courseName);

		// SELECT * FROM COURSE WHERE id -> [Course [name=JPA in 50 Steps - Updated]]
	}

	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id= :id", Course.class);
		query.setParameter("id", 10001l);
		List resultList = query.getResultList();

		logger.info("SELECT * FROM COURSE WHERE id= :id -> {}", resultList);

		assertNotNull(resultList);
		assertFalse(resultList.isEmpty());
		String courseName = ((Course) resultList.get(0)).getName();
//		assertEquals("JPA in 50 Steps", courseName);
		// SELECT * FROM COURSE WHERE id= :id -> [Course [name=JPA in 50 Steps -
		// Updated]]
	}

	/**
	 * There is a situations that you need to use native queries like when you have
	 * to do a mass update.Let's say I would want to update all the rows in a
	 * specific table in a query. In those kind of situations if you do it through
	 * JPA then you would need to get each row And then update it [get row row and
	 * update it].you cannot do a mass update using JPA.
	 * 
	 * So in those kind of situations we would use Native queries so the way we
	 * would do that let's say I would want to update the last_updated_date time
	 * stamp on all the courses in the course table.
	 */
	@Test
	@Transactional
	public void native_queries_to_update() {

		Query query = em.createNativeQuery("update course set last_updated_date= sysdate()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();

//		assertEquals(4, noOfRowsUpdated);
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);

		// noOfRowsUpdated -> 4
	}
}
