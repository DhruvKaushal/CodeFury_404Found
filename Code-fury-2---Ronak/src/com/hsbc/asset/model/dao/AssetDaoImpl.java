package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.asset.exception.AdminNotFound;
import com.hsbc.asset.exception.BorrowerFound;
import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.utility.*;


public class AssetDaoImpl implements AssetDao
{

	@Override
	public Object authenticationBorrower(Object object) throws BorrowerNotFound, ServerDown,WrongCredentials
	
	{
		
		Connection connection=JDBC.getConnection();
		Borrower borrower =(Borrower)object;
		String query0="select * from borrower where username=? or email=?";
		String query1="select * from borrower where (username=? or email=?) and password=?";
		String query2="update borrower set login_date_time=? where (username=? or email=?) and password=?";
		
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query0);
			statement.setString(1,borrower.getBorrower_username());
			statement.setString(2,borrower.getBorrower_username());
			ResultSet result=statement.executeQuery();
			if(result.next()==false)
			{
				throw new BorrowerNotFound("Not a valid User.");
				
			}
			statement = connection.prepareStatement(query1);
			statement.setString(1,borrower.getBorrower_username());
			statement.setString(2,borrower.getBorrower_username());
			statement.setString(3,borrower.getBorrower_password());
			result=statement.executeQuery();
			if(result.next()==false)
			{
				throw new WrongCredentials("Your password is incorrect");
			}
			borrower.setBorrower_email(result.getString(4));
			borrower.setBorrower_id(result.getInt(1));
			borrower.setBorrower_name(result.getString(2));
			statement=connection.prepareStatement(query2);
			statement.setString(1,borrower.getLogin_date_time());
			statement.setString(2,borrower.getBorrower_username());
			statement.setString(3,borrower.getBorrower_username());
			statement.setString(4,borrower.getBorrower_password());
	        statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new ServerDown();
		}
		return object;
		
    }
	
	
	
	@Override
	public Object store(Object object) throws ServerDown,BorrowerFound
	{
		Connection connection=JDBC.getConnection();
		Borrower borrower =(Borrower)object;
		String query0="select * from borrower where email=?";
		String query= "insert into borrower values(?,?,?,?,?,?,?,?,?)";
		int id=0;
		try
		{
			PreparedStatement statement=connection.prepareStatement(query0);
			statement.setString(1, borrower.getBorrower_email());
			ResultSet result=statement.executeQuery();
			if(result.next())
			{
				throw new BorrowerFound("E-mail is already registered");
			}
			statement=connection.prepareStatement("values(next value for borrower_seq)");
			result=statement.executeQuery();
			
			if(result.next())
			{ 
				id=result.getInt(1);
				
			}
			statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2,borrower.getBorrower_name());
			statement.setLong(3,borrower.getBorrower_contact());
			statement.setString(4, borrower.getBorrower_email());
			statement.setString(5,borrower.getBorrower_username());
			statement.setString(6,borrower.getBorrower_password());
			statement.setString(7,null);
			statement.setString(8,borrower.getSignup_date_time());
			statement.setString(9,null);
			statement.executeUpdate();
			borrower.setBorrower_id(id);
			
		} 
		catch (SQLException e)
		{
			throw new ServerDown();
	    }
		return borrower;

	}

	
	@Override
	public Object authenticationAdmin(Object object) throws AdminNotFound,ServerDown,WrongCredentials
	{
		Connection connection=JDBC.getConnection();
		Admin admin =(Admin)object;
		String query0="select * from admin where (username=? or email=?)";
		String query1="select * from admin where (username=? or email=?) and password=?";
		
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query0);
			statement.setString(1,admin.getAdmin_username());
			statement.setString(2,admin.getAdmin_username());
			ResultSet result=statement.executeQuery();
			if(result.next()==false)
			{
				throw new AdminNotFound();
			}
			statement = connection.prepareStatement(query1);
			statement.setString(1,admin.getAdmin_username());
			statement.setString(2,admin.getAdmin_username());
			statement.setString(3,admin.getAdmin_password());
			result=statement.executeQuery();
			if(result.next()==false)
			{
				throw new WrongCredentials("Your Password is incorrect");
				
			}
		}
		catch (SQLException e)
		{
			throw new ServerDown();
		}
		return object;
	}



	@Override
	public List<String> getAllAssetType() throws ServerDown
	{
		Connection connection=JDBC.getConnection();
		String query="select type from asset_type";
		List<String>category_list=null;
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query);
			ResultSet result=statement.executeQuery();
			category_list=new ArrayList<>();
			while(result.next())
			{
				category_list.add(result.getString(1));
			}
			
		}
		catch(SQLException e)
		{
			throw new ServerDown();
		}
		return category_list;
	}



	@Override
	public List<String> getAllAssets(String type) throws ServerDown
	{
		Connection connection=JDBC.getConnection();
		String query="select distinct(asset_name) from asset_details where asset_type=?";
		List<String>asset_list=null;
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1,type);
			ResultSet result=statement.executeQuery();
			asset_list=new ArrayList<>();
			while(result.next())
			{
				asset_list.add(result.getString(1));
			}
			
		}
		catch(SQLException e)
		{
			throw new ServerDown();
		}
		return asset_list;
	}

	


}
