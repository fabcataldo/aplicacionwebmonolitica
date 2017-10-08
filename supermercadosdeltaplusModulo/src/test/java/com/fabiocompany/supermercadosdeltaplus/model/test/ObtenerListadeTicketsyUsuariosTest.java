package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public class ObtenerListadeTicketsyUsuariosTest extends BaseTest{

	@Test
	public void probarObtenerListadeTicketsyUsuarios() throws PersistenceException {
		CabeceraticketDAO cabeceraticketdao;
		cabeceraticketdao=new CabeceraticketDAO((SessionFactory) sessionFactory());
		List<Object> listaticketsyusuarios=null;
		listaticketsyusuarios=cabeceraticketdao.obtenerListadeTicketsyUsuarios();
		assertTrue("Se consiguió la lista de tickets y usuarios",listaticketsyusuarios!=null);
		for(int i=0;i<listaticketsyusuarios.size();i++) {
			System.out.println("\n"+listaticketsyusuarios.get(i));
		}
	}

}
