package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class ProductoTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException, NotFoundException{
		IProductoService productoservice=new ProductoService(new ProductoDAO((SessionFactory) sessionFactory()));
		
        Producto p=new Producto();
        p.setPrecio(61.0);
        p.setDescripcion("Arroz La colonia 500 gr");

        Producto pguardado=new Producto();
        pguardado=productoservice.save(p);
        assertTrue("Error id de producto", pguardado.getIdproducto()>-1 );

        Producto p2=new Producto();
        p2.setPrecio(105.0);
        p2.setDescripcion("Caja de Helado tres sabores La Colonia 1 kg");

        Producto pguardado2=new Producto();
        pguardado2=productoservice.save(p2);
        //si la condición es true, está todo ok. Si no, salta el mensaje que está entre comillas
        assertTrue("Error id de producto", pguardado2.getIdproducto()>-1 );
        
	}
	
}
