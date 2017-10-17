/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.IGenericService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public interface IDetalleticketService extends IGenericService<Detalleticket, Integer>{
	//Servicio de negocio
	public String ofertarProducto() throws ServiceException;
}
