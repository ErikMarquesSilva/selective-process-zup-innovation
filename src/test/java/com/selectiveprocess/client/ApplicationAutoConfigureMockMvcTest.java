package com.selectiveprocess.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selectiveprocess.client.controller.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationAutoConfigureMockMvcTest {
	private static final Long clientId = 1L;
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		String clientName = "Client-" + clientId;

		this.mockMvc
				.perform(
						get("/v1/client/" + clientId)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString("Success")))
				.andExpect(content().string(containsString(clientId.toString())))
				.andExpect(content().string(containsString(clientName)));
	}

	@Test
	public void createClientReturnHttpStatusCode200IfClientIsValid() throws Exception {
		String clientName = "Client-1";
		Long cpf = 12345678901L;
		String dateOfBirth = "10/10/2019";
		String address = "address";

		Client.ClientRequest clientRequest = new Client.ClientRequest(clientName, cpf, dateOfBirth, address);

		String json = objectMapper.writeValueAsString(clientRequest);

		this.mockMvc
				.perform(
						post("/v1/client")
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
