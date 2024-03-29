/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.selectiveprocess;

import com.selectiveprocess.client.controller.ClientController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationContextTest {

	@Autowired private ClientController clientController;

	@Test
	public void contextLoads() {
		assertNotNull(clientController);
	}
}
