package com.jxau.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jxau.dao.User;
import com.jxau.mapper.UserMapper;

public class Demo01 {

	public static void main(String[] args) {
		System.out.println("----");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserMapper usermapper = (UserMapper)context.getBean("UserMapper");
		User user = usermapper.findUserById(1);
		System.out.println(user);
	}
}

