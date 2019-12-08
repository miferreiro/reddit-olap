package com.si.reddit.entities;

import java.io.Serializable;
import java.util.Objects;

public class UserFollowSubredditID implements Serializable{ 
   private String user;
   private Long subreddit;
   
   public UserFollowSubredditID() {
	  super();
   }
   
   public UserFollowSubredditID(String user, Long subreddit) {
	   super();
	   this.user = user;
	   this.subreddit = subreddit;
   }
   
	@Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.subreddit);
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
        final UserFollowSubredditID other = (UserFollowSubredditID) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.subreddit, other.subreddit)) {
            return false;
        }
        
        return true;
    }
   
}
