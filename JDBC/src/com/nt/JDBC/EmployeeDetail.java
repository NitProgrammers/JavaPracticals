package com.nt.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class EmployeeDetail {
 private static String third_proc="{call third_proc(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= null;
	      Connection con=null;
	      CallableStatement cs=null;
	      int first =0;
	      int result=0;
	      
	      try {
	    	  //read input
	    	  sc= new Scanner(System.in);
	    	  System.out.println("Enter First number");
	    	  first=sc.nextInt();
	    	 
	    	  //register jbdc Driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			//create callble statement
			if(con!=null)
			cs=con.prepareCall(third_proc);
			if(cs!=null)
		    //register out param with jdbc
				cs.registerOutParameter(2,Types.VARCHAR);
			    cs.registerOutParameter(3, Types.VARCHAR);
			    cs.registerOutParameter(4, Types.VARCHAR);
			    //set values in param
			    cs.setInt(1,first);
			    //execute query
			    cs.execute();
			    //gather result
			    System.out.println("Employee NAme::"+cs.getString(2));
			    System.out.println("Employee NAme::"+cs.getString(3));
			    System.out.println("Employee NAme::"+cs.getInt(4));
			
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				      catch (Exception e) {
				  		// TODO: handle exception
				  		e.printStackTrace();
				  	}
	      finally {
				try {
					if(sc!=null)
						sc.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					if(con!=null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
 }//main
}
}//class
