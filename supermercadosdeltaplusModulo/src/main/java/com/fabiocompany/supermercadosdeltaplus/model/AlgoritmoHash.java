package com.fabiocompany.supermercadosdeltaplus.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "hashtable")
public class AlgoritmoHash {
	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="texto")
	private String texto;
	
    @Column(name="hash")
	private String resultadohash;
    
    @Column(name="algoritmodehash")
	private String algoritmodehash;
	
	public AlgoritmoHash() {
		id=1;
		texto="";
		resultadohash="";
		algoritmodehash="SHA-256";
	}

	public AlgoritmoHash(String ptexto) {
		id=1;
		texto=ptexto;
		resultadohash="";
		algoritmodehash="SHA-256";
	}
	
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getResultadohash() {
		return resultadohash;
	}

	public void setResultadohash(String resultadohash) {
		this.resultadohash = resultadohash;
	}

	
	public String getAlgoritmodehash() {
		return algoritmodehash;
	}

	
	@Override
	public String toString() {
		return "\n"+"["+ resultadohash+ "]";
	}

	//todo el codigo necesario para obtener el hash, se obtuvo de 
	//http://codesandtags.org/generando-hash-en-java-messagedigest/
	public String GenerarHash() {
      // Variable que guardara el digest generado
      byte[] digest = null;
      // Variable que obtiene el buffer del texto a codificar
      byte[] buffer = getTexto().getBytes();
      //variable que tiene el hash que hay que devolver
      String resultado="";
	      
      // Se intenta obtener el Message Digest del algoritmo
      // seleccionado. Esto es en base a la clase MessageDigest
      // del paquete Security de Java
      try {
        // Instancio un objeto MessageDigest con el algoritmo apropiado
        MessageDigest md = MessageDigest.getInstance(getAlgoritmodehash());
        // Reseteo el digest que pueda existir en el objeto
        md.reset();
        // Envio el buffer el mensaje a encriptar
        md.update(buffer);
        // Obtengo el Digest del Message
        digest = md.digest();
        // Obtengo la cadena del hash en valores hexadecimales
        resultado = toHexadecimal(digest);
        return resultado;
      } catch (NoSuchAlgorithmException e) {
         // Controlo el mensaje de cualquier excepcion generada
         e.printStackTrace();
      }
      return resultado;
	}
	private String toHexadecimal(byte[] digest) {
	      String hash = "";
	      for (byte aux : digest) {
	         int b = aux & 0xff; // Hace un cast del byte a hexadecimal
	         if (Integer.toHexString(b).length() == 1)
	            hash += "0";
	         hash += Integer.toHexString(b);
	      }
	      return hash;
	}
}
