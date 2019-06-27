package org.post.microservice.business.services;

import org.post.microservice.business.entities.Client;
import org.post.microservice.business.entities.repositories.ClientRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {
  private ClientRepository clientRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client createClient(String firstname, String lastname) {
    Client client = new Client();
    client.setFirstname(firstname);
    client.setLastname(lastname);
    clientRepository.save(client);
    return client;
  }

  public List<Client> findAllClients() {
    return Lists.newArrayList(clientRepository.findAll());
  }
}
