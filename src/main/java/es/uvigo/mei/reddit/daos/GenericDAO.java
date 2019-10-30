package es.uvigo.mei.reddit.daos;

import java.util.List;

public interface GenericDAO<T,K> {
    public T create(T entity) throws RedditException ;
    public T refresh(T entity) throws RedditException ;
    public void remove(T entity) throws RedditException ;
    public T searchByKey(K key) throws RedditException ;
    public List<T> searchAll() throws RedditException ;
    public Long countAll() throws RedditException ;
}
