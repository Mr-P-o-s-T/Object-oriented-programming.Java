package org.post.hibernate;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.post.data.RaceCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class RaceCastDAO {
  private SessionFactory factory;

  @Autowired
  public RaceCastDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void save(RaceCast cast) {
    Session currentSession = factory.getCurrentSession();
    currentSession.save(cast);
  }

  public Optional<RaceCast> findById(Long aLong) {
    IdentifierLoadAccess<RaceCast> clientIdentifierLoadAccess =
        factory.getCurrentSession().byId(RaceCast.class);
    return Optional.ofNullable(clientIdentifierLoadAccess.load(aLong));
  }

  public List<RaceCast> findAll() {
    @SuppressWarnings("unchecked")
    TypedQuery<RaceCast> query =
        factory.getCurrentSession().createQuery("from org.post.data.RaceCast");
    return query.getResultList();
  }
}
