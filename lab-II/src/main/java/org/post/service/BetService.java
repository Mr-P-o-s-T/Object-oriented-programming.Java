package org.post.service;

import org.post.data.Client;
import org.post.data.Horse;
import org.post.data.Race;
import org.post.exception.ValidationException;
import org.post.hibernate.BetDAO;
import org.post.data.Bet;
import org.post.hibernate.ClientDAO;
import org.post.hibernate.HorseDAO;
import org.post.hibernate.RaceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BetService {
  private BetDAO dao;

  @Autowired
  private ClientDAO clientDAO;
  @Autowired
  private RaceDAO raceDAO;
  @Autowired
  private HorseDAO horseDAO;

  @Autowired
  public BetService(BetDAO dao) {
    this.dao = dao;
  }

  public Bet createBet(Long clientId, Long raceId, Long horseId, Float _bet, String betType, Long scndHorseId,
                       Long thrdHorseId) {
    Bet bet = new Bet();
    Optional tmp = clientDAO.findById(clientId);
    if (tmp.isPresent()) bet.setClient((Client) tmp.get());
    else throw new ValidationException("Client does not exist!");

    tmp = raceDAO.findById(raceId);
    if (tmp.isPresent()) bet.setRace((Race) tmp.get());
    else throw new ValidationException("Race does not exist!");

    tmp = horseDAO.findById(horseId);
    if (tmp.isPresent()) bet.setFirstHorse((Horse) tmp.get());
    else throw new ValidationException("First horse does not exist!");

    bet.setBet(_bet);
    bet.setBetType(betType);

    if (betType.equals("Forecast") || betType.equals("Tricast") || betType.equals("Head-to-head")) {
      tmp = horseDAO.findById(scndHorseId);
      if (tmp.isPresent()) bet.setSecondHorse((Horse) tmp.get());
      else throw new ValidationException("Second horse does not exist!");

      if (betType.equals("Tricast")) {
        tmp = horseDAO.findById(thrdHorseId);
        if (tmp.isPresent()) bet.setThirdHorse((Horse) tmp.get());
        else throw new ValidationException("Third horse does not exist!");
      }
    }

    dao.save(bet);
    return bet;
  }

  public List<Bet> findAllBets() {
    return dao.findAll();
  }
}
