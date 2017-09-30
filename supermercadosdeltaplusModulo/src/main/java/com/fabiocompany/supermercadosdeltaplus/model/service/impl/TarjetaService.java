/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Tarjeta;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ITarjetaDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ITarjetaService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class TarjetaService extends GenericService<Tarjeta, String>
		implements ITarjetaService {

	public TarjetaService(ITarjetaDAO dao) {
		super(dao);
	}
	
}
