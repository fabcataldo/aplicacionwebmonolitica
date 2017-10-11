/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.dao.IDetalleticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.IDetalleticketService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class DetalleticketService extends GenericService<Detalleticket, Integer>
		implements IDetalleticketService {
	private static Logger LOG = LoggerFactory.getLogger(DetalleticketService.class);
	public DetalleticketService(IDetalleticketDAO dao) {
		super(dao);
	}

	//servicio de negocio
	//el producto menos vendido supongo que tiene mucho stock; lo detecto, y a partir de ahí
	//puedo decir por una salida que se oferte este producot así se vende más
	@Override
	public String ofertarProducto(List<Detalleticket> listadetickets){
		String productomenosvendido=listadetickets.get(0).getProductos().get(0).getDescripcion();	
		List<Integer> cantidaddeproductosporticket=new ArrayList<Integer>();
		int contadordeticketsporproducto=1;
		List<String> productoporticketNombredeProductos=new ArrayList<String>();
		
		for(int i=0;i<listadetickets.size();i++){
			if(productomenosvendido.equals(listadetickets.get(0).getProductos().get(0).getDescripcion())) {
				contadordeticketsporproducto++;
				continue;
			}	
			cantidaddeproductosporticket.add(contadordeticketsporproducto);
			contadordeticketsporproducto=1;
			productoporticketNombredeProductos.add(listadetickets.get(i).getProductos().get(i).getDescripcion());
		}
		
		for(int i=0;i<cantidaddeproductosporticket.size();i++) {
			if(cantidaddeproductosporticket.get(i)<=cantidaddeproductosporticket.get(i+1)) {
				productomenosvendido=productoporticketNombredeProductos.get(i);
			}
		}
		return "El producto menos vendido tiene la siguiente descripción: "+productomenosvendido+". Se va a ofrecer un descuento del 25% para éste.";
	}
}
