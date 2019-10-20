package com.selectiveprocess.client.controller;

import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public class ClientController implements Client {

	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public ClientResponse create(ClientRequest request) {
		ClientEntity client = clientService.create(new ClientEntity(request));
		return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
	}

	@Override
	public ClientResponse delete(Long clientId) {
		clientService.delete(clientId);
		return new ClientResponse(new ArrayList<>(), Result.builder().message("Success").success(true).build());
	}

	@Override
	public ClientResponse update(ClientRequest request) {
		ClientEntity client = clientService.update(new ClientEntity(request));
		return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
	}

	@Override
	public ClientResponse getClientById(Long clientID) {
		ClientEntity client = clientService.getClientById(clientID).get();
		return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
	}

	@Override
	public ClientResponse getClientByCpf(Long clientCpf) {
		ClientEntity client = clientService.getClientByCpf(clientCpf);
		return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
	}

	@Override
	public ClientResponse getAllClients() {
		List<ClientEntity> clients = clientService.getAllClients();
		return new ClientResponse(clients, Result.builder().message("Success").success(true).build());
	}
}
