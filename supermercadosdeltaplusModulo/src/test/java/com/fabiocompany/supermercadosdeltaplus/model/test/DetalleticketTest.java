	package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.CabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class DetalleticketTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException, NotFoundException{
		ICabeceraticketService cabeceraticketservice=new CabeceraticketService(new CabeceraticketDAO((SessionFactory) sessionFactory()));
		IDetalleticketService detalleticketservice=new DetalleticketService(new DetalleticketDAO((SessionFactory) sessionFactory()));
		IProductoService productoservice=new ProductoService(new ProductoDAO((SessionFactory) sessionFactory()));
/*
		User u=new User();
		u.setIdUser(2);
		Producto productoarroz=new Producto();
		productoarroz.setIdproducto(1);
		productoarroz.setPrecio(61.0);
		productoarroz.setDescripcion("Arroz La colonia 500 gr");
        Producto p2=new Producto();
        p2.setPrecio(105.0);
        p2.setDescripcion("Caja de Helado tres sabores La Colonia 1 kg");
        p2.setIdproducto(2);
		
		Cabeceraticket ct=new Cabeceraticket();
		ct.setIdticket(1);
		ct.setUsuario(u);
		
		Cabeceraticket ctguardado=new Cabeceraticket();
		ctguardado=cabeceraticketservice.save(ct);
		assertTrue("Error id de detalle ticket", ctguardado.getIdticket()>-1 );
		
        Detalleticket dt=new Detalleticket();
        dt.setCantidad(3);
        dt.setCabeceraticket(ct);
        dt.getProductos().add(productoarroz);
        
        Detalleticket dt2=new Detalleticket();
        dt2.setCantidad(1);
        dt2.setCabeceraticket(ct);
        dt2.getProductos().add(p2);
        
        Detalleticket dtguardado=new Detalleticket();
        dtguardado=detalleticketservice.save(dt);
        assertTrue("Error id de detalle ticket", dtguardado.getIddetalleticket()>-1 );
        
        Detalleticket dtguardado2=new Detalleticket();
        dtguardado2=detalleticketservice.save(dt2);
        assertTrue("Error id de detalle ticket", dtguardado2.getIddetalleticket()>-1 );

*/ 
//------------------------------------------------------------------------------------------
		Producto productoarroz2=new Producto();
		productoarroz2.setIdproducto(1);
		productoarroz2.setPrecio(61.0);
		productoarroz2.setDescripcion("Arroz La colonia 500 gr");
		
		User u2=new User();
		u2.setIdUser(2);
		
		Cabeceraticket ct2=new Cabeceraticket();
		ct2.setUsuario(u2);
		Cabeceraticket ctguardado2=new Cabeceraticket();
		ctguardado2=cabeceraticketservice.save(ct2);
		assertTrue("Error id de detalle ticket", ctguardado2.getIdticket()>-1 );
		
        Detalleticket dt3=new Detalleticket();
        dt3.setCantidad(2);
        dt3.setCabeceraticket(ct2);
        dt3.getProductos().add(productoarroz2);
        
        Detalleticket dtguardado3=new Detalleticket();
        dtguardado3=detalleticketservice.save(dt3);
        assertTrue("Error id de detalle ticket", dtguardado3.getIddetalleticket()>-1 );
       
//------------------------------------------------------------------------------------------
	}	
}