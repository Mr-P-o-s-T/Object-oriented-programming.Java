package org.web;

import org.config.BeanFactory;
import org.exception.ValidationException;
import org.service.RaceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RaceServlet extends HttpServlet {
	private RaceService service = (RaceService) BeanFactory.getBean(RaceService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("races", service.findAllRaces());
		req.getRequestDispatcher("race.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String racecourse = req.getParameter("racecourse");
		String date = req.getParameter("date");

		if (!ValidationUtils.validateString(racecourse)) {
			throw new ValidationException("Racecourse is not valid");
		} else if (!ValidationUtils.validateString(date)) {
			throw new ValidationException("Date is not valid");
		} else {
			Date dt = Date.valueOf(date);
			service.createRace(racecourse, dt);
			req.setAttribute("races", service.findAllRaces());
			req.getRequestDispatcher("race.jsp").forward(req, resp);
		}
	}
}
