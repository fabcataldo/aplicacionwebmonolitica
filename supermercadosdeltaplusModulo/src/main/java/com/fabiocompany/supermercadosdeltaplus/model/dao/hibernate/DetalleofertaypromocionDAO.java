/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Detalleofertaypromocion;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleofertaypromocionDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class DetalleofertaypromocionDAO extends GenericDAO<Detalleofertaypromocion, Integer> implements IDetalleofertaypromocionDAO {
	public DetalleofertaypromocionDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
