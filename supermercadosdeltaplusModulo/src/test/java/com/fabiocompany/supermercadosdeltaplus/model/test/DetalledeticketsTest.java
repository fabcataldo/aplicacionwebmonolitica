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
import com.fabiocompany.supermercadosdeltaplus.persistence.dao.hibernate.GenericDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class DetalledeticketsTest extends BaseTest{
	@Test
	public void probarServicioDePersistencia() throws ServiceException, NotFoundException, PersistenceException{
		DetalleticketDAO detalleticketdao;
		detalleticketdao=new DetalleticketDAO((SessionFactory) sessionFactory());
		List<Object> listatickets=null;
		listatickets=detalleticketdao.detalleDeTickets();
		assertTrue("Se consiguió la listadetickets",listatickets!=null);
		for(int i=0;i<listatickets.size();i++) {
			System.out.println("\n"+listatickets.get(i));
		}
	}
}
