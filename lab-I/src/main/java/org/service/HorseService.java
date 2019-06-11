package org.service;

import org.dao.HorseDAO;
import org.data.Horse;

import java.util.List;

public class HorseService {
	private HorseDAO dao;

	public HorseService(HorseDAO dao) {
		this.dao = dao;
	}

	public Horse createHorse(String name) {
		Horse horse = new Horse();
		horse.setHorseNickname(name);
		dao.save(horse);
		return horse;
	}

	public List<Horse> findAllHorses() {
		return dao.findAll();
	}
}
