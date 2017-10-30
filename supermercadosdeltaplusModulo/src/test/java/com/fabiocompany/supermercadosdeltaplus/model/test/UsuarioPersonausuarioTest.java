package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.PersonausuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.PersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UsuarioService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

public class UsuarioPersonausuarioTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException, NotFoundException{
        IPersonausuarioService personausuarioservice=new PersonausuarioService(new PersonausuarioDAO((SessionFactory) sessionFactory()));
        IUsuarioService usuarioservice=new UsuarioService(new UsuarioDAO((SessionFactory) sessionFactory()));       

        Usuario u=new Usuario();
        u.setNombreusuario("fabio");
        u.setTipodeusuario("administrador");
        u.setContraseniausuario("fabio1234");

        Personausuario pu=new Personausuario();
        pu.setDnipersonausuario("31451666");
        pu.setNombrepersonausuario("fabio");
        pu.setNumerodetelefono("4336578");
        pu.setCorreo("fabio@gmail.com");
        
        u.setPersonausuario(pu);
        
        
        Usuario usuarioguardado=new Usuario();
        usuarioguardado=usuarioservice.save(u);
        assertTrue("Error id de usuario", usuarioguardado.getIdusuario()>-1 );
        assertEquals("Error nombre de usuario", usuarioguardado.getNombreusuario(), "fabio");
        
        Usuario u2=new Usuario();
        u2.setNombreusuario("ricardo");
        u2.setTipodeusuario("usuario comun");
        u2.setContraseniausuario("ricardo1234");

        Personausuario pu2=new Personausuario();
        pu2.setDnipersonausuario("32435666");
        pu2.setNombrepersonausuario("ricardo lopez");
        pu2.setNumerodetelefono("4312309");
        pu2.setCorreo("ricardo_gago@gmail.com");
        
        u2.setPersonausuario(pu2);
        
        Usuario usuarioguardado2=new Usuario();
        usuarioguardado2=usuarioservice.save(u2);
        assertTrue("Error id de usuario", usuarioguardado2.getIdusuario()>-1 );
        
        //Devuelve el msj que está entre comillas si el nombre del usuario guardado 
        //no es "ricardo"
        assertEquals("Error nombre de usuario", usuarioguardado2.getNombreusuario(), "ricardo");


        Usuario u3=new Usuario();
        u3.setNombreusuario("carlos");
        u3.setTipodeusuario("usuario comun");
        u3.setContraseniausuario("carlos1234");

        Personausuario pu3=new Personausuario();
        pu3.setDnipersonausuario("3300222");
        pu3.setNombrepersonausuario("carlos");
        pu3.setNumerodetelefono("44000000");
        pu3.setCorreo("carlos@gmail.com");
        
        u3.setPersonausuario(pu3);
        
        Usuario usuarioguardado3=new Usuario();
        usuarioguardado3=usuarioservice.save(u3);
        assertTrue("Error id de usuario", usuarioguardado3.getIdusuario()>-1 );
        
        
	}	
}
