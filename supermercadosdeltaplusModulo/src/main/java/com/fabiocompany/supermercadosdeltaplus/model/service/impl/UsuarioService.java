/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IUsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabio
 */
public class UsuarioService extends GenericService<Usuario, Integer>
		implements IUsuarioService {
    private static Logger LOG = LoggerFactory.getLogger(UsuarioService.class);
    private IUsuarioDAO dao;
	public UsuarioService(IUsuarioDAO dao) {
		super(dao);
        this.dao=dao;
	}

    @Override
    public List<Usuario> list(String parteDelNombre) throws ServiceException {
        try {
            return dao.list(parteDelNombre);
        } catch (PersistenceException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
	@Override
	public Usuario load(String nombredeluser) throws ServiceException, NotFoundException {
		try {
			return dao.load(nombredeluser);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
   
}
