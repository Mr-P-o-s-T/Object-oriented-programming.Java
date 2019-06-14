package org.post.hibernate;

import org.post.data.Bet;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class BetDAO {
  private SessionFactory factory;

  @Autowired
  public BetDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(Bet bet) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(bet);
  }

  public Optional<Bet> findById(Long aLong) {
    IdentifierLoadAccess<Bet> clientIdentifierLoadAccess =
        factory.getCurrentSession().byId(Bet.class);
    return Optional.ofNullable(clientIdentifierLoadAccess.load(aLong));
  }

  public List<Bet> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<Bet> query =
        factory.getCurrentSession().createQuery("from org.post.data.Bet");
    return query.getResultList();
  }
}
