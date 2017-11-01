package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertNotNull;
import org.hibernate.SessionFactory;
import org.junit.Test;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class OfertarProductoTest extends BaseTest{
	@Test
	public void OfertarProductoTest() throws ServiceException, NotFoundException, PersistenceException{
		SessionFactory sessionFactory=this.sessionFactory();
//		DetalleticketDAO detalleticketdao=new DetalleticketDAO(sessionFactory);
		
		IDetalleticketService detalleticketservice=new DetalleticketService(new DetalleticketDAO(sessionFactory));		
		String okproducto=detalleticketservice.ofertarProducto();
		
		//este método comprueba si un objeto no es nulo
		//si el string es nulo, assertNotNull tira un assertError junto
		//con el msj que está como parámetro
		assertNotNull("No se consiguió el producto menos vendido",okproducto);
		//System.out.println(okproducto);
	}
	
}
