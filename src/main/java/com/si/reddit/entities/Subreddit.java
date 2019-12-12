package com.si.reddit.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Subreddit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "{NameErrorEmpty}")
    @Size(max = 30, message = "{NameErrorSize}")
    @Column(length = 30)
    private String name;

    @NotNull
    @NotEmpty(message = "{DescriptionErrorEmpty}")
    @Size(max = 200, message = "{DescriptionErrorSize}")
    @Column(length = 200)
    private String description;

    public Subreddit() {
    }

    public Subreddit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            int hash = Objects.hashCode(this.name);
            return hash;
        }
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
        final Subreddit other = (Subreddit) obj;
        if (this.id !=null) {
            return this.id.equals(other.id);
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Subreddit{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
}
