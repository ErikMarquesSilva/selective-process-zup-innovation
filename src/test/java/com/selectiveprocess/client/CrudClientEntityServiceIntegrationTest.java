package com.selectiveprocess.client;

import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.service.ClientService;
import com.selectiveprocess.config.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class CrudClientEntityServiceIntegrationTest extends BaseIntegrationTest {

	@Autowired
	private ClientService clientService;

	@Test
	public void saveClientAfterGetByCPF() {
		String clientName = "nameClient";
		Long cpf = 1234567890L;
		String dateOfBirth = "29/05/1989";
		String address = "Rua Address";
		ClientEntity client = new ClientEntity(clientName, cpf, dateOfBirth, address);
		clientService.create(client);

		Optional<ClientEntity> clientSave = Optional.ofNullable(clientService.getClientByCpf(cpf));

		Assert.assertTrue(clientSave.isPresent());
		Assert.assertEquals(clientName, clientSave.get().getName());
		Assert.assertEquals(cpf, clientSave.get().getCpf());
		Assert.assertEquals(dateOfBirth, clientSave.get().getDateOfBirth());
		Assert.assertEquals(address, clientSave.get().getAddress());

		Optional<ClientEntity> clientGetById = clientService.getClientById(clientSave.get().getId());

		Assert.assertTrue(clientGetById.isPresent());
		Assert.assertEquals(clientName, clientGetById.get().getName());
		Assert.assertEquals(cpf, clientGetById.get().getCpf());
		Assert.assertEquals(dateOfBirth, clientGetById.get().getDateOfBirth());
		Assert.assertEquals(address, clientGetById.get().getAddress());
	}

	@Test
	public void saveClientAfterDelete() {
		String clientName = "nameClient";
		Long cpf = 98765432109L;
		String dateOfBirth = "29/05/1989";
		String address = "Rua Address";
		ClientEntity client = new ClientEntity(clientName, cpf, dateOfBirth, address);
		clientService.create(client);

		Optional<ClientEntity> clientSave = Optional.ofNullable(clientService.getClientByCpf(cpf));

		Assert.assertTrue(clientSave.isPresent());
		Assert.assertEquals(clientName, clientSave.get().getName());
		Assert.assertEquals(cpf, clientSave.get().getCpf());
		Assert.assertEquals(dateOfBirth, clientSave.get().getDateOfBirth());
		Assert.assertEquals(address, clientSave.get().getAddress());

		clientService.delete(clientSave.get().getId());

		boolean clientSDelete = clientService.existsById(clientSave.get().getId());

		Assert.assertFalse(clientSDelete);
	}

	@Test
	public void saveClientAfterUpdate() {
		String clientName = "nameClient";
		Long cpf = 45678912310L;
		String dateOfBirth = "29/05/1989";
		String address = "Rua Address";
		ClientEntity client = new ClientEntity(clientName, cpf, dateOfBirth, address);
		clientService.create(client);

		Optional<ClientEntity> clientSave = Optional.ofNullable(clientService.getClientByCpf(cpf));

		Assert.assertTrue(clientSave.isPresent());
		Assert.assertEquals(clientName, clientSave.get().getName());
		Assert.assertEquals(cpf, clientSave.get().getCpf());
		Assert.assertEquals(dateOfBirth, clientSave.get().getDateOfBirth());
		Assert.assertEquals(address, clientSave.get().getAddress());

		clientSave.get().setName("nameClientChange");
		clientService.update(clientSave.get());

		Optional<ClientEntity> clientSChange = clientService.getClientById(clientSave.get().getId());

		Assert.assertEquals(clientSChange.get().getName(), "nameClientChange");
	}
}
