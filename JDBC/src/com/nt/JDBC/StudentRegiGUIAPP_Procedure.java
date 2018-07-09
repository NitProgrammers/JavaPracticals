package com.nt.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StudentRegiGUIAPP_Procedure extends JFrame implements ActionListener,WindowListener{
  
	private static final String p_call="{call  p_call(?,?,?)}" ;
	
	
	JLabel lsname,lpass,lresult;
     JTextField tsname,tpass;
     JButton  register;
     Connection con;
     CallableStatement cs;
     String result;
     
     public StudentRegiGUIAPP_Procedure(){
    	//create frame  
    	 System.out.println("StudentRegiGUIAPP_Procedure- 0-param Constru");
    	 setTitle("Student Detail Validation");
    	 setSize(200,400);
    	 setLayout(new FlowLayout());
    	 
    	//Add componenet to frame 
    	 
    	lsname = new JLabel("StudentName::") ;
    	add(lsname);
    	tsname = new JTextField(10);
    	add(tsname);
    	lpass = new JLabel("password:: ");
    	add(lpass);
    	tpass = new JTextField(10);
    	add(tpass);
    	
    	register = new JButton("Validate");
    	add(register);
    //add actionlistiner on button
    	register.addActionListener(this);
    	
    //add window closing
    	this.addWindowListener(this);
    	
    	lresult=new JLabel("");
    	add(lresult);
    	
    	setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
      //call connection method
    	istablishConnection();
    	
     }
	public void istablishConnection(){
		System.out.println("connection istablished");
		try {
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1994");
			//create callable statement
			cs=con.prepareCall(p_call);
			//register out param
			cs.registerOutParameter(3,Types.VARCHAR);
		
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
    
	public static void main(String[] args) {
		System.out.println("Main method");
		new StudentRegiGUIAPP_Procedure();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("action listiner..");
		   
		String sname=null; 
		String spass=null;
		String result=null;
		
		try {
			//get Values
			sname=tsname.getText();
			spass=tpass.getText();
			
			//set values to call
			cs.setString(1, sname);
			cs.setString(2, spass);
			cs.execute();
			//gather result from out param
			result=cs.getString(3);
			//set text to result label
			lresult.setText(result);
			System.out.println(result);
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			lresult.setText(e1.getMessage());
			e1.printStackTrace();
		}catch(Exception e2){
			lresult.setText(e2.getMessage());
			e2.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		 System.out.println("windowClosing(-) method");
		//close jdbc objs
		  try{
			  if(cs!=null)
				  cs.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  
		  try{
			  if(con!=null)
				  con.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
