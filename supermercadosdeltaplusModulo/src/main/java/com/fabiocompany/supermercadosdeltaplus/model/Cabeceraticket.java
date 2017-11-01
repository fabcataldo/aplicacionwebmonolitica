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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name="cabeceraticket")
public class Cabeceraticket  implements java.io.Serializable {

	@Id
    @Column(name="idticket")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int idticket;
    
    @ManyToOne
    @JoinColumn(name="idUser")
    private User usuario;
    
    @Column(name="fecha")
    private long fecha;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="idticket")
    private List<Detalleticket> detalletickets;

    public Cabeceraticket() {
    }

	
    public Cabeceraticket(int idticket, User usuario) {
        this.idticket = idticket;
        this.usuario = usuario;
    }
    public Cabeceraticket(int idticket, User usuario, long fecha, List<Detalleticket> detalletickets) {
       this.idticket = idticket;
       this.usuario = usuario;
       this.fecha = fecha;
       this.detalletickets = detalletickets;
    }
   
    public int getIdticket() {
        return this.idticket;
    }
    
    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }
    public User getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    public long getFecha() {
        return this.fecha;
    }
    
    public void setFecha(long fecha) {
        this.fecha = fecha;
    }
    public List getDetalletickets() {
        return this.detalletickets;
    }
    
    public void setDetalletickets(List<Detalleticket> detalletickets) {
        this.detalletickets = detalletickets;
    }

    @Override
    public int hashCode() {
        return getIdticket();
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
        final Cabeceraticket other = (Cabeceraticket) obj;
        if (this.getIdticket() != other.getIdticket()) {
            return false;
        }
        return true;
    }


	@Override
	public String toString() {
		//return "Cabeceraticket [idticket=" + idticket + ", usuario=" + usuario + ", fecha=" + fecha
			//	+ ", detalletickets=" + detalletickets + "]";
		
		//YA ESTA RESUELTO
		//salta error en consola sobre el detalleticket
		//org.hibernate.LazyInitializationException: failed to lazily initialize a
		//collection of role:
		//com.fabiocompany.supermercadosdeltaplus.model.Detalleticket.productos,
		//could not initialize proxy - no Session
		return "Cabeceraticket [idticket=" + idticket + ", usuario=" + usuario + ", fecha=" + fecha+"]";
	}

    
}


