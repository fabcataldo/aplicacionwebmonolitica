package com.fabiocompany.supermercadosdeltaplus.model;
// Generated 24/09/2017 15:11:33 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name="detalleticket")
public class Detalleticket  implements java.io.Serializable {

	@Id
    @Column(name="iddetalleticket")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int iddetalleticket;
     
    @ManyToOne
    @JoinColumn(name="idticket")  
    private Cabeceraticket cabeceraticket;
     
 
    @Column(name="cantidad")
    private int cantidad;

    
    @ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="ticketsyproductos", joinColumns = {
            @JoinColumn(name = "iddetalleticket") }, inverseJoinColumns = {
		@JoinColumn(name = "idproducto") })
    private List<Producto> productos=new ArrayList<Producto>();


    public Detalleticket() {
    }
    
    public Detalleticket(int iddetalleticket, Cabeceraticket cabeceraticket,
    		int cantidad,
    		List<Producto> productos) {
		this.iddetalleticket = iddetalleticket;
		this.cabeceraticket = cabeceraticket;
		this.cantidad = cantidad;
		this.productos = productos;
	}



	public int getIddetalleticket() {
		return iddetalleticket;
	}



	public void setIddetalleticket(int iddetalleticket) {
		this.iddetalleticket = iddetalleticket;
	}



	public Cabeceraticket getCabeceraticket() {
		return cabeceraticket;
	}



	public void setCabeceraticket(Cabeceraticket cabeceraticket) {
		this.cabeceraticket = cabeceraticket;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	@Override
    public int hashCode() {
        return getIddetalleticket();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Detalleticket other = (Detalleticket) obj;
        if (this.getIddetalleticket() != other.getIddetalleticket()) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
   		return "Detalleticket [iddetalleticket=" + iddetalleticket + ", cabeceraticket=" + cabeceraticket
			+ ", cantidad=" + cantidad + ", productos=" + productos + "]";

   		//YA ARREGLADO
		//return "Detalleticket [iddetalleticket=" + iddetalleticket + ","
		//	+ " cantidad=" + cantidad +"]";
		//salta error en productos y en cabecera ticket:
		//org.hibernate.LazyInitializationException: failed to lazily initialize 
		//a collection of role: 
		//com.fabiocompany.supermercadosdeltaplus.model.Detalleticket.productos, 
		//could not initialize proxy - no Session

	}
	
}


