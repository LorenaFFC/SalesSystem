package com.github.lorenaffc.SalesSystem.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.lorenaffc.SalesSystem.model.entity.Cliente;
import com.github.lorenaffc.SalesSystem.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	
	
//FORMAS DE REALIZAR INJECAO DE DEPENDENCIA	
//	@Autowired
//	private ClienteRepository clienteRepository;
//	
//	@Autowired
//	public void setClienteRepository(ClienteRepository clienteRepository) {
//		this.clienteRepository = clienteRepository;
//	}

	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);

	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Cliente> ListClienteFull(){
		return clienteRepository.findAll();
	}
	@GetMapping("{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Cliente buscarCliente( @PathVariable Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The customer id does not exist in the database."));
	}
	
	@DeleteMapping("{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Integer id) {
		clienteRepository
		.findById(id)
		.map(cliente -> {
			clienteRepository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

	@PutMapping("{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		clienteRepository
		.findById(id)
		.map(cliente -> {
			clienteAtualizado.setId(cliente.getId());
			return clienteRepository.save(clienteAtualizado);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
