package com.fabiocompany.supermercadosdeltaplus.test;

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
		ct.setIdticket(2);
		
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
	}
	
}
