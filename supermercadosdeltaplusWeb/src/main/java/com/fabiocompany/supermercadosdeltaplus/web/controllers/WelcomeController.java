package com.fabiocompany.supermercadosdeltaplus.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fabiocompany.supermercadosdeltaplus.model.AuthToken;
import com.fabiocompany.supermercadosdeltaplus.model.service.IAuthTokenService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
/**
 * 
 * @author magm
 *
 */
@Controller
public class WelcomeController {

	@Autowired
	private IAuthTokenService tokenService;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String defaultPage() {
		return "redirect:/index.html";
	}
	
	@RequestMapping(value="/hashredirect/a", method = RequestMethod.GET)
	public String demo() {
		AuthToken at=new AuthToken("fabio", 2);
		try {
			tokenService.save(at);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String token=at.encodeCookieValue();
		//return "redirect:/www.google.com.ar";
		return "redirect:/api/v1/usuarios?token="+token;
	}
}