package kea.design.exam.imdb.repository.internal.service;

import java.util.List;

public interface CrudService<T, ID> {
    public T findByid(ID id);
    public List<T> findAmountByQuery(String query, int amount);
    public T save(T t);
    public void delete(T t);
}
