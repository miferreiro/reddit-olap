package com.si.reddit.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(UserModerateSubredditID.class)
public class UserModerateSubreddit implements Serializable{
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Subreddit subreddit;
    
    @Temporal(TemporalType.DATE)
    private Date dateStartModerate;
    
    public UserModerateSubreddit() {   
    	this.dateStartModerate = Date.from(Instant.now());
    }
    
    public UserModerateSubreddit(User user, Subreddit subreddit) {
    	this.user = user;
    	this.subreddit = subreddit;
    	this.dateStartModerate = Date.from(Instant.now());
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
	
	public Date getDateStartModerate() {
		return this.dateStartModerate;
	}
	
	public void setDateStartModerate(Date dateStartModerate) {
		this.dateStartModerate = dateStartModerate;
	}
    
	@Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.subreddit);
        hash = 53 * hash + Objects.hashCode(this.dateStartModerate);
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
        final UserModerateSubreddit other = (UserModerateSubreddit) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.subreddit, other.subreddit)) {
            return false;
        }
        if (!Objects.equals(this.dateStartModerate, other.dateStartModerate)) {
            return false;
        }
        return true;
    }
    
	@Override
	public String toString() {
		return "UserModerateSubreddit {" + "User=" + user + ", subreddit=" + subreddit + ", dateStartModerate=" + dateStartModerate + "}";
	}
}
