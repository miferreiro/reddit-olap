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

public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String body;
    
    private int score;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;
	
    @ManyToOne
    private User user;

    @ManyToOne
    private Subreddit subreddit;
    
    public Comment() {
    	this.dateCreated = Date.from(Instant.now());
    }
    
	public Comment(String body, int score, Date dateCreated, User user, Subreddit subreddit) {
		this.body = body;
		this.score = score;
		this.dateCreated = Date.from(Instant.now());
		this.user = user;
		this.subreddit = subreddit;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subreddit getSubreddit() {
		return subreddit;
	}
	
	public void setSubreddit(Subreddit subreddit) {
		this.subreddit = subreddit;
	}
	
    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.body);
        hash = 29 * hash + Objects.hashCode(this.score);
        hash = 29 * hash + Objects.hashCode(this.dateCreated);
        hash = 29 * hash + Objects.hashCode(this.user);
        hash = 29 * hash + Objects.hashCode(this.subreddit);
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
        final Comment other = (Comment) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.score, other.score)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.subreddit, other.subreddit)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", body=" + body + ", score=" + score + ", dateCreated=" + dateCreated + ", user=" + user + ", subreddit=" + subreddit + "}";
    }   
}
