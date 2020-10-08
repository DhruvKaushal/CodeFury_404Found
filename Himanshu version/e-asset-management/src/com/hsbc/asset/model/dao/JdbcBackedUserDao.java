package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.utility.DbUtility;

public class JdbcBackedUserDao implements UserDao{

	@Override
	public Asset addAsset(Asset asset) throws  CategoryException{
	
		try {
			Connection connection = DbUtility.getConnection();
			String query = "Insert into asset_details(asset_name, asset_type,asset_description,quantity) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1,asset.getAssetName());
			preparedStatement.setString(2, asset.getAssetType());
			preparedStatement.setString(3, asset.getAssetDescription());
			preparedStatement.setInt(4,asset.getQuantity());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			asset.setAssetId(rs.getInt(1));
			
			preparedStatement.close();
			connection.close();
			
			return asset;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return asset;
		
	}

	@Override
	public AssetType addAssetType(AssetType assetType) {

		try {
			Connection connection = DbUtility.getConnection();
			String query = "Insert into asset_type values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, assetType.getAssetType());
			preparedStatement.setInt(2,assetType.getLendingPeriod());
			preparedStatement.setDouble(3, assetType.getFine());
			preparedStatement.setInt(4, assetType.getBanDays());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
			return assetType;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	
			return assetType;

		
	}

}

	@Override
	public List<String> getCategory()  {
		
		List <String> category = new ArrayList<String>();
		try {
			Connection connection = DbUtility.getConnection();
			String query = "Select type from asset_type";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				category.add(resultSet.getString("type"));
			}
			preparedStatement.close();
			resultSet.close();
			return category;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return category;
	}

	@Override
	public List<String> getName() {
		List <String> category = new ArrayList<String>();
		try {
			Connection connection = DbUtility.getConnection();
			String query = "Select name from user_table";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				category.add(resultSet.getString("name"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public int getTransactionId(String name) {
		int id= 0;
		try {
			Connection connection = DbUtility.getConnection();
			
			String query ="Select user_id from users_table where username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				id = resultSet.getInt("user_id");
			}
			preparedStatement.close();
			resultSet.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Transaction> getTransactionDetails(int id) {
		List<Transaction> transaction= new ArrayList<Transaction>();
		
		try {
		Connection connection = DbUtility.getConnection();

		String query = "select user_order.user_id, users_table.name, user_order.order_id, user_order.item_id, asset_details.asset_name, user_order.item_type, user_order.date_issued, user_order.tentative_return_date, user_order.actual_return_date  from ((user_order inner join users_table on user_order.user_id = users_table.user_id) inner join asset_details on user_order.item_id = asset_details.asset_id)  where users_table.user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			Transaction  trans = new Transaction();
			trans.setOrderId(resultSet.getInt("order_id"));
			trans.setUserName(resultSet.getString("name"));
			trans.setAssetName(resultSet.getString("asset_name"));
			trans.setUserId(resultSet.getInt("user_id"));
			trans.setAssetId(resultSet.getInt("item_id"));
			trans.setItemType(resultSet.getString("item_type"));
			trans.setDateIssue(resultSet.getTimestamp("date_issued"));
			trans.setActualReturn(resultSet.getTimestamp("actual_return_date"));
			trans.setTentativeReturn(resultSet.getTimestamp("tentative_return_date"));
			transaction.add(trans);
			}
		preparedStatement.close();
		resultSet.close();
		return transaction;
		
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
			return transaction;

	}
	
}