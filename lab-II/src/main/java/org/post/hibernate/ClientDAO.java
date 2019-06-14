package org.post.hibernate;

import org.post.data.Client;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDAO {
  private SessionFactory factory;

  @Autowired
  public ClientDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Client client) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(client);
  }

  public Optional<Client> findById(Long aLong) {
    IdentifierLoadAccess<Client> clientIdentifierLoadAccess =
        factory.getCurrentSession().byId(Client.class);
    return Optional.ofNullable(clientIdentifierLoadAccess.load(aLong));
  }

  public List<Client> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Client> query =
        factory.getCurrentSession().createQuery("from org.post.data.Client");
    return query.getResultList();
  }
}
