package com.fabiocompany.supermercadosdeltaplus.model.service;


import com.fabiocompany.supermercadosdeltaplus.model.AlgoritmoHash;
import com.fabiocompany.supermercadosdeltaplus.service.IGenericService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public interface IAlgoritmoHashService extends IGenericService<AlgoritmoHash, Integer> {
	public String ObtenerHashService(String url) throws ServiceException;
	public String[] ObtenerArregloDeHashesService() throws ServiceException;
}
