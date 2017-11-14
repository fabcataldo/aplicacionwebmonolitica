package com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate;

import org.hibernate.SessionFactory;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.AlgoritmoHash;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IAlgoritmoHashDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public class AlgoritmoHashDAO extends GenericDAO<AlgoritmoHash, Integer> implements IAlgoritmoHashDAO {

	public AlgoritmoHashDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public String ObtenerArreglodeHashes() throws PersistenceException{
		String hashaguardar="";
		String hashaguardar2="";
		
		AlgoritmoHash ah=new AlgoritmoHash();
		ah.setTexto("http://localhost:8080/supermercadosdeltaplus/api/v1/usuarioquemascompro");
		hashaguardar=ah.GenerarHash();
		ah.setResultadohash(hashaguardar);
		this.save(ah);
		
		AlgoritmoHash ah2=new AlgoritmoHash();
		ah2.setTexto("http://www.google.com.ar/");
		hashaguardar2=ah2.GenerarHash();
		ah2.setResultadohash(hashaguardar2);
		hashaguardar="\n"+ah.toString()+"\n"+ah2.toString();
		//hashaguardar=""+hashaguardar+"-"+hashaguardar2;
		this.save(ah2);
		
		return hashaguardar;
	}
		
	public String ObtenerHash(String url) throws PersistenceException{
		String hashquevieneenlaurl="";

		if(url.length()>=68) {
			hashquevieneenlaurl=url.substring(58, url.length());
			AlgoritmoHash ahbd=new AlgoritmoHash();
			try {
				ahbd = (AlgoritmoHash) getSession().createQuery(String.format("FROM AlgoritmoHash WHERE resultadohash=:hashquevieneenlaurl", getDomainClass().getSimpleName())).setString("hashquevieneenlaurl", hashquevieneenlaurl).uniqueResult();
				if(ahbd==null)
					throw new NotFoundException();
				else{
					//return "<a href="+ahbd.getTexto()+">Redireccionar a: "+ahbd.getTexto()+"/</a>";
					return ahbd.getTexto();
				}
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage(), e);
			} finally {
				closeSession();
			}
		}
		return null;
	}
}
