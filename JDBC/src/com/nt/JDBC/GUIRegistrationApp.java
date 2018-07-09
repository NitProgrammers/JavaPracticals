package com.nt.JDBC;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIRegistrationApp extends JFrame implements ActionListener {
   
	private final String INSERT_QUERY="INSERT INTO EMP_REGISTRATION VALUES(SQ_EMPREG.NEXTVAL,?,?,?,?)";
	private JLabel  lename,lempsal,lempadd,lempdesi,lresult;	
	private JTextField tename,tempsal,tempdesi,tempadd;
	private JButton register;
	Connection con = null;
	PreparedStatement ps=null;
	
	public GUIRegistrationApp(){
	
	System.out.println("GUIRegistrationApp constructor");
	
	setTitle("Registration App");
	setSize(200, 500);
	setLayout(new FlowLayout());
	//add componets in frame
	lename = new JLabel("Emloyee Name: ");
	add(lename);
	tename = new JTextField(10);
	add(tename);
	lempsal = new JLabel("Employee sal");
	add(lempsal);
	tempsal =new JTextField(10);
	add(tempsal);
	lempadd = new JLabel("Employee Address: ");
	add(lempadd);
	tempadd = new JTextField(10);
	add(tempadd);
	lempdesi = new JLabel("Employee Designetion: ");
	add(lempdesi);
	tempdesi = new JTextField(10);
	add(tempdesi);
	register = new JButton("Register emp detail");
	register.addActionListener(this);
	add(register);
	lresult = new JLabel("RESULT IS:: ");
	add(lresult);
	setVisible(true);
	//call action listiner on button click

	//call connection method
	makeConnection();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void makeConnection(){
		
		try {
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connections
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			if (con!=null)
			ps=con.prepareStatement(INSERT_QUERY);	
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	
	}//makeConnection

	
	
	public static void main(String[] args) {
		System.out.println("Main method...!!!");
		new GUIRegistrationApp();//anonymous object

	}//main

	@Override
	public void actionPerformed(ActionEvent e) {
		String ename,empdesi,empadd;
		double empsal;
		ename=tename.getText();
		empdesi=tempdesi.getText();
		empadd=tempadd.getText();
	    empsal=Double.parseDouble(tempsal.getText());
		
		
		try {
			ps.setString(1, ename);
			ps.setDouble(2,empsal);
			ps.setString(3, empadd);
			ps.setString(4, empdesi);
			
			int result = ps.executeUpdate();
			if (result==1) {
			lresult.setForeground(Color.GREEN);
			lresult.setText("Registration succeed");	
			tename.setText("");
			tempsal.setText("");
			tempadd.setText("");
			tempdesi.setText("");
			}else
			{   lresult.setForeground(Color.red);
				lresult.setText("Registration failed");	
				tename.setText("");
				tempsal.setText("");
				tempadd.setText("");
				tempdesi.setText("");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			lresult.setForeground(Color.red);
			lresult.setText("Registration failed:: "+e1.getMessage());	
			tename.setText("");
			tempsal.setText("");
			tempadd.setText("");
			tempdesi.setText("");
		}
            catch (Exception e1) {
			 e1.printStackTrace();
			lresult.setForeground(Color.red);
			lresult.setText("Registration failed:: "+e1.getMessage());	
			tename.setText("");
			tempsal.setText("");
			tempadd.setText("");
			tempdesi.setText("");
		}
		   
		
	}//action performed

}//class
