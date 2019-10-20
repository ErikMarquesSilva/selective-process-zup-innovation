package com.selectiveprocess.client.service;

import com.selectiveprocess.client.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

	List<ClientEntity> getAllClients();

	Optional<ClientEntity> getClientById(Long clientId);

	ClientEntity getClientByCpf(Long clientCpf);

	ClientEntity create(ClientEntity clientEntity);

	void delete(Long clientId);

	ClientEntity update(ClientEntity clientEntity);

	boolean existsById(Long clientId);
}
