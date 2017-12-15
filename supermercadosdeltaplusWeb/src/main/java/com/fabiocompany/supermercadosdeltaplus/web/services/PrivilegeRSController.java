/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.web.services;

import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.Privilege;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.service.IPrivilegeService;
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
@RequestMapping(value = Constants.URL_PRIVILEGIO)
@Transactional
public class PrivilegeRSController {
	private static Logger LOG = LoggerFactory.getLogger(UsuarioRSController.class);
	@Autowired
	IPrivilegeService privilegeService;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<Object> list() {
		try {
			return new ResponseEntity<Object>(privilegeService.list(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> load(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Object>(privilegeService.load(id), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Privilege privilege) {
		try {
			privilegeService.save(privilege);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_PRIVILEGIO + "/" + privilege.getId());
			return new ResponseEntity<Object>(privilege,responseHeaders, HttpStatus.CREATED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.PUT)
	public ResponseEntity<Object> edit(@RequestBody Privilege privilege) {
		try {
			privilegeService.update(privilege);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_PRIVILEGIO + "/" + privilege.getId());
			return new ResponseEntity<Object>(privilege,responseHeaders, HttpStatus.OK);
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
			Privilege privilege = new Privilege();
			privilege.setId(id);
			//seteo description y name ya que no puedo enviarle algo nulo a Hibernate, 
			//ya que en la bd a description y a name no les puedo no poner algo 
			//Hibernate me avisa con un error de persistencia, que no puede enviar algo
			//nulo a la bd, ya que la/s columna/s están seteadas como NOT NULL
			privilege.setDescription("cualquierdescripcion");
			privilege.setName("cualquiernombre");
			privilegeService.delete(privilege);
			return new ResponseEntity<Object>( HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
}
