package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class OfertarProductoTest extends BaseTest{
	@Test
	public void probarServicioDeNegocio() throws ServiceException, NotFoundException, PersistenceException{
		SessionFactory sessionFactory=this.sessionFactory();
		DetalleticketDAO detalleticketdao=new DetalleticketDAO(sessionFactory);
		List<Object> listadeticketsdelabd;
		listadeticketsdelabd=detalleticketdao.detalleDeTickets();
		
		IDetalleticketService detalleticketservice=new DetalleticketService(new DetalleticketDAO(sessionFactory));		
		String okproducto=detalleticketservice.ofertarProducto(listadeticketsdelabd);
		assertTrue("Se consiguió el producto menos vendido",okproducto!=null);
		System.out.println(okproducto);
	}
	
}
