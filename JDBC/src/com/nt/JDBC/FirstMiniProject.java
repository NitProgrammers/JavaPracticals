package com.nt.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/* TABLE::::create table student_mgt(sno number(10) primary key,sname varchar2(10),address  varchar2(10));
 * INSERT::: insert into student_mgt  values(101,'raja','hyd');
 * UPDATE::: update student_mgt set sno=102,sname='jani',address='mumbai';
 */ 
public class FirstMiniProject extends JFrame implements ActionListener{
    private static final String  insert_query =" insert into student_mgt  values(?,?,?)";
    private static final String  update_query =" update student_mgt set sname=?,address=? where sno=?";	
    private static final String  delete_query="delete from student_mgt where sno=?";
    private static final String  select_query="select * from student_mgt where sno=?";
	
	
	JLabel lsname,lsno,lsadd,lresult;
       JTextField tsname,tsno,tsadd;
       JButton insert,update,delete,view;
       Connection con;
       Scanner sc;
       int stuid;
      int  insertResult,deleteResult,updateResult,selectResult;
       String sname,sadd;
       PreparedStatement ps;
       PreparedStatement ps1;
       PreparedStatement ps2,ps3;
       ResultSet rs=null;
       Boolean Flag=false;
      
       
      public FirstMiniProject(){
    	//create frame  
     	 System.out.println("MIniproject- 0-param Constru");
     	 setTitle("Student Management System");
     	 setSize(200,400);
     	 setLayout(new FlowLayout());  
    	  //add components
     	 
     	lsno = new JLabel("Student Number::") ;
    	add(lsno);
    	tsno = new JTextField(10);
    	add(tsno);
   	 
     	lsname = new JLabel("Student Name::") ;
    	add(lsname);
    	tsname = new JTextField(10);
    	add(tsname);
   	 
    	lsadd = new JLabel("Student Add::") ;
    	add(lsadd);
    	tsadd = new JTextField(10);
    	add(tsadd);
    	
    	insert = new JButton("Insert");
    	add(insert);
    	update = new JButton("Update");
    	add(update);
    	delete = new JButton("Delete");
    	add(delete);
    	view= new JButton("View");
    	add(view);
    	//result label
    	lresult = new JLabel("") ;
    	add(lresult);
    	setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	
        //call connection method
        istablishConnection();
    	//add action listener to each button
       	insert.addActionListener(this);
       	delete.addActionListener(this);
       	update.addActionListener(this);
       	view.addActionListener(this);
      } 
       
      public void istablishConnection(){
  		System.out.println("connection istablished");
  		try {
  			//read inputs like name sno add
  		
  			//register Driver
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			//create connection
  			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
  			//create prepared statement
  			ps=con.prepareStatement(insert_query);
  			ps1=con.prepareStatement(delete_query);
  			ps2=con.prepareStatement(update_query);
  			ps3=con.prepareStatement(select_query);
  			
  			
  		
  		} catch (ClassNotFoundException e) {
  			
  			e.printStackTrace();
  			
  		} catch (SQLException e) {
  			
  			e.printStackTrace();
  		}
  		
  	} 
       
       
	public static void main(String[] args) {
		System.out.println("main method(-)");
		new FirstMiniProject();

	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		//INSERTION LOGIC...........................................................................
		if (e.getSource()==insert) {
			System.out.println("Insert Action..");
			try {
				stuid=Integer.parseInt(tsno.getText());
				sname=tsname.getText();
				sadd=tsadd.getText();
				ps.setInt(1, stuid);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				
				insertResult=ps.executeUpdate();
				if (insertResult==0) {
					lresult.setText("Record not registered");
					tsno.setText("");
					tsname.setText("");
					tsadd.setText("");
					System.out.println("Record not registered");
				}else
					lresult.setText("Record registered successfully..");
					tsno.setText("");
					tsname.setText("");
					tsadd.setText("");
					System.out.println("Record registered successfully..");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Deletion Logic..................................................................................................
		if (e.getSource()==delete) {
			stuid=Integer.parseInt(tsno.getText());
			try {
				ps1.setInt(1, stuid);
				deleteResult=ps1.executeUpdate();
				if (deleteResult==0) {
					lresult.setText("Record not deleted");
					tsno.setText("");
					tsname.setText("");
					tsadd.setText("");
				} else {
					lresult.setText("Record deleted");
					tsno.setText("");
					tsname.setText("");
					tsadd.setText("");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//UPDATION LOGIC...............................................................................
		if (e.getSource()==update) {
			
			stuid=Integer.parseInt(tsno.getText());
			sname=tsname.getText();
			sadd=tsadd.getText();
			
			try {
				ps2.setInt(3, stuid);
				ps2.setString(2, sadd);
				ps2.setString(1, sname);
			    selectResult=ps3.executeUpdate();
				updateResult=ps2.executeUpdate();
				 if (updateResult==0) {
					 lresult.setText("Record not updated..");
						tsno.setText("");
						tsname.setText("");
						tsadd.setText("");
				} else {
					lresult.setText("Record updated sucessfully..");
					tsno.setText("");
					tsname.setText("");
					tsadd.setText("");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		//Select Logic.....................................................
		if (e.getSource()==view) {
			try {
				if (ps3!=null) 
				stuid=Integer.parseInt(tsno.getText());
				ps3.setInt(1, stuid);
				rs=ps3.executeQuery();
			
				while(rs.next()) {
					Flag=true;
					int isno=rs.getInt(1);
					String isname=rs.getString(2);
					String isadd=rs.getString(3);
					tsno.setText(String.valueOf(isno));
					tsname.setText(isname);
					tsadd.setText(isadd);
					if (Flag==true) {
						System.out.println("Record Selected");
					}else
						System.out.println("Record not found");
				}
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
