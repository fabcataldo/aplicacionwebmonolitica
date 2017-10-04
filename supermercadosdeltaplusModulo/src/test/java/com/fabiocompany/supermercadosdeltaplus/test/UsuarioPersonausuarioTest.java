package com.fabiocompany.supermercadosdeltaplus.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Personausuario;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.PersonausuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.ProductoDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UsuarioDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.PersonausuarioService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.ProductoService;
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

        
	}
	
}
