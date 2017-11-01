package com.fabiocompany.supermercadosdeltaplus.model.test;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.Test;
import com.fabiocompany.supermercadosdeltaplus.model.Privilege;
import com.fabiocompany.supermercadosdeltaplus.model.Role;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.UserDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPrivilegeService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IRoleService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUserService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.UserService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.PrivilegeService;
import com.fabiocompany.supermercadosdeltaplus.model.service.impl.RoleService;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.PrivilegeDAO;
import com.fabiocompany.supermercadosdeltaplus.model.dao.hibernate.RoleDAO;


public class UserTest extends BaseTest{
	@Test
	public void metodo() throws ServiceException{
		IUserService userservice=new UserService(new UserDAO((SessionFactory) sessionFactory()));
        IPrivilegeService privilegeservice=new PrivilegeService(new PrivilegeDAO((SessionFactory) sessionFactory()));
        IRoleService roleservice=new RoleService(new RoleDAO((SessionFactory) sessionFactory()));

/* 
        User u=new User();
        Set<Privilege> privuser1=new HashSet<Privilege>();
        
        u.setFirstName("Fabio");
        u.setLastName("Cataldo");
        u.setEmail("fabio@gmail.com");
        u.setPassword("1234");
        u.setUsername("fabio");
        u.setPrivileges(privuser1);
        
        Privilege priv=new Privilege();
        priv.setDescription("DELETE-/usuarios");
        priv.setName("privilegio1");        
        u.getPrivileges().add(priv);
        
        Role role=new Role();
        role.setName("rol1");
        role.setDescription("administrador");
        role.setPrivileges(privuser1);
        role.getPrivileges().add(priv);
        
        
        privilegeservice.save(priv);
        userservice.save(u);
        roleservice.save(role);
        
        assertTrue("Error id de usuario", u.getIdUser()>-1 );
*/
//------------------------------------------------------------------------------        
      
        User u2=new User();
        u2.setFirstName("Leandro");
        u2.setLastName("Torres");
        u2.setEmail("leandro@gmail.com");
        u2.setPassword("1234");
        u2.setUsername("lean");
        Set<Privilege> privuser2=new HashSet<Privilege>();
        u2.setPrivileges(privuser2);
        
        Privilege priv2=new Privilege();
        priv2.setDescription("GET-/usuarios");
        priv2.setName("privilegio2");
        u2.getPrivileges().add(priv2);
        
        Role role2=new Role();
        role2.setName("rol2");
        role2.setDescription("usuario comun");
        role2.setPrivileges(privuser2);
        role2.getPrivileges().add(priv2);
        
        privilegeservice.save(priv2);
        userservice.save(u2);
        roleservice.save(role2);
        assertTrue("Error id de usuario 2", u2.getIdUser()>-1 );        

	}

}