package com.si.reddit.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User implements Serializable {

    @Id
    private String DNI;
    
    private String name;
    
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
