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
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 * Tipodepago generated by hbm2java
 */
@Entity
//@MappedSuperclass
@Table(name="tipodepago")
public class Tipodepago  implements java.io.Serializable {
	//@EmbeddedId
    //@AttributeOverride(name="idtipodepago", column = @Column(name="idtipodepago"))
	
    @Id
    @Column(name="idtipodepago")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idtipodepago;
    
    @Column(name="descripciontipodepago")
    private String descripciontipodepago;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="idtipodepago")
    @IndexColumn(name="idx")
    private Set<Tarjeta> tarjetas = new HashSet(0);

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Pago pago;
 
    public Tipodepago() {
    }

    public Tipodepago(int idtipodepago, String descripciontipodepago, Set<Tarjeta> tarjetas) {
       this.idtipodepago = idtipodepago;
       this.descripciontipodepago = descripciontipodepago;
       this.tarjetas = tarjetas;
    }
   
    public int getIdtipodepago() {
        return this.idtipodepago;
    }
    
    public void setIdtipodepago(int idtipodepago) {
        this.idtipodepago = idtipodepago;
    }
    public String getDescripciontipodepago() {
        return this.descripciontipodepago;
    }
    
    public void setDescripciontipodepago(String descripciontipodepago) {
        this.descripciontipodepago = descripciontipodepago;
    }

    public Set getTarjetas() {
        return this.tarjetas;
    }
    
    public void setTarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    
    public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	@Override
    public int hashCode() {
        return getIdtipodepago();
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
        final Tipodepago other = (Tipodepago) obj;
        if (this.getIdtipodepago()!= other.getIdtipodepago()) {
            return false;
        }
        return true;
    }

}


