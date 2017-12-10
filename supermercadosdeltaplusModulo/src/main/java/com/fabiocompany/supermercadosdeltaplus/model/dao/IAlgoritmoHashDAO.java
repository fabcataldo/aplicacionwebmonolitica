package com.fabiocompany.supermercadosdeltaplus.model.dao;

import com.fabiocompany.supermercadosdeltaplus.model.AlgoritmoHash;
import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.IGenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public interface IAlgoritmoHashDAO extends IGenericDAO<AlgoritmoHash, Integer> {
	public String ObtenerHash(String url) throws PersistenceException;
	public String[] ObtenerArreglodeHashes() throws PersistenceException;
}
