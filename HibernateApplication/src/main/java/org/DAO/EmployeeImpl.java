package org.DAO;

import java.io.Serializable;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.Entity.Addresses;
import org.Entity.Employee;
import org.Entity.Salary;
import org.Util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeImpl {
	SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

	public void addRecords(Employee employee, Salary salary, Addresses address) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Serializable emp = session.save(employee);
			transaction.commit();
		} catch (Exception exception) {
			// TODO: handle exception
			transaction.rollback();
			exception.printStackTrace();
		}
		session.close();
	}

	public Employee viewRecordByCriteria() {
		Session session = sessionFactory.openSession();

	//	Integer id = new Integer(1);

		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Employee.class);
			Criteria criteria1 = criteria.createCriteria("salary");
			// Showing data which is greater than 60000
			criteria1.add(Restrictions.ge("salary", new Double(10000)));
			Employee employee = (Employee) criteria1.uniqueResult();

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
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		Query query = session.createQuery("from Employee where salary.salary>10000");
		Employee employee = (Employee) query.uniqueResult();
		System.out.println("salary= "+employee.getSalary().getSalary());
		t.commit();
		session.close();
		return employee;
	
}
}
