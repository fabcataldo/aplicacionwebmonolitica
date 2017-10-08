/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabio
 */
public class DetalleticketDAO extends GenericDAO<Detalleticket, Integer> implements IDetalleticketDAO {
	private static Logger LOG = LoggerFactory.getLogger(DetalleticketDAO.class);
	
	public DetalleticketDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	//servicio de persistencia
	//servicio que voy a utilizar para el servicio de negocio "ofertarProducto() del detalleticketservice"
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> detalleDeTickets() throws PersistenceException {
		List<Detalleticket> tickets;
		List<Producto> productos;
		List<Object> ticketsyproductos=new ArrayList<Object>();
		try {
			tickets=getSession().createQuery("from Detalleticket").list();
			productos=getSession().createQuery("from Producto").list();
			ticketsyproductos.add(tickets);
			ticketsyproductos.add(productos);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return ticketsyproductos;
	}
	
	
}