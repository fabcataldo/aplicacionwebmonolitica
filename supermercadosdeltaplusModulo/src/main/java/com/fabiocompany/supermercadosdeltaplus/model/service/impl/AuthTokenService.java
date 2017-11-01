package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IAuthTokenDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAuthTokenService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

public class AuthTokenService extends GenericService<AuthToken, String> implements IAuthTokenService {

	public AuthTokenService(IAuthTokenDAO dao) {
		super(dao);
	}

	
}
