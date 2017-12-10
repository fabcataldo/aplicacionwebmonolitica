package com.fabiocompany.supermercadosdeltaplus.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAlgoritmoHashService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;


//@RestController
@Controller
@RequestMapping(value = "/")
public class RedirectController {
	private static Logger LOG = LoggerFactory.getLogger(UsuarioRSController.class);
	@Autowired
	private IAlgoritmoHashService algoritmohashservice;
	
	@RequestMapping(value="/hashredirect/{hash}", method = RequestMethod.GET)
	public String redirect(@PathVariable("hash") String hash) {
		String urlaredireccionar="";
		//String urlbasedelservicio="http://localhost:8080/supermercadosdeltaplus/hashredirect/";
		try {
			urlaredireccionar=algoritmohashservice.ObtenerHashService(hash);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return e.getMessage();
		}
		return "redirect:"+urlaredireccionar;
	}
	
	
	@RequestMapping(value="/arreglodehashes", method = RequestMethod.GET)
	public ResponseEntity<Object> arreglodehashes() {
		String[] arreglodehashes=null;
		try {
			arreglodehashes=algoritmohashservice.ObtenerArregloDeHashesService();
			return new ResponseEntity<Object>(arreglodehashes, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
