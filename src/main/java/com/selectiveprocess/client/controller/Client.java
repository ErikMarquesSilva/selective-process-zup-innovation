package com.selectiveprocess.client.controller;

import com.selectiveprocess.client.entity.ClientEntity;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface Client {

	@PostMapping(value = "/v1/clients", produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	ClientResponse create(@RequestBody @Valid ClientRequest request);

	@GetMapping(value = "/v1/clients/{clientId}", produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse getClientById(@PathVariable("clientId") Long clientId);

	@GetMapping(value = "/v1/clients/cpf/{clientCpf}", produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse getClientByCpf(@PathVariable("clientCpf") Long clientCpf);

	@GetMapping(value = "/v1/clients", produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse getAllClients();

	@DeleteMapping(value = "/v1/clients/{clientId}", produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ClientResponse delete(@PathVariable("clientId") Long clientId);

	@PutMapping(value = "/v1/clients", produces={MediaType.APPLICATION_JSON_VALUE})
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

		public ClientRequest(@NotNull String name, @NotNull Long cpf, @NotNull String dateOfBirth, @NotNull String address) {
			this.name = name;
			this.cpf = cpf;
			this.dateOfBirth = dateOfBirth;
			this.address = address;
		}

		@Override
		public String toString() {
			return "ClientRequest{" +
					"id=" + id +
					", name='" + name + '\'' +
					", cpf=" + cpf +
					", dateOfBirth='" + dateOfBirth + '\'' +
					", address='" + address + '\'' +
					'}';
		}
	}
}
