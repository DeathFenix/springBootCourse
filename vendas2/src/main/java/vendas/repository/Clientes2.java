package vendas.repository;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vendas.entity.Cliente;

@Repository
public class Clientes2 {
	
	
//	private static String INSERT = "insert into cliente (nome) values (?) ";
//	private static String SELECT_ALL = "select * from cliente ";
//	private static String UPDATE = "update cliente set nome = ? where id = ? ";
//	private static String DELETE = "delete from cliente where id = ? ";
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;			//conexao JDBC
	
	@Autowired
	private EntityManager entityManager;		//mapeamento JPA
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
//		jdbcTemplate.update( INSERT, new Object[] {cliente.getName()});
		entityManager.persist(cliente);
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
//		jdbcTemplate.update(UPDATE, new Object[] {
//				cliente.getName(), cliente.getId()
//		});
		entityManager.merge(cliente);
		return cliente;
	}
	
	@Transactional
	public void deletar(Integer id) {
//		jdbcTemplate.update(DELETE, new Object[] {id});
		Cliente cliente = entityManager.find(Cliente.class, id);
		entityManager.remove(cliente);
	}
	
	@Transactional
	public void deletar(Cliente cliente) {
//		deletar(cliente.getId());
		if (!entityManager.contains(cliente)) {
			cliente = entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional(readOnly = true )
	public List<Cliente> buscarPorNome(String nome){
//		return jdbcTemplate.query(
//				SELECT_ALL.concat(" where nome like ? "),
//				new Object[] {"%" + nome + "%"},
//				obterClienteMapper());
		String jpql = "select c from Cliente c where nome like :nome ";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
	
	@Transactional(readOnly = true )
	public List<Cliente> obterTodos(){
//		return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
		return entityManager.createQuery(" from Cliente", Cliente.class).getResultList();
	}

//	private RowMapper<Cliente> obterClienteMapper() {
//		return new RowMapper<Cliente>() {
//
//			@Override
//			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Integer id = rs.getInt("id");
//				String nome = rs.getString("nome");
//				return new Cliente(id, nome);
//			}
//			
//		};
//	}
}
