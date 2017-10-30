package com.fabiocompany.supermercadosdeltaplus.model;

import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name="producto")
public class Producto  implements java.io.Serializable {
    @Id
    @Column(name="idproducto")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idproducto;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="precio")
    private double precio;
    
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="productos")
    private List<Detalleticket> detalletickets;

    public Producto() {
    }

    public Producto(int idproducto, String descripcion, Double precio, List<Detalleticket> detalletickets) {
       this.idproducto = idproducto;
       this.descripcion = descripcion;
       this.precio = precio;
       this.detalletickets = detalletickets;
    }
   
    public int getIdproducto() {
        return this.idproducto;
    }
    
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public List getDetalletickets() {
        return this.detalletickets;
    }
    
    public void setDetalletickets(List<Detalleticket> detalletickets) {
        this.detalletickets = detalletickets;
    }
    
    @Override
    public int hashCode() {
        return getIdproducto();
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
        final  Producto other = (Producto) obj;
        if (this.getIdproducto() != other.getIdproducto()) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", detalletickets=" + detalletickets + "]";
	}
	
	/*@Override
	public String toString() {
		
		return "Producto [idproducto=" + idproducto + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
		//salta error en detalletickets:
		//org.hibernate.LazyInitializationException: failed to lazily initialize 
		//a collection of role: 
		//com.fabiocompany.supermercadosdeltaplus.model.Detalleticket.productos, 
		//could not initialize proxy - no Session
	}*/
    
	

}


