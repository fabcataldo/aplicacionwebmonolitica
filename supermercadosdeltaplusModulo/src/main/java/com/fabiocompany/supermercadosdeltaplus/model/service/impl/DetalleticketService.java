/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

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
	public String ofertarProducto(List<Object> listadetickets){
		String productomenosvendido="";
		List<Detalleticket> listadelostickets=(List<Detalleticket>) listadetickets.get(0);
		List<Producto> listadelosproductos=(List<Producto>) listadetickets.get(1);

		//en el for haría el "join" que hago en mysql, pero con objetos
		for(int i=0;i<listadetickets.size();i++){
			if(((i+1)<listadelostickets.size())&&(listadelostickets.get(i).getCantidad()<listadelostickets.get(i+1).getCantidad())){
				productomenosvendido=listadelosproductos.get(i).getDescripcion();
			}
		}
		return "El producto menos vendido tiene la siguiente descripción: "+productomenosvendido+". Se va a ofrecer un descuento del 25% para éste.";
	}
}
