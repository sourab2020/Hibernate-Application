package org.DAO;

import java.io.Serializable;
import java.util.List;

import org.Entity.Addresses;
import org.Entity.Employee;
import org.Entity.SearchCriteria;
import org.Util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class EmployeeDAOImpl implements EmployeeDAO {
	SessionFactory sf = HibernateUtils.getSessionFactory();

	public Employee createEmployee(Employee employee) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Serializable identifier = session.save(employee);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return null;
	}

	public void deleteEmployee(int empid) {
		Session session = sf.openSession();
		Employee employee = findEmployee(empid);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(employee);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public Employee updateEmployee(Employee employee) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(employee);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return employee;
	}


	public Employee viewRecordByCriteria() {
		/*
		 * Session session = sessionFactory.openSession(); String queryString
		 * ="from employee where employeeId = :employeeId";
		 * org.hibernate.query.Query query = session.createQuery(queryString);
		 * query.setParameter(1, new Integer(1)); Employee employee = (Employee)
		 * query.uniqueResult();
		 * 
		 * 
		 * Query query = session.createQuery("from employees");
		 * query.setParameter("id", "1"); List<Employee> listCategories =
		 * ((org.hibernate.query.Query) query).list();
		 * System.out.println(employee.getFirstName()); session.close();
		 */
		Session session = sf.openSession();

		Integer id = new Integer(1);

		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Employee.class);
			Criteria criteria2 = criteria.createCriteria("salary");
			// Showing data which is greater than 60000
			criteria2.add(Restrictions.ge("salary", new Integer(10000)));
			// criteria.add(Restrictions.eq("employeeId", id));
			Employee employee = (Employee) criteria2.uniqueResult();

			if (employee != null) {
				System.out.println(employee.getSalary().getSalary());
				return employee;
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return null;

	}

	public Employee viewRecordByHQL() {
		Session session = sf.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();

		Query query = session.createQuery("from Employee where salary.salary>10000");//
		Employee employee = (Employee) query.uniqueResult();
		System.out.println("salary= "+employee.getSalary().getSalary());
		t.commit();
		session.close();
		return employee;
	}	

	

	
	public Employee findEmployee(int empid) {
		return (Employee) sf.openSession().get(Employee.class, new Integer(empid));
	}


}
