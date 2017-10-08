package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.CabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class CabeceraticketTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException, NotFoundException{
        ICabeceraticketService cabeceraticketservice=new CabeceraticketService(new CabeceraticketDAO((SessionFactory) sessionFactory()));
  /*
        Usuario u=new Usuario();
        u.setIdusuario(1);
        
        Cabeceraticket ct=new Cabeceraticket();
        ct.setFecha(4102017);
        ct.setUsuario(u);
         
        Cabeceraticket ctguardado=new Cabeceraticket();
        ctguardado=cabeceraticketservice.save(ct);
        assertTrue("Error id de cabecera ticket", ctguardado.getIdticket()>-1 );

 
        Usuario u2=new Usuario();
        u2.setIdusuario(3);
        
        Cabeceraticket ct2=new Cabeceraticket();
        ct2.setFecha(6102017);
        ct2.setUsuario(u2);
         
        Cabeceraticket ctguardado2=new Cabeceraticket();
        ctguardado2=cabeceraticketservice.save(ct2);
        assertTrue("Error id de cabecera ticket", ctguardado2.getIdticket()>-1 );

    
        Usuario u3=new Usuario();
        u3.setIdusuario(3);
        
        Cabeceraticket ct3=new Cabeceraticket();
        ct3.setFecha(15102017);
        ct3.setUsuario(u3);
         
        Cabeceraticket ctguardado3=new Cabeceraticket();
        ctguardado3=cabeceraticketservice.save(ct3);
        assertTrue("Error id de cabecera ticket", ctguardado3.getIdticket()>-1 );
             
        Usuario u4=new Usuario();
        u4.setIdusuario(3);
        
        Cabeceraticket ct4=new Cabeceraticket();
        ct4.setFecha(15102017);
        ct4.setUsuario(u4);
         
        Cabeceraticket ctguardado4=new Cabeceraticket();
        ctguardado4=cabeceraticketservice.save(ct4);
        assertTrue("Error id de cabecera ticket", ctguardado4.getIdticket()>-1 );
 
*/    
        
        Usuario u5=new Usuario();
        u5.setIdusuario(3);
        
        Cabeceraticket ct5=new Cabeceraticket();
        ct5.setFecha(15102017);
        ct5.setUsuario(u5);
         
        Cabeceraticket ctguardado5=new Cabeceraticket();
        ctguardado5=cabeceraticketservice.save(ct5);
        assertTrue("Error id de cabecera ticket", ctguardado5.getIdticket()>-1 );
	}
	
}
