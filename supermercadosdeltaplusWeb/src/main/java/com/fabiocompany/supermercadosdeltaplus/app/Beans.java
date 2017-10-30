package com.fabiocompany.supermercadosdeltaplus.app;

import com.fabiocompany.supermercadosdeltaplus.model.dao.*;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.*;
import com.fabiocompany.supermercadosdeltaplus.model.service.*;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
	@Bean
	@Autowired
	public IUsuarioDAO UsuarioDAO(final SessionFactory sessionFactory) {
		return new UsuarioDAO(sessionFactory);
	}
	
	@Bean
	@Autowired
	public IUsuarioService UsuarioService(final IUsuarioDAO usuarioDAO) {
		return new UsuarioService(usuarioDAO);
	}
	
	@Bean
	@Autowired
	public IProductoDAO ProductoDAO(final SessionFactory sessionFactory) {
		return new ProductoDAO(sessionFactory);
	}
	
	@Bean
	@Autowired
	public IProductoService Producto(final IProductoDAO productoDAO) {
		return new ProductoService(productoDAO);
	}
	
	
	@Bean
	@Autowired
	public CabeceraticketDAO CabeceraticketDAO(final SessionFactory sessionFactory) {
		return new CabeceraticketDAO(sessionFactory);
	}	

	@Bean
	@Autowired
	public ICabeceraticketService CabeceraticketService(final ICabeceraticketDAO cabeceraticketDAO) {
		return new CabeceraticketService(cabeceraticketDAO);
	}
	
	
	@Bean
	@Autowired
	public DetalleticketDAO DetalleticketDAO(final SessionFactory sessionFactory) {
		return new DetalleticketDAO(sessionFactory);
	}	

	@Bean
	@Autowired
	public IDetalleticketService DetalleticketService(final IDetalleticketDAO detalleticketDAO) {
		return new DetalleticketService(detalleticketDAO);
	}


}
