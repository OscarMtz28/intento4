package mx.uam.proyecto.dal;

import java.util.List;

public interface EntityDao <T> {
    public List<T> getAll();
    public T save(T entity);
    public T update(T entity);
    public T delete(int id);
   
}
