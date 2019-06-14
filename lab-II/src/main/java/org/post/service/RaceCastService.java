package org.post.service;

import org.post.data.Client;
import org.post.data.Horse;
import org.post.data.Race;
import org.post.exception.ValidationException;
import org.post.hibernate.HorseDAO;
import org.post.hibernate.RaceCastDAO;
import org.post.data.RaceCast;
import org.post.hibernate.RaceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RaceCastService {
  private RaceCastDAO dao;

  @Autowired
  private RaceDAO raceDAO;
  @Autowired
  private HorseDAO horseDAO;

  @Autowired
  public RaceCastService(RaceCastDAO dao) {
    this.dao = dao;
  }

  public RaceCast createRaceCast(Long raceId, Long horseId, String firstname, String lastname, Float coefficient) {
    RaceCast cast = new RaceCast();

    Optional tmp = raceDAO.findById(raceId);
    if (tmp.isPresent()) cast.setRace((Race) tmp.get());
    else throw new ValidationException("Race does not exist!");

    tmp = horseDAO.findById(horseId);
    if (tmp.isPresent()) cast.setHorse((Horse) tmp.get());
    else throw new ValidationException("Horse does not exist!");

    cast.setJockeyFirstname(firstname);
    cast.setJockeyLastname(lastname);
    cast.setCoefficient(coefficient);
    dao.save(cast);
    return cast;
  }

  public List<RaceCast> findAllRaceCasts() {
    return dao.findAll();
  }
}
