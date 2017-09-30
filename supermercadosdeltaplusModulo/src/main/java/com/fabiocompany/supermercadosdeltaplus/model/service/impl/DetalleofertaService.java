/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Detalleoferta;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleofertaDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleofertaService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class DetalleofertaService extends GenericService<Detalleoferta, Integer>
		implements IDetalleofertaService {

	public DetalleofertaService(IDetalleofertaDAO dao) {
		super(dao);
	}
	
}
