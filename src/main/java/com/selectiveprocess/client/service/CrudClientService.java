package com.selectiveprocess.client.service;

import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.interfaces.ClientRepository;
import com.selectiveprocess.client.interfaces.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CrudClientService implements ClientService {

	private final ClientRepository clientRepository;

	@Autowired
	public CrudClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientEntity> getAllClients() {
		List<ClientEntity> clients = clientRepository.findAll();
		return clients;
	}

	@Override
	public ClientEntity getClientById(Long clientId) {
		Optional<ClientEntity> client = clientRepository.findById(clientId);
		return client.get();
	}

	@Override
	public ClientEntity create(ClientEntity client) {
		return clientRepository.save(client);
	}

	@Override
	public void delete(Long id) {
		ClientEntity client = new ClientEntity(id);
		clientRepository.delete(client);
	}

	@Override
	public ClientEntity update(ClientEntity client) {
		return clientRepository.save(client);
	}


}
