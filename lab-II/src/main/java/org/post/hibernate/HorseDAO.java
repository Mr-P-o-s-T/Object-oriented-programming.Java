package org.post.hibernate;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.post.data.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class HorseDAO {
  private SessionFactory factory;

  @Autowired
  public HorseDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Horse horse) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(horse);
  }

  public Optional<Horse> findById(Long aLong) {
    IdentifierLoadAccess<Horse> clientIdentifierLoadAccess =
        factory.getCurrentSession().byId(Horse.class);
    return Optional.ofNullable(clientIdentifierLoadAccess.load(aLong));
  }

  public List<Horse> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Horse> query =
        factory.getCurrentSession().createQuery("from org.post.data.Horse");
    return query.getResultList();
  }
}
