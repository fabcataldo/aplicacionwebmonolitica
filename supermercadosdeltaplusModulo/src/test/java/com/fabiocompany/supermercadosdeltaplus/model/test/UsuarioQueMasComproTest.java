package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.*;

import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Test;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.CabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;

public class UsuarioQueMasComproTest extends BaseTest{

	@Test
	public void UsuarioQueMasCompro() throws PersistenceException {
		SessionFactory sessionFactory=this.sessionFactory();
		CabeceraticketDAO cabeceraticketdao=new CabeceraticketDAO(sessionFactory());
		List<Object> listaticketsyusuarios;
		listaticketsyusuarios=cabeceraticketdao.obtenerListadeTicketsyUsuarios();
		
		ICabeceraticketService cabeceraticketservice=new CabeceraticketService(new CabeceraticketDAO(sessionFactory));
		String usuarioconmascompras=cabeceraticketservice.usuarioQueMasCompro(listaticketsyusuarios);	
		assertTrue("Se consiguió el usuario con mas compras",usuarioconmascompras!=null);
		System.out.println(usuarioconmascompras);
	}

}
