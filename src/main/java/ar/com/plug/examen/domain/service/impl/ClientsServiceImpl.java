package ar.com.app.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.app.examen.app.api.ClientsApi;
import ar.com.app.examen.app.api.MessageApi;
import ar.com.app.examen.app.exception.ExamenException;
import ar.com.app.examen.app.mapper.ClientsMapper;
import ar.com.app.examen.domain.model.Clients;
import ar.com.app.examen.domain.service.ClientsRepository;

@Service
public class ClientsServiceImpl {

	@Autowired
	private ClientsRepository clienteRepository;

	@Transactional
	public ClientsApi saveClients(ClientsApi clientesApi) throws Exception {
		ClientsMapper mapper = new ClientsMapper();
		Clients clientes = mapper.map(clientesApi, Clients.class);
		Clients clientes2 = clienteRepository.save(clientes);
		return mapper.map(clientes2, ClientsApi.class);
	}

	public List<ClientsApi> getClients() {
		ClientsMapper mapper = new ClientsMapper();
		return mapper.mapAsList(clienteRepository.findAll(), ClientsApi.class);
	}

	public ClientsApi getClientsById(Integer idCliente) {
		ClientsMapper mapper = new ClientsMapper();
		Clients clientes = clienteRepository.findById(idCliente).orElse(null);
		return mapper.map(clientes, ClientsApi.class);
	}

	@Transactional
	public ClientsApi updateClients(ClientsApi clientes) {
		ClientsMapper mapper = new ClientsMapper();
		Clients clienteEn = clienteRepository.findById(clientes.getIdclient()).orElse(null);
		if(clienteEn != null && clienteEn.getIdclient() != null) {
			clienteEn.setDocument(clientes.getDocument());
			clienteEn.setName(clientes.getName());
		}
		return mapper.map(clienteRepository.save(clienteEn), ClientsApi.class);
	}

	@Transactional
	public MessageApi deleteclientById(Integer idcliente) throws Exception{
		if(idcliente == null) {
			throw new ExamenException("The customerid field cannot be null.");
		}
		MessageApi api = new MessageApi();
		Clients clienteEn = clienteRepository.findById(idcliente).orElse(null);
		if(clienteEn != null && clienteEn.getIdclient() != null) {
			clienteRepository.deleteById(clienteEn.getIdclient());
			api.setMessage("customer removed successfully.");
		} else {
			api.setMessage("client with id " + idcliente + " Not found in the system.");
		}
		return api;
	}

}
