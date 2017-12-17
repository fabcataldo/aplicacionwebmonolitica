package com.fabiocompany.supermercadosdeltaplus.model.service;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.service.IGenericService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public interface IUserService extends IGenericService<User, Integer> {
	public User load(String username) throws ServiceException, NotFoundException;
	public User loadByEmail(String email) throws ServiceException, NotFoundException;
}
