package com.fabiocompany.supermercadosdeltaplus.app;

import com.fabiocompany.supermercadosdeltaplus.model.dao.IUsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UsuarioService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
	public IUsuarioService UsuarioService(final IUsuarioDAO usuarioDAO) {
		return new UsuarioService(usuarioDAO);
	}

	
}
