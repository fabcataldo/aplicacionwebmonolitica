/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleoferta;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Tarjeta;
import com.fabiocompany.supermercadosdeltaplus.model.Tipodepago;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.CabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleofertaDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.DetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.PersonausuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.TarjetaDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.TipodepagoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleofertaService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.ITarjetaService;
import com.fabiocompany.supermercadosdeltaplus.model.service.ITipodepagoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.CabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleofertaService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.DetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.PersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.TarjetaService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.TipodepagoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UsuarioService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabio
 */
public class SupermercadosDeltaPlusTest extends BaseTest{
    @Test
    public void guardarUsuario() throws ServiceException, NotFoundException{
	IUsuarioService usuarioservice=new UsuarioService(new UsuarioDAO((SessionFactory) sessionFactory()));
        IPersonausuarioService personausuarioservice=new PersonausuarioService(new PersonausuarioDAO((SessionFactory) sessionFactory()));
        ICabeceraticketService cabeceraticketservice=new CabeceraticketService(new CabeceraticketDAO((SessionFactory) sessionFactory()));
        IDetalleofertaService detalleofertaservice=new DetalleofertaService(new DetalleofertaDAO((SessionFactory) sessionFactory()));
        IDetalleticketService detalleticketservice=new DetalleticketService(new DetalleticketDAO((SessionFactory) sessionFactory()));
        IProductoService productoservice=new ProductoService(new ProductoDAO((SessionFactory) sessionFactory()));
        ITarjetaService tarjetaservice=new TarjetaService(new TarjetaDAO((SessionFactory) sessionFactory()));
        ITipodepagoService tipodepagoservice=new TipodepagoService(new TipodepagoDAO((SessionFactory) sessionFactory()));
        
        Usuario u=new Usuario();
        u.setNombreusuario("fabio");
        u.setTipodeusuario("administrador");
        u.setContraseniausuario("fabio1234");
        //u.setIdusuario(1);

        Personausuario pu=new Personausuario();
        pu.setDnipersonausuario("31451666");
        pu.setNombrepersonausuario("fabio");
        pu.setNumerodetelefono("4336578");
        pu.setCorreo("fabio@gmail.com");

        Cabeceraticket ct=new Cabeceraticket();
        ct.setFecha("24/09/2017");
        ct.setUsuario(u);
        //ct.setIdticket(1);
        
        Producto p=new Producto();
        p.setIdproducto(1);
        p.setPrecio(60.50);
        p.setDescripcion("Arroz La Colonia 1kg");
        
        
        //Es un solo producto, por lo tanto hago un solo detalle ticket
        Detalleticket dt=new Detalleticket();
        dt.setCantidad(4);
        dt.setCantidadporproducto(4);
        dt.setCabeceraticket(ct);
        dt.setSubtotal(dt.getCantidad()*p.getPrecio());
        dt.setTotal(dt.getSubtotal()+0);
        dt.setMontodepago(61.0);
        
        Tipodepago tp=new Tipodepago();
        tp.setDescripciontipodepago("En efectivo");        
        
        Tarjeta t=new Tarjeta();
        t.setNombretarjeta("Visa");
        t.setNumtarjeta("865744093-334");
        
        Detalleoferta detalleoferta=new Detalleoferta();
        detalleoferta.setDescripciondepromocion("Ninguna oferta o promocion");
        detalleoferta.setMontodedescuento(0.0);

        u.setPersonausuario(pu);
        ct.getDetalletickets().add(dt);
        dt.setTipodepago(tp);
        tp.getDetalletickets().add(dt);
        dt.getProductos().add(p);
        dt.getDetalleofertas().add(detalleoferta);
        p.getDetalletickets().add(dt);
        //t.setTipodepago(tp);
        //tp.getTarjetas().add(t);
        
//-----------------GUARDADO en la BD--------------------------------------
        Usuario usuarioguardado=new Usuario();
        usuarioguardado=usuarioservice.saveOrUpdate(u);
        assertTrue("Error id de usuario", usuarioguardado.getIdusuario()>-1 );
        assertEquals("Error nombre de usuario", usuarioguardado.getNombreusuario(), "fabio");
/*        
        Personausuario puguardado=new Personausuario();
        puguardado=personausuarioservice.saveOrUpdate(pu);
        assertTrue("Error id de personausuario", puguardado.getIdpersonausuario()>-1 );
        assertEquals("Error nombre de personausuario", puguardado.getNombrepersonausuario(), "fabio");

        Detalleticket dtguardado=new Detalleticket();
        dtguardado=detalleticketservice.save(dt);
        assertTrue("Error id de detalle ticket", dtguardado.getIddetalleticket()>-1 );
*/         
        Cabeceraticket ctguardado=new Cabeceraticket();
        ctguardado=cabeceraticketservice.saveOrUpdate(ct);
        assertTrue("Error id de cabecera ticket", ctguardado.getIdticket()>-1 );

        Tipodepago tipodepagoguardado=new Tipodepago();
        tipodepagoguardado=tipodepagoservice.saveOrUpdate(tp);
        assertTrue("Error id de tipo de pago", tipodepagoguardado.getIdtipodepago()>-1 );
       

        /*
        Tarjeta tarjetaguardada=new Tarjeta();
        tarjetaguardada=tarjetaservice.save(t);
        assertTrue("Error id de tarjeta", tarjetaguardada.getIdtarjeta()>-1 );

        Producto pguardado=new Producto();
        pguardado=productoservice.save(p);
        assertTrue("Error id de producto", pguardado.getIdproducto()>-1 );
        */

        /*Detalleoferta doguardado=new Detalleoferta();
        doguardado=detalleofertaservice.save(detalleoferta);
        assertTrue("Error id de detalle oferta", doguardado.getIddetalleoferta()>-1 );
        */  
    }
}
