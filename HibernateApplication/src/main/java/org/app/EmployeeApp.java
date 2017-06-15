package org.app;

import java.util.Arrays;
import java.util.HashSet;

import org.DAO.EmployeeDAO;
import org.DAO.EmployeeDAOImpl;
import org.Entity.Addresses;
import org.Entity.Employee;
import org.Entity.Salary;
import org.Util.HibernateUtils;
import org.DAO.EmployeeImpl;

public class EmployeeApp {

		public static void main(String[] args) {
			HibernateUtils.getSessionFactory();
			EmployeeDAO dao = new EmployeeDAOImpl();
			Employee newemployee = new Employee("Sourabh","SP","M");
			Addresses address = new Addresses(1452L,75063L, "Malibu", "CA", "USA");
			Salary employeesalary = new Salary(12000.00);
			address.setEmployee(newemployee);
			newemployee.setAddress(new HashSet<Addresses>(Arrays.asList(address)));
			employeesalary.setEmployee(newemployee);
			newemployee.setSalary(employeesalary);
		 
	
			EmployeeImpl employeeimpl = new EmployeeImpl();
			employeeimpl.addRecords(newemployee, employeesalary, address);
			System.out.println("Name: "+employeeimpl.viewRecordByCriteria().getFirstName() + " "
					+ employeeimpl.viewRecordByCriteria().getLastName());
			System.err.println("Salary: "+employeeimpl.viewRecordByCriteria().getSalary().getSalary());
			System.out.println(employeeimpl.viewRecordByHQL().getFirstName() + " "
					+ employeeimpl.viewRecordByHQL().getLastName());
			 

   
		}

	}


