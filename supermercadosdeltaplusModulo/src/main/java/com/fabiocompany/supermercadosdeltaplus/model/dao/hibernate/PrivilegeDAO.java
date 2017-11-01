package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.SessionFactory;
import com.fabiocompany.supermercadosdeltaplus.model.Privilege;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPrivilegeDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;

public class PrivilegeDAO extends GenericDAO<Privilege, Integer> implements IPrivilegeDAO {

	public PrivilegeDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
