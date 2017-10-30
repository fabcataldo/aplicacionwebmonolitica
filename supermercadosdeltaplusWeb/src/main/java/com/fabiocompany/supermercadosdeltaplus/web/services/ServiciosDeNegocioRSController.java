/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.web.services;

import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class ServiciosDeNegocioRSController {
	private static Logger LOG = LoggerFactory.getLogger(ServiciosDeNegocioRSController.class);
	
	//SERVICIOS DE NEGOCIO QUE SE PUBLICAN EN LO QUE SIGUE
	@Autowired
	ICabeceraticketService cabeceraticketservice;
	@Autowired
	IDetalleticketService detalleticketservice;
	
	@RequestMapping(value = "/usuarioquemascompro", method = RequestMethod.GET)
	public ResponseEntity<Object> usuarioQueMasCompro() {
		try {
			return new ResponseEntity<Object>(cabeceraticketservice.usuarioQueMasCompro(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/ofertarproducto", method = RequestMethod.GET)
	public ResponseEntity<Object> ofertarProducto() {
		try {
			return new ResponseEntity<Object>(detalleticketservice.ofertarProducto(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
