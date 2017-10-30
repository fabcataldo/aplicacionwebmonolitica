package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Test;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class DetalledeticketsTest extends BaseTest{
	@Test
	public void TestDetalledeTickets() throws ServiceException, NotFoundException, PersistenceException{
		DetalleticketDAO detalleticketdao;
		detalleticketdao=new DetalleticketDAO((SessionFactory) sessionFactory());
		List<Detalleticket> listatickets=null;
		listatickets=detalleticketdao.detalleDeTickets();
		
		//este método comprueba si un objeto no es nulo
		//si la lista es nula, assertNotNull tira un assertError junto
		//con el msj que está como parámetro
		assertNotNull("No se consiguió la listadetickets",listatickets!=null);
		/*for(int i=0;i<listatickets.size();i++) {
			System.out.println("\n"+listatickets.get(i));
		}
		*/
	}
}
