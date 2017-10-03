/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.model.Detalleofertaypromocion;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleofertaypromocionDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleofertaypromocionService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class DetalleofertaypromocionService extends GenericService<Detalleofertaypromocion, Integer>
		implements IDetalleofertaypromocionService {

	public DetalleofertaypromocionService(IDetalleofertaypromocionDAO dao) {
		super(dao);
	}
	
}
