package br.com.rafael.controleestoque.services.base;

import br.com.rafael.controleestoque.exceptions.NaoEncontradoException;
import br.com.rafael.controleestoque.exceptions.RegistrosDependentesException;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class CrudService<T, R extends CrudRepository, I> {

    @Autowired
    protected R repo;

    @Autowired
    protected MessagesHandler messagesHandler;

    public T findById(I id) throws Throwable {
        String entidade = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        return (T) repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(messagesHandler.getMessage("entidade.notfound", entidade, id)));
    }

    public T insert(T entity) {
        return (T) repo.save(entity);
    }

    public void deleteById(I id) {
        String entidade = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RegistrosDependentesException(messagesHandler.getMessage("entidade.nao_foi_possivel_deletar", entidade, id));
        } catch (EmptyResultDataAccessException e1) {
            throw new NaoEncontradoException(messagesHandler.getMessage("entidade.notfound", entidade, id));
        }
    }
}
