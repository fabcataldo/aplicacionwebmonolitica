package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.SessionFactory;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IUserDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

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
	public int addRoleDAO(User user, int idrole) throws PersistenceException, NotFoundException {
		User r=null;
		int a=0;
		//update Customer c set c.name = :newName where c.name = :oldName
			//insert into DelinquentAccount (id, name) select c.id, c.name from Customer c where ..c.
		try {
			String hqlupdate="INSERT INTO User u (u.roles.id)="+idrole;
			r = (User) getSession().createQuery(hqlupdate);
			if(r==null)
				throw new NotFoundException();
			else 
				a++;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return a;
	}

}
