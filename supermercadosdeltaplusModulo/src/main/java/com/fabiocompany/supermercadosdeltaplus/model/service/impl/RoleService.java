package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Role;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IRoleDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IRoleService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

public class RoleService extends GenericService<Role, Integer> implements IRoleService {

	public RoleService(IRoleDAO dao) {
		super(dao);
	}

}
