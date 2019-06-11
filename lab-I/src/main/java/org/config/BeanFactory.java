package org.config;

import org.dao.*;
import org.service.*;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
	private static Map<Class<?>, Object> beans = new HashMap<>();

	static {
		RaceDAO dao = new RaceDAO();
		RaceService service = new RaceService(dao);
		beans.put(RaceDAO.class, dao);
		beans.put(RaceService.class, service);

		HorseDAO horseDAO = new HorseDAO();
		HorseService horseService = new HorseService(horseDAO);
		beans.put(HorseDAO.class, horseDAO);
		beans.put(HorseService.class, horseService);

		ClientDAO clientDAO = new ClientDAO();
		ClientService clientService = new ClientService(clientDAO);
		beans.put(ClientDAO.class, clientDAO);
		beans.put(ClientService.class, clientService);

		RaceCastDAO raceCastDAO = new RaceCastDAO();
		RaceCastService raceCastService = new RaceCastService(raceCastDAO);
		beans.put(RaceCastDAO.class, dao);
		beans.put(RaceCastService.class, raceCastService);

		BetDAO betDAO = new BetDAO();
		BetService betService = new BetService(betDAO);
		beans.put(BetDAO.class, betDAO);
		beans.put(BetService.class, betService);
	}

	public static Object getBean(Class<?> beanClass) {
		return beans.get(beanClass);
	}
}
