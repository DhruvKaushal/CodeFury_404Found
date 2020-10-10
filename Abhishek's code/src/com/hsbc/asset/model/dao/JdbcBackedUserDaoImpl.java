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
import com.hsbc.asset.exception.BorrowerNotFoundException;
import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.util.DBUtility;
import com.hsbc.asset.model.util.RoleFactory;
import com.hsbc.asset.model.util.UserType;

public class JdbcBackedUserDaoImpl implements UserDao {
	
	@Override
	public Borrower createUser(Borrower borrower) throws DuplicateUserException, DatabaseDownException {
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtility.getConnection();
			String checkQuery = "select * from user_record where username = ? and password = ?";
			preparedStatement = connection.prepareStatement(checkQuery);
			preparedStatement.setString(1, borrower.getUserName());
			preparedStatement.setString(2, borrower.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				borrower = null;
				return borrower;
			}
			
			String recordQuery = "insert into user_record values (next value for user_seq, ?, ?, ?, ?,null,?,?,'a')";
			preparedStatement = connection.prepareStatement(recordQuery);
			preparedStatement.setString(1, borrower.getName());
			preparedStatement.setString(2, borrower.getUserName());
			preparedStatement.setLong(3, borrower.getContact());
			preparedStatement.setString(4, borrower.getEmail());
			Timestamp date = Timestamp.valueOf(borrower.getSignUpDate());
			preparedStatement.setObject(5, date);
			preparedStatement.setString(6, borrower.getPassword());
			//preparedStatement.setString(7, borrower.getSalt());
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
			throw new DatabaseDownException();
		}
		return borrower;
	}
	
	@Override
	public Borrower authenticate(String email, String password) throws AuthenticationException, BorrowerNotFoundException, DatabaseDownException {
		Borrower borrower = null;
		try {
			Connection connection = DBUtility.getConnection();
			String loginQuery = "select * from user_record where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()){
				throw new BorrowerNotFoundException();
			}
			
			loginQuery = "select * from user_record where email = ? and password = ?";
			preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				throw new AuthenticationException();
			}
			else {
				borrower = (Borrower) RoleFactory.getInstance(UserType.BORROWER);
				borrower.setUserId(resultSet.getInt("user_id"));
				borrower.setName(resultSet.getString("name"));
				borrower.setUserName(resultSet.getString("username"));
				borrower.setContact(resultSet.getLong("contact"));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setSignUpDate(resultSet.getObject("signup_date_time").toString());
				Timestamp date = new Timestamp(new Date().getTime());
				borrower.setSignInDate(date.toString());
				borrower.setPassword(resultSet.getString("password"));
			}
			
			loginQuery = "update user_record set login_date_time = ? where email = ?";
			preparedStatement = connection.prepareStatement(loginQuery);
			Timestamp date = Timestamp.valueOf(borrower.getSignInDate());
			preparedStatement.setObject(1, date);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} 
		catch(SQLException | ClassNotFoundException e) {
			throw new DatabaseDownException();
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
	public List<String> fetchCategory() throws DatabaseDownException {
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
			throw new DatabaseDownException();
		}
		return categoryList;
	}
	
	@Override
	public List<Asset> fetchAllAssets(String type) throws DatabaseDownException {
		List<Asset> assetList = new ArrayList<Asset>();
		try {
			Connection connection = DBUtility.getConnection();
			String assetQuery = "select * from asset_details where asset_type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Asset newAsset = new Asset();
				newAsset.setAssetId(resultSet.getInt("asset_id"));
				newAsset.setName(resultSet.getString("asset_name"));
				newAsset.setDesc(resultSet.getString("asset_description"));
				newAsset.setQuantity(resultSet.getInt("quantity"));
				assetList.add(newAsset);
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseDownException();
		}
		return assetList;
	}
	
	@Override
	public boolean banCheck(int userId) throws DatabaseDownException {
		boolean isBan = false;
		try {
			Connection connection = DBUtility.getConnection();
			String assetQuery = "select is_ban from user_ban where user_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				isBan = resultSet.getBoolean("is_ban");
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseDownException();
		}
		return isBan;
	}

	@Override
	public String borrowAsset(int assetId) throws DatabaseDownException {
		String ans = "";
		try {
			Connection connection = DBUtility.getConnection();
			
			String assetQuery = "select quantity from asset_details where asset_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setInt(1, assetId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int quantity = resultSet.getInt("quantity");
				if(quantity > 0) {
					assetQuery = "update asset_details set quantity = ? where asset_id = ?";
					preparedStatement = connection.prepareStatement(assetQuery);
					preparedStatement.setInt(1, quantity-1);
					preparedStatement.setInt(2, assetId);
					preparedStatement.executeUpdate();
					ans = "Your Request for this asset has been processed. Kindly collect it from the Asset Department.";
				}
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseDownException();
		}
		return ans;
	}

	@Override
	public void deleteUser(int userId) throws DatabaseDownException {
		try {
			Connection connection = DBUtility.getConnection();
			String loginQuery = "delete from user_record where user_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseDownException();
		}
	}

}
