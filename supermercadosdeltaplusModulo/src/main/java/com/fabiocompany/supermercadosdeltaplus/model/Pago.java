package com.fabiocompany.supermercadosdeltaplus.model;


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
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="pago")
public class Pago  implements java.io.Serializable {
    @Id
    @Column(name="idpago")
    @GeneratedValue(strategy=GenerationType.AUTO) //columna autoincremental
    private int idpago;
       
    @Column(name="nombreusuario")
    private double montodepago;
 
    
    public Pago() {
    }

	
 
    public Pago(int idpago, double montodepago) {
		this.idpago = idpago;
		this.montodepago = montodepago;
	}


	public int getIdpago() {
		return idpago;
	}

	public void setIdpago(int idpago) {
		this.idpago = idpago;
	}

	public double getMontodepago() {
		return montodepago;
	}



	public void setMontodepago(double montodepago) {
		this.montodepago = montodepago;
	}



	@Override
    public int hashCode() {
        return getIdpago();
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
        final Pago other = (Pago) obj;
        if (this.getIdpago()!= other.getIdpago()) {
            return false;
        }
        return true;
    }

}


