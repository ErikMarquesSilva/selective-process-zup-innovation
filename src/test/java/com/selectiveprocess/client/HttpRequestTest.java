package com.selectiveprocess.client;

import com.selectiveprocess.client.controller.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	private static final Long clientId = 1L;
	@LocalServerPort private int port;

	@Autowired private TestRestTemplate restTemplate;

	@Test
	public void returnClientWithHttpStatusCode200_ifClientIsExist() {
		String clientName = "Client-1";
		Long cpf = 12345678901L;
		String dateOfBirth = "10/10/2019";
		String address = "address";

		Client.ClientRequest clientRequest = new Client.ClientRequest(clientName, cpf, dateOfBirth, address);

		restTemplate.postForObject("http://localhost:" + port + "/v1/client", clientRequest, String.class);

		assertThat(restTemplate.getForObject("http://localhost:" + port + "/v1/client/" + clientId, String.class))
				.contains(clientId.toString())
				.contains(clientName);
	}
}
