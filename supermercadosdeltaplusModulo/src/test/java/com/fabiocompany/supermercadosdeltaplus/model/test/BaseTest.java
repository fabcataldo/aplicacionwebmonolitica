package com.fabiocompany.supermercadosdeltaplus.model.test;

import org.hibernate.SessionFactory;

public class BaseTest {
	public BaseTest() {
	
	}

	public SessionFactory sessionFactory() {
		
		return HibernateUtil.getSessionFactory();		
		
	}
	
	

}
