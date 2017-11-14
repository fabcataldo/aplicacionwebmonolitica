package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fabiocompany.supermercadosdeltaplus.model.AlgoritmoHash;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IAlgoritmoHashDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ICabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAlgoritmoHashService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

public class AlgoritmoHashService extends GenericService<AlgoritmoHash, Integer> implements IAlgoritmoHashService {
	private static Logger LOG = LoggerFactory.getLogger(DetalleticketService.class);
	private IAlgoritmoHashDAO dao;
	
	public AlgoritmoHashService(IAlgoritmoHashDAO dao) {
		super(dao);
		this.dao=dao;
	}
	
	public String ObtenerHashService(String url) throws ServiceException {
		try {
			return this.dao.ObtenerHash(url);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public String ObtenerArregloDeHashesService() throws ServiceException{
		try {
			return this.dao.ObtenerArreglodeHashes();
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
