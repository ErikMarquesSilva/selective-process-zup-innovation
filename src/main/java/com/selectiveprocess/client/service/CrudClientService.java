package com.selectiveprocess.client.service;

import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.exception.ResourceAlreadyExistsException;
import com.selectiveprocess.client.exception.ResourceMandatoryException;
import com.selectiveprocess.client.exception.ResourceNotFoundException;
import com.selectiveprocess.client.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.selectiveprocess.client.constants.APIConstants.*;

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
	public Optional<ClientEntity> getClientById(Long clientId) {
		Optional<ClientEntity> client = clientRepository.findById(clientId);
		if(!client.isPresent()) {
			throw new ResourceNotFoundException(CLIENT_NOT_FOUND_WITH_ID + clientId);
		}
		return client;
	}

	@Override
	public ClientEntity create(ClientEntity client) {
		clientValidate(client);
		return clientRepository.save(client);
	}

	@Override
	public void delete(Long clientId) {
		if(!existsById(clientId)) {
			throw new ResourceNotFoundException(CLIENT_NOT_FOUND_WITH_ID + clientId);
		}
		clientRepository.delete(new ClientEntity(clientId));
	}

	@Override
	public ClientEntity update(ClientEntity client) {
		if(!existsById(client.getId())) {
			throw new ResourceNotFoundException(CLIENT_NOT_FOUND_WITH_ID + client.getId());
		}
		return clientRepository.save(client);
	}

	@Override
	public ClientEntity getClientByCpf(Long clientCpf) {
		Optional<ClientEntity> client = Optional.ofNullable(clientRepository.findByCpf(clientCpf));
		if(!client.isPresent()) {
			throw new ResourceNotFoundException(CLIENT_NOT_FOUND_WITH_CPF + clientCpf);
		}
		return client.get();
	}

	@Override
	public boolean existsById(Long clientId) {
		return clientRepository.findById(clientId).isPresent();
	}

	private void clientValidate(ClientEntity client) {
		if (client.getName().isEmpty()) {
			throw new ResourceMandatoryException(NAME_MANDATORY);
		}
		if (client.getDateOfBirth().isEmpty()) {
			throw new ResourceMandatoryException(DATE_BIRTH_MANDATORY);
		}
		if (Objects.isNull(client.getCpf())) {
			throw new ResourceMandatoryException(CPF_MANDATORY);
		}
		if (client.getAddress().isEmpty()) {
			throw new ResourceMandatoryException(ADDRESS_MANDATORY);
		}
		if (Optional.ofNullable(clientRepository.findByCpf(client.getCpf())).isPresent()) {
			throw new ResourceAlreadyExistsException(CLIENT_ALREADY_EXISTS);
		}
	}

}
