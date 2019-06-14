package org.post.service;

import org.post.data.Client;
import org.post.hibernate.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {
  private ClientDAO dao;

  @Autowired
  public ClientService(ClientDAO dao) {
    this.dao = dao;
  }

  public Client createClient(String firstname, String lastname) {
    Client client = new Client();
    client.setFirstname(firstname);
    client.setLastname(lastname);
    dao.save(client);
    return client;
  }

  public List<Client> findAllClients() {
    return dao.findAll();
  }
}
