package com.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeDao {
	
	public void saveEmployee(Employee employee) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			session.save(employee);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	

	public void updateEmployee(Employee employee) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the employee object
			String hql = "UPDATE Employee set firstName = :firstName " + "WHERE empno = :empno";
			Query query = session.createQuery(hql);
			query.setParameter("firstName", employee.getFirstName());
			System.out.println("***********"+employee.getEmpno());
			query.setParameter("empno", employee.getEmpno());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int eid) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a employee object
			Employee employee = session.get(Employee.class, eid);
			if (employee != null) {
				String hql = "DELETE FROM Employee " + "WHERE empno = :empno";
				Query query = session.createQuery(hql);
				query.setParameter("empno", eid);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Employee getEmployee(int eid) {

		Transaction transaction = null;
		Employee employee = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an employee object
			String hql = " FROM Employee E WHERE E.empno = :empno";
			Query query = session.createQuery(hql);
			query.setParameter("empno", eid);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				employee = (Employee) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> getEmployees() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Employee", Employee.class).list();
		}
	}
}
