/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Tipodepago;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ITipodepagoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ITipodepagoService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class TipodepagoService extends GenericService<Tipodepago, Integer>
		implements ITipodepagoService {

	public TipodepagoService(ITipodepagoDAO dao) {
		super(dao);
	}
	
}
