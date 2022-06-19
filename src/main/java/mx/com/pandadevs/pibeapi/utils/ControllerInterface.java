package mx.com.pandadevs.pibeapi.utils;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ControllerInterface<T> {
    /** Controller Interface
     *  This interface helps to implement all repeated method each class
     * methods of a CRUD.
     *  In this part we don't use classes such as Optional because we handle
     * the response through the Object ResponseEntity accompanied by an HTTP status.
     */
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getOne(Long id);
    ResponseEntity<T> save(T entity);
    ResponseEntity<T> update(T entity);
    ResponseEntity<T> partialUpdate(Long id, Map<Object,Object> fields);
    ResponseEntity<Boolean> delete(Long id);

}
