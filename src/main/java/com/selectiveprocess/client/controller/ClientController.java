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
        log.debug("START - Creating client, {}",
                request.toString());

        ClientEntity client = clientService.create(new ClientEntity(request));

        log.debug("END - Creating client, {}",
                request.toString());
        return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
    }

    @Override
    public ClientResponse delete(Long clientId) {
        log.debug("START - Delete Client Id, {}", clientId);

        clientService.delete(clientId);

        log.debug("END - Delete Client Id, {}", clientId);

        return new ClientResponse(new ArrayList<>(), Result.builder().message("Success").success(true).build());
    }

    @Override
    public ClientResponse update(ClientRequest request) {
        log.debug("START - Updating Client, {}", request.toString());

        ClientEntity client = clientService.update(new ClientEntity(request));

        log.debug("END - Updating Client, {}", request.toString());

        return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
    }

    @Override
    public ClientResponse getClientById(Long clientId) {
        log.debug("START - Get Client Id, {}", clientId);

        ClientEntity client = clientService.getClientById(clientId).get();

        log.debug("END - Get Client Id, {}", clientId);

        return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
    }

    @Override
    public ClientResponse getClientByCpf(Long clientCpf) {
        log.debug("START - Get Client CPF, {}", clientCpf);

        ClientEntity client = clientService.getClientByCpf(clientCpf);

        log.debug("END - Get Client CPF, {}", clientCpf);

        return new ClientResponse(Collections.singletonList(client), Result.builder().message("Success").success(true).build());
    }

    @Override
    public ClientResponse getAllClients() {
        log.debug("START - Get All Clients");

        List<ClientEntity> clients = clientService.getAllClients();

        log.debug("END - Get All Clients Count");

        return new ClientResponse(clients, Result.builder().message("Success").success(true).build());
    }
}
