package com.amp.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.amp.asset.exception.AdminNotFound;
import com.amp.asset.exception.EmployeeFound;
import com.amp.asset.exception.EmployeeNotFound;
import com.amp.asset.exception.InvalidCredentials;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.utility.JDBC;



public class AssetDaoImpl implements AssetDao
{

	@Override
	// Employee Authentication
	public Employee employeeAuthentication(Employee employee) throws EmployeeNotFound, ServerDown, InvalidCredentials
	{
		Connection connection=JDBC.getConnection();
		String query0="select * from emp_master_record where username=? or emp_email=?";
		String query1="select * from emp_master_record where (username=? or emp_email=?) and emp_pwd=?";
		String query2="update emp_master_record set signin_date=? where (username=? or emp_email=?) and emp_pwd=?";
		
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query0);
			statement.setString(1,employee.getEmployeeUsername());
			statement.setString(2,employee.getEmployeeUsername());
			ResultSet result=statement.executeQuery();
			if(result.next()==false)
			{
				// Employee is not registered.
				throw new EmployeeNotFound("Not a Valid User");
			}
			
			statement = connection.prepareStatement(query1);
			statement.setString(1,employee.getEmployeeUsername());
			statement.setString(2,employee.getEmployeeUsername());
			statement.setString(3,employee.getEmployeePassword()); // Salt need to be take care;-->We need to put encrypted password here.
			result=statement.executeQuery();
			if(result.next()==false)
			{
				// Password is incorrect.
				throw new InvalidCredentials("Your password is incorrect");
			}
			employee.setEmployeeEmail(result.getString(5));
			employee.setEmployeeId(result.getInt(1));
			employee.setEmployeeName(result.getString(2));
			
			statement=connection.prepareStatement(query2);
			
			Timestamp date=Timestamp.valueOf(employee.getSignInDate());
			
			// updating loginDateTime
			statement.setObject(1,date);
			statement.setString(2,employee.getEmployeeUsername());
			statement.setString(3,employee.getEmployeeUsername());
			statement.setString(4,employee.getEmployeePassword());
	        statement.executeUpdate();
		} 
		catch (SQLException e)
		{
			throw new ServerDown();
		}
		
		return employee;
	}

	@Override
	// Storing the employee Details.
	public Employee storeDetails(Employee employee) throws EmployeeFound, ServerDown
	{
		
		Connection connection=JDBC.getConnection();
		
		String query0="select * from emp_master_record where emp_email=?";
		String query1= "insert into emp_master_record(emp_name,emp_contact,username,emp_email,emp_pwd,pwd_salt,signup_date) values(?,?,?,?,?,?,?)";
		String query2="Select * from emp_master_record where emp_email=?";
		try
		{
			PreparedStatement statement=connection.prepareStatement(query0);
			statement.setString(1,employee.getEmployeeEmail());
			ResultSet result=statement.executeQuery();
			if(result.next())
			{
				// Employee is already registered user.
				  throw new EmployeeFound();
			}
			
			statement=connection.prepareStatement(query1);
			statement.setString(1,employee.getEmployeeName());
			statement.setLong(2,employee.getEmployeeContact());
			statement.setString(3,employee.getEmployeeUsername());
			statement.setString(4,employee.getEmployeeEmail());
			statement.setString(5,employee.getEmployeePassword());
			
			statement.setString(6,null); // ----->This is for salt.

			Timestamp date=Timestamp.valueOf(employee.getSignUpDate());
			statement.setObject(7,date);
			statement.executeUpdate();
			
			// This is for fetching automated generated Employee Id.
			
			statement=connection.prepareStatement(query2);
			statement.setString(1,employee.getEmployeeEmail());
			result=statement.executeQuery();
			if(result.next())
			{
				employee.setEmployeeId(result.getInt(1));
			}
		} 
		catch (SQLException e)
		{
			throw  new ServerDown();
	    }
		
		return employee;
	}

	@Override
	// Admin Authentication
	
	public Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown, InvalidCredentials
	{
		Connection connection=JDBC.getConnection();
		String query0="select * from admin_master_record where username=? or admin_email=?";
		String query1="select * from admin_master_record where (username=? or admin_email=?) and admin_pwd=?";
		String query2="update admin_master_record set signin_date=? where (username=? or admin_email=?) and admin_pwd=?";
		
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query0);
			statement.setString(1,admin.getAdminUsername());
			statement.setString(2,admin.getAdminUsername());
			ResultSet result=statement.executeQuery();
			if(result.next()==false)
			{
				// Entered Admin Details are invalid.
				throw new AdminNotFound();
				
			}
			statement = connection.prepareStatement(query1);
			statement.setString(1,admin.getAdminUsername());
			statement.setString(2,admin.getAdminUsername());
			statement.setString(3,admin.getAdminPassword()); // Salt need to be take care here again for decryption.
			result=statement.executeQuery();
			if(result.next()==false)
			{
				// Entered password is incorrect
				throw new InvalidCredentials("Your password is incorrect");
			}
			admin.setAdminEmail(result.getString(5));
			admin.setAdminId(result.getInt(1));
			admin.setAdminName(result.getString(2));
			
			statement=connection.prepareStatement(query2);
			Timestamp date=Timestamp.valueOf(admin.getSignInDate());
			statement.setObject(1,date);
			statement.setString(2,admin.getAdminUsername());
			statement.setString(3,admin.getAdminUsername());
			statement.setString(4,admin.getAdminPassword());
	        statement.executeUpdate();
		} 
		catch (SQLException e)
		{
			// DataBase Down
			throw new ServerDown();
		}
		
		return admin;
	}

}
