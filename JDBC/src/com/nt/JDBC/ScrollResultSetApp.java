package com.nt.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollResultSetApp extends JFrame implements ActionListener{
	 
	 private final static String Select_query="SELECT * FROM STU_REGISTRATION";
	 private JLabel lsno,lsname,laddrs;	
	 private JTextField tsname,tsno,taddrs;
	 private JButton btnFirst,btnNext,btnLast,btnPrevious;
	 private Connection con;
	 private PreparedStatement ps;
	 private ResultSet rs;
	 Boolean flag = false;
	
	 
	public  ScrollResultSetApp(){
		
		setTitle("ScrollResultSetApp");
		setSize(300,300);
		setLayout(new FlowLayout());
		
		lsno= new JLabel("Student Number::");
		add(lsno);
		
		tsno= new JTextField(10);
		add(tsno);
		
		lsname= new JLabel("Student Name::");
		add(lsname);
		
		tsname= new JTextField(10);
		add(tsname);
		
		laddrs= new JLabel("Student Address::");
		add(laddrs);
		
		taddrs= new JTextField(10);
		add(taddrs);
		
		btnFirst = new JButton("First");
		add(btnFirst);
		btnFirst.addActionListener(this);

		btnNext = new JButton("Next");
		add(btnNext);
		btnNext.addActionListener(this);
		
		btnLast = new JButton("Last");
		add(btnLast);
		btnLast.addActionListener(this);
		
		btnPrevious = new JButton("Previous");
		add(btnPrevious);
		btnPrevious.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		makeConnection();
		
	}
       public  void makeConnection(){
		
		System.out.println("make connection....");
		
		try {
			
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//make connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			
			//CHECK CONNECTION...
			if(con==null){
				System.out.println("NOT CONNECTED....");
			}else{
				System.out.println("CONNECTED...");
			}
			if(con!=null)
				  ps=con.prepareStatement(Select_query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				//create resultset
			if (ps!=null){
				rs=ps.executeQuery();
                System.out.println("CONNECTION RS....."+rs);
			}
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
    }//makeconnection
			
	 
	public static void main(String[] args) {
		new ScrollResultSetApp();
	}




	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("Action-Performed");
		System.out.println("Action Performend recently..."+ae.getActionCommand());
		 //create Statement
		System.out.println(rs);
			try {
				
				if (ae.getSource()==btnFirst) {
				rs.first();
				flag=true;
				}
				else if (ae.getSource()==btnNext) {
					if(!rs.isLast())
					rs.next();
					flag=true;
					
				} //next logic if block.....................................................
				
				else if (ae.getSource()==btnPrevious) {
					if(!rs.isFirst())
						rs.previous();
					flag=true;
						
				}// previous logic if block..................................................
				else{
					if(ae.getSource()==btnLast)
					rs.last();
				}//else
				
				if(flag==true){
				tsno.setText(rs.getString(1));
				tsname.setText(rs.getString(2));
				taddrs.setText(rs.getString(3));
				
				}
			}//try..............................................................................
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//catch
		}//main
		
	}//class


