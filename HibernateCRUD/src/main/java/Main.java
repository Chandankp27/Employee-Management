import java.util.List;

import com.hibernate.dao.EmployeeDao;
import com.hibernate.entity.Employee;

public class Main {
	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDao();
		
	  // Employee employee = new Employee("Bill", "Bush", "bush@gmail.com");
	//	employeeDao.saveEmployee(employee);
		  
	
		// update employee
 	  /*  Employee employee1 = new Employee("George", "Bush", "bush@gmail.com");
		employee1.setEmpno(1);
		employeeDao.updateEmployee(employee1);
  */
		
		// get Employees
  /* 	List<Employee> employees = employeeDao.getEmployees();
		employees.forEach(e -> System.out.println(e.getFirstName()));
	*/
		// get single employee
	/*	Employee employee2 = employeeDao.getEmployee(1);
		System.out.println(employee2.getFirstName());
	*/
		// delete employee
	 employeeDao.deleteEmployee(1);
	}
}
