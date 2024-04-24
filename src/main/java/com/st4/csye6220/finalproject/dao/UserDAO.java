package com.st4.csye6220.finalproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.st4.csye6220.finalproject.pojo.User;

public class UserDAO {

	public void saveUser(User user) {
		System.out.println("User DAO reached");
		Session session = DAO.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(user);
		session.getTransaction().commit();
	}

	public User getUser(User user) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM User WHERE email = :email AND password = :password";
		Query<User> query = session.createQuery(hql);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		User loggedUser = (User) query.uniqueResult();
		session.getTransaction().commit();
		return loggedUser;
	}
	
	public User getUserByEmail(User user) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM User WHERE email = :email";
		Query<User> query = session.createQuery(hql);
		query.setParameter("email", user.getEmail());
		User userByEmail = (User) query.uniqueResult();
		session.getTransaction().commit();
		return userByEmail;
	}
	
	public User getUserByEmail(String email) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM User WHERE email = :email";
		Query<User> query = session.createQuery(hql);
		query.setParameter("email", email);
		User userByEmail = (User) query.uniqueResult();
		session.getTransaction().commit();
		return userByEmail;
	}
	
	public List<User> fetchUsersByRole(String role) {
		Session session = DAO.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM User WHERE role = :role";
		Query<User> query = session.createQuery(hql);
		query.setParameter("role", role);
		List<User> users = (List<User>) query.list();
		session.getTransaction().commit();
		return users;
	}
}
