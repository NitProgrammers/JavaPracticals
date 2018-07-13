package com.nt.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllStmtApp extends JFrame implements ActionListener{
 private static String selectQuery ="SELECT SNO FROM  STUDENT_MARKS";//STUDENT_MARKS
 private static String infoQuery="";
 private JLabel lsno,lsname,lm1,lm2,lm3,lresult;	
 private JComboBox tsno;
 private JTextField tsname,tm1,tm2,tm3,tresult;
 private JButton btnDetail,btnResult;
 private Connection con;
 private Statement st;
 private PreparedStatement ps;
 private ResultSet rs1,rs2;
 private CallableStatement cs;
 
	
	public AllStmtApp(){
		//create frame add all components
		
		setTitle("AllStatement App");
		setSize(400,400);
		setLayout(new FlowLayout());
		
		lsno= new JLabel("Student no::");
		add(lsno);
		
		tsno= new JComboBox();
		add(tsno);
		
		btnDetail=new JButton("Detail");
		add(btnDetail);
		btnDetail.addActionListener(this);
		
		lsname=new JLabel("Sname::");
		add(lsname);
		
		tsname = new JTextField(10);
		add(tsname);
		
		lm1= new JLabel("Marks1::");
		add(lm1);
		
		tm1= new JTextField(10);
		add(tm1);
		
		lm2= new JLabel("Marks2::");
		add(lm2);
		
		tm2= new JTextField(10);
		add(tm2);
		
		lm3= new JLabel("Marks3::");
		add(lm3);
		
		tm3= new JTextField(10);
		add(tm3);
		
		lresult = new JLabel("Result:::");
		add(lresult);
		
		btnResult = new JButton("Result");
		add(btnResult);
		btnResult.addActionListener(this);
		
		setVisible(true);
		makeConnection();
		
		
	}//Constructor
	
	public  void makeConnection(){
		
		System.out.println("make connection....");
		
		try {
			
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//make connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			if (con==null) {
				System.out.println("not connected");
			} else {
                 System.out.println("connected");
			}
			
			//create Statement
			if(con!=null)
				st=con.createStatement();
			    //get resultset
			if(st!=null)
				rs1=st.executeQuery(selectQuery);
			System.out.println(rs1);
			if (rs1!=null){
			while(rs1.next()){
			//tsno.addItem(rs1.getInt(1));
				System.out.println("sno::"+rs1.getInt(1));
				
			}//while
				
			}//if
			
			//create prepared statement OBJECT
			if(con!=null)
			ps=con.prepareStatement("SELECT * FROM ALL_STATEMENT WHERE SNO=?");
			
			//CREATE CALLBLE STATEMENT
			if(con!=null){
				cs=con.prepareCall("{CALL P_FIND_RESULT(?,?,?,?)}");
				cs.registerOutParameter(4, Types.VARCHAR);
			}//IF	
			
		}// TRY
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	}

	public static void main(String[] args) {
		new AllStmtApp();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
