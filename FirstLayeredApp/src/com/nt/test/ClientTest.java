package com.nt.test;

import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import com.nt.controller.StudentController;

public class ClientTest {

	public static void main(String[] args) {
		//create ioc cointainer
		Scanner sc = null;
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/com/nt/cfgs/applicationContext.xml"));
		//get bean
		StudentController controller = factory.getBean("stController",StudentController.class);
		
		sc=new Scanner(System.in);
		
		System.out.println("Enter Student no..");
		String sno=sc.next();
		System.out.println("Enter Student Name..");
		String sname=sc.next();
		System.out.println("Enter Student marks1..");
		String m1=sc.next();
		System.out.println("Enter Student marks2..");
		String m2=sc.next();
		System.out.println("Enter Student marks3..");
		String m3=sc.next();
        System.out.println(controller.process(sno,sname, m1, m2, m3));
        sc.close();
	}

}
