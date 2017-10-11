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
import com.fabiocompany.supermercadosdeltaplus.model.dao.ICabeceraticketDAO;
import com.fabiocompany.supermercadosdeltaplus.model.service.ICabeceraticketService;
import com.fabiocompany.supermercadosdeltaplus.persistence.exception.PersistenceException;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;
import com.fabiocompany.supermercadosdeltaplus.service.impl.GenericService;


/**
 *
 * @author fabio
 */
public class CabeceraticketService extends GenericService<Cabeceraticket, Integer>
		implements ICabeceraticketService {
	
	private static Logger LOG = LoggerFactory.getLogger(DetalleticketService.class);
	private ICabeceraticketDAO dao;
	
	public CabeceraticketService(ICabeceraticketDAO dao) {
		super(dao);
		this.dao=dao;
		
	}
	
	//servicio de negocio
	@Override
	 public String usuarioQueMasCompro() throws ServiceException {
		List<Cabeceraticket> listadeticketsyusuarios;
		try {
			listadeticketsyusuarios = dao.obtenerListadeTicketsyUsuarios();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage(),e);
		}
		//variables auxiliares
		String usuarioquemascompro="";
		int numerodecomprasdelusuarioquemascompro=0;
		List<String> nombredelusuariodexcompras=new ArrayList<String>();
		List<Integer> numerodecomprasdelusuarioi=new ArrayList<Integer>();
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

		int contadordeticketsporusuario=1;
		int jusuarioprocesado=0;
		
		//almaceno en una lista, un nombre de usuario
		//en otra lista, almaceno las compras de cada usuario de la lista anteriormente dicha
		String nombredelusuarioqueseestaprocesando=listadeticketsyusuarios.get(jusuarioprocesado).getUsuario().getNombreusuario();
		for(int i=0;i<listadeticketsyusuarios.size();i++) {
			if(((i+1)!=listadeticketsyusuarios.size())&&(listadeticketsyusuarios.get(i).getFecha()>=fechaactual)&&(listadeticketsyusuarios.get(i).getFecha()<=diadelfindesemanalong)){
				if(nombredelusuarioqueseestaprocesando==listadeticketsyusuarios.get(i+1).getUsuario().getNombreusuario()) {
					contadordeticketsporusuario++;
				}
				//guardo los tickets de la semana actual, sin importar los usuarios
				listadelosticketsdelasemanaactual.add(listadeticketsyusuarios.get(i));
			}
			if(i==listadeticketsyusuarios.size()-1) {
				//System.out.println("NOmbre del usuario:"+nombredelusuarioqueseestaprocesando);
				//System.out.println("Numero de compras del usuario:"+contadordeticketsporusuario);
				//System.out.println("-------------------------------------------");
				
				numerodecomprasdelusuarioi.add(contadordeticketsporusuario);
				contadordeticketsporusuario=0;
				nombredelusuariodexcompras.add(nombredelusuarioqueseestaprocesando);
				jusuarioprocesado++;
				nombredelusuarioqueseestaprocesando=listadeticketsyusuarios.get(jusuarioprocesado).getUsuario().getNombreusuario();
				i=jusuarioprocesado+1;
				
			}
		}
		
		//saco el promedio de compras
		int sumadecomprasdelosusuarios=0;
		double promediodecomprasdelosusuarios=0.0;
		for(int i=0;i<numerodecomprasdelusuarioi.size();i++) {
			//System.out.println("variable numerodecomprasdelusuarioi.get(i):"+numerodecomprasdelusuarioi.get(i));
			sumadecomprasdelosusuarios+=numerodecomprasdelusuarioi.get(i);
			//System.out.println(sumadecomprasdelosusuarios);
		}
		//System.out.println("SUMA DE COMPRAS DE LOS USUARIOS:"+sumadecomprasdelosusuarios);
		//System.out.println(listadelosticketsdelasemanaactual.size());
		
		promediodecomprasdelosusuarios=Math.ceil(sumadecomprasdelosusuarios/listadelosticketsdelasemanaactual.size());
		//System.out.println(promediodecomprasdelosusuarios+"\n\n\n\n");
		
		//Ya saqué el promedio de las compras, ahora veo qué usuario iguala 
		//o supera el promedio, para luego darle la oferta del 30%
		for(int i=0;i<nombredelusuariodexcompras.size();i++) {
			//System.out.println(numerodecomprasdelusuarioquemascompro);
			//System.out.println(nombredelusuariodexcompras.get(i));
			if(numerodecomprasdelusuarioi.get(i)>promediodecomprasdelosusuarios) {
				usuarioquemascompro=nombredelusuariodexcompras.get(i);
				numerodecomprasdelusuarioquemascompro=numerodecomprasdelusuarioi.get(i);
				//System.out.println(numerodecomprasdelusuarioquemascompro);
			}
		}

		return "\n El usuario que más compras realizó fué el que tiene por nombre de usuario"
				+ ": "+usuarioquemascompro+" con "+numerodecomprasdelusuarioquemascompro
				+ " realizadas. Él tiene, por una semana, un descuento del 30% en todos los" 
				+ "productos que compre.";
	}
}
