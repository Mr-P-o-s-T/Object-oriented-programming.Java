package org.service;

import org.dao.RaceCastDAO;
import org.data.RaceCast;

import java.util.List;

public class RaceCastService {
	private RaceCastDAO dao;

	public RaceCastService(RaceCastDAO dao) {
		this.dao = dao;
	}

	public void createRaceCast(String race_id, Long horse_id, String firstname, String lastname, Float coefficient) {
		RaceCast raceCast = new RaceCast();
		raceCast.setRaceId(race_id);
		raceCast.setHorseId(horse_id);
		raceCast.setJockeyFirstname(firstname);
		raceCast.setJockeyLastname(lastname);
		raceCast.setCoefficient(coefficient);
		dao.save(raceCast);
	}

	public List<RaceCast> findAll() {
		return dao.findAll();
	}
}
