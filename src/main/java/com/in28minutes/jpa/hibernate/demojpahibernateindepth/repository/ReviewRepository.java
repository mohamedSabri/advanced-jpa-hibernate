package com.in28minutes.jpa.hibernate.demojpahibernateindepth.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity.Review;

@Repository
@Transactional
public class ReviewRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	public Review findById(Long id) {
		return em.find(Review.class, id);
	}

	public Review save(Review review) {

		return em.merge(review);

	}

	public void deleteById(Long id) {
		Review review = findById(id);
		em.remove(review);
	}

}
