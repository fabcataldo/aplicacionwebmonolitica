package com.fabiocompany.supermercadosdeltaplus.model.dao;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.IGenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public interface IUserDAO extends IGenericDAO<User, Integer> {
	public User load(String username) throws PersistenceException, NotFoundException;
}
