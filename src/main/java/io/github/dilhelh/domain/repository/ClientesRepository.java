package io.github.dilhelh.domain.repository;

import io.github.dilhelh.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientesRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        getEntityManager().persist(cliente);
        return cliente;
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAll(){
        return getEntityManager()
                .createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> getByNome(String nome){
      String jpql = "select c from Cliente c where c.nome like :nome";
      TypedQuery<Cliente> query = getEntityManager().createQuery(jpql, Cliente.class);
      query.setParameter("nome", "%" + nome + "%");
      return query.getResultList();
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        getEntityManager().merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente) {
        if(!getEntityManager().contains(cliente))
            cliente = getEntityManager().merge(cliente);
        getEntityManager().remove(cliente);
    }

    @Transactional
    public void delete(Integer id) {
        delete(getEntityManager().find(Cliente.class, id));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
