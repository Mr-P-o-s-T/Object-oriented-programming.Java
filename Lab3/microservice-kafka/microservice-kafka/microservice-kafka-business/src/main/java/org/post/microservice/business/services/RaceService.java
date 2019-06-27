package org.post.microservice.business.services;

import org.post.microservice.business.entities.Race;
import org.post.microservice.business.entities.repositories.RaceRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class RaceService {
  private RaceRepository raceRepository;

  @Autowired
  public RaceService(RaceRepository raceRepository) {
    this.raceRepository = raceRepository;
  }

  public Race createRace(String racecourse, Date date) {
    Race race = new Race();
    race.setRacecourse(racecourse);
    race.setDate(date);
    raceRepository.save(race);
    return race;
  }

  public List<Race> findAllRaces() {
    return Lists.newArrayList(raceRepository.findAll());
  }
}
