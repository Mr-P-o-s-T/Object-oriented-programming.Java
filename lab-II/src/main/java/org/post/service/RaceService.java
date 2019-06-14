package org.post.service;

import org.post.hibernate.RaceDAO;
import org.post.data.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class RaceService {
  private RaceDAO dao;

  @Autowired
  public RaceService(RaceDAO dao) {
    this.dao = dao;
  }

  public Race createRace(String racecourse, Date date) {
    Race race = new Race();
    race.setRacecourse(racecourse);
    race.setDate(date);
    dao.save(race);
    return race;
  }

  public List<Race> findAllRaces() {
    return dao.findAll();
  }
}
