package com.example.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.example.neeraj.EmployeeController;

public class HibernateDAOImpl implements IHibernateDAO, ApplicationContextAware {

	private HibernateUtil hibernateUtil;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Override
	public <T> T saveRecord(T value) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			logger.info("Saving Object: " + value.toString());
			/**
			 * Save = persisit + flush
			 * 
			 */

			session.save(value);
			logger.info("Method called :" + value.toString());
			/*
			 * if object needs to be modifed after the db call then flush it
			 * again so that it will sync objetc state with db
			 * 
			 */
			/*
			 * Employee emp = (Employee) value; emp.setCountry("Nepal");
			 * session.flush();
			 */
			logger.info("After Flush :" + value.toString());
			session.clear();
			logger.info("After Clear :" + value.toString());
			tx.commit();
			logger.info("After Commit :" + value.toString());

		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (session != null)
				session.close();
			tx = null;

		}

		return value;

	}

	@Override
	public <T> List<T> getRecords(String query) {

		List<T> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q;
		try {
			q = session.createSQLQuery(query);
			data = q.list();
			session.clear();

		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}

		return data;

	}

	@Override
	public <T> void deleteRecord(Class<?> c, Serializable id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Query q;
		try {
			T obj = (T) session.load(c, id);
			tx = session.beginTransaction();
			if (null != obj) {
				session.delete(obj);
			}
			session.flush();
			session.clear();
			tx.commit();

		} catch (Exception e) {
			throw e;
		} finally {

			if (session != null) {
				session.close();
				tx = null;
			}
		}

	}

	@Override
	public <T> T getRecordOne(Class<?> c, Serializable id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		T obj;
		try {
			obj = (T) session.get(c, id);

			session.flush();
			session.clear();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	@Override
	public <T> void updateRecord(T obj) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			session.merge(obj);

			session.flush();
			session.clear();
			tx.commit();

		} catch (Exception e) {
			throw e;
		} finally {

			if (session != null) {
				session.close();
				tx = null;
			}
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		hibernateUtil = (HibernateUtil) applicationContext.getBean("hibernateUtil");

	}

}
