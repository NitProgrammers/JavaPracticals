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

public class GUIStudentRegistration extends JFrame implements ActionListener{
	private final String STUDENT_REG ="insert into STU_REGISTRATION values(SQ_STU.nextval,?,?)";
	private JLabel lsname,lsadd,lresult;
	private JTextField tsname,tsadd;
	private JButton register;
	Connection con;
	PreparedStatement ps;
	
	public GUIStudentRegistration(){
		
		//create frame and add all component
		
		setTitle("Student Registartion");
		setLayout(new FlowLayout());
		setSize(200,400);
		
		
		lsname = new JLabel("Student Name");
		add(lsname);
		tsname = new JTextField(10);
		add(tsname);
		lsadd = new JLabel("Student Address:");
		add(lsadd);
		tsadd = new JTextField(10);
		add(tsadd);
		register = new JButton("SignUp");
		add(register);
		register.addActionListener(this);
		lresult=new JLabel("Result is:: ");
		add(lresult);
		setVisible(true);
		//call action method
		 makeConnection();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	} 
	public void makeConnection(){
		
		try {
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			if(con!=null)
			ps=con.prepareStatement(STUDENT_REG);
			
			
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIStudentRegistration();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	String sname,sadd;
	sname=tsname.getText();
	sadd=tsadd.getText();
	
	try {
		ps.setString(1,sname);
		ps.setString(2, sadd);
		int result =ps.executeUpdate();
		if(result==1){
		lresult.setForeground(Color.green);	
		lresult.setText("registartion sucessfull");
		tsname.setText("");
		tsadd.setText("");
			
			
		}else{
			
			lresult.setForeground(Color.red);	
			lresult.setText("registartion failed");
			tsname.setText("");
			tsadd.setText("");	
			
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		lresult.setForeground(Color.red);	
		lresult.setText("registartion failed");
		tsname.setText("");
		tsadd.setText("");
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		lresult.setForeground(Color.red);	
		lresult.setText("registartion failed");
		tsname.setText("");
		tsadd.setText("");
	}
	
		
	}//action performeed

}//class
