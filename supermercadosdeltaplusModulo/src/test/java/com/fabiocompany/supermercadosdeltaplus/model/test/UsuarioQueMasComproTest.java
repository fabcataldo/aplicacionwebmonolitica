package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.*;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.CabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class UsuarioQueMasComproTest extends BaseTest{

	@Test
	public void TestUsuarioQueMasCompro() throws ServiceException {
		SessionFactory sessionFactory=this.sessionFactory();
		ICabeceraticketService cabeceraticketservice=new CabeceraticketService(new CabeceraticketDAO(sessionFactory));
		String usuarioconmascompras=cabeceraticketservice.usuarioQueMasCompro();	
		
		//Comrpuebo si el String no es nulo. Si lo es, tiro una excepción junto con el msj
		//que está como uno de los parámetros del assert
		assertNotNull("Error en la detección del usuario con más compras",usuarioconmascompras!=null);
		System.out.println(usuarioconmascompras);
	}

}
