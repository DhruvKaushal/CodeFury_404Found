package com.hsbc.asset.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.jdbc.ClientDriver;



public class DBUtility {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// 1st step is to load the class, Class.forName(classname.class.getName());
		Class.forName(ClientDriver.class.getName());
		// 2nd step is to establish the connection Connection con = DriverManager.getConnection(url, un, pw);
		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/mydb", "dhruv", "dhruv123");
		return connection;
	}
}