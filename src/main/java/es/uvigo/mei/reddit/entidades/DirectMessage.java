package es.uvigo.mei.reddit.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DirectMessage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String body;
    
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    
    @ManyToOne
    private User transmitter;
    
    @ManyToOne
    private User receiver;
    
    public DirectMessage() {
    	this.dateCreated = Date.from(Instant.now());
    }
    
	public DirectMessage(String body, Date dateCreated, User transmitter, User receiver) {
		this.body = body;
		this.dateCreated = Date.from(Instant.now());
		this.transmitter = transmitter;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(User transmitter) {
		this.transmitter = transmitter;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.body);
        hash = 29 * hash + Objects.hashCode(this.dateCreated);
        hash = 29 * hash + Objects.hashCode(this.transmitter);
        hash = 29 * hash + Objects.hashCode(this.receiver);
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
        final DirectMessage other = (DirectMessage) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        if (!Objects.equals(this.transmitter, other.transmitter)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        
        return true;
    }
    @Override
    public String toString() {
        return "DirectMessage{" + "id=" + id + ", body=" + body + ", dateCreated=" + dateCreated + ", transmitter=" + transmitter + ", receiver=" + receiver + "}";
    }
}
