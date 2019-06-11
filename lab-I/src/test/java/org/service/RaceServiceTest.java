package org.service;

import org.dao.RaceDAO;
import org.junit.Test;

import java.sql.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RaceServiceTest {
	private RaceDAO dao = mock(RaceDAO.class);
	private RaceService service = new RaceService(dao);

	@Test
	public void raceCanBeSaved() {
		service.createRace("Test", (new Date(1L)));
		verify(dao).save(any());
	}
}
