package com.amp.asset.model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.util.DBUtility;
import com.amp.asset.model.util.RoleFactory;
import com.amp.asset.model.util.UserType;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;

//DAO layer implementation.
public class AssetDaoImpl implements AssetDao {

	@Override
	// Storing the employee Details.
	public Employee createEmployee(Employee employee) throws DuplicateEmployeeException, ServerDownException {
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtility.getConnection();
			String checkQuery = "select * from emp_master_record where emp_email = ?";
			preparedStatement = connection.prepareStatement(checkQuery);
			preparedStatement.setString(1, employee.getEmployeeUsername());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				// Employee is already registered user.
				throw new DuplicateEmployeeException("Employee already registered.");
			}

			String recordQuery = "insert into emp_master_record (emp_name,emp_contact,username,emp_email,emp_pwd,pwd_salt,signup_date) values (?,?,?,?,?,null,?)";
			preparedStatement = connection.prepareStatement(recordQuery);
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setLong(2, employee.getEmployeeContact());
			preparedStatement.setString(3, employee.getEmployeeUsername());
			preparedStatement.setString(4, employee.getEmployeeEmail());
			preparedStatement.setString(5, employee.getEmployeePassword());
			// preparedStatement.setString(6, employee.getSalt()); // ----->This is for
			// salt.
			Timestamp date = Timestamp.valueOf(employee.getSignUpDate());
			preparedStatement.setObject(7, date);
			preparedStatement.executeUpdate();

			// This is for fetching automated generated Employee Id.
			recordQuery = "select * from emp_master_record where emp_email=?";
			preparedStatement = connection.prepareStatement(recordQuery);
			preparedStatement.setString(1, employee.getEmployeeEmail());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				employee.setEmployeeId(rs.getInt(1));
			}

			connection.commit();
			System.out.println("Everything stored properly");
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServerDownException();
		}
		return employee;
	}

	@Override
	public Employee employeeAuthentication(String username, String password)
			throws AuthenticationException, EmployeeNotFoundException, ServerDownException {
		Employee employee = null;
		try {
			Connection connection = DBUtility.getConnection();
			String loginQuery = "select * from emp_master_record where username = ? or emp_email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				throw new EmployeeNotFoundException();
			}
			loginQuery = "select * from emp_master_record where (username = ? or emp_email = ?) and emp_pwd=?";
			preparedStatement = connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				throw new AuthenticationException();
			}
			employee = (Employee) RoleFactory.getInstance(UserType.EMPLOYEE);
			employee.setEmployeeId(resultSet.getInt("emp_id"));
			employee.setEmployeeName(resultSet.getString("emp_name"));
			employee.setEmployeeContact(resultSet.getLong("emp_contact"));
			employee.setEmployeeUsername(resultSet.getString("username"));
			employee.setEmployeeEmail(resultSet.getString("emp_email"));
			employee.setEmployeePassword(resultSet.getString("emp_pwd"));
			employee.setSignUpDate(resultSet.getObject("signup_date").toString());
			Timestamp date = new Timestamp(new Date().getTime());
			employee.setSignInDate(date.toString());

			loginQuery = "update emp_master_record set signin_date=? where (username=? or emp_email=?) and emp_pwd=?";
			preparedStatement = connection.prepareStatement(loginQuery);
			date = Timestamp.valueOf(employee.getSignInDate());
			preparedStatement.setObject(1, date);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.executeUpdate();

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServerDownException();
		}
		return employee;
	}

//	@Override
//	public Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown, InvalidCredentials
//	{
//		Connection connection=JDBC.getConnection();
//		String query0="select * from admin_master_record where username=? or admin_email=?";
//		String query1="select * from admin_master_record where (username=? or admin_email=?) and admin_pwd=?";
//		String query2="update admin_master_record set signin_date=? where (username=? or admin_email=?) and admin_pwd=?";
//		
//		PreparedStatement statement;
//		try
//		{
//			statement = connection.prepareStatement(query0);
//			statement.setString(1,admin.getAdminUsername());
//			statement.setString(2,admin.getAdminUsername());
//			ResultSet result=statement.executeQuery();
//			if(result.next()==false)
//			{
//				// Entered Admin Details are invalid.
//				throw new AdminNotFound();
//				
//			}
//			statement = connection.prepareStatement(query1);
//			statement.setString(1,admin.getAdminUsername());
//			statement.setString(2,admin.getAdminUsername());
//			statement.setString(3,admin.getAdminPassword()); // Salt need to be take care here again for decryption.
//			result=statement.executeQuery();
//			if(result.next()==false)
//			{
//				// Entered password is incorrect
//				throw new InvalidCredentials("Your password is incorrect");
//			}
//			admin.setAdminEmail(result.getString(5));
//			admin.setAdminId(result.getInt(1));
//			admin.setAdminName(result.getString(2));
//			
//			statement=connection.prepareStatement(query2);
//			Timestamp date=Timestamp.valueOf(admin.getSignInDate());
//			statement.setObject(1,date);
//			statement.setString(2,admin.getAdminUsername());
//			statement.setString(3,admin.getAdminUsername());
//			statement.setString(4,admin.getAdminPassword());
//	        statement.executeUpdate();
//		} 
//		catch (SQLException e)
//		{
//			// DataBase Down
//			throw new ServerDown();
//		}
//		
//		return admin;
//	}

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
	public List<String> fetchCategory() throws ServerDownException {
		List<String> categoryList = new ArrayList<String>();
		try {
			Connection connection = DBUtility.getConnection();
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
			Connection connection = DBUtility.getConnection();
			String assetQuery = "select * from asset_details where asset_type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(assetQuery);
			preparedStatement.setString(1, type);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Asset newAsset = new Asset();
				newAsset.setAssetId(resultSet.getInt("asset_id"));
				newAsset.setAssetName(resultSet.getString("asset_name"));
				newAsset.setAssetDescription(resultSet.getString("asset_description"));
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
	public List<Asset> getAllLinked(Employee employee) {
		List<Asset> allAssets = new ArrayList<Asset>();
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement searchStatement = connection
					.prepareStatement("select * from transaction_master where emp_id = ? and is_return = true");
			searchStatement.setInt(1, employee.getEmployeeId());
			ResultSet rs = searchStatement.executeQuery();
			while (rs.next()) {
				Asset Asset = new Asset();
				Asset.setTransId(rs.getInt(1));
				Asset.setAssetId(rs.getInt(2));
				Asset.setAssetType(rs.getString(3));
				Asset.setDateIssued(rs.getTimestamp(4));
				Asset.setDateReturn(rs.getTimestamp(5));
				Asset.setActualReturn(rs.getTimestamp(6));
				Timestamp actual = Timestamp.valueOf("23/09/2007");
				if (Asset.getActualReturn().equals(actual)) {
					allAssets.add(Asset);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allAssets;
	}

	@Override
	public void returnProduct(int orderId) {
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(
					"update transaction_master set is_return=? and actual_return_date=? where trans_id=?");
			Timestamp date = new Timestamp(new Date().getTime());
			updateStatement.setObject(1, date);
			updateStatement.setInt(2, orderId);
			updateStatement.executeUpdate();

			PreparedStatement searchStatement = connection
					.prepareStatement("select asset_id from transaction_master where trans_id=?");
			searchStatement.setInt(1, orderId);
			ResultSet rs = searchStatement.executeQuery();
			int AssetId = 0;
			if (rs.next()) {
				AssetId = rs.getInt(1);
			}

			PreparedStatement updateStatementAsset = connection
					.prepareStatement("update asset_record set quantity = quantity + 1 where asset_id=?");
			updateStatementAsset.setInt(1, AssetId);
			updateStatementAsset.executeUpdate();

			String query = "select ban_days from asset_record where asset_id = "
					+ "(select asset_id from transaction_master where trans_id = ?)";

			searchStatement = connection.prepareStatement(query);
			searchStatement.setInt(1, orderId);
			rs = searchStatement.executeQuery();
			if (rs.next()) {
				int days = rs.getInt(1);

				long lendingPeriodSeconds = days * (24 * 60 * 60 * 1000);
				Timestamp finalDate = new Timestamp(date.getTime() + lendingPeriodSeconds);

				updateStatement = connection.prepareStatement("select emp_id from transaction_master where trans_id=?");
				updateStatement.setInt(1, orderId);
				ResultSet rs1 = updateStatement.executeQuery();
				if (rs1.next()) {
					int userId = rs1.getInt(1);
					PreparedStatement selectStatement = connection
							.prepareStatement("select ban_date from ban_status where emp_id=?");
					selectStatement.setInt(1, userId);
					ResultSet rs2 = selectStatement.executeQuery();
					if (rs2.next()) {
						Timestamp banDate = rs.getTimestamp(1);
						updateDate(finalDate, banDate, userId);
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
	public Asset order(Asset assetStore, Employee employee) {
		boolean returnFlag = false;
		try {
			Connection connection = DBUtility.getConnection();
			
			PreparedStatement insertStatementProductBorrow = connection
					.prepareStatement("insert into transaction_master(emp_id, asset_id, issued_date, default_return_date, actual_return_date, is_return, current_fine) values(?,?,?,?,?,?,?)");
			insertStatementProductBorrow.setInt(1, employee.getEmployeeId());
			insertStatementProductBorrow.setInt(2, assetStore.getAssetId());
			Timestamp date = new Timestamp(new Date().getTime());
			insertStatementProductBorrow.setTimestamp(3, date);
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("select lending_period from asset_master where asset_type=?");
			preparedStatement.setString(1, assetStore.getAssetType());
			ResultSet searchSet = preparedStatement.executeQuery();
			
			int lendingPeriod = 0;
			
			if (searchSet.next()) {
				lendingPeriod = searchSet.getInt(1);
			}
			
			long lendingPeriodSeconds = (lendingPeriod * 24 * 60 * 60 * 1000);
			Timestamp addDays = new Timestamp(date.getTime() + lendingPeriodSeconds);
			insertStatementProductBorrow.setTimestamp(4, addDays);
			
			Timestamp actual = Timestamp.valueOf("23/09/2007");
			insertStatementProductBorrow.setTimestamp(5, actual);
			insertStatementProductBorrow.setBoolean(6, true);
			insertStatementProductBorrow.setFloat(7, 0);
			
			PreparedStatement preparedStatementSearch = connection
					.prepareStatement("select is_ban from ban_status where emp_id=?");
			preparedStatementSearch.setInt(1, employee.getEmployeeId());
			ResultSet searchSetSearch = preparedStatementSearch.executeQuery();
			
			boolean flag = false;
			if (searchSetSearch.next()) {
				flag = searchSetSearch.getBoolean(1);
			}
			if (!flag) {
				insertStatementProductBorrow.executeUpdate();
				PreparedStatement updateStatement = connection
						.prepareStatement("update asset_details set quantity=quantity-1 where asset_id=?");
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
				assetStore.setTransId(searchSet.getInt(1));
			}
						
			insertStatementProductBorrow.close();
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
			Connection connection = DBUtility.getConnection();
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
			Connection connection = DBUtility.getConnection();
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
