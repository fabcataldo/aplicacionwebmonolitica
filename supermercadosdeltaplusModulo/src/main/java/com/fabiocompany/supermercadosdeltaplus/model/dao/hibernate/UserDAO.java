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
	public int addRoleDAO(int idrole, User user) throws PersistenceException, NotFoundException {
		Role r=new Role();
		r.setId(idrole);
		int a=0;
		Session session = getSession();
		try {
			for(int i=0;i<user.getRoles().size();i++) {
				if(user.getRoles().contains(r)) {
					session.beginTransaction();
					session.save(user);
					session.getTransaction().commit();
					a++;
				}
				else {
					a=0;
				}
			}	
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return a;
	}

}
