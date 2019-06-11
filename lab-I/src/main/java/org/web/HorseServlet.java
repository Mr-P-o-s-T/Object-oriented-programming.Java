package org.web;

import org.config.BeanFactory;
import org.exception.ValidationException;
import org.service.HorseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HorseServlet extends HttpServlet {
	private HorseService service = (HorseService) BeanFactory.getBean(HorseService.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("horses", service.findAllHorses());
		req.getRequestDispatcher("horse.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nickname = req.getParameter("horse_nickname");
		if (!ValidationUtils.validateString(nickname)) {
			throw new ValidationException("Horse nickname is not valid!");

		} else {
			service.createHorse(nickname);
			req.setAttribute("horses", service.findAllHorses());
			req.getRequestDispatcher("horse.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
