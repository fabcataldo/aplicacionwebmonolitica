package com.fabiocompany.supermercadosdeltaplus.app;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fabiocompany.supermercadosdeltaplus.model.dao.IUsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UsuarioService;
/**
 * 
 * @author magm
 *
 */
@Component
public class Beans {

	//DAO
	@Bean
	@Autowired
	public IUsuarioDAO UsuarioDAO(final SessionFactory sessionFactory) {
		return new UsuarioDAO(sessionFactory);
	}
	
	//Services
	
	
	@Bean
	@Autowired
	public IUsuarioService UsuarioService(final IUsuarioDAO entidadDAO) {
		return new UsuarioService(entidadDAO);
	}

	
}
