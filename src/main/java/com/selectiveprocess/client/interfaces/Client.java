package com.selectiveprocess.client.interfaces;

import com.selectiveprocess.client.entity.ClientEntity;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface Client {

	@PostMapping(value = "/v1/client", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	ClientResponse create(@RequestBody @Valid ClientRequest request);

	@GetMapping(value = "/v1/client/{clientId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse getClientById(@PathVariable("clientId") Long clientId);

	@GetMapping(value = "/v1/client", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse getAllClients();

	@DeleteMapping(value = "/v1/client/{clientId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse delete(@PathVariable("clientId") Long clientId);

	@PutMapping(value = "/v1/client", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse update(@RequestBody @Valid ClientRequest request);

	@Data
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	class ClientResponse {
		List<ClientEntity> clients;
		Result result;
	}

	@Data
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	class Result {
		private boolean success;
		private String message;
	}

	@Data
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	class ClientRequest {
		private Long id;
		@NotNull private String name;
		@NotNull private Long cpf;
		@NotNull private String dateOfBirth;
		@NotNull private String address;
	}
}
