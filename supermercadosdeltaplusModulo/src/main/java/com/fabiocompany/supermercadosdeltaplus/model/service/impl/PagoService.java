/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Pago;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPagoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPagoService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class PagoService extends GenericService<Pago, Integer>
		implements IPagoService {

	public PagoService(IPagoDAO dao) {
		super(dao);
	}
	
}
