/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.dao;

import java.util.List;

/**
 *
 * @author fabio
 */

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.IGenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public interface ICabeceraticketDAO extends IGenericDAO<Cabeceraticket, Integer>{
	public List<Object> obtenerListadeTicketsyUsuarios() throws PersistenceException;
}
