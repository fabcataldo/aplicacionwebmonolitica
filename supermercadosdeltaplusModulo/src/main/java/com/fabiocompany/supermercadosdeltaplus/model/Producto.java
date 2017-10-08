package com.fabiocompany.supermercadosdeltaplus.model;
// Generated 24/09/2017 15:11:33 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.List;
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
 * Producto generated by hbm2java
 */
@Entity
//@MappedSuperclass
@Table(name="producto")
public class Producto  implements java.io.Serializable {

    //@EmbeddedId
    //@AttributeOverride(name="idproducto", column = @Column(name="idproducto"))
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
}


