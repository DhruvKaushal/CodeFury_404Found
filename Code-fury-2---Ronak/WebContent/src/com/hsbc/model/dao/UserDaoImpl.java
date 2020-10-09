package com.hsbc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.model.utility.*;
import com.hsbc.exception.DataBaseDown;
import com.hsbc.exception.UserNotFound;
import com.hsbc.model.beans.Borrower;
import com.hsbc.model.beans.User;

public class UserDaoImpl implements UserDao {

	@Override
	// Login Purpose
	public User authentication(User user) throws UserNotFound
	{
		Connection connection=JDBC.getConnection();
		String query1="Select * from user_login where (username=? or email=?)and password=?";
		String query2="update  user_login set date_time=? where (username=? or email=?) and password=?";
		String email;
		try
		{
			PreparedStatement statement=connection.prepareStatement(query1);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getUsername());
			statement.setString(3,user.getPassword());
			ResultSet result=statement.executeQuery();
			if(result.next()==false)
			{
				throw new UserNotFound("User is not Found");
			}
			email=result.getString(2);
			statement=connection.prepareStatement(query2);
			statement.setString(1,user.getDate_time());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getUsername());
			statement.setString(4,user.getPassword());
	        statement.executeUpdate();
			
		} 
		catch (SQLException e)
		{
			throw new UserNotFound("Database Down");
		}
		 return  (new Borrower(email,user.getUsername()));
	}

	@Override
	public User storeUser(User user) throws DataBaseDown
	{
		Connection connection=JDBC.getConnection();
		String query1= "insert into user_record values(?,?,?,?,?,?)";
		String query2= "insert into user_login values(?,?,?,?)";
		int id=0;
		try
		{
			//When you connect to any databases, the auto-commit mode is set to true by default.
			//It means that the changes will be applied to the database once the statement successfully executed.
			connection.setAutoCommit(false);
			PreparedStatement statement=connection.prepareStatement("values(next value for user_seq)");
			ResultSet result=statement.executeQuery();
			if(result.next())
			{ 
				id=result.getInt(1);
				
			}
			statement=connection.prepareStatement(query2);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getDate_time());
			int i2=statement.executeUpdate();
			statement=connection.prepareStatement(query1);
			statement.setInt(1, id);
			statement.setString(2, user.getName());
			statement.setString(3, user.getRole());
			statement.setLong(4, user.getContact());
			statement.setString(5,user.getEmail());
			statement.setString(6, user.getDate_time());
			int i1=statement.executeUpdate();
			if(i1==1 && i2==1)
			{
				connection.commit();
				user.setUser_id(id);
			}
			
		} 
		catch (SQLException e)
		{
			try
			{
				connection.rollback();
				throw new DataBaseDown();
			} 
			catch (SQLException e1)
			{
				System.out.println(e1.getMessage());
				throw new DataBaseDown();
			}
			
	    }
		return user;

	}
	public User getUserInfo(User user) throws DataBaseDown
	{
		Connection connection=JDBC.getConnection();
		System.out.println(user.getUser_id());
		String query1="Select * from user_record where email=?";
		User user_info=null;
		try
		{
			PreparedStatement statement=connection.prepareStatement(query1);
			statement.setString(1, user.getEmail());
			ResultSet result=statement.executeQuery();
			if(result.next())
			{
				user_info=new Borrower(result.getInt(1),result.getString(2),result.getString(5));
			}
			
		} 
		catch (SQLException e)
		{
			throw new DataBaseDown("Database Down");
		}
		return user_info;
	}
	public List<String> getAllCategory() throws DataBaseDown
	{
		Connection connection=JDBC.getConnection();
		String query1="Select type from category";
		List<String>list=null;
		try
		{
			PreparedStatement statement=connection.prepareStatement(query1);
			ResultSet result=statement.executeQuery();
			list=new ArrayList<>();
			while(result.next())
			{
				list.add(result.getString(1));
			}
			
		} 
		catch (SQLException e)
		{
			throw new DataBaseDown();
		}
		return list;
		
	}
	public List<String> getAllAsset(String type) throws DataBaseDown
	{
		Connection connection=JDBC.getConnection();
		String query1="Select asset_name from assest where asset_type=?";
		List<String>list=null;
		try
		{
			PreparedStatement statement=connection.prepareStatement(query1);
			statement.setString(1, type);
			ResultSet result=statement.executeQuery();
			list=new ArrayList<>();
			while(result.next())
			{
				list.add(result.getString(1));
			}
		} 
		catch (SQLException e)
		{
			System.out.println("police");
			throw new DataBaseDown();
		}
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
		return list;
	}
}
