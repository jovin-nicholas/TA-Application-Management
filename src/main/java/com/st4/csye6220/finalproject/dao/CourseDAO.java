package com.st4.csye6220.finalproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.st4.csye6220.finalproject.pojo.Course;

@Repository
public class CourseDAO {

	public void saveCourse(Course course) {
		System.out.println("Course DAO reached");
		Session session = DAO.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(course);
		session.getTransaction().commit();
		session.close();
	}

	public List<Course> getCourses() {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Course";
		Query<Course> query = session.createQuery(hql);
		List<Course> courses = (List<Course>) query.list();
		session.getTransaction().commit();
		return courses;
	}

	public Course getCourse(long courseId) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Course WHERE courseId = :courseId";
		Query<Course> query = session.createQuery(hql);
		query.setParameter("courseId", courseId);
		Course course = (Course) query.uniqueResult();
		session.getTransaction().commit();
		return course;
	}

	public Course getCourse(String courseCode) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Course WHERE courseCode = :courseCode";
		Query<Course> query = session.createQuery(hql);
		query.setParameter("courseCode", courseCode);
		Course course = (Course) query.uniqueResult();
		session.getTransaction().commit();
		return course;
	}

	public boolean removeCourse(Course course) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.remove(course);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
