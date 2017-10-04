package com.fabiocompany.supermercadosdeltaplus.test;

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
        
        Usuario u=new Usuario();
        u.setIdusuario(1);
        
        Cabeceraticket ct=new Cabeceraticket();
        ct.setFecha(4102017);
        ct.setUsuario(u);
         
        Cabeceraticket ctguardado=new Cabeceraticket();
        ctguardado=cabeceraticketservice.save(ct);
        assertTrue("Error id de cabecera ticket", ctguardado.getIdticket()>-1 );

 
	}
	
}
