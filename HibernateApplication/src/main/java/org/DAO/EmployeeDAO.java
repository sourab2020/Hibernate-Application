package org.DAO;

import java.util.List;

import org.Entity.Addresses;
import org.Entity.Employee;
import org.Entity.SearchCriteria;

public interface EmployeeDAO {
	Employee createEmployee(Employee student);
	void deleteEmployee(int  empid);
	Employee updateEmployee(Employee student);
	Employee findEmployee(int empid);

	
	public Employee viewRecordByHQL();
	public Employee viewRecordByCriteria();

}
