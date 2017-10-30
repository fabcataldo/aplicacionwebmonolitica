/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ICabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
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
	public List<Cabeceraticket> obtenerListadeTicketsyUsuarios() throws PersistenceException{
		List<Cabeceraticket> listatickets;
		try {
			listatickets=getSession().createQuery("from Cabeceraticket").list();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return listatickets;
	}
}
