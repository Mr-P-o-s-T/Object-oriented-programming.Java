package org.post.service;

import org.post.hibernate.HorseDAO;
import org.post.data.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HorseService {
  private HorseDAO dao;

  @Autowired
  public HorseService(HorseDAO dao) {
    this.dao = dao;
  }

  public Horse createHorse(String nickname) {
    Horse horse = new Horse();
    horse.setHorseNickname(nickname);
    dao.save(horse);
    return horse;
  }

  public List<Horse> findAllHorses() {
    return dao.findAll();
  }
}
