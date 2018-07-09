package com.nt.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CalCubeSquareroceduer {
 /*
   create or replace procedure first_proc(x in number,y in number,z out number)as  begin
   z:=x+y;
  end; */
	 private static String first_proc="{call first_proc(?,?,?)}";
	 
	public static void main(String[] args){
		  Scanner sc= null;
	      Connection con=null;
	      CallableStatement cs=null;
	      int first =0;
	      int second=0;
	      int result=0;
	      
	      try {
	    	  //read input
	    	  sc= new Scanner(System.in);
	    	  System.out.println("Enter First number");
	    	  first=sc.nextInt();
	    	  System.out.println("Enter Second number.");
	    	  second=sc.nextInt();
	    	  //register jbdc Driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			//create callble statement
			if(con!=null)
			cs=con.prepareCall(first_proc);
			if(cs!=null)
				//register out param with jdbc
				cs.registerOutParameter(3,Types.INTEGER);
			//SET VALUES IN PARAM
			cs.setInt(1,first);
			cs.setInt(2,second);
			//execute the query
			cs.execute();
			//gather result from out param
			result=cs.getInt(3);
			System.out.println("Addition value is: "+result);
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
	      catch (Exception se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
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
		}
	}//main
		 
		 
	
	
	
	
}
