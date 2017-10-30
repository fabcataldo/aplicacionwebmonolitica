package com.fabiocompany.supermercadosdeltaplus.model;


import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name="usuario")
public class Usuario  implements UserDetails, java.io.Serializable {
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
    private List<Cabeceraticket> cabeceratickets;



	private boolean accountEnabled = true;

	private boolean accountExpired = false;

	private boolean accountLocked = false;

	private boolean credentialsExpired = false;

	
    public Usuario() {
    }

	
    public Usuario(int idusuario, Personausuario personausuario) {
        this.idusuario = idusuario;
        this.personausuario = personausuario;
    }
    public Usuario(int idusuario, Personausuario personausuario, String nombreusuario, String contraseniausuario, String tipodeusuario, List<Cabeceraticket> cabeceratickets) {
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
    public List getCabeceratickets() {
        return this.cabeceratickets;
    }
    
    public void setCabeceratickets(List<Cabeceraticket> cabeceratickets) {
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

	
	public boolean isAccountEnabled() {
		return accountEnabled;
	}


	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}


	public boolean isAccountExpired() {
		return accountExpired;
	}


	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}


	public boolean isAccountLocked() {
		return accountLocked;
	}


	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}


	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}


	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean containsAuthority(String auth) {
		for (GrantedAuthority ga : getAuthorities()) {
			if (ga.getAuthority().equalsIgnoreCase(auth))
				return true;
		}
		return false;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(getTipodeusuario());
	}


	@Override
	public String getPassword() {
		return getContraseniausuario();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getNombreusuario();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !isAccountExpired();
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !isAccountLocked();
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return !isCredentialsExpired();
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isAccountEnabled();
	}


	@Override
	public String toString() {
		return "Usuario:\nNombre de usuario: "+getNombreusuario()+". Contraseña: "
				+getContraseniausuario()+". Tipo de usuario"+getTipodeusuario()
				+". Account Expired?: "+isAccountExpired()+
				". Account enabled?: "+isAccountEnabled()+". Account locked?: "+isAccountLocked()
				+". Credentials expired?:"+isCredentialsExpired()+". Info de su persona:"
				+getPersonausuario();		
	}
}


