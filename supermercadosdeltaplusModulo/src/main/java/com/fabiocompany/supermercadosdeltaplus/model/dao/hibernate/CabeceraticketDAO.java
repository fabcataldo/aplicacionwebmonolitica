/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ICabeceraticketDAO;
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
public class CabeceraticketDAO extends GenericDAO<Cabeceraticket, Integer> implements ICabeceraticketDAO {
	private static Logger LOG = LoggerFactory.getLogger(CabeceraticketDAO.class);
	public CabeceraticketDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	//servicio de persistencia
	//que ayuda al servicio de negocio usuarioQueMasCompro()
	public List<Object> obtenerListadeTicketsyUsuarios() throws PersistenceException{
		List<Cabeceraticket> listatickets;
		List<Usuario> usuarios;
		List<Object> ticketsyusuarios=new ArrayList<Object>();
		try {
			listatickets=getSession().createQuery("from Cabeceraticket").list();
			usuarios=getSession().createQuery("from Usuario").list();
			ticketsyusuarios.add(listatickets);
			ticketsyusuarios.add(usuarios);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return ticketsyusuarios;
	}
}
