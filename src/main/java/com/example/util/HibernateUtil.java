package com.example.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
	static {
		try {
			Configuration cfg = new Configuration();

			cfg.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties());
			HibernateUtil.sessionFactory = cfg.buildSessionFactory(ssrb.build());
			System.out.println("Session Generated");
		} catch (Exception e) {
			// Log the exception.
			LOGGER.error("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		return sessionFactory.openSession();
	}

}