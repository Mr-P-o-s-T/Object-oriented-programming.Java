package org.post.service;

import org.post.hibernate.BetDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BetServiceTest {
	private BetDAO dao = mock(BetDAO.class);
	//private BetService service = new BetService(dao);

	@Test
	public void betCanBeSaved() {
		//service.createBet(1L, 1L, 1L, 1.1f, "Win", 2L, 3L);
		verify(dao).save(any());
	}
}
