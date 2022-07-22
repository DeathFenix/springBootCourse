package vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vendas.model.Clientes;
import vendas.repository.ClientesRepository;

@Service
public class ClientesService {
	
	private ClientesRepository repository;
	
	@Autowired
	public ClientesService(ClientesRepository repository) {  // Injeção de dependências
		this.repository = repository;
	}
	
	public void salvarCliente(Clientes cliente) {
		validarCliente(cliente);
		this.repository.persistir(cliente);
	}
	
	public void validarCliente(Clientes cliente) {
		//todo
	}

}
