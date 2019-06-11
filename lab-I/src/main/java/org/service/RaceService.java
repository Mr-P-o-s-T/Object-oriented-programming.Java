package org.service;

import org.dao.RaceDAO;
import org.data.Race;

import java.sql.Date;
import java.util.List;

public class RaceService {
	private final RaceDAO raceDAO;

	public RaceService(RaceDAO raceDAO) {
		this.raceDAO = raceDAO;
	}

	public Race createRace(String racecourse, Date date) {
		Race race = new Race();

		race.setRacecourse(racecourse);
		race.setDate(date);
		raceDAO.save(race);
		return race;
	}

	public List<Race> findAllRaces() {
		return raceDAO.findAll();
	}
}
