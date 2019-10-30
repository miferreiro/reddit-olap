package es.uvigo.mei.reddit.daos;

public class RedditException extends Exception{
    public RedditException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
