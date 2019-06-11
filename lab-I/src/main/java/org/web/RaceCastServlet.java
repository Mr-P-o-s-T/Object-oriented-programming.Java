package org.web;

import org.config.BeanFactory;
import org.exception.ValidationException;
import org.service.RaceCastService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RaceCastServlet extends HttpServlet {
	private RaceCastService service = (RaceCastService) BeanFactory.getBean(RaceCastService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("raceCasts", service.findAll());
		req.getRequestDispatcher("race_cast.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstname = req.getParameter("jockey_firstname");
		String lastname = req.getParameter("jockey_lastname");
		String raceId = req.getParameter("race_id");
		String horseId = req.getParameter("horse_id");
		String coefficient = req.getParameter("coefficient");

		if (!ValidationUtils.validateString(firstname)) {
			throw new ValidationException("Jockey first name is not valid");
		} else if (!ValidationUtils.validateString(lastname)) {
			throw new ValidationException("Jockey last name is not valid");
		} else if (!ValidationUtils.validateString(raceId)) {
			throw new ValidationException("Race ID is not valid");
		} else if (!ValidationUtils.validateInt(horseId)) {
			throw new ValidationException("Horse ID is not valid");
		} else if (!ValidationUtils.validateFloat(coefficient)) {
			throw new ValidationException("Coefficient is not valid");
		} else {
			//Long rId = Long.parseLong(raceId);
			Long hId = Long.parseLong(horseId);
			Float coeff = Float.parseFloat(coefficient);
			service.createRaceCast(raceId, hId, firstname, lastname, coeff);
			req.setAttribute("raceCasts", service.findAll());
			req.getRequestDispatcher("race_cast.jsp").forward(req, resp);
		}
	}
}
