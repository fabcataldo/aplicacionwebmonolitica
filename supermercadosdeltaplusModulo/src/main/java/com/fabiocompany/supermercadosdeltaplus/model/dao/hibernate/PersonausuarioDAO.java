/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IPersonausuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author fabio
 */
public class PersonausuarioDAO extends GenericDAO<Personausuario, String> implements IPersonausuarioDAO {
	public PersonausuarioDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
