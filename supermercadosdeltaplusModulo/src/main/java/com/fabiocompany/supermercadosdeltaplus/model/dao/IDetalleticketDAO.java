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

import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.IGenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public interface IDetalleticketDAO extends IGenericDAO<Detalleticket, Integer>{
	public List<Detalleticket> detalleDeTickets() throws PersistenceException;
}
