package org.post.microservice.business.services;

import org.post.microservice.business.entities.Horse;
import org.post.microservice.business.entities.Race;
import org.post.microservice.business.entities.RaceCast;
import org.post.microservice.business.entities.repositories.HorseRepository;
import org.post.microservice.business.entities.repositories.RaceCastRepository;
import org.post.microservice.business.entities.repositories.RaceRepository;
import org.post.microservice.business.exceptions.ValidationException;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RaceCastService {
  private RaceCastRepository raceCastRepository;

  private RaceRepository raceRepository;
  private HorseRepository horseRepository;

  @Autowired
  public RaceCastService(RaceCastRepository raceCastRepository, RaceRepository raceRepository, HorseRepository
          horseRepository) {
    this.raceCastRepository = raceCastRepository;
    this.raceRepository = raceRepository;
    this.horseRepository = horseRepository;
  }

  public RaceCast createRaceCast(Long raceId, Long horseId, String firstname, String lastname, Float coefficient) {
    RaceCast cast = new RaceCast();

    Optional tmp = raceRepository.findById(raceId);
    if (tmp.isPresent()) cast.setRace((Race) tmp.get());
    else throw new ValidationException("Race does not exist!");

    tmp = horseRepository.findById(horseId);
    if (tmp.isPresent()) cast.setHorse((Horse) tmp.get());
    else throw new ValidationException("Horse does not exist!");

    cast.setJockeyFirstname(firstname);
    cast.setJockeyLastname(lastname);
    cast.setCoefficient(coefficient);
    raceCastRepository.save(cast);
    return cast;
  }

  public List<RaceCast> findAllRaceCasts() {
    return Lists.newArrayList(raceCastRepository.findAll());
  }
}
