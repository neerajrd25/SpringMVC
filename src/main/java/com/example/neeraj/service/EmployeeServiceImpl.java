package com.example.neeraj.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.example.neeraj.HomeController;
import com.example.neeraj.model.Employee;
import com.example.util.HibernateUtil;
import com.example.util.IHibernateDAO;

public class EmployeeServiceImpl implements IEmployeeService, ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	IHibernateDAO hibernateDao;

	public EmployeeServiceImpl() {
		System.out.println("Requested now");
	}

	@Override
	public int saveEmployee(Employee empObj) {
		int id = 0;

		try {
			hibernateDao.saveRecord(empObj);
			id = empObj.getId();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return id;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> data = Collections.emptyList();
		try {
			data = hibernateDao.getRecords(
					"Select USER_ID as id, EMPLOYEE_NAME as empName, SALARY AS salary, country as COUNTRY FROM EMPLOYEE");
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		return data;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee data = null;
		try {
			data = hibernateDao.getRecordOne(Employee.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		return data;
	}

	@Override
	public void deleteEmployee(int id) {

		try {
			hibernateDao.deleteRecord(Employee.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

	}

	@Override
	public void updateEmployee(Employee empObj) throws Exception {

		try {
			hibernateDao.updateRecord(empObj);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		hibernateDao = (IHibernateDAO) applicationContext.getBean("hibernateDao");

	}

	@Override
	public void cachingDemo(int id) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			// get if not found will return null 
			Employee emp = (Employee) session.get(Employee.class, id);
			System.out.println("Employee get called");
			System.out.println("Employee ID= " + emp.getId() + " Employee class Name : " + emp.getClass());
			System.out.println("Employee Get Details:: " + emp + "\n");

			Employee emp1 = (Employee) session.load(Employee.class, 5);
			// it will hit db only if object is not found in cache, so emp
			// object was present in db with id 1 but not with 5 so it will lazy
			// load at toString() call Line
			System.out.println("Employee load called");
			System.out.println("Employee ID= " + emp1.getId() + " Employee class Name : " + emp1.getClass());
			System.out.println("Employee Get Details:: " + emp1 + "\n");
			
			
			
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

}
