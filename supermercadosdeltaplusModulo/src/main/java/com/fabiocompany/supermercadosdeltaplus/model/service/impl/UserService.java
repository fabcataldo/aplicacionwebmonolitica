package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IUserDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUserService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

public class UserService extends GenericService<User, Integer> implements IUserService {
	private IUserDAO dao;

	public UserService(IUserDAO dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public User load(String username) throws ServiceException, NotFoundException {
		try {
			return dao.load(username);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int addRoleService(User user, int idrole) throws ServiceException, NotFoundException {
		try {
			return dao.addRoleDAO(user, idrole);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
