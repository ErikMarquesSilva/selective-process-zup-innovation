package com.selectiveprocess.client.interfaces;

import com.selectiveprocess.client.entity.ClientEntity;

import java.util.List;

public interface ClientService {

	List<ClientEntity> getAllClients();

	ClientEntity getClientById(Long clientId);

	ClientEntity create(ClientEntity clientEntity);

	void delete(Long clientId);

	ClientEntity update(ClientEntity clientEntity);
}
