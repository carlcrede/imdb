package kea.design.exam.imdb.service;

import kea.design.exam.imdb.models.IModel;

import java.util.ArrayList;

public interface CrudService<T, ID> {
    public void save(T t);
    public void delete(T t);
    public ArrayList<T> findByQuery(String query);
    public T findById(ID id);

}
