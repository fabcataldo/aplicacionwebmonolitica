package com.fabiocompany.supermercadosdeltaplus.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAlgoritmoHashService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAuthTokenService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IProductoService;
import com.fabiocompany.supermercadosdeltaplus.service.SimpleResponse;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

@RestController
@RequestMapping(value = "/")
public class RedirectController {
	private static Logger LOG = LoggerFactory.getLogger(UsuarioRSController.class);
	@Autowired
	private IAlgoritmoHashService algoritmohashservice;
	
/*	@RequestMapping(value="/hashredirect/a", method = RequestMethod.GET)
	public String demo() {
		AuthToken at=new AuthToken("user", 2);
		try {
			tokenService.save(at);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String token=at.encodeCookieValue();
		return "redirect:/api/v1/entidades?token="+token;
	}
*/
	@RequestMapping(value="/hashredirect/{hash}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectuno(@PathVariable("hash") String hash) {
		String cadenadehashes="";
		String urlbasedelservicio="http://localhost:8080/supermercadosdeltaplus/hashredirect/";
		try {
			cadenadehashes=algoritmohashservice.ObtenerHashService(urlbasedelservicio+hash);
			return new ResponseEntity<Object>(cadenadehashes, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return new ResponseEntity<Object>("asddas", HttpStatus.OK);
	}

}
