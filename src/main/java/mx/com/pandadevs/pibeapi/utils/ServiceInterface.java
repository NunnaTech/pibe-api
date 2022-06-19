package mx.com.pandadevs.pibeapi.utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceInterface<T>{
    /** Service Interface
     *  This interface helps to implement all repeated method each class
     *  methods of a CRUD.
    */
    List<T> getAll();
    Optional<T> getById(int id);
    T save(T entity);
    Optional<T> update(T entity);
    Optional<T> partialUpdate(Integer id, Map<Object, Object> fields);
    Boolean delete(int id);
}