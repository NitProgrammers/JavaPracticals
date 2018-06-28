package com.nt.bao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.nt.bo.StudentBo;

public class StudentDAOImpl implements StudentDAO {

	private static final String STUDENT_INSERT_QUERY="INSERT INTO SP_STUDENT VALUES(?,?,?,?,?)";
	private DataSource ds;
	
	public StudentDAOImpl(DataSource ds){
		this.ds=ds;
	}
	
	@Override
	public int insert(StudentBo bo){
		 Connection con = null;
		 PreparedStatement ps =null;
		 
		try {

			//get connection;
			con=ds.getConnection();
			
			//form prepared statement
			ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			//get values
			ps.setInt(1,bo.getSno());
			ps.setString(2, bo.getSname());
			ps.setInt(3, bo.getTotal());
			ps.setFloat(4,bo.getAvg());
			ps.setString(5, bo.getResult());
			
			int result=ps.executeUpdate();
			return result;
		
		} 
		catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

		
		
		
		
		
	}//method

}//class
