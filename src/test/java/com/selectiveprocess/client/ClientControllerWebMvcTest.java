package com.selectiveprocess.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selectiveprocess.client.controller.ClientController;
import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.controller.Client;
import com.selectiveprocess.client.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerWebMvcTest {

	private static final String CONTEXT = "/v1/clients/";
	private static final Long clientId = 1L;

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	@MockBean private ClientService clientService;

	@Test
	public void createClientReturnHttpStatusCode200IfClientIsValid() throws Exception {
		String clientName = "Client-1";
		Long cpf = 12345678901L;
		String dateOfBirth = "10/10/2019";
		String address = "address";

		Client.ClientRequest clientRequest = new Client.ClientRequest(clientName, cpf, dateOfBirth, address);

		String json = objectMapper.writeValueAsString(clientRequest);

		ClientEntity client = new ClientEntity(clientName, cpf, dateOfBirth, address);

		when(clientService.create(any())).thenReturn(client);

		this.mockMvc
				.perform(
						post(CONTEXT)
								.contentType(MediaType.APPLICATION_JSON)
								.content(json)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(content().string(containsString("Success")))
				.andExpect(content().string(containsString(clientId.toString())))
				.andExpect(content().string(containsString(clientName)));
	}

	@Test
	public void getClientWithHttpStatusCode200IfClientIsExist() throws Exception {
		String clientName = "Client-1";
		Long cpf = 12345678901L;
		String dateOfBirth = "10/10/2019";
		String address = "address";

		Client.ClientRequest clientRequest = new Client.ClientRequest(clientName, cpf, dateOfBirth, address);

		String json = objectMapper.writeValueAsString(clientRequest);

		ClientEntity client = new ClientEntity(clientId, clientName, cpf, dateOfBirth, address);

		when(clientService.getClientById(any())).thenReturn(java.util.Optional.of(client));

		this.mockMvc
				.perform(
						get(CONTEXT + clientId)
								.contentType(MediaType.APPLICATION_JSON)
								.content(json)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString("Success")))
				.andExpect(content().string(containsString(clientId.toString())))
				.andExpect(content().string(containsString(clientName)));
	}
}
