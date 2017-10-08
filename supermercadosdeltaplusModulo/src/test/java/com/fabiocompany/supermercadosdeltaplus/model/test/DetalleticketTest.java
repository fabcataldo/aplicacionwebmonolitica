	package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.PersonausuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.PersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UsuarioService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class DetalleticketTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException, NotFoundException{
		IDetalleticketService detalleticketservice=new DetalleticketService(new DetalleticketDAO((SessionFactory) sessionFactory()));
		IProductoService productoservice=new ProductoService(new ProductoDAO((SessionFactory) sessionFactory()));
		/*
		Producto productoarroz=new Producto();
		productoarroz.setIdproducto(1);
		productoarroz.setPrecio(61.0);
		productoarroz.setDescripcion("Arroz La colonia 500 gr");
		
        Producto p2=new Producto();
        p2.setPrecio(105.0);
        p2.setDescripcion("Caja de Helado tres sabores La Colonia 1 kg");
        p2.setIdproducto(2);
        Producto pguardado2=new Producto();
        pguardado2=productoservice.save(p2);
        assertTrue("Error id de producto", pguardado2.getIdproducto()>-1 );
		
		Cabeceraticket ct=new Cabeceraticket();
		ct.setIdticket(3);
		
        Detalleticket dt=new Detalleticket();
        dt.setCantidad(2);
        dt.setCabeceraticket(ct);
        dt.getProductos().add(productoarroz);
        
        Detalleticket dtguardado=new Detalleticket();
        dtguardado=detalleticketservice.save(dt);
        assertTrue("Error id de detalle ticket", dtguardado.getIddetalleticket()>-1 );
		
        Detalleticket dt2=new Detalleticket();
        dt2.setCantidad(1);
        dt2.setCabeceraticket(ct);
        dt2.getProductos().add(p2);
        
        Detalleticket dtguardado2=new Detalleticket();
        dtguardado2=detalleticketservice.save(dt2);
        assertTrue("Error id de detalle ticket", dtguardado2.getIddetalleticket()>-1 );
    
		
		Producto productoarroz2=new Producto();
		productoarroz2.setIdproducto(1);
		productoarroz2.setPrecio(61.0);
		productoarroz2.setDescripcion("Arroz La colonia 500 gr");
		
		Cabeceraticket ct2=new Cabeceraticket();
		ct2.setIdticket(4);
		
        Detalleticket dt3=new Detalleticket();
        dt3.setCantidad(7);
        dt3.setCabeceraticket(ct2);
        dt3.getProductos().add(productoarroz2);
        
        Detalleticket dtguardado3=new Detalleticket();
        dtguardado3=detalleticketservice.save(dt2);
        assertTrue("Error id de detalle ticket", dtguardado3.getIddetalleticket()>-1 );
       

		
		//ricardo compró dos veces el mismo día, por eso son dos detalletickets distintos
		Producto productoarroz3=new Producto();
		productoarroz3.setIdproducto(1);
		productoarroz3.setPrecio(61.0);
		productoarroz3.setDescripcion("Arroz La colonia 500 gr");
		
		Cabeceraticket ct3=new Cabeceraticket();
		ct3.setIdticket(10);
		
        Detalleticket dt3=new Detalleticket();
        dt3.setCantidad(3);
        dt3.setCabeceraticket(ct3);
        dt3.getProductos().add(productoarroz3);
        
        Detalleticket dtguardado3=new Detalleticket();
        dtguardado3=detalleticketservice.save(dt3);
        assertTrue("Error id de detalle ticket", dtguardado3.getIddetalleticket()>-1 );

		Producto productoarroz4=new Producto();
		productoarroz4.setIdproducto(10);
		productoarroz4.setPrecio(105.0);
		productoarroz4.setDescripcion("Caja de Helado tres sabores La Colonia 1 kg");
		
		Cabeceraticket ct4=new Cabeceraticket();
		ct4.setIdticket(11);
		
        Detalleticket dt4=new Detalleticket();
        dt4.setCantidad(1);
        dt4.setCabeceraticket(ct4);
        dt4.getProductos().add(productoarroz4);
        
        Detalleticket dtguardado4=new Detalleticket();
        dtguardado4=detalleticketservice.save(dt4);
        assertTrue("Error id de detalle ticket", dtguardado4.getIddetalleticket()>-1 );
        */

		Producto p5=new Producto();
		p5.setIdproducto(2);
		p5.setPrecio(40.5);
		p5.setDescripcion("Caja de Leche La Serenisima");
		
		Cabeceraticket ct5=new Cabeceraticket();
		ct5.setIdticket(12);
		
        Detalleticket dt5=new Detalleticket();
        dt5.setCantidad(1);
        dt5.setCabeceraticket(ct5);
        dt5.getProductos().add(p5);
        
        Detalleticket dtguardado5=new Detalleticket();
        dtguardado5=detalleticketservice.save(dt5);
        assertTrue("Error id de detalle ticket", dtguardado5.getIddetalleticket()>-1 );
        
        
	}
	
}
