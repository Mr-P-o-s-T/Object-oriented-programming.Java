package org.service;

import org.dao.ClientDAO;
import org.data.Client;

import java.util.List;

public class ClientService {
	private ClientDAO dao;

	public ClientService(ClientDAO dao) {
		this.dao = dao;
	}

	public void createClient(String firstname, String lastname) {
		Client client = new Client();
		client.setFirstname(firstname);
		client.setLastname(lastname);
		dao.save(client);
	}

	public List<Client> findAllClients() {
		return dao.findAll();
	}
}
