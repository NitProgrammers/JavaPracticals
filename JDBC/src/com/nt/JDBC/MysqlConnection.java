package com.nt.JDBC;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class MysqlConnection {

	public static void main(String[] args) {
	  Connection con = null;
	  Statement st = null;
	  ResultSet rs=null;
	    
	 try {
		// register Driver
		// Class.forName("org.gjt.mm.mysql.Driver");
		 Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ntajDB","root","root");
		 //st = con.createStatement();
		 if (con==null) {
			System.out.println("Connection not Establish");
		}else{
			System.out.println("connection Establish...hello");
		}
		
	     } catch (Exception e) {
		// TODO: handle exception
	}
		

	}//main

}//class
