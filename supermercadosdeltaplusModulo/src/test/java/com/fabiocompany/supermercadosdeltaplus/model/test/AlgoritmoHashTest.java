package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertNotNull;
import org.hibernate.SessionFactory;
import org.junit.Test;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.AlgoritmoHash;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.AlgoritmoHashDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAlgoritmoHashService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.AlgoritmoHashService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class AlgoritmoHashTest extends BaseTest{
	@Test
	public void TestDetalledeTickets() throws ServiceException, NotFoundException, PersistenceException{		
		IAlgoritmoHashService algoritmohashservice=new AlgoritmoHashService(new AlgoritmoHashDAO((SessionFactory) sessionFactory()));
		AlgoritmoHash ah=new AlgoritmoHash();
		ah.setTexto("cualquier cosa");
		String hashdeltexto=ah.GenerarHash();
		ah.setResultadohash(hashdeltexto);
		algoritmohashservice.save(ah);

		//este método comprueba si un objeto no es nulo
		//si la lista es nula, assertNotNull tira un assertError junto
		//con el msj que está como parámetro
		assertNotNull("No se consiguió el resultado del hash",hashdeltexto!=null);
		
		System.out.println("Texto que se codifico: \n"+ah.getTexto());
		System.out.println("Valor del hash: \n"+hashdeltexto);
	}
}
