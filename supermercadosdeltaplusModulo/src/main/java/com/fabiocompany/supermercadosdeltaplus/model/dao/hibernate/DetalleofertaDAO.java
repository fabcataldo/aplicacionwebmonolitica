/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Detalleoferta;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleofertaDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class DetalleofertaDAO extends GenericDAO<Detalleoferta, Integer> implements IDetalleofertaDAO {
	public DetalleofertaDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
