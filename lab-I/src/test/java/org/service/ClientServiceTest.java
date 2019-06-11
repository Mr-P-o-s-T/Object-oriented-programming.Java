package org.service;

import org.dao.ClientDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientServiceTest {
	private ClientDAO dao = mock(ClientDAO.class);
	private ClientService service = new ClientService(dao);

	@Test
	public void horseCanBeSaved() {
		service.createClient("firstname", "lastname");
		verify(dao).save(any());
	}
}
