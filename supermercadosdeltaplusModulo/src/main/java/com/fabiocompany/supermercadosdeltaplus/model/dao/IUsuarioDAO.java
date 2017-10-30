/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao;

/**
 *
 * @author fabio
 */

import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.IGenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;

import java.util.List;

public interface IUsuarioDAO extends IGenericDAO<Usuario, Integer>{
    	public List<Usuario> list(String parteDelNombre) throws PersistenceException; 
    	public Usuario load(String nombredeusuario) throws PersistenceException, NotFoundException;
}
