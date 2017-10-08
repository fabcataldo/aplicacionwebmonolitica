/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fabiocompany.supermercadosdeltaplus.model.Cabeceraticket;
import com.fabiocompany.supermercadosdeltaplus.model.Detalleticket;
import com.fabiocompany.supermercadosdeltaplus.model.Producto;
import com.fabiocompany.supermercadosdeltaplus.model.Usuario;
import com.fabiocompany.supermercadosdeltaplus.model.dao.ICabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUsuarioService;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;

/**
 *
 * @author fabio
 */
public class CabeceraticketService extends GenericService<Cabeceraticket, Integer>
		implements ICabeceraticketService {
	
	private static Logger LOG = LoggerFactory.getLogger(DetalleticketService.class);
	public CabeceraticketService(ICabeceraticketDAO dao) {
		super(dao);
	}

	
	//servicio de negocio
	@Override
	 public String usuarioQueMasCompro(List<Object> listadeticketsyusuarios) {
		//Descargo del parámetro que viene del método, las listas que me hacen falta 
		//para procesar la información que recibo
		List<Cabeceraticket> listadelascabecerasticket=(List<Cabeceraticket>) listadeticketsyusuarios.get(0);
		List<Usuario> listadelosusuarios=(List<Usuario>) listadeticketsyusuarios.get(1);
		
		//variables auxiliares
		String usuarioquemascompro="";
		List<String> nombredelusuariodexcompras=new ArrayList<String>();
		List<Integer> numerodecomprasdelusuarioi=new ArrayList<Integer>();
		int contadordeticketsporusuario=0;
		int numerodecomprasdelusuarioquemascompro=0;
		List<Cabeceraticket> listadelosticketsdelasemanaactual=new ArrayList<Cabeceraticket>();

		//Armo la fecha actual como long, y luego el ultimo dia de la semana como long
		Calendar c1=Calendar.getInstance();
		//BORRAR EL +1 EN EL DIADEFINDESEMANALONGGG
		//lo puse para que entrara al if, ya que la fecha que le puse fué antes del fin de semana
		int diadelfindesemana=c1.get(Calendar.DATE)+6+1;	
		int diaactual=c1.get(Calendar.DATE);
		int mesactual=c1.get(Calendar.MONTH)+1;
		int anioactual=c1.get(Calendar.YEAR);
		String fechaactualstring=diaactual+""+mesactual+""+anioactual;
		long fechaactual=Long.parseLong(fechaactualstring);
		String diadelfindesemanastring=diadelfindesemana+""+mesactual+""+anioactual;
		long diadelfindesemanalong=Long.parseLong(diadelfindesemanastring);

		//guardo los tickets que están en la semana actual
		//pregunto primero si la fecha del ticket está dentro de la semana actual
		//luego, pregunto si el usuario de la iteración i coincide con el
		//usuario del ticket i
		//si es así, cuento los tickets de dicho usuario
		//luego guardo dicho usuario en una lista de usuarios, que luego me va a servir
		//para decir bueno X usuario, hizo TALES compras (pienso como si tuviese una matriz)
		//guardo luego también el numero de compras del usuario i		
		for(int i=0;i<listadelosusuarios.size();i++) {
			for(int j=0;j<listadelascabecerasticket.size();j++) {
				if((listadelascabecerasticket.get(j).getFecha()>=fechaactual)&&(listadelascabecerasticket.get(j).getFecha()<=diadelfindesemanalong)){
					if(listadelosusuarios.get(i).getIdusuario()==listadelascabecerasticket.get(j).getUsuario().getIdusuario()) {
						contadordeticketsporusuario++;	
					}
					//guardo los tickets de la semana actual, sin importar los usuarios
					listadelosticketsdelasemanaactual.add(listadelascabecerasticket.get(i));
				}
			}
			nombredelusuariodexcompras.add(listadelosusuarios.get(i).getNombreusuario());
		
			//si ya se terminaron de guardar los tickets del usuario i,guardo la cantidad de compras 
			//del usuarioi y reinicio el contador de tickets para el próximo usuario
			numerodecomprasdelusuarioi.add(contadordeticketsporusuario);
			contadordeticketsporusuario=0;
		}
		
		//saco el promedio de compras
		double sumadecomprasdelosusuarios=0.0;
		double promediodecomprasdelosusuarios=0.0;
		for(int i=0;i<numerodecomprasdelusuarioi.size();i++) {
			sumadecomprasdelosusuarios+=numerodecomprasdelusuarioi.get(i);
		}
		promediodecomprasdelosusuarios=Math.ceil(sumadecomprasdelosusuarios/listadelosticketsdelasemanaactual.size());
		
		//Ya saqué el promedio de las compras, ahora veo qué usuario iguala 
		//o supera el promedio, para luego darle la oferta del 30%
		for(int i=0;i<nombredelusuariodexcompras.size();i++) {
			if(numerodecomprasdelusuarioi.get(i)>=promediodecomprasdelosusuarios) {
				usuarioquemascompro=nombredelusuariodexcompras.get(i);
				numerodecomprasdelusuarioquemascompro=numerodecomprasdelusuarioi.get(i);
			}
		}

		
		return "\n El usuario que más compras realizó fué el que tiene por nombre de usuario"
				+ ": "+usuarioquemascompro+". La cantidad de compras que realizó hasta "
				+ "el momento es de: "+numerodecomprasdelusuarioquemascompro+" compras. "
				+ "Él tiene, por una semana, un descuento del 30% en todos los productos "
				+ "que compre.";
	}
}
