package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IUserDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.model.Role;

public class UserDAO extends GenericDAO<User, Integer> implements IUserDAO {

	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User load(String username) throws PersistenceException, NotFoundException {
		User r=null;
		try {
			r = (User) getSession().createQuery(String.format("FROM %s WHERE username=:username", getDomainClass().getSimpleName())).setString("username", username).uniqueResult();
			if(r==null)
				throw new NotFoundException();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return r;
	}
	
	@Override
	public User loadByEmail(String pemail) throws PersistenceException, NotFoundException {
		User r=null;
		try {
			r = (User) getSession().createQuery(String.format("FROM %s WHERE email=:pemail", getDomainClass().getSimpleName())).setString("pemail", pemail).uniqueResult();
			if(r==null)
				throw new NotFoundException();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return r;
	}
	
}
