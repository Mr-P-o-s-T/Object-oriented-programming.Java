package org.post.service;

import org.post.hibernate.HorseDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HorseServiceTest {
	private HorseDAO dao = mock(HorseDAO.class);
	private HorseService service = new HorseService(dao);

	@Test
	public void horseCanBeSaved() {
		service.createHorse("Test");
		verify(dao).save(any());
	}
}
