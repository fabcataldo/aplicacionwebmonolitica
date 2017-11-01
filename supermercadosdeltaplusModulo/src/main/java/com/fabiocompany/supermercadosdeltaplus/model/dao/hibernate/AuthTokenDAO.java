package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.SessionFactory;

import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IAuthTokenDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;

public class AuthTokenDAO extends GenericDAO<AuthToken, String> implements IAuthTokenDAO {

	public AuthTokenDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
