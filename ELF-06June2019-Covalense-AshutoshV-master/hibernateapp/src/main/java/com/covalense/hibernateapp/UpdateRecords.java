package com.covalense.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.covalense.hibernateapp.dto.EmployeeInfoBean;

public class UpdateRecords {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		EmployeeInfoBean empthe = session.get(EmployeeInfoBean.class, 101);
		empthe.setAge(35);
		session.update(empthe);
		Transaction transaction = session.beginTransaction();
		session.update(empthe);
		transaction.commit();
		session.close();
	}

}