/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Pago;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPagoDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class PagoDAO extends GenericDAO<Pago, Integer> implements IPagoDAO {
	public PagoDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
