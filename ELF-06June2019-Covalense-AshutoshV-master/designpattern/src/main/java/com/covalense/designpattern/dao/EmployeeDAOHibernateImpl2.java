package com.covalense.designpattern.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.covalense.designpattern.beans.EmployeeInfoBean;
import com.covalense.designpattern.util.HibernateUtil;

public class EmployeeDAOHibernateImpl2 implements EmployeeDAO {

	@Override
	public EmployeeInfoBean getEmployeeInfo(String id) {
		return getEmployeeInfo(Integer.parseInt(id));
	}

	@Override
	public EmployeeInfoBean getEmployeeInfo(int id) {

		// 3. Open Session
		Session session = HibernateUtil.openSession();

		// 4. Interact with db via Session
		EmployeeInfoBean bean = session.get(EmployeeInfoBean.class, id);

		// 5. Close Session
		session.close();
		return bean;
	}

	private boolean saveOrUpdate(EmployeeInfoBean bean) {

		Transaction txn = null;
		try {
			Session session = HibernateUtil.openSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(bean);
			txn.commit();
			return true;
		} catch (Exception e) {
			txn.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<EmployeeInfoBean> getAllEmployeeInfo() {
		// to do
		return null;
	}

	@Override
	public boolean createEmployeeInfo(EmployeeInfoBean bean) {
		return saveOrUpdate(bean);
	}

	@Override
	public boolean updateEmployeeInfo(EmployeeInfoBean bean) {
		return saveOrUpdate(bean);
	}

	@Override
	public boolean deleteEmployeeInfoBean(int id) {
		Transaction txn = null;
		try {
			EmployeeInfoBean bean = new EmployeeInfoBean();
			bean.setId(id);
			Session session = HibernateUtil.openSession();
			txn = session.beginTransaction();
			session.delete(bean);
			txn.commit();
			return true;
		} catch (Exception e) {
			txn.rollback();
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteEmployeeInfo(String id) {
		return deleteEmployeeInfoBean(Integer.parseInt(id));
	}

}
