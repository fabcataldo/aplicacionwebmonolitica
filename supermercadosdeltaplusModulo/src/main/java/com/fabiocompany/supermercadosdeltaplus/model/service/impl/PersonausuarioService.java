/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;
import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPersonausuarioDAO;
/**
 *
 * @author fabio
 */
public class PersonausuarioService extends GenericService<Personausuario, String>
		implements IPersonausuarioService {

	public PersonausuarioService(IPersonausuarioDAO dao) {
		super(dao);
	}
	
}
