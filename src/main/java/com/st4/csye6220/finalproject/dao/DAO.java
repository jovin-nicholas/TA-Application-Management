package com.st4.csye6220.finalproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.st4.csye6220.finalproject.pojo.Application;
import com.st4.csye6220.finalproject.pojo.Course;
import com.st4.csye6220.finalproject.pojo.User;

public class DAO {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySetting(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver")
						.applySetting(Environment.JAKARTA_JDBC_URL,
								"jdbc:mysql://localhost:3306/ESDFinalProject?createDatabaseIfNotExist=true")
						.applySetting(Environment.JAKARTA_JDBC_USER, "root")
						.applySetting(Environment.JAKARTA_JDBC_PASSWORD, "root@123")
						.applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect")
						.applySetting(Environment.HBM2DDL_AUTO, "none")
						.applySetting(Environment.SHOW_SQL, "false")
                        .applySetting(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
						.build();

				MetadataSources meta = new MetadataSources(serviceRegistry);
				meta.addAnnotatedClass(User.class);
				meta.addAnnotatedClass(Application.class);
				meta.addAnnotatedClass(Course.class);

				Metadata metadata = meta.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	protected Session getSession() {
//      if (getSessionFactory().getCurrentSession() == null) {
//          return getSessionFactory().openSession();
//      } else {
//          return getSessionFactory().getCurrentSession();
//      }
		return getSessionFactory().openSession();
	}

	public void beginTransaction() {
		getSession().beginTransaction();
	}

	public void commitTransaction() {
		getSession().getTransaction().commit();
	}

	public void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}

	public void closeSession() {
		getSession().close();
	}

}
