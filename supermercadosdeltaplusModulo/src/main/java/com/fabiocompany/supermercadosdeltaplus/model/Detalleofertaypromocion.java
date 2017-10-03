package com.fabiocompany.supermercadosdeltaplus.model;
// Generated 24/09/2017 15:11:33 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * Detalleoferta generated by hbm2java
 */

//@MappedSuperclass
@Entity
@Table(name="detalleofertaypromocion")
public class Detalleofertaypromocion implements java.io.Serializable {

    //@EmbeddedId
    //@AttributeOverride(name="iddetalleoferta", column = @Column(name="iddetalleoferta"))
    @Id
    @Column(name="iddetalleofertaypromocion")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int iddetalleofertaypromocion;
    
    @Column(name="monto")
    private double monto;
    
    @Column(name="descripcion")
    private String descripcion;
     
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="detallesofertaypromocion")
    private Set<Detalleticket> detalletickets = new HashSet(0);

    public Detalleofertaypromocion() {
    }

    

    public Detalleofertaypromocion(int iddetalleofertaypromocion, double monto, String descripcion,
			Set<Detalleticket> detalletickets) {
		super();
		this.iddetalleofertaypromocion = iddetalleofertaypromocion;
		this.monto = monto;
		this.descripcion = descripcion;
		this.detalletickets = detalletickets;
	}



	public int getIddetalleofertaypromocion() {
		return iddetalleofertaypromocion;
	}



	public void setIddetalleofertaypromocion(int iddetalleofertaypromocion) {
		this.iddetalleofertaypromocion = iddetalleofertaypromocion;
	}



	public double getMonto() {
		return monto;
	}



	public void setMonto(double monto) {
		this.monto = monto;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Set<Detalleticket> getDetalletickets() {
		return detalletickets;
	}



	public void setDetalletickets(Set<Detalleticket> detalletickets) {
		this.detalletickets = detalletickets;
	}



	@Override
    public int hashCode() {
        return getIddetalleofertaypromocion();
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
        final Detalleofertaypromocion other = (Detalleofertaypromocion) obj;
        if (this.getIddetalleofertaypromocion() != other.getIddetalleofertaypromocion()) {
            return false;
        }
        return true;
    }



}

