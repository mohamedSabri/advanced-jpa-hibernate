package com.in28minutes.jpa.hibernate.demojpahibernateindepth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	/**
	 * If we want to make a Bidirectional Relationship ie we want to get the
	 * passport from student and get the student from passport now we make a
	 * passport data member in the Student class with @OneToOne and make a student
	 * data member in the Passport class with @OneToOne ,So we will have passport_id
	 * at the student table as foreign key and also a student_id at the Passport
	 * table as foreign key.
	 * 
	 * So we have a student table that has a passport ID and passport table that has
	 * a student ID.This is duplication of information The association between any
	 * passport_id and student_id is being stored in two different places in two
	 * different raws in two different tables.And that's not good in database. One
	 * of the most important things to have good data quality is to ensure that
	 * you're not duplicating the same information again and again.
	 * 
	 * So the option we have typically is to make one of these entities the owning
	 * side of the relationship.If student has the passport ID then Student will be
	 * the owning side of the relationship.If passport has the student ID then
	 * passport will be the owning side of the relationship.
	 * 
	 * For now let's keep student is the owning side of the relationship.That way we
	 * can do that by adding mappedBy in the passport class and put in what is the
	 * name of the variable to be mapped in the student class.
	 * 
	 * Now the most important thing is I want to make student the owning side of the
	 * relationship I'm adding mapped by on the passport. So you don't add mapped by
	 * on the owning side of the relationship you would add mapped by on the non
	 * owning side of the relationship passport is the non owning side of the
	 * relationship.
	 * 
	 * Now you can see that student table has a passport Id ,but the passport table
	 * does not have a student Id. This is good for the table design point of view.
	 * 
	 * So we now have student and passport We have the relationship established
	 * between the entities.From the student I can navigate to the passport using
	 * student.getPassport() from the passport I can navigate to the student using
	 * passport.getStudent() ,but we have one association between student_id and
	 * passport_id in the table that own the relationship (Student table) no data
	 * duplication there is no student_id in the passport table but I can get the
	 * student that has a specific passport.
	 * 
	 * what the mapped by attribute insures that in the passport table the student
	 * id column is not created.So passport is not the owning side of the
	 * relationship the column would be stored in Student.But the @OneToOne
	 * annotation gives us a way to navigate from the passport to the student. So
	 * even though there is no column associated with the student_id it gives us a
	 * way to navigate from the passport to the student.And this is what is called
	 * Bidirectional navigation.
	 */

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	protected Passport() {
	}

	public Passport(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [number=" + number + "]";
	}

}
