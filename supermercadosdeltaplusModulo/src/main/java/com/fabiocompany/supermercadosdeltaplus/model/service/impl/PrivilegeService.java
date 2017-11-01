package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Privilege;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPrivilegeDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPrivilegeService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

public class PrivilegeService extends GenericService<Privilege, Integer> implements IPrivilegeService {

	public PrivilegeService(IPrivilegeDAO dao) {
		super(dao);
	}

}
