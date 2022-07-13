package mx.com.pandadevs.pibeapi.utils.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceInterface<I,T>{
    /** Service Interface
     *  This interface helps to implement all repeated method each class
     *  methods of a CRUD.
    */
    List<T> getAll();
    Optional<T> getById(I id);
    T save(T entity);
    Optional<T> update(T entity);
    Optional<T> partialUpdate(I id, Map<Object, Object> fields);
    Boolean delete(I id);
}