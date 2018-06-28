package com.nt.service;

import com.nt.bao.StudentDAO;
import com.nt.bo.StudentBo;
import com.nt.dto.StudentDTO;

public class StudentServiceImpl implements StudentService{

	private StudentDAO dao;
	public StudentServiceImpl(StudentDAO dao){
		this.dao=dao;
	}
	
	
	@Override
	public String generateResult(StudentDTO dto) {
		// TODO Auto-generated method stub
		
		
		int total =dto.getM1()+dto.getM2()+dto.getM3();
		float avg = (float)total/3f;
		String result=null;
		if(avg<35){
		 result="fail";
		}
		else{
			result="pass";
		}
		StudentBo bo = new StudentBo();
		bo.setSno(dto.getSno());
		bo.setSname(dto.getSname());
		bo.setTotal(total);
		bo.setAvg(avg);
		bo.setResult(result);
		
		//use dao
		
		int count = dao.insert(bo);
		
		if(count==0)
		return"record not inserted and result is ";
		
		else return"record is inserted "+result;
		
	}
  
}
