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
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
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
        	l = getSession().createQuery("FROM Usuario u WHERE u.nombreusuario LIKE :parteNombre")
        			.setString("parteNombre", "%"+parteDelNombre+"%").list();
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        	throw new PersistenceException(e.getMessage(), e);
        } finally {
        	closeSession();
        }
        return l;
    }
    
	public Usuario load(String nombredeusuario) throws PersistenceException, NotFoundException {
		Usuario r=null;
		try {
			r = (Usuario) getSession().createQuery(String.format("FROM %s WHERE nombreusuario=:nombredeusuario", getDomainClass().getSimpleName())).setString("nombreusuario", nombredeusuario).uniqueResult();
			if(r==null)
				throw new NotFoundException();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			closeSession();
		}
		return r;
	}
}
