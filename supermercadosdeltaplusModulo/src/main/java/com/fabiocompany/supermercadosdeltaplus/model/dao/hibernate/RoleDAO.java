package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.SessionFactory;
import com.fabiocompany.supermercadosdeltaplus.model.Role;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IRoleDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;

public class RoleDAO extends GenericDAO<Role, Integer> implements IRoleDAO {

	public RoleDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


}
