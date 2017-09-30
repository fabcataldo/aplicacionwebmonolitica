/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IUsuarioDAO;
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
public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements IUsuarioDAO {
    	private static Logger LOG = LoggerFactory.getLogger(UsuarioDAO.class);
	public UsuarioDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> list(String parteDelNombre) throws PersistenceException {
        List<Usuario> l = null;
	try {
		l = getSession().createQuery("FROM usuario u WHERE u.nombreusuario LIKE :parteNombre")
				.setString("parteNombre", "%"+parteDelNombre+"%").list();
	} catch (Exception e) {
		LOG.error(e.getMessage(), e);
		throw new PersistenceException(e.getMessage(), e);
	} finally {
		closeSession();
	}
	return l;
    }
}
