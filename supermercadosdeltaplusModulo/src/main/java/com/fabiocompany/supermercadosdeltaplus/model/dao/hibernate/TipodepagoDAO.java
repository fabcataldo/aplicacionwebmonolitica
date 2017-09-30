/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Tipodepago;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ITipodepagoDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class TipodepagoDAO extends GenericDAO<Tipodepago, Integer> implements ITipodepagoDAO {
	public TipodepagoDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
