/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.web.services;


import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUserService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = Constants.URL_USUARIO)
//@Transactional
public class UsuarioRSController {
	private static Logger LOG = LoggerFactory.getLogger(UsuarioRSController.class);
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<Object> list() {
		try {
			return new ResponseEntity<Object>(userService.list(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> load(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Object>(userService.load(id), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody User user) {
		try {
			userService.save(user);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_USUARIO + "/" + user.getIdUser());
			return new ResponseEntity<Object>(user,responseHeaders, HttpStatus.CREATED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.PUT)
	public ResponseEntity<Object> edit(@RequestBody User user) {
		try {
			userService.update(user);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_USUARIO + "/" + user.getIdUser());
			return new ResponseEntity<Object>(user,responseHeaders, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> remove(@PathVariable("id") int id) {
		try {
			User user = new User();
			user.setIdUser(id);
			userService.delete(user);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/agregarrol/{iduser}{idrole}", method = RequestMethod.POST)
	public ResponseEntity<Object> addRole(@PathVariable("iduser") int iduser,@PathVariable("idrole") int idrole){
		try {
			User user=new User();
			user.setIdUser(iduser);
			userService.addRoleService(user,idrole);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
