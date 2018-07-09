package com.nt.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener,WindowListener{

	JLabel lfNumber,lsecNumber , lresult;
    JTextField tfNumber,tsecNumber,tresult;
    JButton Add,Sub,Divide,Multi;
    Connection con;
	
	
	  public Calculator(){
		  System.out.println("Calculator- 0-param Constru");
	     	 setTitle("Student Management System");
	     	 setSize(180,300);
	     	 setLayout(new FlowLayout());  
	    	  //add components
	     	 
	     	lfNumber = new JLabel("First Number::") ;
	    	add(lfNumber);
	    	tfNumber = new JTextField(10);
	    	add(tfNumber);
	   	 
	     	lsecNumber = new JLabel("Second Name::") ;
	    	add(lsecNumber);
	    	tsecNumber = new JTextField(10);
	    	add(tsecNumber);

	    	
	    	Sub = new JButton("Subtraction");
	    	add(Sub);
	    	Divide = new JButton("Divide");
	    	add(Divide);
	    	Multi = new JButton("Multiplication");
	    	add(Multi);
	    	Add= new JButton("Addition");
	    	add(Add);
	    	lresult = new JLabel("::::::::::::RESULT::::::::") ;
	    	add(lresult);
	    	//result label
	    	tresult =new  JTextField(10);
	    	add(tresult);
	    	setVisible(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    	
	    	Add.addActionListener(this);
	    	Sub.addActionListener(this);
	    	Multi.addActionListener(this);
		    Divide.addActionListener(this);
		    
		    this.addWindowListener(this);
		  
		  
		  
		  
	  }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Calculator();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		double fno=Double.parseDouble(tfNumber.getText());
		double secno=Double.parseDouble(tsecNumber.getText());
		//Addition Logic
		if (e.getSource()==Add) {
			
			double result=fno+secno;
			tresult.setText(String.valueOf(result));
		}
		//Subtraction Logic
		if (e.getSource()==Sub) {
			
			double result=fno-secno;
			tresult.setText(String.valueOf(result));
			
		}
		// Multiple logic
		if (e.getSource()==Multi) {
			double result=fno*secno;
			tresult.setText(String.valueOf(result));
		}
		
		//Divide logic
		if (e.getSource()==Divide) {
			double result=fno/secno;
			tresult.setText(String.valueOf(result));
		}
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("window is closing");
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
