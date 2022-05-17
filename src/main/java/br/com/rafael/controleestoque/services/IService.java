package br.com.rafael.controleestoque.services;

public interface IService<T, ID> {

    T findById(ID id);

    T insert(T entity);

    T update(ID id, T entity);

    void deleteById(ID id);

}
