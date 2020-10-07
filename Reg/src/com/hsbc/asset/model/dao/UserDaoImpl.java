package com.hsbc.asset.model.dao;
import com.hsbc.asset.exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.util.DBUtility;

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
}
