package com.si.reddit.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User implements Serializable {

    @Id
    @Size(min = 9, max = 9, message = "El atributo DNI tiene una longitud incorrecta (Longitud correcta: 9).")
    @Pattern(regexp="\\d{8}[A-HJ-NP-TV-Z]", message = "El atributo DNI tiene que tener una estructura de 8 dígitos y 1 letra.")
    @Column(length = 9)
    private String DNI;
    
    @NotNull
    @NotEmpty(message = "El atributo nombre no puede ser vacío.")
    @Size(max = 30, message = "El atributo nombre tiene una longitud incorrecta (Longitud maxima: 30).")
    @Column(length = 30)
    private String name;
    
    @NotNull
    @Min(value = 0, message = "El atributo numero de trofeos debe tener un valor mayor que 0.")
    private int numTrophies;

    public User() {
    }

    public User(String DNI, String nombre, int numTrophies) {
        this.DNI = DNI;
        this.name = nombre;
        this.numTrophies = numTrophies;
    }

    public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumTrophies() {
		return numTrophies;
	}
	
	public void setNumTrophies(int numTrophies) {
		this.numTrophies = numTrophies;
	}

	@Override
    public int hashCode() {
        int hash = Objects.hashCode(this.DNI);
        return hash;
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
        final User other = (User) obj;
        if (!Objects.equals(this.DNI, other.DNI)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "User {" + "DNI=" + DNI + ", name=" + name + ", numTrophies=" + numTrophies + "}";
	}
}
