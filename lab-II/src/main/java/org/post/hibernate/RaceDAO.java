package org.post.hibernate;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.post.data.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class RaceDAO {
  private SessionFactory factory;

  @Autowired
  public RaceDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Race race) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(race);
  }

  public Optional<Race> findById(Long aLong) {
    IdentifierLoadAccess<Race> clientIdentifierLoadAccess =
        factory.getCurrentSession().byId(Race.class);
    return Optional.ofNullable(clientIdentifierLoadAccess.load(aLong));
  }

  public List<Race> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Race> query =
        factory.getCurrentSession().createQuery("from org.post.data.Race");
    return query.getResultList();
  }
}
