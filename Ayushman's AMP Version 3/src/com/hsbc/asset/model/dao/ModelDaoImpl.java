package com.hsbc.asset.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.exception.EmployeeNotFound;
import com.hsbc.asset.exception.OrderNotAllowed;

//Dao layer implimentation.


import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;
import com.hsbc.asset.model.utility.DbUtility;

import sun.nio.ch.SelChImpl;

public class ModelDaoImpl implements ModelDao {

	@Override
	public Employee store(Employee emp) throws DbDown{
		try {
			Connection connection=DbUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for user_id_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			
			int seq = 0;
			if(rs.next()) {
				seq = rs.getInt(1);
			}
			
			emp.setUser_id(seq);
			
			PreparedStatement insertStatementEmployee=connection.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");
			insertStatementEmployee.setInt(1,emp.getUser_id());
			insertStatementEmployee.setString(2,emp.getUserName());
			insertStatementEmployee.setLong(3,emp.getContact());
			insertStatementEmployee.setString(4,emp.getEmail());
			Date today=new Date();
			insertStatementEmployee.setTimestamp(5,new Timestamp(today.getTime()));
			insertStatementEmployee.setString(6,emp.getUserName());
			insertStatementEmployee.setString(7,emp.getPassword());
			insertStatementEmployee.setTimestamp(8,new Timestamp(today.getTime()));
			
			
			int resultSetEmployee=insertStatementEmployee.executeUpdate();
			
			insertStatementEmployee.close();
			sequenceStatement.close();
			connection.close();
			}catch(Exception e) {
				throw new DbDown("Something went wrong.Please try again later.");
			}
			return emp;
	}

	@Override
	public Employee authenticate(Employee employeeLogin) throws EmployeeNotFound {
		Employee employeeLoginStore=null;
		int flag=0;
		try {
			Connection connection=DbUtility.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("select * from employee where (username=? or email=?) and password=?");
			preparedStatement.setString(1,employeeLogin.getUserName());
			preparedStatement.setString(2,employeeLogin.getEmail());
			preparedStatement.setString(3,employeeLogin.getPassword());
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				flag=1;
				employeeLoginStore=new Employee();
				employeeLoginStore.setEmail(resultSet.getString(4));
				employeeLoginStore.setUserName(resultSet.getString(6));
				PreparedStatement updateStatement = connection.prepareStatement("update employee set last_login=? where username=? or email=?");
				Date today=new Date();
				updateStatement.setTimestamp(1,(new Timestamp(today.getTime())));
				updateStatement.setString(2,employeeLoginStore.getUserName());
				updateStatement.setString(3,employeeLoginStore.getEmail());
				int updateSet=updateStatement.executeUpdate();
			}
			if(flag==1) {
				PreparedStatement searchStatement = connection.prepareStatement("select * from employee where email=?");
				searchStatement.setString(1,employeeLoginStore.getEmail());
				ResultSet searchSet=searchStatement.executeQuery();
				while(searchSet.next()) {
					employeeLoginStore=new Employee();
					employeeLoginStore.setUser_id(searchSet.getInt(1));
					employeeLoginStore.setName(searchSet.getString(2));
					employeeLoginStore.setContact(searchSet.getLong(3));
					employeeLoginStore.setEmail(searchSet.getString(4));
					employeeLoginStore.setDate(searchSet.getTimestamp(5));
					employeeLoginStore.setUserName(searchSet.getString(6));
					employeeLoginStore.setPassword(searchSet.getString(7));
					employeeLoginStore.setDate2(searchSet.getTimestamp(8));
					Date today=new Date();
				}
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
			if(employeeLoginStore==null) {
				throw new EmployeeNotFound("Sorry you have entered invalid credentials.");
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return employeeLoginStore;
		
	}

	@Override
	public List<String> getCategory() {
		List<String> list=new ArrayList<String>();
		try {
			Connection connection=DbUtility.getConnection();
			String loginQuery="select * from asset_type";
			PreparedStatement preparedStatement=connection.prepareStatement(loginQuery);
			ResultSet searchSet=preparedStatement.executeQuery();
			while(searchSet.next()) {
				String category=searchSet.getString(1);
				list.add(category);
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Item> getItem(String category) {
		List<Item> list=new ArrayList<Item>();
		try {
			Connection connection=DbUtility.getConnection();
			String loginQuery="select * from asset_details where item_type=?";
			PreparedStatement preparedStatement=connection.prepareStatement(loginQuery);
			preparedStatement.setString(1,category);
			ResultSet searchSet=preparedStatement.executeQuery();
			while(searchSet.next()) {
				Item item=new Item();
				item.setItem_id(searchSet.getInt(1));
				item.setItem_name(searchSet.getString(2));
				item.setItem_description(searchSet.getString(3));
				item.setItem_type(searchSet.getString(4));
				item.setQuantity(searchSet.getInt(5));
				list.add(item);
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int order(Item itemStore, Employee userSession) {
		int returnFlag=0;
		try {
			Connection connection=DbUtility.getConnection();
			PreparedStatement sequenceStatement = connection.prepareStatement("values(next value for order_placed_seq)");
			ResultSet rs = sequenceStatement.executeQuery();
			int seq = 0;
			if(rs.next()) {
				seq = rs.getInt(1);
			}
			PreparedStatement insertStatementProductBorrow=connection.prepareStatement("insert into product_borrow values(?,?,?,?,?,?,?,?)");
			insertStatementProductBorrow.setInt(1,seq);
			insertStatementProductBorrow.setInt(2,userSession.getUser_id());
			insertStatementProductBorrow.setInt(3,itemStore.getItem_id());
			insertStatementProductBorrow.setString(4,itemStore.getItem_type());
			Date today=new Date();
			Timestamp date=new Timestamp(today.getTime());
			insertStatementProductBorrow.setTimestamp(5,date);
			PreparedStatement preparedStatement=connection.prepareStatement("select lending_period from asset_type where type=?");
			preparedStatement.setString(1,itemStore.getItem_type());
			ResultSet searchSet=preparedStatement.executeQuery();
			int lendingPeriod=0;
			if(searchSet.next()) {
			lendingPeriod=searchSet.getInt(1);
			}
			long lendingPeriodSeconds=lendingPeriod * 24 * 60 * 60 * 1000;
			
			
			Timestamp addDays=new Timestamp(date.getTime() + lendingPeriodSeconds);
			
			
			insertStatementProductBorrow.setTimestamp(6,addDays);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateOld;
			try {
				dateOld = dateFormat.parse("23/09/2007");
				long time = dateOld.getTime();
				Timestamp actual=new Timestamp(time);
				insertStatementProductBorrow.setTimestamp(7,actual);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			insertStatementProductBorrow.setInt(8,1);
			PreparedStatement preparedStatementSearch=connection.prepareStatement("select isban from banned_users where user_id=?");
			preparedStatementSearch.setInt(1,userSession.getUser_id());
			ResultSet searchSetSearch=preparedStatementSearch.executeQuery();
			int flag=0;
			if(searchSetSearch.next()) {
				flag=searchSetSearch.getInt(1);
			}
			if(flag==0) {
				int resultSetUserLogin=insertStatementProductBorrow.executeUpdate();
				PreparedStatement updateStatement = connection.prepareStatement("update asset_details set quantity=quantity-1 where item_id=?");
				updateStatement.setInt(1,itemStore.getItem_id());
				int updateSet=updateStatement.executeUpdate();
				returnFlag=1;
			}
			
			insertStatementProductBorrow.close();
			sequenceStatement.close();
			connection.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return returnFlag;
	}

	@Override
	public List<Item> getAllLinked(Employee userSession) {
		List<Item> allItems=new ArrayList<Item>();
		try {
			Connection connection=DbUtility.getConnection();
			PreparedStatement searchStatement = connection.prepareStatement("select * from product_borrow where user_id=?");
			searchStatement.setInt(1,userSession.getUser_id());
			ResultSet rs = searchStatement.executeQuery();
			while(rs.next()) {
				Item item=new Item();
				item.setItem_id(rs.getInt(2));
				item.setItem_type(rs.getString(3));
				item.setOrder_id(rs.getInt(1));
				item.setDate_issued(rs.getTimestamp(5));
				item.setDate_return(rs.getTimestamp(6));
				item.setActual_return(rs.getTimestamp(7));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Timestamp actual=null;
				try {
					Date dateOld = dateFormat.parse("23/09/2007");
					long time = dateOld.getTime();
					actual=new Timestamp(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(item.getActual_return().equals(actual)) {
					allItems.add(item);
				}
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return allItems;
	}

	@Override
	public void returnProduct(int orderId) {
		
		try {
			Connection connection=DbUtility.getConnection();
			PreparedStatement updateStatement = connection.prepareStatement("update transaction_master set is_return=? and actual_return_date=? where trans_id=?");
			Timestamp date=new Timestamp(new Date().getTime());
			updateStatement.setObject(1,date);
			updateStatement.setInt(2,orderId);
			updateStatement.executeUpdate();
			
			PreparedStatement searchStatement=connection.prepareStatement("select asset_id from transaction_master where trans_id=?");
			searchStatement.setInt(1,orderId);
			ResultSet r=searchStatement.executeQuery();
			int itemId=0;
			if(r.next())
			{
				itemId=r.getInt(1);
			}
			
			PreparedStatement updateStatementAsset = connection.prepareStatement("update asset_record set quantity=quantity+1 where asset_id=?");
			updateStatementAsset.setInt(1,itemId);
			int updateSet=updateStatementAsset.executeUpdate();
			
			String query="select ban_days from asset_record where asset_id = "
					+ "(select asset_id from transaction_master where trans_id = ?)";

			
			searchStatement=connection.prepareStatement(query);
			searchStatement.setInt(1,orderId);
			r=searchStatement.executeQuery();
			if(r.next()) {
				int days=r.getInt(1);
				
				long lendingPeriodSeconds=days * 24 * 60 * 60 * 1000;
				Timestamp finalDate=new Timestamp(date.getTime() + lendingPeriodSeconds);
				
				updateStatement = connection.prepareStatement("select emp_id from transaction_master where trans_id=?");
				updateStatement.setInt(1,orderId);
				ResultSet rs=updateStatement.executeQuery();
				if(rs.next()) {
					int userId=rs.getInt(1);
					PreparedStatement selectStatement = connection.prepareStatement("select ban_date from ban_status where emp_id=?");
					selectStatement.setInt(1,userId);
					ResultSet rs1=selectStatement.executeQuery();
					if(rs1.next()) {
						Timestamp banDate=rs.getTimestamp(1);
						updateDate(finalDate,banDate,userId);
					}	
				}
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void updateDate(Timestamp date,Timestamp banDate,int userId) {
		try {
				Connection connection=DbUtility.getConnection();
				if(date.after(banDate)) {
					PreparedStatement selectStatement1 = connection.prepareStatement("update ban_status set ban_date=? where emp_id=?");
					selectStatement1.setObject(1,date);
					selectStatement1.setInt(2,userId);
					selectStatement1.executeUpdate();
					selectStatement1.close();
				}
				connection.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}


	}

	public void isBan(Employee emp) {
		try {
				
			Connection connection=DbUtility.getConnection();			
			PreparedStatement orderStatement = connection.prepareStatement("select tentative_return_date,item_type,item_id from product_borrow where user_id=? and is_return=false");
			int userId=emp.getUser_id();
			
			orderStatement.setInt(1,userId);
			ResultSet rs = orderStatement.executeQuery();
			
				
			
			
			Timestamp tent_date_return=null;
			
			Timestamp curr_date=new Timestamp(new Date().getTime());
			boolean is_return=false;
			String type=null;
			int item_id=0;
			
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
				tent_date_return=rs.getTimestamp(1);
				type=rs.getString(2);
				item_id=rs.getInt(3);
				
				if(curr_date.after(tent_date_return) && !is_return){
			
					PreparedStatement lendingStatement = connection.prepareStatement("update banned_users set isban=1 where user_id=?");
					lendingStatement.setInt(1,userId);
					lendingStatement.executeUpdate();
			
					lendingStatement = connection.prepareStatement("select fine from asset_master where asset_type=?");
					lendingStatement.setString(1,type);
					ResultSet rs1 = lendingStatement.executeQuery();
					
			
					if(rs1.next()) {
							double total_fine=rs1.getFloat(1);
							LocalDate d1 = LocalDate.parse(curr_date.toString());
							LocalDate d2 = LocalDate.parse(tent_date_return.toString());
							long diff=Duration.between(d1,d2).toDays();
							total_fine*=diff;
							lendingStatement=connection.prepareStatement("update transaction_master set current_fine=? where emp_id=? and asset_id=? and actual_return_date=null");
							lendingStatement.setInt(1,userId);
							lendingStatement.setInt(1,item_id);
							lendingStatement.executeUpdate();
						}

					}
				}
			
			}
			
			else {
				PreparedStatement resetStatement = connection.prepareStatement("select ban_date from ban_status where user_id=? and is_ban=true");
				resetStatement.setInt(1,userId);
				ResultSet rs1=resetStatement.executeQuery();
				if(rs1.next()){
					Timestamp ban_date=(Timestamp) rs1.getObject("ban_date");
					if(curr_date.after(ban_date)){
						PreparedStatement resetStatement1=connection.prepareStatement("delete from banned_users where user_id=?");
						resetStatement1.setInt(1,userId);
						resetStatement1.executeUpdate();
					}
				}
				
			}
			
			}catch(ClassNotFoundException e) {
					e.printStackTrace();
			}catch(SQLException e) {
					e.printStackTrace();
		}
	}
}
