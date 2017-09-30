package com.fabiocompany.supermercadosdeltaplus.model;
// Generated 24/09/2017 15:11:33 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 * Cabeceraticket generated by hbm2java
 */
@Entity
//@MappedSuperclass
@Table(name="cabeceraticket")
public class Cabeceraticket  implements java.io.Serializable {

    //@EmbeddedId
    //@AttributeOverride(name="idcabeceraticket", column = @Column(name="idcabeceraticket"))
	@Id
    @Column(name="idticket")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int idticket;
    
    @ManyToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;
    
    @Column(name="fecha")
    private String fecha;
    
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "cabeceraticket", cascade= CascadeType.ALL)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="idticket")
    @IndexColumn(name="idx")
    private Set<Detalleticket> detalletickets = new HashSet(0);

    public Cabeceraticket() {
    }

	
    public Cabeceraticket(int idticket, Usuario usuario) {
        this.idticket = idticket;
        this.usuario = usuario;
    }
    public Cabeceraticket(int idticket, Usuario usuario, String fecha, Set<Detalleticket> detalletickets) {
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
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Set getDetalletickets() {
        return this.detalletickets;
    }
    
    public void setDetalletickets(Set<Detalleticket> detalletickets) {
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

    
}


