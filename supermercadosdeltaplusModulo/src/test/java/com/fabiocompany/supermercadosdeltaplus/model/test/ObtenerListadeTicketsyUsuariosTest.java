package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public class ObtenerListadeTicketsyUsuariosTest extends BaseTest{

	@Test
	public void TestObtenerListadeTicketsyUsuarios() throws PersistenceException {
		CabeceraticketDAO cabeceraticketdao;
		cabeceraticketdao=new CabeceraticketDAO((SessionFactory) sessionFactory());
		List<Cabeceraticket> listaticketsyusuarios=null;
		listaticketsyusuarios=cabeceraticketdao.obtenerListadeTicketsyUsuarios();

		//este método comprueba si un objeto no es nulo
		//si la lista es nula, assertNotNull tira un assertError junto
		//con el msj que está como parámetro
		assertNotNull("No se pudo conseguir la lista de tickets y usuarios",listaticketsyusuarios);
		/*for(int i=0;i<listaticketsyusuarios.size();i++) {
			System.out.println("\n"+listaticketsyusuarios.get(i));
		}
		*/
		
	}

}
