package com.amp.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.CategoryException;
import com.amp.asset.exception.ContactNoAlreadyExistsException;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.EmailAlreadyExistsException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.UsernameAlreadyExistsException;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.AssetType;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.beans.Transaction;

import com.amp.asset.model.utility.DbUtility;
import com.amp.asset.model.utility.PasswordEncryptionUtility;



public class JdbcBackedUserDao implements UserDao{

	@Override
	public Asset storeAsset(Asset asset) throws  CategoryException{
	
		try {
			Connection connection = DbUtility.getConnection();
			String query = "insert into asset_record (asset_name, asset_type, asset_info, quantity) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1,asset.getAssetName());
			preparedStatement.setString(2, asset.getTypeName());
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
			String query = "select asset_type from asset_master ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				category.add(resultSet.getString(1));
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
		List<String> nameList = new ArrayList<String>();
		try {
		Connection connection = DbUtility.getConnection();
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
	public void addMessage(Message message) {
		
		try {
			Connection connection = DbUtility.getConnection();
			String query = "Insert into message_record(message_content,trans_id) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1,message.getMessageContent());
			preparedStatement.setInt(2,message.getTransId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	
	}
	
}

	@Override
	public List<Message> getMessage(int userId) {
		List<Message> messageList = new ArrayList<Message>();
		try {			
			Connection connection = DbUtility.getConnection();
			String query = "select message_record.message_id, message_record.message_content , message_record.trans_id  from message_record INNER JOIN transaction_master on message_record.trans_id = transaction_master.trans_id  and transaction_master.trans_id = ? and message_record.is_read = false";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Message m = new Message();
				m.setMessageId(resultSet.getInt("message_id"));
				m.setTransId(resultSet.getInt("trans_id"));
				m.setMessageContent(resultSet.getString("message_content"));
				messageList.add(m);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();		
			return messageList;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return messageList;
	}

	@Override
	public void updateMessage(int messageId) {
		
		try {
			Connection connection = DbUtility.getConnection();
			String query = "update message_record set is_read = true where message_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, messageId);
			preparedStatement.executeUpdate(); 

			preparedStatement.close();
			connection.close();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	}

}

	@Override
	public int noOfUsers() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select count(emp_id) from emp_master_record";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();}
		return 0;
	}

	@Override
	public int noOfIssue() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select count(trans_id) from transaction_master";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();}
		return 0;
	}

	@Override
	public int noUsersBanned() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select count(emp_id) from ban_status";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();}
		return 0;
	}

	@Override
	public double getFine() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select sum(current_fine) from transaction_master";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			double id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int assetDue() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select count(*) from transaction_master where actual_return_date > default_return_date";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int assetsToday() {
		try {
			Connection connection = DbUtility.getConnection();
			String query = "select count(*) from transaction_master where actual_return_date  = CURRENT_TIMESTAMP";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(); 
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			preparedStatement.close();
			connection.close();
			return id;
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			}
		return 0;
	}

	@Override
	public List<Transaction> fetchDetails(int userId) {
		List<Transaction> userData=new ArrayList<Transaction>();
		try {
		Connection connection=DbUtility.getConnection();
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
		data.setReturn(resultSet.getBoolean("is_return"));
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
	public List<Employee> fetchAll() {
		List<Employee> allUsers=new ArrayList<Employee>();
		try {
		Connection connection=DbUtility.getConnection();
		PreparedStatement selectStatement=connection.prepareStatement("select * from emp_master_record");
		
		ResultSet resultSet=selectStatement.executeQuery();
		while(resultSet.next()) {
		Employee emp = new Employee();
		emp.setEmployeeContact(resultSet.getLong("emp_contact"));
		emp.setEmployeeName(resultSet.getString("emp_name"));
		emp.setEmployeeEmail(resultSet.getString("emp_email"));
		emp.setEmployeeUsername(resultSet.getString("username"));
		emp.setEmployeeId(resultSet.getInt("emp_id"));
		emp.setSignInDate(resultSet.getTimestamp("signin_date"));
		allUsers.add(emp);
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
	public List<Employee> fetchBanned() {
		List<Employee> bannedUsers=new ArrayList<Employee>();
		try {
		Connection connection=DbUtility.getConnection();
		PreparedStatement selectStatement=connection.prepareStatement("select t1.* from emp_master_record t1 join ban_status t2 on t1.emp_id = t2.emp_id");
			
		ResultSet resultSet=selectStatement.executeQuery();
		while(resultSet.next()) {
		Employee emp = new Employee();
		emp.setEmployeeContact(resultSet.getLong("emp_contact"));
		emp.setEmployeeName(resultSet.getString("emp_name"));
		emp.setEmployeeEmail(resultSet.getString("emp_email"));
		emp.setEmployeeUsername(resultSet.getString("username"));
		emp.setEmployeeId(resultSet.getInt("emp_id"));
		emp.setSignInDate(resultSet.getTimestamp("signin_date"));
		bannedUsers.add(emp);
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

	@Override
	public List<Transaction> fetchOrdersByCategory(String type) {
				
		List<Transaction> orderList = new ArrayList<Transaction>();
		try {
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where asset_record.asset_type = ? order by transaction_master.issued_date desc" ;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where asset_record.asset_type = ? and emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				
				
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, type);
		preparedStatement.setString(2, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));

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
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				
				
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? order by transaction_master.issued_date desc";
				
	
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and emp_master_record.emp_name = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, name);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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
		Connection connection = DbUtility.getConnection();
		String query = "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and asset_record.asset_type = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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
		Connection connection = DbUtility.getConnection();
		String query =  "select transaction_master.is_return ,transaction_master.emp_id, emp_master_record.emp_name, transaction_master.trans_id, transaction_master.asset_id, asset_record.asset_name, asset_record.asset_type, transaction_master.issued_date, transaction_master.default_return_date, transaction_master.actual_return_date, transaction_master.current_fine from ((transaction_master inner join emp_master_record on transaction_master.emp_id = emp_master_record.emp_id) inner join asset_record on transaction_master.asset_id = asset_record.asset_id) where transaction_master.issued_date < ? and emp_master_record.emp_name = ? and asset_record.asset_type = ? order by transaction_master.issued_date desc";
				

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setTimestamp(1, date);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, type);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			Transaction order = new Transaction();
			order.setEmpId(rs.getInt("emp_id"));
			order.setUserName(rs.getString("emp_name"));
			order.setTransId(rs.getInt("trans_id"));
			order.setAssetId(rs.getInt("asset_id"));
			order.setAssetName(rs.getString("asset_name"));
			order.setAssetType(rs.getString("asset_type"));
			order.setDateIssued(rs.getTimestamp("issued_date"));
			order.setTentativeReturn(rs.getTimestamp("default_return_date"));
			order.setActualReturn(rs.getTimestamp("actual_return_date"));
			order.setFine(rs.getFloat("current_fine"));
			order.setReturn(rs.getBoolean("is_return"));
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

	// user login added
	public Admin authenticateAdmin(String userCredential, String password) throws AuthenticationException {
		Admin user = null;
		
		
		try {
			Connection connection = DbUtility.getConnection();
			
			String loginQuery;

			loginQuery="select * from admin_master_record where admin_email = ?  or username=?";	

			
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, userCredential);
			preparedStatement.setString(2, userCredential);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			
			//resultSet.next();
			if(resultSet.next())
			{
				String salt = resultSet.getString("pwd_salt");
		        boolean passwordMatch = PasswordEncryptionUtility.verifyUserPassword(password, resultSet.getString("admin_pwd"), salt);
		        if(passwordMatch)
		        {
		        	user = new Admin();
					Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
					
					//update login time in users_table
					String loginTimeUpdateQuery;
					loginTimeUpdateQuery="update admin_master_record set signin_date =? where admin_id=? ";	
					preparedStatement = connection.prepareStatement(loginTimeUpdateQuery);
					preparedStatement.setTimestamp(1, loginTimestamp);
					preparedStatement.setInt(2, resultSet.getInt("admin_id"));
					preparedStatement.executeUpdate();
					
					
					//form user object
					
					user.setContact(resultSet.getLong("admin_contact"));
					user.setEmail(resultSet.getString("admin_email"));
					user.setDate(loginTimestamp);
					user.setName(resultSet.getString("admin_name"));
					user.setPassword(password);
//					user.setRole(resultSet.getString("role"));
					user.setAdminId(resultSet.getInt("admin_id"));
					user.setUserName(resultSet.getString("username"));
					user.setPasswordSalt(salt);
					
					return user;
		        }
		        else {
		        	throw new AuthenticationException("Sorry password incorrect");

		        }
			}
			
			
//			resultSet.next();
//			String salt = resultSet.getString("pwd_salt");
//			System.out.println(salt);
//			
//			 // User provided password to verify(1st arg)// Encrypted and Base64 encoded password read from database(2nd arg)
		        
		        
		        // Salt value stored in database 
				
//				//TO test when integrating with the registration procedure
//				String query = "select pwd_salt from admin_master_record where admin_id=?";
//				preparedStatement.setInt(1,resultSet.getInt("admin_id"));
//				preparedStatement = connection.prepareStatement(query);
//		        String salt = preparedStatement.executeQuery().getString("pwd_salt");
//		        
//		        boolean passwordMatch = PasswordEncryptionUtility.verifyUserPassword(password, resultSet.getString("admin_pwd"), salt);
//		        System.out.println(passwordMatch);
//				//passwordMatch=true;
//		        if(passwordMatch) 
//		        {
//		        	
//		        	while(resultSet.next()) {
//		        		
//		        		//CREATING USER OBJECT FOR RETURNING TO SERVICE FOR SETTING SESSION ATTRIBUTE
//						user = new Admin();
//						Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
//						
//						//update login time in users_table
//						String loginTimeUpdateQuery;
//						loginTimeUpdateQuery="update admin_master_record set signin_date =? where admin_id=? ";	
//						preparedStatement = connection.prepareStatement(loginTimeUpdateQuery);
//						preparedStatement.setTimestamp(1, loginTimestamp);
//						preparedStatement.setInt(2, resultSet.getInt("admin_id"));
//						preparedStatement.executeUpdate();
//						
//						
//						//form user object
//						
//						user.setContact(resultSet.getLong("admin_contact"));
//						user.setEmail(resultSet.getString("admin_email"));
//						user.setDate(loginTimestamp);
//						user.setName(resultSet.getString("admin_name"));
//						user.setPassword(password);
////						user.setRole(resultSet.getString("role"));
//						user.setAdminId(resultSet.getInt("admin_id"));
//						user.setUserName(resultSet.getString("username"));
//						user.setPasswordSalt(salt);
//						
//						return user;
//					}
//		        } else {
//
//		        	throw new AuthenticationException("Sorry password incorrect");
//		        }
//				
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
	public Employee store(Employee emp) throws EmailAlreadyExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException{
		try {
			Connection connection = DbUtility.getConnection();
//			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for user_seq)");
//			ResultSet rs = sequenceStatement.executeQuery();
//			int seq = 0;
//			if(rs.next()) {
//				seq = rs.getInt(1);
//			} 
			PreparedStatement insertStatement1=connection.prepareStatement("insert into emp_master_record(emp_name,emp_contact,username, emp_email,emp_pwd,pwd_salt,signin_date,signup_date) values(?,?,?,?,?,?,?,?)");
//			insertStatement1.setInt(1,seq);
//			emp.setEmployeeId(seq);
			
			insertStatement1.setString(1,emp.getEmployeeName());
			insertStatement1.setString(3, emp.getEmployeeUsername());
			insertStatement1.setLong(2,emp.getEmployeeContact());
			insertStatement1.setString(4,emp.getEmployeeEmail());
			insertStatement1.setTimestamp(7, emp.getSignInDate());
			insertStatement1.setTimestamp(8, emp.getSignUpDate());
			insertStatement1.setString(5, emp.getEmployeePassword());
			insertStatement1.setString(6, emp.getPasswordSalt());
			
			//Check if user already exists with provided user email
			PreparedStatement insertStatement2 = connection.prepareStatement("select * from emp_master_record where emp_email=?");
			insertStatement2.setString(1,emp.getEmployeeEmail());
			ResultSet r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new EmailAlreadyExistsException("This email is already registered. Please try with a different email ID!");
			}
			
			//Check if user already exists with provided user name
			insertStatement2 = connection.prepareStatement("select * from emp_master_record where username=?");
			insertStatement2.setString(1,emp.getEmployeeUsername());
			r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new UsernameAlreadyExistsException("This username is already registered. Please using a different username!");
			}
			
			//Check if user already exists with provided user name
			insertStatement2 = connection.prepareStatement("select * from emp_master_record where emp_contact=?");
			insertStatement2.setLong(1,emp.getEmployeeContact());
			r = insertStatement2.executeQuery();
			if(r.next()) {
				throw new ContactNoAlreadyExistsException("This contact number is already registered. Please using a different number!");
			}
			
			
			int resultSet1 = insertStatement1.executeUpdate();
			//sequenceStatement.close();
			insertStatement1.close();
			connection.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return emp;
	}
	public Employee authenticateEmployee(String userCredential, String password) throws AuthenticationException {
		Employee user = null;
		
		
		try {
			Connection connection = DbUtility.getConnection();
			
			String loginQuery;
			loginQuery="select * from emp_master_record where emp_email = ?  or username=?";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, userCredential);
			preparedStatement.setString(2, userCredential);
			ResultSet resultSet = preparedStatement.executeQuery();
			

//			 // User provided password to verify(1st arg)// Encrypted and Base64 encoded password read from database(2nd arg)
		        
		        
		        // Salt value stored in database 
				
				//TO test when integrating with the registeration procedure
			
				preparedStatement = connection.prepareStatement("select pwd_salt from emp_master_record where emp_id=?");
				preparedStatement.setInt(1,resultSet.getInt("emp_id"));
		        String salt = preparedStatement.executeQuery().getString("pwd_salt");
		        
		        boolean passwordMatch = PasswordEncryptionUtility.verifyUserPassword(password, resultSet.getString("emp_pwd"), salt);
		        
				//passwordMatch=true;
		        if(passwordMatch) 
		        {
		        	while(resultSet.next()) {
		        		
		        		//CREATING USER OBJECT FOR RETURNING TO SERVICE FOR SETTING SESSION ATTRIBUTE
						user = new Employee();
						Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
						
						//update login time in users_table
						String loginTimeUpdateQuery;

						loginTimeUpdateQuery="update emp_master_record set signin_date =? where emp_id=? ";	

						preparedStatement = connection.prepareStatement(loginTimeUpdateQuery);
						preparedStatement.setTimestamp(1, loginTimestamp);
						preparedStatement.setInt(2, resultSet.getInt("emp_id"));
						preparedStatement.executeUpdate();
						
						
						//form user object
						
						user.setEmployeeContact(resultSet.getLong("emp_contact"));
						user.setEmployeeEmail(resultSet.getString("emp_email"));
						user.setSignInDate(loginTimestamp);
						user.setEmployeeName(resultSet.getString("emp_name"));
						user.setEmployeePassword(password);
//						user.setRole(resultSet.getString("role"));
						user.setSignUpDate(resultSet.getTimestamp("signup_date"));
						user.setEmployeeId(resultSet.getInt("emp_id"));
						user.setEmployeeUsername(resultSet.getString("username"));
						user.setPasswordSalt(salt);
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
	@Override
	public List<String> fetchCategory() throws ServerDownException {
		List<String> categoryList = new ArrayList<String>();
		try {
			Connection connection = DbUtility.getConnection();
			String categoryQuery = "select asset_type from asset_master";
			PreparedStatement preparedStatement = connection.prepareStatement(categoryQuery);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				categoryList.add(resultSet.getString(1));
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServerDownException();
		}
		return categoryList;
	}

	@Override
	public List<Asset> fetchAllAssets(String type) throws ServerDownException {
		List<Asset> assetList = new ArrayList<Asset>();
		try {
			Connection connection = DbUtility.getConnection();
			String assetQuery = "select * from asset_record where asset_type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Asset newAsset = new Asset();
				newAsset.setAssetId(resultSet.getInt("asset_id"));
				newAsset.setAssetName(resultSet.getString("asset_name"));
				newAsset.setAssetDescription(resultSet.getString("asset_info"));
				newAsset.setQuantity(resultSet.getInt("quantity"));
				assetList.add(newAsset);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServerDownException();
		}
		return assetList;
	}

	@Override
	public List<Asset> fetchAllBorrowed(int empId, int flag) throws NoProductBorrowedException {
		List<Asset> allAssets = new ArrayList<Asset>();
		List<Asset> finalList = new ArrayList<Asset>();
		Asset asset = null;
		try {
			Connection connection = DbUtility.getConnection();
			PreparedStatement searchStatement = connection
					.prepareStatement("select * from transaction_master where emp_id = ? and is_return = false");
			searchStatement.setInt(1, empId);
			ResultSet rs = searchStatement.executeQuery();
			
			while (rs.next()) {
				asset = new Asset();
				asset.setOrderId(rs.getInt("trans_id"));
				asset.setAssetId(rs.getInt("asset_id"));
				asset.setDateIssued(rs.getTimestamp("issued_date"));
				asset.setTentativeReturn(rs.getTimestamp("default_return_date"));
				asset.setActualReturn(rs.getTimestamp("actual_return_date"));
				allAssets.add(asset);
			}
			
			if(allAssets.isEmpty() && flag == 0) {
				throw new NoProductBorrowedException();
			}
			
			for(Asset as : allAssets) {
				Asset temp = as;
				searchStatement = connection
						.prepareStatement("select * from asset_record where asset_id = ?");
				searchStatement.setInt(1, as.getAssetId());
				rs = searchStatement.executeQuery();
				if(rs.next()) {
					temp.setTypeName(rs.getString("asset_type"));
					temp.setAssetName(rs.getString("asset_name"));
					temp.setAssetDescription(rs.getString("asset_info"));
					temp.setQuantity(rs.getInt("quantity"));
				}
				finalList.add(temp);
			}
			
			searchStatement.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return finalList;
	}

	@Override
	public void returnProduct(int orderId) throws ServerDownException {
		try {
			Connection connection = DbUtility.getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(
					"update transaction_master set is_return = true, actual_return_date = ? where trans_id=?");
			Timestamp date = new Timestamp(new Date().getTime());
			updateStatement.setObject(1, date);
			updateStatement.setInt(2, orderId);
			updateStatement.executeUpdate();
			
			System.out.println("transid: " + orderId);
			PreparedStatement searchStatement = connection
					.prepareStatement("select asset_id from transaction_master where trans_id=?");
			searchStatement.setInt(1, orderId);
			ResultSet rs = searchStatement.executeQuery();
			int assetId = 0;
			if (rs.next()) {
				assetId = rs.getInt("asset_id");
			}
			
			System.out.println("assetid: "+assetId);
			PreparedStatement updateStatementAsset = connection
					.prepareStatement("update asset_record set quantity = quantity + 1 where asset_id=?");
			updateStatementAsset.setInt(1, assetId);
			updateStatementAsset.executeUpdate();

			String query = "select ban_period from asset_master where asset_type = (select asset_type from asset_record where asset_id = (select asset_id from transaction_master where trans_id = ?))";

			searchStatement = connection.prepareStatement(query);
			searchStatement.setInt(1, orderId);
			rs = searchStatement.executeQuery();
			
			if (rs.next()) {
				int days = rs.getInt(1);

				long lendingPeriodSeconds = days * (24 * 60 * 60 * 1000);
				Timestamp finalDate = new Timestamp(date.getTime() + lendingPeriodSeconds);
				updateStatement = connection.prepareStatement("select emp_id from transaction_master where trans_id=?");
				updateStatement.setInt(1, orderId);
				System.out.println("second1");
				ResultSet rs1 = updateStatement.executeQuery();
				System.out.println("second2");
				
				if (rs1.next()) {
					int empId = rs1.getInt(1);
					PreparedStatement selectStatement = connection
							.prepareStatement("select ban_date from ban_status where emp_id=?");
					selectStatement.setInt(1, empId);
					ResultSet rs2 = selectStatement.executeQuery();
					if (rs2.next()) {
						Timestamp banDate = rs.getTimestamp("ban_date");
						updateDate(finalDate, banDate, empId);
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Asset order(Asset assetStore, Employee employee) throws DuplicateOrderException {
		boolean returnFlag = false;
		try {
			Connection connection = DbUtility.getConnection();
			
			PreparedStatement insertStatement = connection
					.prepareStatement("select asset_type from asset_record where asset_type = (select asset_type from transaction_master where asset_id = ? and is_return = false)");
			insertStatement.setInt(1, assetStore.getAssetId());
			ResultSet searchSet = insertStatement.executeQuery();
			
			if(searchSet.next()) {
				throw new DuplicateOrderException();
			}
			
			insertStatement = connection
					.prepareStatement("insert into transaction_master(emp_id, asset_id, issued_date, default_return_date, actual_return_date, is_return, current_fine) values(?,?,?,?,?,?,?)");
			insertStatement.setInt(1, employee.getEmployeeId());
			insertStatement.setInt(2, assetStore.getAssetId());
			Timestamp date = new Timestamp(new Date().getTime());
			insertStatement.setTimestamp(3, date);
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("select lending_period from asset_master where asset_type=?");
			preparedStatement.setString(1, assetStore.getTypeName());
			searchSet = preparedStatement.executeQuery();
			
			int lendingPeriod = 0;
			
			if (searchSet.next()) {
				lendingPeriod = searchSet.getInt(1);
			}
			
			long lendingPeriodSeconds = (lendingPeriod * 24 * 60 * 60 * 1000);
			Timestamp addDays = new Timestamp(date.getTime() + lendingPeriodSeconds);
			insertStatement.setTimestamp(4, addDays);
			
			Timestamp actual = new Timestamp(new Date().getTime());
			insertStatement.setTimestamp(5, actual);
			insertStatement.setBoolean(6, false);
			insertStatement.setFloat(7, 0);
			
			PreparedStatement preparedStatementSearch = connection
					.prepareStatement("select is_ban from ban_status where emp_id=?");
			preparedStatementSearch.setInt(1, employee.getEmployeeId());
			ResultSet searchSetSearch = preparedStatementSearch.executeQuery();
			
			boolean flag = false;
			if (searchSetSearch.next()) {
				flag = searchSetSearch.getBoolean(1);
			}
			if (!flag) {
				insertStatement.executeUpdate();
				PreparedStatement updateStatement = connection
						.prepareStatement("update asset_record set quantity=quantity-1 where asset_id=?");
				updateStatement.setInt(1, assetStore.getAssetId());
				updateStatement.executeUpdate();
				returnFlag = true;
			}
			
			preparedStatement = connection
					.prepareStatement("select trans_id from transaction_master where asset_id = ? and emp_id = ? and is_return = false");
			preparedStatement.setInt(1, assetStore.getAssetId());
			preparedStatement.setInt(2, employee.getEmployeeId());
			searchSet = preparedStatement.executeQuery();
			
			if(searchSet.next()) {
				assetStore.setOrderId(searchSet.getInt(1));
			}
						
			insertStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(returnFlag) {
			return assetStore;
		}
		return null;
	}

	private void updateDate(Timestamp date, Timestamp banDate, int userId) {
		try {
			Connection connection = DbUtility.getConnection();
			if (date.after(banDate)) {
				PreparedStatement selectStatement1 = connection
						.prepareStatement("update ban_status set ban_date=? where emp_id=?");
				selectStatement1.setObject(1, date);
				selectStatement1.setInt(2, userId);
				selectStatement1.executeUpdate();
				selectStatement1.close();
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void isBan(Employee employeeLoginSuccess) {
		try {
			Connection connection = DbUtility.getConnection();
			PreparedStatement orderStatement = connection.prepareStatement(
					"select tentative_return_date,Asset_type,Asset_id from product_borrow where user_id=? and is_return=false");
			int empId = employeeLoginSuccess.getEmployeeId();

			orderStatement.setInt(1, empId);
			ResultSet rs = orderStatement.executeQuery();

			Timestamp tentDateReturn = null;
			Timestamp currDate = new Timestamp(new Date().getTime());
			boolean isReturn = false;
			String type = null;
			int assetId = 0;

			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					tentDateReturn = rs.getTimestamp(1);
					type = rs.getString(2);
					assetId = rs.getInt(3);

					if (currDate.after(tentDateReturn) && !isReturn) {

						PreparedStatement lendingStatement = connection
								.prepareStatement("update banned_users set isban=1 where user_id=?");
						lendingStatement.setInt(1, empId);
						lendingStatement.executeUpdate();

						lendingStatement = connection
								.prepareStatement("select fine from asset_master where asset_type=?");
						lendingStatement.setString(1, type);
						ResultSet rs1 = lendingStatement.executeQuery();

						if (rs1.next()) {
							float totalFine = rs1.getFloat(1);
							LocalDate d1 = LocalDate.parse(currDate.toString());
							LocalDate d2 = LocalDate.parse(tentDateReturn.toString());
							long diff = Duration.between(d1, d2).toDays();
							totalFine *= diff;
							lendingStatement = connection.prepareStatement(
									"update transaction_master set current_fine=? where emp_id=? and asset_id=? and actual_return_date=null");
							lendingStatement.setFloat(1, totalFine);
							lendingStatement.setInt(2, empId);
							lendingStatement.setInt(3, assetId);
							lendingStatement.executeUpdate();
						}

					}
				}

			}

			else {
				PreparedStatement resetStatement = connection
						.prepareStatement("select ban_date from ban_status where user_id=? and is_ban=true");
				resetStatement.setInt(1, empId);
				ResultSet rs1 = resetStatement.executeQuery();
				if (rs1.next()) {
					Timestamp ban_date = (Timestamp) rs1.getObject("ban_date");
					if (currDate.after(ban_date)) {
						PreparedStatement resetStatement1 = connection
								.prepareStatement("delete from banned_users where user_id=?");
						resetStatement1.setInt(1, empId);
						resetStatement1.executeUpdate();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}