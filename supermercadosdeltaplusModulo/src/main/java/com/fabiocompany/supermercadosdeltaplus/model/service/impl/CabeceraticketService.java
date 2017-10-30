/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabiocompany.supermercadosdeltaplus.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

		Calendar c1=Calendar.getInstance();
		int diaactual=c1.get(Calendar.DATE);
		int mesactual=c1.get(Calendar.MONTH)+1;
		int anioactual=c1.get(Calendar.YEAR);
		String fechaactualstring=diaactual+""+mesactual+""+anioactual;
		long fechaactual=Long.parseLong(fechaactualstring);
    	long fechadeldiadefindesemana=ObtenerFechaDelDiaDeFinDeSemana();
    	//System.out.println(fechadeldiadefindesemana);
    	//System.out.println(Math.max(29102017,5112017));
		int contadordeticketsporusuario=1;
		int j=0;
		int i=0;
		//almaceno en una lista, un nombre de usuario
		//en otra lista, almaceno las compras de cada usuario de la lista anteriormente dicha
		String nombredelusuarioqueseestaprocesando=listadeticketsyusuarios.get(0).getUsuario().getNombreusuario();
		while(i<(listadeticketsyusuarios.size()-1)) {
			if(listadeticketsyusuarios.get(i+1).getUsuario().getNombreusuario()!=null) {
				//si está dentro de la semana actual el ticket, lo cuento
				if((listadeticketsyusuarios.get(i).getFecha()>=fechaactual)||(listadeticketsyusuarios.get(i).getFecha()<=fechaactual)&&(listadeticketsyusuarios.get(i).getFecha()<=fechadeldiadefindesemana)){
					if((nombredelusuarioqueseestaprocesando==listadeticketsyusuarios.get(i+1).getUsuario().getNombreusuario())) {
						contadordeticketsporusuario++;
					}
					//guardo los tickets de la semana actual, sin importar los usuarios
					listadelosticketsdelasemanaactual.add(listadeticketsyusuarios.get(i));
				}
				i++;
			}
			if(i==listadeticketsyusuarios.size()-1) {
				//System.out.println("ENTRO AL IFF121");
				numerodecomprasdelusuarioi.add(contadordeticketsporusuario);
				contadordeticketsporusuario=0;
				nombredelusuariodexcompras.add(nombredelusuarioqueseestaprocesando);
				j++;
				nombredelusuarioqueseestaprocesando=listadeticketsyusuarios.get(j).getUsuario().getNombreusuario();
				i=0;
				i=j+1;
			}
			//System.out.println("NOmbre del usuario:"+nombredelusuarioqueseestaprocesando);
			//System.out.println("Numero de compras del usuario:"+contadordeticketsporusuario);
			//System.out.println("-------------------------------------------");
		}
		//saco el promedio de compras
		int sumadecomprasdelosusuarios=0;
		double promediodecomprasdelosusuarios=0.0;
		//System.out.println("POR ENTRAR AL NUMERODECOMPRASDELUSUARIOI");
		//System.out.println(numerodecomprasdelusuarioi.size());
		for(i=0;i<numerodecomprasdelusuarioi.size();i++) {
			//System.out.println("variable numerodecomprasdelusuarioi.get(i):"+numerodecomprasdelusuarioi.get(i));
			sumadecomprasdelosusuarios+=numerodecomprasdelusuarioi.get(i);
			//System.out.println(sumadecomprasdelosusuarios);
		}
		//System.out.println("SUMA DE COMPRAS DE LOS USUARIOS:"+sumadecomprasdelosusuarios);
		//System.out.println(listadelosticketsdelasemanaactual.size());
		
		//System.out.println("PROMEDIO: "+Math.ceil(sumadecomprasdelosusuarios/(double)listadelosticketsdelasemanaactual.size()));
		promediodecomprasdelosusuarios=Math.ceil(sumadecomprasdelosusuarios/(double)listadelosticketsdelasemanaactual.size());
		//System.out.println(promediodecomprasdelosusuarios+"\n\n\n\n");
		
		//Ya saqué el promedio de las compras, ahora veo qué usuario iguala 
		//o supera el promedio, para luego darle la oferta del 30%
		for(i=0;i<nombredelusuariodexcompras.size();i++) {
			//System.out.println(numerodecomprasdelusuarioquemascompro);
			//System.out.println(nombredelusuariodexcompras.get(i));
			if(numerodecomprasdelusuarioi.get(i)>=promediodecomprasdelosusuarios) {
				usuarioquemascompro=nombredelusuariodexcompras.get(i);
				numerodecomprasdelusuarioquemascompro=numerodecomprasdelusuarioi.get(i);
				//System.out.println(numerodecomprasdelusuarioquemascompro);
			}
		}

		return "\nEl usuario que mas compras realizo fue el que tiene por nombre de usuario"
				+ ": "+usuarioquemascompro+" con "+numerodecomprasdelusuarioquemascompro
				+ " realizada/s. El tiene, por una semana, un descuento del 30% en todos los" 
				+ "productos que compre.";
	}
	
	
	private long ObtenerFechaDelDiaDeFinDeSemana() {
		long fechafinal=0;
		
		Date fechadefindesemana;
		Date fechaactual=new Date();
	    Calendar calendar2 = Calendar.getInstance();
	    
	    //Configuramos la fecha que se recibe
	    calendar2.setTime(fechaactual); 
	    
	    //numero de días a añadir, en este caso 7
	    calendar2.add(Calendar.DAY_OF_YEAR, 7); 
	    
	    //devuelve el objeto Date con los nuevos días añadidos
	    fechadefindesemana=calendar2.getTime(); 
       	
	    String fechadefindesemanastring=fechadefindesemana.toString();
    	//corto desde la posicion 8 hasta la 9, y desde la 9 hasta la 10, y lo pego en 
    	//nuevafecha (ver el string de fechadefindesemanastring)
    	String diadelfindesemanastring=fechadefindesemanastring.substring(8, 9)+fechadefindesemanastring.substring(9, 10);
		
		String mesdeldiadelfindesemana=fechadefindesemanastring.substring(4, 5)+fechadefindesemanastring.substring(5, 6)+fechadefindesemanastring.substring(6, 7);
    	
		int mesint=0;
    	if(mesdeldiadelfindesemana.equals("Jan")) {
    		mesint=1;
    	}
    	if(mesdeldiadelfindesemana.equals("Feb")) {
    		mesint=2;
    	}if(mesdeldiadelfindesemana.equals("Mar")) {
    		mesint=3;
    	}if(mesdeldiadelfindesemana.equals("Apr")) {
    		mesint=4;
    	}if(mesdeldiadelfindesemana.equals("May")) {
    		mesint=5;
    	}if(mesdeldiadelfindesemana.equals("Jun")) {
    		mesint=6;
    	}if(mesdeldiadelfindesemana.equals("Jul")) {
    		mesint=7;
    	}if(mesdeldiadelfindesemana.equals("Ago")) {
    		mesint=8;
    	}if(mesdeldiadelfindesemana.equals("Sep")) {
    		mesint=9;
    	}if(mesdeldiadelfindesemana.equals("Oct")) {
    		mesint=10;
    	}if(mesdeldiadelfindesemana.equals("Nov")) {
    		mesint=11;
    	}if(mesdeldiadelfindesemana.equals("Dic")) {
    		mesint=12;
    	}

    	String aniodeldiadelfindesemana=fechadefindesemanastring.substring(24,25)+fechadefindesemanastring.substring(25,26)+fechadefindesemanastring.substring(26,27)+fechadefindesemanastring.substring(27,28);
    	String fechafinalstring="";
    	fechafinalstring=""+diadelfindesemanastring+mesint+aniodeldiadelfindesemana;
    	fechafinal=Long.parseLong(fechafinalstring);
    	return fechafinal;
	}
}
