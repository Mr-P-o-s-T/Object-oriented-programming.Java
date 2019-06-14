package org.post.service;

import org.post.hibernate.RaceCastDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RaceCastServiceTest {

	private RaceCastDAO dao = mock(RaceCastDAO.class);
	private RaceCastService service = new RaceCastService(dao);

	@Test
	public void raceCastCanBeSaved() {
		service.createRaceCast(1L, 1L, "firstname", "lastname", 3.0f);
		verify(dao).save(any());
	}
}
