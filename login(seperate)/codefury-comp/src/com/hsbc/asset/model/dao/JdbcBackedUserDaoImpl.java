package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.utilities.DBUtility;
import com.hsbc.asset.model.utilities.PasswordUtils;

public class JdbcBackedUserDaoImpl implements UserDao{
	
	public User authenticate(String userCredential, String password,boolean isAdmin) throws AuthenticationException {
		User user = null;
		
		
		try {
			Connection connection = DBUtility.getConnection();
			
			String loginQuery;

			
			if(isAdmin)
			{
				 loginQuery="select * from admin_table where email = ?  or username=?";	
			}
			else
			{
				loginQuery="select * from users_table where email = ?  or username=?";	
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, userCredential);
			preparedStatement.setString(2, userCredential);
			ResultSet resultSet = preparedStatement.executeQuery();
			


//			 // User provided password to verify(1st arg)// Encrypted and Base64 encoded password read from database(2nd arg)
		        
		        
		        // Salt value stored in database 
				
				//TO test when integrating with the registeration procedure
			
//				preparedStatement = connection.prepareStatement("select salt from users_table where user_id=?");
//				preparedStatement.setInt(1,resultSet.getInt("user_id"));
//		        String salt = preparedStatement.executeQuery().getString("salt");
//		        
//		        boolean passwordMatch = PasswordUtils.verifyUserPassword(password, resultSet.getString("passowrd"), salt);
		        
				boolean passwordMatch=true;
		        if(passwordMatch) 
		        {
		        	while(resultSet.next()) {
		        		
		        		//CREATING USER OBJECT FOR RETURNING TO SERVICE FOR SETTING SESSION ATTRIBUTE
						user = new User();
						Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
						
						//update login time in users_table
						String loginTimeUpdateQuery;
						if(isAdmin)
						{
							 loginTimeUpdateQuery="update admin_table set login_date_time =? where user_id=? ";	
						}
						else
						{
							loginTimeUpdateQuery="update users_table set login_date_time =? where user_id=? ";	
						}
						preparedStatement = connection.prepareStatement(loginTimeUpdateQuery);
						preparedStatement.setTimestamp(1, loginTimestamp);
						preparedStatement.setInt(2, resultSet.getInt("user_id"));
						preparedStatement.executeUpdate();
						
						
						//form user object
						
						user.setContactNo(resultSet.getLong("contact"));
						user.setEmail(resultSet.getString("email"));
						user.setLogin_date_and_time(loginTimestamp);
						user.setName(resultSet.getString("name"));
						user.setPassword(password);
//						user.setRole(resultSet.getString("role"));
						user.setSignup_date_and_time(resultSet.getTimestamp("signup_date_time"));
						user.setUser_id(resultSet.getInt("user_id"));
						user.setUsername(resultSet.getString("username"));
						user.setSalt("testSalt");
						return user;
					}
		        } else {

		        	throw new AuthenticationException("Sorry password incorrect");
		        }
				
			resultSet.close();
			preparedStatement.close();
			connection.close();
			//means user not present in db
			if(user == null) {
				throw new AuthenticationException("Sorry This is not registered user ");
			} 
			 
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}

}
