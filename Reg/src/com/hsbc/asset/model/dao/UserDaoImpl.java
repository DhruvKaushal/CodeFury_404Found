package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.util.DBUtility;

public class UserDaoImpl implements UserDao{
	public User store(User user){
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for user_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			int seq = 0;
			if(rs.next()) {
				seq = rs.getInt(1);
			} 
			PreparedStatement insertStatement1=connection.prepareStatement("insert into user_record values(?,?,?,?,?,?)");
			PreparedStatement insertStatement2=connection.prepareStatement("insert into user_login values(?,?,?,?)");
			insertStatement1.setInt(1,seq);
			user.setUser_id(seq);
//			System.out.println(seq);
//			System.out.println(user.getEmail());
//			System.out.println(user.getContactNo());
//			System.out.println(user.getSignup_date_and_time());
			System.out.println("hello1");
			insertStatement1.setString(2,user.getName());
			insertStatement1.setString(3,user.getRole());
			insertStatement1.setString(4,user.getEmail());
			
			insertStatement1.setTimestamp(5, user.getSignup_date_and_time());
			insertStatement1.setLong(6,user.getContactNo());
			System.out.println("hello2");
			insertStatement2.setString(1, user.getEmail());
			insertStatement2.setString(2, user.getUsername());
			insertStatement2.setString(3, user.getPassword());
			insertStatement2.setTimestamp(4, user.getLogin_date_and_time());
			int resultSet2 = insertStatement2.executeUpdate();
			int resultSet1 = insertStatement1.executeUpdate();
			sequenceStatement.close();
			insertStatement1.close();
			insertStatement2.close();
			connection.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
