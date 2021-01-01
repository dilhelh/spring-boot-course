package io.github.dilhelh.domain.repository;

import io.github.dilhelh.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClientesRepository {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";

    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";

    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        getEntityManager().persist(cliente);
        return cliente;
    }

    public List<Cliente> getAll(){
        return getJdbcTemplate().query(SELECT_ALL, getClienteRowMapper());
    }

    public List<Cliente> getByName(String name){
        return getJdbcTemplate().query(SELECT_ALL.concat(" WHERE NOME LIKE ?"),
                new Object[]{"%" + name + "%"}, getClienteRowMapper());
    }

    private RowMapper<Cliente> getClienteRowMapper() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            return new Cliente(id, nome);
        };
    }


    public void update(Cliente cliente) {
        getJdbcTemplate().update(UPDATE, new Object[] {cliente.getNome(), cliente.getId()});
    }

    public void delete(Cliente cliente) {
        getJdbcTemplate().update(DELETE, new Object[] {cliente.getId()});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
