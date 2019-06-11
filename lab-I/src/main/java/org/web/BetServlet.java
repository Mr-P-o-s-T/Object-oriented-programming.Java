package org.web;

import org.config.BeanFactory;
import org.exception.ValidationException;
import org.service.BetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BetServlet extends HttpServlet {
	private BetService service = (BetService) BeanFactory.getBean(BetService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("bets", service.findAll());
		req.getRequestDispatcher("bet.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String clientId = req.getParameter("client_id");
		String raceId = req.getParameter("race_id");
		String horseId = req.getParameter("horse_id");
		String bet = req.getParameter("bet");
		String betType = req.getParameter("bet_type");
		String scndHorseId = req.getParameter("scnd_horse_id");
		String thrdHorseId = req.getParameter("thrd_horse_id");

		if (!ValidationUtils.validateInt(clientId)) {
			throw new ValidationException("Client ID is not valid");
		} else if (!ValidationUtils.validateInt(raceId)) {
			throw new ValidationException("Race ID is not valid");
		} else if (!ValidationUtils.validateInt(horseId)) {
			throw new ValidationException("Horse ID is not valid");
		} else if (!ValidationUtils.validateFloat(bet)) {
			throw new ValidationException("Bet is not valid");
		} else if (!(betType.equals("Win") || betType.equals("Each-way") || betType.equals("Forecast") ||
				betType.equals("Tricast") || betType.equals("Head-to-head"))) {
			throw new ValidationException("Bet type is not valid");
		} else if (!(scndHorseId.isEmpty() || ValidationUtils.validateInt(scndHorseId))) {
			throw new ValidationException("Second horse ID is not valid");
		} else if (!(thrdHorseId.isEmpty() || ValidationUtils.validateInt(thrdHorseId))) {
			throw new ValidationException("Third horse ID is not valid");
		} else {
			Long cId = Long.parseLong(clientId);
			Long rId = Long.parseLong(raceId);
			Long hId = Long.parseLong(horseId);
			Float _bet = Float.parseFloat(bet);

			if (thrdHorseId.isEmpty()) {
				if (scndHorseId.isEmpty()) service.createBet(cId, rId, hId, _bet, betType);
				else service.createBet(cId, rId, hId, _bet, betType, Long.parseLong(scndHorseId));
			}
			else service.createBet(cId, rId, hId, _bet, betType, Long.parseLong(scndHorseId), Long.parseLong(scndHorseId));

			req.setAttribute("bets", service.findAll());
			req.getRequestDispatcher("bet.jsp").forward(req, resp);
		}
	}
}
