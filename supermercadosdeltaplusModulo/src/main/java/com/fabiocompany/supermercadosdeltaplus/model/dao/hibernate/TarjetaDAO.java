/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Tarjeta;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ITarjetaDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class TarjetaDAO extends GenericDAO<Tarjeta, String> implements ITarjetaDAO {
	public TarjetaDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
