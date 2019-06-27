package org.post.microservice.business.services;

import org.post.microservice.business.entities.Horse;
import org.post.microservice.business.entities.repositories.HorseRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HorseService {
  private HorseRepository horseRepository;

  @Autowired
  public HorseService(HorseRepository horseRepository) {
    this.horseRepository = horseRepository;
  }

  public Horse createHorse(String nickname) {
    Horse horse = new Horse();
    horse.setHorseNickname(nickname);
    horseRepository.save(horse);
    return horse;
  }

  public List<Horse> findAllHorses() {
    return Lists.newArrayList(horseRepository.findAll());
  }
}
