package org.post.microservice.business.services;

import org.post.microservice.business.entities.Bet;
import org.post.microservice.business.entities.Client;
import org.post.microservice.business.entities.Horse;
import org.post.microservice.business.entities.Race;
import org.post.microservice.business.entities.repositories.BetRepository;
import org.post.microservice.business.entities.repositories.ClientRepository;
import org.post.microservice.business.entities.repositories.HorseRepository;
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
public class BetService {
  private BetRepository betRepository;
  private ClientRepository clientRepository;
  private RaceRepository raceRepository;
  private HorseRepository horseRepository;

  @Autowired
  public BetService(BetRepository betRepository, ClientRepository clientRepository, RaceRepository raceRepository,
                    HorseRepository horseRepository) {
    this.betRepository = betRepository;
    this.clientRepository = clientRepository;
    this.raceRepository = raceRepository;
    this.horseRepository = horseRepository;
  }

  public Bet createBet(Long clientId, Long raceId, Long horseId, Float _bet, String betType, Long scndHorseId,
                       Long thrdHorseId) {
    Bet bet = new Bet();
    Optional tmp = clientRepository.findById(clientId);
    if (tmp.isPresent()) bet.setClient((Client) tmp.get());
    else throw new ValidationException("Client does not exist!");

    tmp = raceRepository.findById(raceId);
    if (tmp.isPresent()) bet.setRace((Race) tmp.get());
    else throw new ValidationException("Race does not exist!");

    tmp = horseRepository.findById(horseId);
    if (tmp.isPresent()) bet.setFirstHorse((Horse) tmp.get());
    else throw new ValidationException("First horse does not exist!");

    bet.setBet(_bet);
    bet.setBetType(betType);

    if (betType.equals("Forecast") || betType.equals("Tricast") || betType.equals("Head-to-head")) {
      tmp = horseRepository.findById(scndHorseId);
      if (tmp.isPresent()) bet.setSecondHorse((Horse) tmp.get());
      else throw new ValidationException("Second horse does not exist!");

      if (betType.equals("Tricast")) {
        tmp = horseRepository.findById(thrdHorseId);
        if (tmp.isPresent()) bet.setThirdHorse((Horse) tmp.get());
        else throw new ValidationException("Third horse does not exist!");
      }
    }

    betRepository.save(bet);
    return bet;
  }

  public List<Bet> findAllBets() {
    return Lists.newArrayList(betRepository.findAll());
  }
}
