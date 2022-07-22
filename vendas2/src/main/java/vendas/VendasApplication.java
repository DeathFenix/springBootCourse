package vendas;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vendas.entity.Cliente;
import vendas.entity.Pedido;
import vendas.repository.Clientes;
import vendas.repository.Pedidos;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init( 
			@Autowired Clientes clientes,
			@Autowired Pedidos pedidos) {
		return args ->{
			System.out.println("Salvando cliente...");
//			clientes.save( new Cliente("Alan"));
//			clientes.save( new Cliente("Joyce"));
			
			Cliente fulano = new Cliente("Fulano");
			clientes.save(fulano);
			
			Pedido p = new Pedido();
			
			p.setCliente(fulano);
			p.setData_pedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);
			
			Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
			System.out.println(cliente);
			System.out.println(cliente.getPedidos());
			
			pedidos.findByCliente(fulano).forEach(System.out::println);
			
//			List<Cliente> todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//			
//			System.out.println("Atualizando cliente...");
//			todosClientes.forEach(c ->{
//				c.setName(c.getName() + " atualizado.");
//				clientes.save(c);
//			});
//			
//			System.out.println("Buscando cliente...");
////			clientes.findByNameLike("Ala").forEach(System.out::println);  //por query methods
//			List<Cliente> result = clientes.encontrarPorNome("Ala");
//			result.forEach(System.out::println);
//			
//			todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//			
//			System.out.println("Deletando clientes...");
//			clientes.findAll().forEach(c ->{
//				clientes.delete(c);
//			});
//			
//			todosClientes = clientes.findAll();
//			if(todosClientes.isEmpty()) {
//				System.out.println("Nenhum cliente encontrado.");
//			}else {
//				todosClientes.forEach(System.out::println);
//			}	
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
