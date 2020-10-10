package com.hsbc.asset.model.dao;
import com.hsbc.asset.exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.util.DBUtility;
import com.hsbc.asset.model.util.PasswordEncryptionUtility;

public class UserDaoImpl implements UserDao{
	public User store(User user) throws EmailAlreadylExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException{
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for user_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			int seq = 0;
			if(rs.next()) {
				seq = rs.getInt(1);
			} 
			PreparedStatement insertStatement1=connection.prepareStatement("insert into users_table values(?,?,?,?,?,?,?,?,?)");
			insertStatement1.setInt(1,seq);
			user.setUser_id(seq);
			
			insertStatement1.setString(2,user.getName());
			insertStatement1.setString(3, user.getUsername());
			insertStatement1.setLong(4,user.getContactNo());
			insertStatement1.setString(5,user.getEmail());
			insertStatement1.setTimestamp(6, user.getLogin_date_and_time());
			insertStatement1.setTimestamp(7, user.getSignup_date_and_time());
			insertStatement1.setString(8, user.getPassword());
			insertStatement1.setString(9, user.getSalt());
			
			//Check if user already exists with provided user email
			PreparedStatement insertStatement2 = connection.prepareStatement("select * from users_table where email=?");
			insertStatement2.setString(1,user.getEmail());
			ResultSet r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new EmailAlreadylExistsException("This email is already registered. Please try with a different email ID!");
			}
			
			//Check if user already exists with provided user name
			insertStatement2 = connection.prepareStatement("select * from users_table where username=?");
			insertStatement2.setString(1,user.getUsername());
			r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new UsernameAlreadyExistsException("This username is already registered. Please using a different username!");
			}
			
			//Check if user already exists with provided user name
			insertStatement2 = connection.prepareStatement("select * from users_table where contact=?");
			insertStatement2.setLong(1,user.getContactNo());
			r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new ContactNoAlreadyExistsException("This contact number is already registered. Please using a different number!");
			}
			
			
			int resultSet1 = insertStatement1.executeUpdate();
			sequenceStatement.close();
			insertStatement1.close();
			connection.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	// user login added
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
