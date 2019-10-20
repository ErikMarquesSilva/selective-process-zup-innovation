package com.selectiveprocess.client;

import com.selectiveprocess.client.entity.ClientEntity;
import com.selectiveprocess.client.repository.ClientRepository;
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
	private ClientRepository clientRepository;

	@Test
	public void getClientByNameBeforeSaveClientAfterGetById() {
		String clientName = "nameClient";
		Long cpf = 1234567890L;
		String dateOfBirth = "29/05/1989";
		String address = "Rua Address";
		ClientEntity client = new ClientEntity(clientName, cpf, dateOfBirth, address);
		clientRepository.save(client);

		Optional<ClientEntity> clientSave = Optional.ofNullable(clientRepository.findByName(clientName));

		Assert.assertTrue(clientSave.isPresent());
		Assert.assertEquals(clientName, clientSave.get().getName());
		Assert.assertEquals(cpf, clientSave.get().getCpf());
		Assert.assertEquals(dateOfBirth, clientSave.get().getDateOfBirth());
		Assert.assertEquals(address, clientSave.get().getAddress());
	}
}
