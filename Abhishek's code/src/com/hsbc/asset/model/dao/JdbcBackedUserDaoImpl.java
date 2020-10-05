package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.util.DBUtility;

public class JdbcBackedUserDaoImpl implements UserDao {
	
	@Override
	public User authenticate(String email, String password) throws AuthenticationException {
		User borrower = null;
		try {
			Connection connection = DBUtility.getConnection();
			String loginQuery = "select * from user_login where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				borrower = new Borrower();
				borrower.setEmail(resultSet.getString("email"));
				borrower.setUserName(resultSet.getString("username"));
				borrower.setPassword(resultSet.getString("password"));
				Timestamp date = new Timestamp(new Date().getTime());
				borrower.setSignInDate(date.toString());
			}
			
			if(borrower == null) {
				throw new AuthenticationException("Sorry Username or Password is Incorrect");
			} 
			
			loginQuery = "update user_login set signin_date_time = ? where email = ?";
			preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setObject(1, borrower.getSignInDate());
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
			loginQuery = "select * from user_record where email = ?";
			preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				borrower.setUserId(resultSet.getInt("user_id"));
				borrower.setName(resultSet.getString("name"));
				borrower.setRole(resultSet.getString("role"));
				borrower.setContact(Long.parseLong(resultSet.getString("contact")));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setSignUpDate(resultSet.getObject("signup_date_time").toString());
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return borrower;
	}

	@Override
	public User createUser(User borrower) {
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtility.getConnection();
			String checkQuery = "select * from user_login where username = ? and password = ?";
			preparedStatement = connection.prepareStatement(checkQuery);
			preparedStatement.setString(1, borrower.getUserName());
			preparedStatement.setString(2, borrower.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				borrower = null;
				return borrower;
			}
			
			String loginQuery = "insert into user_login values (?,?,?,null)";
			preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, borrower.getEmail());
			preparedStatement.setString(2, borrower.getUserName());
			preparedStatement.setString(3, borrower.getPassword());
			preparedStatement.executeUpdate();
			
			String recordQuery = "insert into user_record values (next value for user_seq,?,'Borrower',?,?,?)";
			preparedStatement = connection.prepareStatement(recordQuery);
			preparedStatement.setString(1, borrower.getName());
			preparedStatement.setLong(2, borrower.getContact());
			preparedStatement.setString(3, borrower.getEmail());
			Timestamp date = Timestamp.valueOf(borrower.getSignUpDate());
			preparedStatement.setObject(4, date);
			preparedStatement.executeUpdate();
			
			recordQuery = "select user_id from user_record where email = ?";
			preparedStatement = connection.prepareStatement(recordQuery);
			preparedStatement.setString(1, borrower.getEmail());
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				borrower.setUserId(rs.getInt(1));
			}
			
			connection.commit();
			System.out.println("Everything stored properly");
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return borrower;
	}
	
//	@Override
//	public List<Asset> fetchPopularAssets() {
//		List<Asset> popularList = new ArrayList<Asset>();
//		try {
//			Connection connection = DBUtility.getConnection();
//			String assetQuery = "select * from (select *, count(asset_id) from user_order group by asset_id order by count(asset_id) desc) where rownum <= 5";
//			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()) {
//				Asset newAsset = new Asset();
//				newAsset.setAssetId(Integer.valueOf(resultSet.getString("asset_id")));
//				newAsset.setType(resultSet.getString("type"));
//				popularList.add(newAsset);
//			}
//			
//			assetQuery = "select * asset_details where asset_id = ?";
//			while(resultSet.next()) {
//				Asset newAsset = new Asset();
//				newAsset.setAssetId(resultSet.getInt("asset_id"));
//				newAsset.setType(resultSet.getString("type"));
//				popularList.add(newAsset);
//			}
//			preparedStatement = connection.prepareStatement(assetQuery);
//			resultSet = preparedStatement.executeQuery();
//			
//			resultSet.close();
//			preparedStatement.close();
//			connection.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return categoryList;
//	}
	
	@Override
	public List<String> fetchCategory() {
		List<String> categoryList = new ArrayList<String>();
		try {
			Connection connection = DBUtility.getConnection();
			String categoryQuery = "select type from asset_type";
			PreparedStatement preparedStatement = connection.prepareStatement(categoryQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				categoryList.add(resultSet.getString(1));
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	@Override
	public List<Asset> fetchAllAssets(String type) {
		List<Asset> assetList = new ArrayList<Asset>();
		try {
			Connection connection = DBUtility.getConnection();
			String assetQuery = "select * from asset_details where type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Asset newAsset = new Asset();
				newAsset.setAssetId(resultSet.getInt("asset_id"));
				newAsset.setName(resultSet.getString("asset_name"));
				newAsset.setDesc(resultSet.getString("description"));
				newAsset.setQuantity(resultSet.getInt("quantity"));
				assetList.add(newAsset);
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return assetList;
	}

	@Override
	public String borrowAsset(int assetId) {
		String ans = "";
		try {
			Connection connection = DBUtility.getConnection();
//			String assetQuery = "select is_ban from user_ban where user_id = ?";
//			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
//			preparedStatement.setInt(1, assetId);
//			ResultSet resultSet = preparedStatement.executeQuery();
			
			String assetQuery = "select quantity from asset_details where asset_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setInt(1, assetId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int quantity = resultSet.getInt("quantity");
				if(quantity > 0) {
					assetQuery = "update asset_details set quantity = ? where asset_id = ?";
					preparedStatement = connection.prepareStatement(assetQuery);
					preparedStatement.setInt(1, quantity-1);
					preparedStatement.setInt(2, assetId);
					preparedStatement.executeUpdate();
					ans = "Request for this asset has been processed. Kindly collect it from the Asset Department.";
				}
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}

}
