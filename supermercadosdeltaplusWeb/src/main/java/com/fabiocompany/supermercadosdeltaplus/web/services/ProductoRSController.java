package com.fabiocompany.supermercadosdeltaplus.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProductoRSController {
	private static Logger LOG = LoggerFactory.getLogger(UsuarioRSController.class);
	@Autowired
	IProductoService productoservice;
	
	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public ResponseEntity<Object> list() {
		try {
			return new ResponseEntity<Object>(productoservice.list(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
