package vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.entity.Cliente;
import vendas.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByCliente(Cliente cliente);
}
