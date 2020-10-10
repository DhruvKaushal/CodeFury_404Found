package com.hsbc.asset.model.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.utility.DBUtility;

public class UserDaoImpl implements UserDao {

	@Override
	public Asset storeAsset(Asset asset) throws  CategoryException{
		try {
			
			Connection connection = DBUtility.getConnection();
			String query = "insert into asset_record (asset_name, asset_type, asset_info, quantity) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, asset.getAssetName());
			preparedStatement.setString(2, asset.getAssetType());
			preparedStatement.setString(3, asset.getAssetDescription());
			preparedStatement.setInt(4, asset.getQuantity());
			
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			asset.setAssetId(rs.getInt(1));
			
			
			preparedStatement.close();
			connection.close();
			return asset;
			
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return asset;
	}

	@Override
	public AssetType addAssetType(AssetType assetType) {
		try {
			Connection connection = DBUtility.getConnection();
			String query = "Insert into asset_master values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, assetType.getTypeName());
			preparedStatement.setInt(2,assetType.getLendingPeriod());
			preparedStatement.setDouble(3, assetType.getFine());
			preparedStatement.setInt(4, assetType.getBan());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
			return assetType;
			} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	
			return assetType;
		
			}

	}

	@Override
	public List<String> getCategory() {
		List<String> categoryList = new ArrayList<String>();
			try {
			Connection connection = DBUtility.getConnection();
			String query = "select * from asset_master ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				categoryList.add(resultSet.getString(1));
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
			
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}
	
	

	@Override
	public List<String> getName() {
		List<String> nameList = new ArrayList<String>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select * from emp_master_record ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			nameList.add(resultSet.getString(2));
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return nameList;
		
	}

	@Override
	public List<Transaction> fetchOrdersByCategory(String type) {
				
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where asset_record.asset_type = ? order by transaction_master.issued_date desc" ;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
		
		
	}

	@Override
	public List<Transaction> fetchOrdersByCategoryAndName(String type, String name) {
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where asset_record.asset_type = ? and emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				
				
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, type);
		preparedStatement.setString(2, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
	
		
		
	}

	@Override
	public List<Transaction> fetchOrdersByName(String name) {
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				
				
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
	
		
	}

	@Override
	public List<Transaction> fetchOrdersByDate(Timestamp date) {
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? order by transaction_master.issued_date desc";
				
	
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
			
		
	}

	@Override
	public List<Transaction> fetchOrdersByDateAndName(Timestamp date, String name) {
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
			
	}

	@Override
	public List<Transaction> fetchOrdersByDateAnCategory(Timestamp date, String type) {
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query = "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and asset_record.asset_type = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
			
	}
	

	@Override
	public List<Transaction> fetchOrdersByDateNameCategory(Timestamp date, String name, String type) {
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DBUtility.getConnection();
		String query =  "select transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and emp_master_record.emp_name = ? and asset_record.asset_type = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setUserId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			orderList.add(order);
		}
		
		rs.close();
		preparedStatement.close();
		connection.close();
		
		
		
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return orderList;
	}

	@Override
	public List<Transaction> fetchDetails(int userId) {
		List<Transaction> userData=new ArrayList<Transaction>();
		try {
		Connection connection=DBUtility.getConnection();
		PreparedStatement selectStatement=connection.prepareStatement("select t1.*, t2.asset_type, t2.asset_name from transaction_master t1 join asset_record t2 on t1.asset_id = t2.asset_id where t1.emp_id = ?");
		
		selectStatement.setInt(1,userId);
		ResultSet resultSet=selectStatement.executeQuery();
		while(resultSet.next()) {
		Transaction data = new Transaction();
		
		data.setTransId(resultSet.getInt("trans_id"));
		data.setAssetId(resultSet.getInt("asset_id"));
		data.setAssetName(resultSet.getString("asset_name"));
		data.setAssetType(resultSet.getString("asset_type"));
		data.setDateIssued(resultSet.getTimestamp("issued_date"));
		data.setTentativeReturn(resultSet.getTimestamp("default_return_date"));
		data.setActualReturn(resultSet.getTimestamp("actual_return_date"));
		
		userData.add(data);
		}
		selectStatement.close();
		resultSet.close();
		connection.close();
		}catch(ClassNotFoundException e) {
		e.printStackTrace();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return userData;
	}

	@Override
	public List<User> fetchAll() {
		List<User> allUsers=new ArrayList<User>();
		try {
		Connection connection=DBUtility.getConnection();
		PreparedStatement selectStatement=connection.prepareStatement("select * from emp_master_record");
		
		ResultSet resultSet=selectStatement.executeQuery();
		while(resultSet.next()) {
		User user = new User();
		user.setContact(resultSet.getLong("emp_contact"));
		user.setName(resultSet.getString("emp_name"));
		user.setEmail(resultSet.getString("emp_email"));
		user.setUserName(resultSet.getString("username"));
		user.setUserId(resultSet.getInt("emp_id"));
		user.setDate(resultSet.getTimestamp("signin_date"));
		allUsers.add(user);
		}
		selectStatement.close();
		resultSet.close();
		connection.close();
		}catch(ClassNotFoundException e) {
		e.printStackTrace();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return allUsers;
	}

	@Override
	public List<User> fetchBanned() {
		List<User> bannedUsers=new ArrayList<User>();
		try {
		Connection connection=DBUtility.getConnection();
		PreparedStatement selectStatement=connection.prepareStatement("select t1.* from emp_master_record t1 join ban_status t2 on t1.emp_id = t2.emp_id");
			
		ResultSet resultSet=selectStatement.executeQuery();
		while(resultSet.next()) {
		User user = new User();
		user.setContact(resultSet.getLong("emp_contact"));
		user.setName(resultSet.getString("emp_name"));
		user.setEmail(resultSet.getString("emp_email"));
		user.setUserName(resultSet.getString("username"));
		user.setUserId(resultSet.getInt("emp_id"));
		user.setDate(resultSet.getTimestamp("signin_date"));
		bannedUsers.add(user);
		}
		selectStatement.close();
		resultSet.close();
		connection.close();
		}catch(ClassNotFoundException e) {
		e.printStackTrace();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return bannedUsers;
	}
	
	

}
