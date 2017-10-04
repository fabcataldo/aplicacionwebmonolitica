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
//@MappedSuperclass
@Table(name="usuario")
public class Usuario  implements java.io.Serializable {
    //@EmbeddedId
    //@AttributeOverride(name="idusuario", column = @Column(name="idusuario"))
    @Id
    @Column(name="idusuario")
    @GeneratedValue(strategy=GenerationType.AUTO) //columna autoincremental
    private int idusuario;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idpersonausuario")
    private Personausuario personausuario;
    
    @Column(name="nombreusuario")
    private String nombreusuario;
    
    @Column(name="contraseniausuario")
    private String contraseniausuario;
    
    @Column(name="tipodeusuario")
    private String tipodeusuario;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="idusuario")
    @IndexColumn(name="idx")
    private Set<Cabeceraticket> cabeceratickets = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idusuario, Personausuario personausuario) {
        this.idusuario = idusuario;
        this.personausuario = personausuario;
    }
    public Usuario(int idusuario, Personausuario personausuario, String nombreusuario, String contraseniausuario, String tipodeusuario, Set<Cabeceraticket> cabeceratickets) {
       this.idusuario = idusuario;
       this.personausuario = personausuario;
       this.nombreusuario = nombreusuario;
       this.contraseniausuario = contraseniausuario;
       this.tipodeusuario = tipodeusuario;
       this.cabeceratickets = cabeceratickets;
    }
   
    public int getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public Personausuario getPersonausuario() {
        return this.personausuario;
    }
    
    public void setPersonausuario(Personausuario personausuario) {
        this.personausuario = personausuario;
    }
    public String getNombreusuario() {
        return this.nombreusuario;
    }
    
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }
    public String getContraseniausuario() {
        return this.contraseniausuario;
    }
    
    public void setContraseniausuario(String contraseniausuario) {
        this.contraseniausuario = contraseniausuario;
    }
    public String getTipodeusuario() {
        return this.tipodeusuario;
    }
    
    public void setTipodeusuario(String tipodeusuario) {
        this.tipodeusuario = tipodeusuario;
    }
    public Set getCabeceratickets() {
        return this.cabeceratickets;
    }
    
    public void setCabeceratickets(Set<Cabeceraticket> cabeceratickets) {
        this.cabeceratickets = cabeceratickets;
    }

    @Override
    public int hashCode() {
        return getIdusuario();
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
        final Usuario other = (Usuario) obj;
        if (this.getIdusuario()!= other.getIdusuario()) {
            return false;
        }
        return true;
    }

}


