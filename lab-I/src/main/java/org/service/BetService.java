package org.service;

import org.dao.BetDAO;
import org.data.Bet;

import java.util.List;

public class BetService {
	private final BetDAO dao;

	public BetService(BetDAO dao) {
		this.dao = dao;
	}

	public void createBet(Long clientId, Long raceId, Long horseId, Float _bet, String betType) {
		Bet bet = init(clientId, raceId, horseId, _bet, betType);
		dao.save(bet);
	}

	public void createBet(Long clientId, Long raceId, Long horseId, Float _bet, String betType, Long scndHorseId) {
		Bet bet = init(clientId, raceId, horseId, _bet, betType);
		bet.setScndHorseId(scndHorseId);
		dao.save(bet);
	}

	public void createBet(Long clientId, Long raceId, Long horseId, Float _bet, String betType, Long scndHorseId,
						  Long thrdHorseId) {
		Bet bet = init(clientId, raceId, horseId, _bet, betType);
		bet.setScndHorseId(scndHorseId);
		bet.setThrdHorseId(thrdHorseId);
		dao.save(bet);
	}

	public List<Bet> findAll() {
		return dao.findAll();
	}

	private Bet init(Long clientId, Long raceId, Long horseId, Float _bet, String betType) {
		Bet bet = new Bet();
		bet.setClientId(clientId);
		bet.setRaceId(raceId);
		bet.setHorseId(horseId);
		bet.setBet(_bet);
		bet.setBetType(betType);
		return bet;
	}
}
