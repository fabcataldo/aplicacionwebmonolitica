package com.fabiocompany.supermercadosdeltaplus.app;

import com.fabiocompany.supermercadosdeltaplus.model.dao.*;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.*;
import com.fabiocompany.supermercadosdeltaplus.model.service.*;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.*;

import com.fabiocompany.supermercadosdeltaplus.model.dao.IAuthTokenDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.AuthTokenDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAuthTokenService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.AuthTokenService;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
	@Bean
	@Autowired
	public IUserDAO UserDAO(final SessionFactory sessionFactory) {
		return new UserDAO(sessionFactory);
	}
	
	@Bean
	@Autowired
	public IUserService User(final IUserDAO userDAO) {
		return new UserService(userDAO);
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

	@Bean
	@Autowired
	public IAuthTokenService authTokenService(final IAuthTokenDAO authTokenDAO) {
		return new AuthTokenService(authTokenDAO);
	}
	@Bean
	@Autowired
	public IAuthTokenDAO authTokenDAO(final SessionFactory sessionFactory) {
		return new AuthTokenDAO(sessionFactory);
	}
	
	@Bean
	@Autowired
	public IAlgoritmoHashService algoritmohash(final IAlgoritmoHashDAO algoritmohashDAO) {
		return new AlgoritmoHashService(algoritmohashDAO);
	}
	@Bean
	@Autowired
	public IAlgoritmoHashDAO algoritmohashDAO(final SessionFactory sessionFactory) {
		return new AlgoritmoHashDAO(sessionFactory);
	}
	
	
	
}
