package io.github.dilhelh.domain.repository;

import io.github.dilhelh.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientesRepository {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";

    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Cliente cliente) {
        getJdbcTemplate().update(INSERT, new Object[] {cliente.getNome()});
    }

    public List<Cliente> getAll(){
        return getJdbcTemplate().query(SELECT_ALL,(resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            return new Cliente(id, nome);
        } );
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
