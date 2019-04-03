package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

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
import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoJpaHibernateInDepthApplication.class)
public class ReviewRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReviewRepository repository;

	@Test
	@Transactional
	// debug it to see in the default case (eager fetch) there is one select query
	// will be executed when you call findById(reviewId) and this query will get
	// review details and course details either ,So when you call review.getCourse()
	// you will get it you won't hit the database

	// any relation ends with %ToMany the default fetch type is Lazy and any
	// relation ends with %ToOne the default fetch type is Eager
	public void retrieveCourseForReview() {

		Review review = repository.findById(50001l);
		logger.info("review for course  -> {}", review.getCourse());

	}
}
