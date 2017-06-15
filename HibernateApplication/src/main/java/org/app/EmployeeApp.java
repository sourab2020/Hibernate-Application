package org.app;

import java.util.Arrays;
import java.util.HashSet;

import org.DAO.EmployeeDAO;
import org.DAO.EmployeeDAOImpl;
import org.Entity.Addresses;
import org.Entity.Employee;
import org.Entity.Salary;
import org.Util.HibernateUtils;
import org.services.EmployeeServices;

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
		 
	
			EmployeeServices employeeServices = new EmployeeServices();
			employeeServices.addRecords(newemployee, employeesalary, address);
			System.out.println("Name: "+employeeServices.viewRecordByCriteria().getFirstName() + " "
					+ employeeServices.viewRecordByCriteria().getLastName());
			System.err.println("Salary: "+employeeServices.viewRecordByCriteria().getSalary().getSalary());
			System.out.println(employeeServices.viewRecordByHQL().getFirstName() + " "
					+ employeeServices.viewRecordByHQL().getLastName());
			 

   
		}

	}


