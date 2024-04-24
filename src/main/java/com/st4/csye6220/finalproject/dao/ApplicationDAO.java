package com.st4.csye6220.finalproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.st4.csye6220.finalproject.pojo.Application;
import com.st4.csye6220.finalproject.pojo.Course;
import com.st4.csye6220.finalproject.pojo.User;

@Repository
public class ApplicationDAO {

	public void saveApplication(Application application){ 
		System.out.println("Application DAO reached");
        Session session = DAO.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(application);
        session.getTransaction().commit();
    }
	
	public Application getApplication(long id) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application WHERE applicationId = :applicationId";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("applicationId", id);
		Application application = (Application) query.uniqueResult();
		session.getTransaction().commit();
		return application;
	}
	
	public boolean checkDuplicate(long userId, long courseId) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application A WHERE A.user.userId = :userId AND A.course.courseId = :courseId";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("courseId", courseId);
		List<Application> applications = (List<Application>) query.list();
		session.getTransaction().commit();
		return applications.size()==0;
	}
	
	public void update(Application application) {
		Session session = DAO.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(application);
		session.getTransaction().commit();
	}
	
	public List<Application> fetchApplicationsByUser(User user) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application A WHERE A.user.userId = :userId";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("userId", user.getUserId());
		List<Application> applications = (List<Application>) query.list();
		session.getTransaction().commit();
		return applications;
	}
	
	public List<Application> fetchApplicationsByCourse(Course course) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application A WHERE A.course.courseId = :courseId";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("courseId", course.getCourseId());
		List<Application> applications = (List<Application>) query.list();
		session.getTransaction().commit();
		return applications;
	}
	
	public List<Application> fetchApplicationsByApplicantEmail(String email) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application A WHERE A.user.email = :applicantEmail";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("applicantEmail", email);
		List<Application> applications = (List<Application>) query.list();
		session.getTransaction().commit();
		return applications;
	}
	
	public List<Application> fetchApplicationsByProfessorEmail(String email) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Application WHERE professorEmail = :professorEmail";
		Query<Application> query = session.createQuery(hql);
		query.setParameter("professorEmail", email);
		List<Application> applications = (List<Application>) query.list();
		session.getTransaction().commit();
		return applications;
	}
}
