package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.Employee;
import com.dto.EmployeeImp;
import com.exception.DataNotFoundException;
import com.exception.SomethingWentWrong;

public class EmployeeDoa {
	public static boolean createEmployee(Employee employee) throws SomethingWentWrong {
		Connection connection = null;
		
		try {
			connection  = ConnectToDataBase.makeConnnection();
			PreparedStatement statement = connection.prepareStatement("insert into employee (ename, email_id, contact, dob, joining_date) values (?, ?, ?, ?, CURDATE())");
			
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getEmail());
			statement.setString(3, employee.getContact());
			statement.setDate(4, Date.valueOf(employee.getDob()));
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SomethingWentWrong();
		} finally {
			try {
				ConnectToDataBase.closeConnection(connection);
			} catch (SQLException e) {
				throw new SomethingWentWrong();
			}
		}
		
		return true;
	}
	
	
	public static Employee viewDetails(int id) throws DataNotFoundException, SomethingWentWrong {
		Employee employee = null;
		Connection connection = null;
		
		try {
			connection = ConnectToDataBase.makeConnnection();
			
			PreparedStatement statement = connection.prepareStatement("select * from employee where eid = ?");
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			employee = new EmployeeImp();
			
			employee.setEid(id);
			employee.setName(resultSet.getString("ename"));
			employee.setEmail(resultSet.getString("email_id"));
			employee.setContact(resultSet.getString("contact"));
			employee.setDob(Date.valueOf(resultSet.getDate("dob") + "").toLocalDate());
			employee.setJoiningDate(Date.valueOf(resultSet.getDate("joining_date") + "").toLocalDate());
			employee.setWages(resultSet.getDouble("wages"));
			employee.setEpid(resultSet.getInt("epid"));
			
		} catch (SQLException e) {
			throw new DataNotFoundException("Employee Not found with this Id");
		} finally {
			try {
				ConnectToDataBase.closeConnection(connection);
			} catch (SQLException e) {
				throw new SomethingWentWrong();
			}
		}
		
		return employee;
	}
	
	
	public static boolean assignProjectToEmployee(Integer eid, Integer pid) throws SomethingWentWrong {
		Connection connection = null;
		
		try {
			connection = ConnectToDataBase.makeConnnection();
			
			PreparedStatement statement = connection.prepareStatement("update employee set epid = ? where eid = ?");
			statement.setInt(1, pid);
			statement.setInt(2, eid);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SomethingWentWrong();
		} finally {
			try {
				ConnectToDataBase.closeConnection(connection);
			} catch (SQLException e) {
				throw new SomethingWentWrong();
			}
		}
		
		return true;
	}
	
	public static boolean deleteEmployee(Integer eid) throws SomethingWentWrong {
		Connection connection = null;
		
		try {
			connection = ConnectToDataBase.makeConnnection();
			
			PreparedStatement statement = connection.prepareStatement("delete from employee where eid = ?");
			statement.setInt(1, eid);
			
			ResultSet  resultSet = statement.executeQuery();
			resultSet.next();
			
			
			System.out.println(resultSet);
		} catch (SQLException e) {
			throw new SomethingWentWrong();
		} finally {
			try {
				ConnectToDataBase.closeConnection(connection);
			} catch (SQLException e) {
				throw new SomethingWentWrong();
			}
		}
		return true;
	}
	
}






